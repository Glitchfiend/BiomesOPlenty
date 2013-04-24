package thaumcraft.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;

import org.w3c.dom.Document;

import thaumcraft.api.aura.AuraNode;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.IInfusionRecipe;
import thaumcraft.api.crafting.RecipeCrucible;
import thaumcraft.api.crafting.ShapedArcaneCraftingRecipes;
import thaumcraft.api.crafting.ShapedInfusionCraftingRecipes;
import thaumcraft.api.crafting.ShapelessArcaneCraftingRecipes;
import thaumcraft.api.crafting.ShapelessInfusionCraftingRecipes;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ResearchList;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInterModComms;


/**
 * @author Azanor
 *
 *
 * IMPORTANT: If you are adding your own aspects to items it is a good idea to do it AFTER Thaumcraft adds its aspects, otherwise odd things may happen.
 *
 */
public class ThaumcraftApi {
	
	//Materials	
	public static EnumToolMaterial toolMatThaumium = EnumHelper.addToolMaterial("THAUMIUM", 3, 400, 7F, 2, 22);
	public static EnumToolMaterial toolMatElemental = EnumHelper.addToolMaterial("THAUMIUM_ELEMENTAL", 3, 1500, 10F, 3, 18);
	public static EnumArmorMaterial armorMatThaumium = EnumHelper.addArmorMaterial("THAUMIUM", 25, new int[] { 2, 6, 5, 2 }, 25);
	public static EnumArmorMaterial armorMatSpecial = EnumHelper.addArmorMaterial("SPECIAL", 25, new int[] { 1, 3, 2, 1 }, 25);
	
	
	
	//Miscellaneous
	/**
	 * Portable Hole Block-id Blacklist. 
	 * Simply add the block-id's of blocks you don't want the portable hole to go through.
	 */
	public static ArrayList<Integer> portableHoleBlackList = new ArrayList<Integer>();
	
	
	//RESEARCH/////////////////////////////////////////
	public static Document researchDoc = null;
	public static ArrayList<String> apiResearchFiles = new ArrayList<String>(); 
	public static ArrayList<IScanEventHandler> scanEventhandlers = new ArrayList<IScanEventHandler>();
	
	/**
	 * Used to add your own xml files that the research system will check of recipes and descriptions of custom research
	 * @param resourceLoc The xml file. For example The default file used by TC is 
	 * "/thaumcraft/resources/research.xml"
	 */
	public static void registerResearchXML(String resourceLoc) {
		if (!apiResearchFiles.contains(resourceLoc)) apiResearchFiles.add(resourceLoc);
	}
	
	public static void registerScanEventhandler(IScanEventHandler scanEventHandler) {
		scanEventhandlers.add(scanEventHandler);
	}
	
	//RECIPES/////////////////////////////////////////
	private static ArrayList crucibleRecipes = new ArrayList();
	private static List craftingRecipes = new ArrayList();	
	private static HashMap<List,ItemStack> smeltingBonus = new HashMap<List,ItemStack>();
	private static ArrayList<List> smeltingBonusExlusion = new ArrayList<List>();
	
	/**
	 * This method is used to determine what bonus items are generated when the infernal furnace smelts items
	 * @param in The result (not input) of the smelting operation. e.g. new ItemStack(ingotGold)
	 * @param out The bonus item that can be produced from the smelting operation e.g. new ItemStack(nuggetGold,0,0).
	 * Stacksize should be 0 unless you want to guarantee that at least 1 item is always produced.
	 */
	public static void addSmeltingBonus(ItemStack in, ItemStack out) {
		smeltingBonus.put(
				Arrays.asList(in.itemID,in.getItemDamage()), 
				new ItemStack(out.itemID,0,out.getItemDamage()));
	}
	
	/**
	 * Returns the bonus item produced from a smelting operation in the infernal furnace
	 * @param in The result of the smelting operation. e.g. new ItemStack(ingotGold)
	 * @return the The bonus item that can be produced
	 */
	public static ItemStack getSmeltingBonus(ItemStack in) {
		return smeltingBonus.get(Arrays.asList(in.itemID,in.getItemDamage()));
	}
	
