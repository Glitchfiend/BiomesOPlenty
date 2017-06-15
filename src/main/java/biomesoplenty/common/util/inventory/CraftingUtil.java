/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.inventory;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.oredict.OreIngredient;

import java.util.*;

public class CraftingUtil
{
    public static void addShapelessRecipe(ItemStack output, Object... inputs)
    {
        NonNullList<Ingredient> ingredients = NonNullList.create();

        for (Object input : inputs)
        {
            ingredients.add(CraftingHelper.getIngredient(input));
        }

        if (ingredients.isEmpty())
        {
            throw new IllegalArgumentException("No ingredients for shapeless recipe");
        }
        else if (ingredients.size() > 9)
        {
            throw new IllegalArgumentException("Too many ingredients for shapeless recipe");
        }

        ShapelessRecipes recipe = new ShapelessRecipes("biomesoplenty", output, ingredients);
        registerRecipe(unusedLocForOutput(output), recipe);
    }

    public static void addShapedRecipe(ItemStack output, Object... inputs)
    {
        ArrayList<String> pattern = Lists.newArrayList();
        Map<String, Ingredient> key = Maps.newHashMap();

        parseRecipe(pattern, key, inputs);
        int width = pattern.get(0).length();
        int height = pattern.size();

        NonNullList<Ingredient> ingredients = ShapedRecipes.func_192402_a(pattern.toArray(new String[pattern.size()]), key, width, height);
        ShapedRecipes recipe = new ShapedRecipes("biomesoplenty", width, height, ingredients, output);
        registerRecipe(unusedLocForOutput(output), recipe);
    }

    public static void addRecipe(String name, IRecipe recipe)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, name);
        registerRecipe(location, recipe);
    }

    private static void registerRecipe(ResourceLocation location, IRecipe recipe)
    {
        if (CraftingManager.field_193380_a.containsKey(location))
        {
            throw new IllegalStateException("Duplicate recipe ignored with ID " + location);
        }
        else
        {
            CraftingManager.field_193380_a.register(CraftingManager.field_193380_a.getKeys().size(), location, recipe);
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
                key.put(((Character)obj).toString(), CraftingHelper.getIngredient(itr.next()));
            }
            else
            {
                throw new IllegalArgumentException("Unexpected argument of type " + obj.getClass().toString());
            }
        }

        key.put(" ", Ingredient.field_193370_a);
    }

    private static ResourceLocation unusedLocForOutput(ItemStack output)
    {
        ResourceLocation baseLoc = new ResourceLocation(BiomesOPlenty.MOD_ID, output.getItem().getRegistryName().getResourcePath());
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;

        // find unused recipe name
        while (CraftingManager.field_193380_a.containsKey(recipeLoc))
        {
            index++;
            recipeLoc = new ResourceLocation(BiomesOPlenty.MOD_ID, baseLoc.getResourcePath() + "_" + index);
        }

        return recipeLoc;
    }
}
