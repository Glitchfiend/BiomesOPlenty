/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.inventory;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.base.Charsets;
import com.google.common.collect.*;
import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.OreIngredient;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

/**
 * Utilities for adding recipes and generating
 * json files programmatically in 1.12+.
 */
public class CraftingUtil
{
    private static final Gson SERIALIZER = new GsonBuilder().setPrettyPrinting().create();
    public static final File RECIPES_DIR = new File(BiomesOPlenty.configDirectory, "recipes");

    // We should no longer need this now we've switched over to JSON, however
    // it might be handy to keep around for future reference
    public static boolean doRegistration = false;
    public static boolean generateJson = false;

    private static Map<OreIngredient, String> ingredientOreMap = Maps.newHashMap();

    static
    {
        // remove any existing auto-generated recipe files
        try
        {
            FileUtils.deleteDirectory(RECIPES_DIR);
            RECIPES_DIR.mkdir();
        }
        catch (Exception e)
        {
            BiomesOPlenty.logger.error("Could not delete default biome config directory!");
        }
    }

    public static void addShapelessRecipe(ItemStack output, Object... inputs)
    {
        NonNullList<Ingredient> ingredients = NonNullList.create();

        for (Object input : inputs)
        {
            Ingredient ingredient = CraftingHelper.getIngredient(input);
            // keep track of the origin of ore ingredients since they don't do so themselves
            if (ingredient instanceof OreIngredient)
            {
                ingredientOreMap.put((OreIngredient)ingredient, (String)input);
            }

            ingredients.add(ingredient);
        }

        if (ingredients.isEmpty())
        {
            throw new IllegalArgumentException("No ingredients for shapeless recipe");
        }
        else if (ingredients.size() > 9)
        {
            throw new IllegalArgumentException("Too many ingredients for shapeless recipe");
        }

        ShapelessRecipes recipe = new ShapelessRecipes("", output, ingredients);
        registerRecipe(unusedLocForOutput(output), recipe);
    }

    public static void addShapedRecipe(ItemStack output, Object... inputs)
    {
        ArrayList<String> pattern = Lists.newArrayList();
        Map<String, Ingredient> key = Maps.newHashMap();

        parseRecipe(pattern, key, inputs);
        int width = pattern.get(0).length();
        int height = pattern.size();

        NonNullList<Ingredient> ingredients = ShapedRecipes.deserializeIngredients(pattern.toArray(new String[pattern.size()]), key, width, height);
        ShapedRecipes recipe = new ShapedRecipes("", width, height, ingredients, output);
        registerRecipe(unusedLocForOutput(output), recipe);
    }