	/**
	 * Excludes specific items from producing bonus items when they are smelted in the infernal furnace, even 
	 * if their smelt result would normally produce a bonus item.
	 * @param in The item to be smelted that should never produce a bonus item (e.g. the various macerated dusts form IC2)
	 * Even though they produce gold, iron, etc. ingots, they should NOT produce bonus nuggets as well.
	 */
	public static void addSmeltingBonusExclusion(ItemStack in) {
		smeltingBonusExlusion.add(Arrays.asList(in.itemID,in.getItemDamage()));
	}
	
	/**
	 * Sees if an item is allowed to produce bonus items when smelted in the infernal furnace
	 * @param in The item you wish to check
	 * @return true or false
	 */
	public static boolean isSmeltingBonusExluded(ItemStack in) {
		return smeltingBonusExlusion.contains(Arrays.asList(in.itemID,in.getItemDamage()));
	}
	
	public static List getCraftingRecipes() {
		return craftingRecipes;
	}
	
	/**
	 * NOTE:
	 * All arcane and infusion crafting recipes are NBT sensitive. 
	 * Simply add as much nbt data to the crafting ingredient itemstacks as you wish 
	 * to match with the actual input items. For example this recipe will turn a warded 
	 * jar filled with crystal essentia into a stack of diamonds:
	 * 
	 *   ItemStack is = new ItemStack(itemJarFilled);
	 *   is.setTagInfo("tag", new NBTTagByte("tag", (byte) EnumTag.CRYSTAL.id));
	 *   is.setTagInfo("amount", new NBTTagByte("amount", (byte) 64));
	 *   ThaumcraftApi.addShapelessArcaneCraftingRecipe("THEJARISNOWDIAMONDS", 50,
	 *		 	new ItemStack(Item.diamond,64,0), new Object[] { is });	
	 *
	 * If no tag was added for "amount" then the jar would simply have had to contain any 
	 * amount of crystal essentia.
	 */
	
	/**
	 * @param key the research key required for this recipe to work. Leave blank if it will work without research
	 * @param cost the vis cost
	 * @param par1ItemStack the recipe output
	 * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla recipes
	 */
	public static void addArcaneCraftingRecipe(String key, int cost, ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        int var9;

        if (par2ArrayOfObj[var4] instanceof String[])
        {
            String[] var7 = (String[])((String[])par2ArrayOfObj[var4++]);
            String[] var8 = var7;
            var9 = var7.length;

            for (int var10 = 0; var10 < var9; ++var10)
            {
                String var11 = var8[var10];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        }
        else
        {
            while (par2ArrayOfObj[var4] instanceof String)
            {
                String var13 = (String)par2ArrayOfObj[var4++];
                ++var6;
                var5 = var13.length();
                var3 = var3 + var13;
            }
        }

        HashMap var14;

        for (var14 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2)
        {
            Character var16 = (Character)par2ArrayOfObj[var4];
            ItemStack var17 = null;

            if (par2ArrayOfObj[var4 + 1] instanceof Item)
            {
                var17 = new ItemStack((Item)par2ArrayOfObj[var4 + 1]);
            }
            else if (par2ArrayOfObj[var4 + 1] instanceof Block)
            {
                var17 = new ItemStack((Block)par2ArrayOfObj[var4 + 1], 1, -1);
            }
            else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack)
            {
                var17 = (ItemStack)par2ArrayOfObj[var4 + 1];
            }

            var14.put(var16, var17);
        }

        ItemStack[] var15 = new ItemStack[var5 * var6];

        for (var9 = 0; var9 < var5 * var6; ++var9)
        {
            char var18 = var3.charAt(var9);

            if (var14.containsKey(Character.valueOf(var18)))
            {
                var15[var9] = ((ItemStack)var14.get(Character.valueOf(var18))).copy();
            }
            else
            {
                var15[var9] = null;
            }
        }

        craftingRecipes.add(new ShapedArcaneCraftingRecipes(key, var5, var6, var15, par1ItemStack,cost));
    }

	/**
	 * @param key the research key required for this recipe to work. Leave blank if it will work without research
	 * @param recipeKey a string value of the key used in your research.xml for this recipe to display in the thaumonomicon
	 * @param cost the vis cost
	 * @param par1ItemStack the recipe output
	 * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla recipes
	 */
	public static void addArcaneCraftingRecipe(String key, String recipeKey, int cost, ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
		addArcaneCraftingRecipe(key,cost,par1ItemStack,par2ArrayOfObj);
		ResearchList.craftingRecipesForResearch.put(recipeKey, Arrays.asList(getCraftingRecipes().size()-1));
	}
	
    /**
     * @param key the research key required for this recipe to work. Leave blank if it will work without research
     * @param cost the vis cost
     * @param par1ItemStack the recipe output
     * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla shapeless recipes
     */
    public static void addShapelessArcaneCraftingRecipe(String key, int cost, ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        ArrayList var3 = new ArrayList();
        Object[] var4 = par2ArrayOfObj;
        int var5 = par2ArrayOfObj.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            Object var7 = var4[var6];

            if (var7 instanceof ItemStack)
            {
                var3.add(((ItemStack)var7).copy());
            }
            else if (var7 instanceof Item)
            {
                var3.add(new ItemStack((Item)var7));
            }
            else
            {
                if (!(var7 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }

                var3.add(new ItemStack((Block)var7));
            }
        }

        craftingRecipes.add(new ShapelessArcaneCraftingRecipes(key, par1ItemStack, var3, cost));
    }
    
    /**
	 * @param key the research key required for this recipe to work. Leave blank if it will work without research
	 * @param recipeKey a string value of the key used in your research.xml for this recipe to display in the thaumonomicon
	 * @param cost the vis cost
	 * @param par1ItemStack the recipe output
	 * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla shapeless recipes
	 */
	public static void addShapelessArcaneCraftingRecipe(String key, String recipeKey, int cost, ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
		addShapelessArcaneCraftingRecipe(key,cost,par1ItemStack,par2ArrayOfObj);
		ResearchList.craftingRecipesForResearch.put(recipeKey, Arrays.asList(getCraftingRecipes().size()-1));
	}
    
    /**
     * @param key the research key required for this recipe to work. Leave blank if it will work without research
     * @param cost the vis cost
     * @param tags ObjectTags list of required aspects and their amounts. No more than 5 aspects should be used in a recipe.
     * @param par1ItemStack the recipe output
     * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla recipes
     */
    public static void addInfusionCraftingRecipe(String key, int cost, ObjectTags tags, ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        int var9;

        if (par2ArrayOfObj[var4] instanceof String[])
        {
            String[] var7 = (String[])((String[])par2ArrayOfObj[var4++]);
            String[] var8 = var7;
            var9 = var7.length;

            for (int var10 = 0; var10 < var9; ++var10)
            {
                String var11 = var8[var10];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        }
        else
        {
            while (par2ArrayOfObj[var4] instanceof String)
            {
                String var13 = (String)par2ArrayOfObj[var4++];
                ++var6;
                var5 = var13.length();
                var3 = var3 + var13;
            }
        }

        HashMap var14;

        for (var14 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2)
        {
            Character var16 = (Character)par2ArrayOfObj[var4];
            ItemStack var17 = null;

            if (par2ArrayOfObj[var4 + 1] instanceof Item)
            {
                var17 = new ItemStack((Item)par2ArrayOfObj[var4 + 1]);
            }
            else if (par2ArrayOfObj[var4 + 1] instanceof Block)
            {
                var17 = new ItemStack((Block)par2ArrayOfObj[var4 + 1], 1, -1);
            }
            else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack)
            {
                var17 = (ItemStack)par2ArrayOfObj[var4 + 1];
            }

            var14.put(var16, var17);
        }

        ItemStack[] var15 = new ItemStack[var5 * var6];

        for (var9 = 0; var9 < var5 * var6; ++var9)
        {
            char var18 = var3.charAt(var9);

            if (var14.containsKey(Character.valueOf(var18)))
            {
                var15[var9] = ((ItemStack)var14.get(Character.valueOf(var18))).copy();
            }
            else
            {
                var15[var9] = null;
            }
        }

        craftingRecipes.add(new ShapedInfusionCraftingRecipes(key, var5, var6, var15, par1ItemStack,cost,tags));
    }

    /**
     * Recipe is NBT sensitive
     * @param key the research key required for this recipe to work. Leave blank if it will work without research
     * @param recipeKey a string value of the key used in your research.xml for this recipe to display in the thaumonomicon
     * @param cost the vis cost
     * @param tags ObjectTags list of required aspects and their amounts. No more than 5 aspects should be used in a recipe.
     * @param par1ItemStack the recipe output
     * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla recipes
     */
    public static void addInfusionCraftingRecipe(String key, String recipeKey, int cost, ObjectTags tags, ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
    	addInfusionCraftingRecipe(key, cost, tags, par1ItemStack, par2ArrayOfObj);
    	ResearchList.craftingRecipesForResearch.put(recipeKey, Arrays.asList(getCraftingRecipes().size()-1));
    }
    