    public static void addRecipe(String name, IRecipe recipe)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, name);
        registerRecipe(location, recipe);
    }

    private static void registerRecipe(ResourceLocation location, IRecipe recipe)
    {
        // only generate json if enabled and it isn't a custom recipe implementation
        if (generateJson && (recipe instanceof ShapedRecipes || recipe instanceof ShapelessRecipes))
        {
            String json = createJsonRecipe(recipe);
            File recipeFile = new File(RECIPES_DIR, location.getResourcePath() + ".json");

            try
            {
                FileUtils.writeStringToFile(recipeFile, json, Charsets.US_ASCII);
            }
            catch (Exception e)
            {
                BiomesOPlenty.logger.error("Could not write recipe to file " + recipeFile.getName());
            }
        }

        // don't register if it has been disabled
        // mainly for debugging whilst switching to json files
        if (!doRegistration) return;

        if (CraftingManager.REGISTRY.containsKey(location))
        {
            throw new IllegalStateException("Duplicate recipe ignored with ID " + location);
        }
        else
        {
            CraftingManager.REGISTRY.register(CraftingManager.REGISTRY.getKeys().size(), location, recipe);
        }
    }

    private static void parseRecipe(List<String> pattern, Map<String, Ingredient> key, Object... inputs)
    {
        Iterator itr = Arrays.asList(inputs).iterator();

        while (itr.hasNext())
        {
            Object obj = itr.next();

            if (obj instanceof String)
            {
                String str = (String) obj;

                if (str.length() > 3)
                {
                    throw new IllegalArgumentException("Invalid string length for recipe " + str.length());
                }

                if (pattern.size() <= 2)
                {
                    pattern.add(str);
                }
                else
                {
                    throw new IllegalArgumentException("Recipe has too many crafting rows!");
                }
            }
            else if (obj instanceof Character)
            {
                Object next = itr.next();
                Ingredient ingredient = CraftingHelper.getIngredient(next);
                // keep track of the origin of ore ingredients since they don't do so themselves
                if (ingredient instanceof OreIngredient)
                {
                    ingredientOreMap.put((OreIngredient)ingredient, (String)next);
                }
                key.put(((Character)obj).toString(), ingredient);
            }
            else
            {
                throw new IllegalArgumentException("Unexpected argument of type " + obj.getClass().toString());
            }
        }

        key.put(" ", Ingredient.EMPTY);
    }

    private static ResourceLocation unusedLocForOutput(ItemStack output)
    {
        ResourceLocation baseLoc = new ResourceLocation(BiomesOPlenty.MOD_ID, output.getItem().getRegistryName().getResourcePath());
        // purely for generating some nicer names for json files
        //ResourceLocation baseLoc = new ResourceLocation(BiomesOPlenty.MOD_ID, output.getDisplayName().toLowerCase().replace(" ", "_"));
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;

        // find unused recipe name
        while (CraftingManager.REGISTRY.containsKey(recipeLoc))
        {
            index++;
            recipeLoc = new ResourceLocation(BiomesOPlenty.MOD_ID, baseLoc.getResourcePath() + "_" + index);
        }

        return recipeLoc;
    }

    private static String createJsonRecipe(IRecipe recipe)
    {
        JsonObject root = new JsonObject();

        if (!recipe.getGroup().isEmpty())
            root.add("group", new JsonPrimitive(recipe.getGroup()));

        if (recipe instanceof ShapedRecipes)
        {
            ShapedRecipes shapedRecipe = (ShapedRecipes)recipe;
            BiMap<String, Ingredient> keyMap = createShapedRecipeKey(recipe.getIngredients());
            String[] pattern = createShapedPattern(shapedRecipe, keyMap);

            String type = "minecraft:crafting_shaped";

            // create the pattern and add it to the generated json
            JsonArray patternJsonArray = new JsonArray();
            for (String row : pattern)
            {
                patternJsonArray.add(row);
            }

            // create the key and add it to the generated json
            JsonObject keyObj = new JsonObject();
            for (String key : keyMap.keySet())
            {
                // get the ingredient for the corresponding key
                Ingredient ingredient = keyMap.get(key);

                if (ingredient instanceof OreIngredient)
                {
                    type = "forge:ore_shaped";
                }

                // add the ingredient to the key map
                keyObj.add(key, createJsonIngredient(ingredient));
            }

            // add in this order to look nice
            root.add("type", new JsonPrimitive(type));
            root.add("pattern", patternJsonArray);
            root.add("key", keyObj);
        }
        else if (recipe instanceof ShapelessRecipes)
        {
            String type = "minecraft:crafting_shapeless";

            JsonArray ingredientsArray = new JsonArray();
            for (Ingredient ingredient : recipe.getIngredients())
            {
                // validate the ingredient - we only cover json generation for the cases we use
                if (ingredient.getMatchingStacks().length > 1 && !(ingredient instanceof OreIngredient))
                {
                    throw new IllegalArgumentException("Cannot create key for ingredient matching multiple stacks!");
                }
                else if (ingredient.getMatchingStacks().length == 0)
                {
                    // skip ingredients which are 'empty'
                    continue;
                }

                if (ingredient instanceof OreIngredient)
                {
                    type = "forge:ore_shapeless";
                }

                ingredientsArray.add(createJsonIngredient(ingredient));
            }

            root.add("type", new JsonPrimitive(type));
            root.add("ingredients", ingredientsArray);
        }
        root.add("result", createJsonItemStack(recipe.getRecipeOutput()));

        return SERIALIZER.toJson(root);
    }

    private static JsonObject createJsonIngredient(Ingredient ingredient)
    {
        JsonObject ret;
        if (ingredient instanceof OreIngredient)
        {
            ret = new JsonObject();
            ret.add("type", new JsonPrimitive("forge:ore_dict"));
            ret.add("ore", new JsonPrimitive(ingredientOreMap.get(ingredient)));
        }
        else
        {
            ret = createJsonItemStack(ingredient.getMatchingStacks()[0]);
        }
        return ret;
    }

    private static JsonObject createJsonItemStack(ItemStack stack)
    {
        JsonObject ret = new JsonObject();
        ret.add("item", new JsonPrimitive(stack.getItem().getRegistryName().toString()));
        if (stack.getCount() != 1) ret.add("count", new JsonPrimitive(stack.getCount()));
        if (stack.getMetadata() != 0 || stack.getHasSubtypes()) ret.add("data", new JsonPrimitive(stack.getMetadata()));
        return ret;
    }

    private static String[] createShapedPattern(ShapedRecipes recipe, BiMap<String, Ingredient> keyMap)
    {
        String[] pattern = new String[recipe.recipeHeight];

        // initialize the pattern rows
        for (int i = 0; i < pattern.length; i++)
        {
            pattern[i] = "";
        }

        // don't bother with ore dict recipes yet
        if (keyMap.isEmpty())
        {
            return pattern;
        }

        // iterate over the recipe ingredients
        for (int i = 0; i < recipe.getIngredients().size(); i++)
        {
            Ingredient ingredient = recipe.getIngredients().get(i);
            String key = " ";

            if (ingredient.getMatchingStacks().length > 1 && !(ingredient instanceof OreIngredient))
            {
                throw new IllegalArgumentException("Cannot generate json for a recipe matching multiple stacks!");
            }
            else if (ingredient.getMatchingStacks().length != 0) // blank spaces just use the default key value
            {
                key = keyMap.inverse().get(ingredient);
            }

            int row = i / recipe.recipeWidth;
            pattern[row] += key;
        }

        return pattern;
    }

    private static BiMap<String, Ingredient> createShapedRecipeKey(NonNullList<Ingredient> ingredients)
    {
        Set<Ingredient> ingredientsSet = Sets.newHashSet(ingredients);
        BiMap<String, Ingredient> key = HashBiMap.create();
        int fallbackCount = 0;

        for (Ingredient ingredient : ingredientsSet)
        {
            // validate the ingredient - we only cover json generation for the cases we use
            if (ingredient.getMatchingStacks().length > 1 && !(ingredient instanceof OreIngredient))
            {
                throw new IllegalArgumentException("Cannot create key for ingredient matching multiple stacks!");
            }
            else if (ingredient.getMatchingStacks().length == 0)
            {
                // skip ingredients which are 'empty'
                continue;
            }

            // default to using # (completely arbitrary)
            if (fallbackCount == 0)
            {
                key.put(getFallbackRecipeKey(fallbackCount), ingredient);
                fallbackCount++;
                continue;
            }

            String letterKey = ingredient.getMatchingStacks()[0].getItem().getRegistryName().getResourcePath().substring(0, 1).toUpperCase();

            if (!key.containsKey(letterKey))
            {
                key.put(letterKey, ingredient);
            }
            else
            {
                String fallbackKey = getFallbackRecipeKey(fallbackCount);

                // this should never happen
                if (key.containsKey(fallbackKey))
                {
                    throw new RuntimeException("Fallback key " + fallbackKey + " is already present!");
                }

                key.put(fallbackKey, ingredient);
                fallbackCount++;
            }
        }

        return key;
    }

    private static String getFallbackRecipeKey(int index)
    {
        switch (index)
        {
            case 1:
                return "*";
            case 2:
                return "@";
            case 3:
                return "%";
            case 4:
                return "+";
            case 5:
                return "-";
            case 6:
                return "~";
            case 7:
                return "=";
            case 8:
                return "?";
            default:
                return "#";
        }
    }
}