    /**
     * Recipe is NBT sensitive
     * @param key the research key required for this recipe to work. Leave blank if it will work without research
     * @param cost the vis cost
     * @param tags ObjectTags list of required aspects and their amounts. No more than 5 aspects should be used in a recipe.
     * @param par1ItemStack the recipe output
     * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla shapeless recipes
     */
    public static void addShapelessInfusionCraftingRecipe(String key, int cost, ObjectTags tags, ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        ArrayList var3 = new ArrayList();
        Object[] var4 = par2ArrayOfObj;
        int var5 = par2ArrayOfObj.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            Object var7 = var4[var6];

            if (var7 instanceof ItemStack)
            {
                var3.add(((ItemStack)var7).copy());
            }
            else if (var7 instanceof Item)
            {
                var3.add(new ItemStack((Item)var7));
            }
            else
            {
                if (!(var7 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }

                var3.add(new ItemStack((Block)var7));
            }
        }

        craftingRecipes.add(new ShapelessInfusionCraftingRecipes(key, par1ItemStack, var3, cost,tags));
    }
    
    /**
     * Recipe is NBT sensitive
     * @param key the research key required for this recipe to work. Leave blank if it will work without research
     * @param recipeKey a string value of the key used in your research.xml for this recipe to display in the thaumonomicon
     * @param cost the vis cost
     * @param tags ObjectTags list of required aspects and their amounts. No more than 5 aspects should be used in a recipe.
     * @param par1ItemStack the recipe output
     * @param par2ArrayOfObj the recipe. Format is exactly the same as vanilla shapeless recipes
     */
    public static void addShapelessInfusionCraftingRecipe(String key, String recipeKey, int cost, ObjectTags tags, ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
    	addShapelessInfusionCraftingRecipe(key, cost, tags, par1ItemStack, par2ArrayOfObj);
    	ResearchList.craftingRecipesForResearch.put(recipeKey, Arrays.asList(getCraftingRecipes().size()-1));
    }
    
    /**
     * @param key the research key required for this recipe to work. Unlike the arcane crafting and infusion crafting
     * recipes a recipe key is automatically created using the same key. 
     * See method below if the research and recipes keys do not match
     * @param result the output result
     * @param cost the vis cost
     * @param tags the aspects required to craft this
     */
    public static void addCrucibleRecipe(String key, ItemStack result, int cost, ObjectTags tags) {
		getCrucibleRecipes().add(new RecipeCrucible(key, result, tags, cost));
	}
    
    /**
     * @param key the research key required for this recipe to work. 
     * @param recipeKey a string value of the key used in your research.xml for this recipe to display in the thaumonomicon
     * @param result the output result
     * @param cost the vis cost
     * @param tags the aspects required to craft this
     */
	public static void addCrucibleRecipe(String key, String recipeKey, ItemStack result, int cost, ObjectTags tags) {
		getCrucibleRecipes().add(new RecipeCrucible(key, recipeKey, result, tags, cost));
	}
	
	/**
	 * @param key the recipe key (NOT research key)
	 * @return the recipe
	 */
	public static RecipeCrucible getCrucibleRecipe(String key) {
		for (Object r:getCrucibleRecipes()) {
			if (r instanceof RecipeCrucible) {
				if (((RecipeCrucible)r).key.equals(key))
					return (RecipeCrucible)r;
			}
		}
		return null;
	}
	
	/**
	 * @param stack the recipe result
	 * @return the recipe
	 */
	public static RecipeCrucible getCrucibleRecipe(ItemStack stack) {
		for (Object r:getCrucibleRecipes()) {
			if (r instanceof RecipeCrucible) {
				if (((RecipeCrucible)r).recipeOutput.isItemEqual(stack))
					return (RecipeCrucible)r;
			}
		}
		return null;
	}
	
	/**
	 * @param stack the item
	 * @return the thaumcraft recipe key that produces that item. Used by the thaumonomicon drilldown feature.
	 */
	public static String getCraftingRecipeKey(ItemStack stack) {
		for (Object r:getCraftingRecipes()) {
			if (r instanceof IArcaneRecipe) {
				if (ThaumcraftApiHelper.areItemsEqual(stack,((IArcaneRecipe)r).getRecipeOutput()))
					return ((IArcaneRecipe)r).getKey();
			}
			if (r instanceof IInfusionRecipe) {
				if (ThaumcraftApiHelper.areItemsEqual(stack,((IInfusionRecipe)r).getRecipeOutput()))
					return ((IInfusionRecipe)r).getKey();
			}
		}
		return "";
	}
	
	//TAGS////////////////////////////////////////
	
	public static Map<List,ObjectTags> objectTags = new HashMap<List,ObjectTags>();
	
	/**
	 * Checks to see if the passed item/block already has aspects associated with it.
	 * @param id
	 * @param meta
	 * @return 
	 */
	public static boolean exists(int id, int meta) {
		ObjectTags tmp = ThaumcraftApi.objectTags.get(Arrays.asList(id,meta));
		if (tmp==null) {
			tmp = ThaumcraftApi.objectTags.get(Arrays.asList(id,-1));
			if (meta==-1 && tmp==null) {
				int index=0;
				do {
					tmp = ThaumcraftApi.objectTags.get(Arrays.asList(id,index));
					index++;
				} while (index<16 && tmp==null);
			}
			if (tmp==null) return false;
		}
		
		return true;
	}
	
	/**
	 * Used to assign apsects to the given item/block. Here is an example of the declaration for cobblestone:<p>
	 * <i>ThaumcraftApi.registerObjectTag(Block.cobblestone.blockID, -1, (new ObjectTags()).add(EnumTag.ROCK, 1).add(EnumTag.DESTRUCTION, 1));</i>
	 * @param id
	 * @param meta pass -1 if all damage values of this item/block should have the same aspects
	 * @param aspects A ObjectTags object of the associated aspects
	 */
	public static void registerObjectTag(int id, int meta, ObjectTags aspects) {
		aspects = ThaumcraftApiHelper.cullTags(aspects);
		objectTags.put(Arrays.asList(id,meta), aspects);
	}
	
	/**
	 * Used to assign aspects to the given item/block. 
	 * Attempts to automatically generate aspect tags by checking registered recipes.
	 * Here is an example of the declaration for pistons:<p>
	 * <i>ThaumcraftApi.registerComplexObjectTag(Block.pistonBase.blockID, 0, (new ObjectTags()).add(EnumTag.MECHANISM, 2).add(EnumTag.MOTION, 4));</i>
	 * @param id
	 * @param meta pass -1 if all damage values of this item/block should have the same aspects
	 * @param aspects A ObjectTags object of the associated aspects
	 */
	public static void registerComplexObjectTag(int id, int meta, ObjectTags aspects ) {
		if (!exists(id,meta)) {
			ObjectTags tmp = ThaumcraftApiHelper.generateTags(id, meta);
			if (tmp != null && tmp.size()>0) {
				for(EnumTag tag:tmp.getAspects()) {
					aspects.add(tag, tmp.getAmount(tag));
				}
			}
			registerObjectTag(id,meta,aspects);
		} else {
			ObjectTags tmp = ThaumcraftApiHelper.getObjectTags(new ItemStack(id,1,meta));
			for(EnumTag tag:aspects.getAspects()) {
				tmp.merge(tag, tmp.getAmount(tag));
			}
			registerObjectTag(id,meta,tmp);
		}
	}
	
	public static ArrayList getCrucibleRecipes() {
		return crucibleRecipes;
	}


	//AURAS//////////////////////////////////////////////////
	
	private static Method addFluxToClosest;
	/**
	 * Adds flux to the node closest to the given location
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param tags ObjectTags list of all the EnumTags and amounts of flux to add.
	 */
	public static void addFluxToClosest(World world, float x, float y, float z, ObjectTags tags) {
		try {
            if(addFluxToClosest == null) {
                Class fake = Class.forName("thaumcraft.common.aura.AuraManager");
                addFluxToClosest = fake.getMethod("addFluxToClosest", World.class, float.class, float.class, float.class, ObjectTags.class);
            }
            addFluxToClosest.invoke(null, world,x,y,z,tags);
        } catch(Exception ex) { 
        	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.aura.AuraManager method addFluxToClosest");
        }
    }
	
	private static Method decreaseClosestAura;
	/**
	 * Removes an amount of vis from the aura node closest to the given location
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param amount The amount of vis to remove
	 * @param doit If set to false it will merely perform a check to see if there is enough vis to perform the operation. 
	 * If set to true it will actually decrease the vis as well.
	 * @return It will return true if there was enough vis to perform this operation
	 */
	public static boolean decreaseClosestAura(World world, double x, double y, double z, int amount, boolean doit) {
		boolean ret=false;
		try {
            if(decreaseClosestAura == null) {
                Class fake = Class.forName("thaumcraft.common.aura.AuraManager");
                decreaseClosestAura = fake.getMethod("decreaseClosestAura", World.class, double.class, double.class, double.class, int.class, boolean.class);
            }
            ret = (Boolean) decreaseClosestAura.invoke(null, world,x,y,z,amount,doit);
        } catch(Exception ex) { 
        	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.aura.AuraManager method decreaseClosestAura");
        }
		return ret;
    }
	
	private static Method increaseLowestAura;
	/**
	 * Increases the lowest aura near the given location.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param amount
	 * @return it will return true if the operation was a success
	 */
	public static boolean increaseLowestAura(World world, double x, double y, double z, int amount) {
		boolean ret=false;
		try {
            if(increaseLowestAura == null) {
                Class fake = Class.forName("thaumcraft.common.aura.AuraManager");
                increaseLowestAura = fake.getMethod("increaseLowestAura", World.class, double.class, double.class, double.class, int.class);
            }
            ret = (Boolean) increaseLowestAura.invoke(null, world,x,y,z,amount);
        } catch(Exception ex) { 
        	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.aura.AuraManager method increaseLowestAura");
        }
		return ret;
    }
	
	private static Method getClosestAuraWithinRange;
	/**
	 * Gets the id of the closest aura node within range of the given coordinates. Only checks loaded chunks
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param range distance (in blocks) to check
	 * @return returns -1 if no aura is found, otherwise returns the aura node id.
	 */
	public static int getClosestAuraWithinRange(World world, double x, double y, double z, double range) {
		int ret=-1;
		try {
            if(getClosestAuraWithinRange == null) {
                Class fake = Class.forName("thaumcraft.common.aura.AuraManager");
                getClosestAuraWithinRange = fake.getMethod("getClosestAuraWithinRange", World.class, double.class, double.class, double.class, double.class);
            }
            ret = (Integer) getClosestAuraWithinRange.invoke(null, world,x,y,z,range);
        } catch(Exception ex) { 
        	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.aura.AuraManager method getClosestAuraWithinRange");
        }
		return ret;
    }
	
	private static Method getNodeCopy;
	/**
	 * Gets an copy of the AuraNode object for the given node
	 * @param nodeId the int key of the aura node
	 * @return returns a COPY of the auranode object, not the object itself. 
	 * Auranode values should only be altered via queNodeChanges - NEVER directly
	 */
	public static AuraNode getNodeCopy(int nodeId) {
		AuraNode node = null;
		try {
            if(getNodeCopy == null) {
                Class fake = Class.forName("thaumcraft.common.aura.AuraManager");
                getNodeCopy = fake.getMethod("getNodeCopy", int.class);
            }
            node = (AuraNode) getNodeCopy.invoke(null, nodeId);
        } catch(Exception ex) { 
        	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.aura.AuraManager method getNodeCopy");
        }
		return node;
    }
	
	private static Method queueNodeChanges;
	/**
	 * This method is used to alter the values of aura nodes. The changes specified are placed in a queue for processing by
	 * the aura thread.<br>
	 * 
	 * For example:<br>
	 * queNodeChanges(55,8,0,false,null,0,0,0); //will increase node 55's current vis by 8<br>
	 * queNodeChanges(55,0,0,false,new ObjectTags().remove(EnumTag.FIRE,3),0,0,0); //will reduce node 55's FIRE flux levels by 3<br>
	 * queNodeChanges(55,11,11,false,null,0,.5f,0); //will increase node 55's current and base level by 11 and increase its y pos by .5f<br>
	 * 
	 * @param nodeId
	 * @param levelMod the amount by which the auras vis level should be changed (positive or negative)
	 * @param baseMod the amount by which the auras max vis level should be changed (positive or negative)
	 * @param toggleLock if set to true the nodes lock state will toggle to its opposite value. Currently doesn't do much
	 * @param flx a ObjectTags collection of the all the flux to add (if positive) or remove (if negative) to the node
	 * @param x by how much the nodes x position should be altered. Should usually be less than 1
	 * @param y by how much the nodes y position should be altered. Should usually be less than 1
	 * @param z by how much the nodes z position should be altered. Should usually be less than 1
	 */
	public static void queueNodeChanges(int nodeId, int levelMod, int baseMod, boolean toggleLock, ObjectTags flux,
			  						  float x,float y,float z) {
		try {
            if(queueNodeChanges == null) {
                Class fake = Class.forName("thaumcraft.common.aura.AuraManager");
                queueNodeChanges = fake.getMethod("queueNodeChanges", 
                		int.class, int.class, int.class, boolean.class, ObjectTags.class, float.class, float.class, float.class);
            }
            queueNodeChanges.invoke(null, nodeId, levelMod, baseMod, toggleLock, flux, x, y, z);
        } catch(Exception ex) { 
        	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.aura.AuraManager method queueNodeChanges");
        }
    }
	
	//BIOMES//////////////////////////////////////////////////
	@Deprecated 
	public static HashMap<Integer,List> biomeInfo = new HashMap<Integer,List>();
	
	/**
	 * Registers custom biomes with thaumcraft
	 * @Deprecated I will be switching over the the forge BiomeDictionary system in the future so any mods that add biomes should just make sure they are tagged correctly
	 * @param biomeID The id of the biome
	 * @param visLevel The average vis level of nodes generated in this biome
	 * @param tag The EnumTag associated with this biome (used to determine infused ore spawns among other things)
	 * @param greatwood Does this biome support greatwood trees
	 * @param silverwood Does this biome support silverwood trees
	 */
	@Deprecated
	public static void registerBiomeInfo(int biomeID, int visLevel, EnumTag tag, boolean greatwood, boolean silverwood) {
		biomeInfo.put(biomeID, Arrays.asList(visLevel, tag, greatwood, silverwood));
	}
	
	@Deprecated
	public static int getBiomeAura(int biomeId) {
		try { 
			return (Integer) biomeInfo.get(biomeId).get(0);
		} catch (Exception e) {}
		return 200;
	}
	
	@Deprecated
	public static EnumTag getBiomeTag(int biomeId) {
		try {
			return (EnumTag) biomeInfo.get(biomeId).get(1);
		} catch (Exception e) {}
		return EnumTag.UNKNOWN;
	}
	
	@Deprecated
	public static boolean getBiomeSupportsGreatwood(int biomeId) {
		try {
			return (Boolean) biomeInfo.get(biomeId).get(2);
		} catch (Exception e) {}
		return false;
	}
	
	@Deprecated
	public static boolean getBiomeSupportsSilverwood(int biomeId) {
		try {
			return (Boolean) biomeInfo.get(biomeId).get(3);
		} catch (Exception e) {}
		return false;
	}
	
	//CROPS //////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * To define mod crops you need to use FMLInterModComms in your @Mod.Init method.
	 * There are two 'types' of crops you can add. Standard crops and clickable crops.
	 * 
	 * Standard crops work like normal vanilla crops - they grow until a certain metadata 
	 * value is reached and you harvest them by destroying the block and collecting the blocks.
	 * You need to create and ItemStack that tells the golem what block id and metadata represents
	 * the crop when fully grown.
	 * Example for vanilla wheat: 
	 * FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(Block.crops,1,7));
	 *  
	 * Clickable crops are crops that you right click to gather their bounty instead of destroying them.
	 * As for standard crops, you need to create and ItemStack that tells the golem what block id 
	 * and metadata represents the crop when fully grown. The golem will trigger the blocks onBlockActivated method.
	 * Example (this will technically do nothing since clicking wheat does nothing, but you get the idea): 
	 * FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Block.crops,1,7));
	 */
	
	@Deprecated
	public static HashMap<Integer,Integer> crops = new HashMap<Integer,Integer>();
	
	/**
	 * This is used to add mod crops to the list of crops harvested by golems 
	 * that do not use the standard crop growth pattern<br> 
	 * (i.e. being an instance of BlockCrops and being fully grown at meta 7).<br>
	 * Only seeds implementing IPlantable will be replanted.
	 * @param blockID the block id of the crop 
	 * @param grownMeta the metadata value when the crop is considered fully grown. 
	 * The block with this id and meta will be the one the golem breaks.
	 */
	@Deprecated
	public static void addHarvestableCrop(int blockID, int grownMeta) {
		crops.put(blockID, grownMeta);
	}
	
}
