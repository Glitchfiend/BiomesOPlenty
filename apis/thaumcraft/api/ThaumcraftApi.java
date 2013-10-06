package thaumcraft.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;


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
	
	//Enchantment references
	public static int enchantFrugal;
	public static int enchantPotency;
	public static int enchantWandFortune;
	public static int enchantHaste;
	public static int enchantRepair;
	
	//Miscellaneous
	/**
	 * Portable Hole Block-id Blacklist. 
	 * Simply add the block-id's of blocks you don't want the portable hole to go through.
	 */
	public static ArrayList<Integer> portableHoleBlackList = new ArrayList<Integer>();
	
	
	//RESEARCH/////////////////////////////////////////
//	public static Document researchDoc = null;
//	public static ArrayList<String> apiResearchFiles = new ArrayList<String>(); 
	public static ArrayList<IScanEventHandler> scanEventhandlers = new ArrayList<IScanEventHandler>();
	public static ArrayList<EntityTags> scanEntities = new ArrayList<EntityTags>();
	public static class EntityTags {
		public EntityTags(String entityName, NBTBase[] nbts, AspectList aspects) {
			this.entityName = entityName;
			this.nbts = nbts;
			this.aspects = aspects;
		}
		public String entityName;
		public NBTBase[] nbts;
		public AspectList aspects;
	}
	
	/**
	 * not really working atm, so ignore it for now
	 * @param scanEventHandler
	 */
	public static void registerScanEventhandler(IScanEventHandler scanEventHandler) {
		scanEventhandlers.add(scanEventHandler);
	}
	
	/**
	 * This is used to add aspects to entities which you can then scan using a thaumometer.
	 * Also used to calculate vis drops from mobs.
	 * @param entityName
	 * @param aspects
	 * @param nbt you can specify certain nbt keys and their values 
	 * 			  to differentiate between mobs. <br>For example the normal and wither skeleton:
	 * 	<br>ThaumcraftApi.registerEntityTag("Skeleton", (new AspectList()).add(Aspect.DEATH, 5));
	 * 	<br>ThaumcraftApi.registerEntityTag("Skeleton", (new AspectList()).add(Aspect.DEATH, 8), new NBTTagByte("SkeletonType",(byte) 1));
	 */
	public static void registerEntityTag(String entityName, AspectList aspects, NBTBase... nbt ) {
		scanEntities.add(new EntityTags(entityName,nbt,aspects));
	}
	
	//RECIPES/////////////////////////////////////////
	private static ArrayList craftingRecipes = new ArrayList();	
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
	 * @param research the research key required for this recipe to work. Leave blank if it will work without research
	 * @param result the recipe output
	 * @param aspects the vis cost per aspect. 
	 * @param recipe The recipe. Format is exactly the same as vanilla recipes. Input itemstacks are NBT sensitive.
	 */
	public static ShapedArcaneRecipe addArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe)
    {
		ShapedArcaneRecipe r= new ShapedArcaneRecipe(research, result, aspects, recipe);
        craftingRecipes.add(r);
		return r;
    }
	
	/**
	 * @param research the research key required for this recipe to work. Leave blank if it will work without research
	 * @param result the recipe output
	 * @param aspects the vis cost per aspect
	 * @param recipe The recipe. Format is exactly the same as vanilla shapeless recipes. Input itemstacks are NBT sensitive.
	 */
	public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe)
    {
		ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
        craftingRecipes.add(r);
		return r;
    }
	
	/**
	 * @param research the research key required for this recipe to work. Leave blank if it will work without research
	 * @param result the recipe output. It can either be an itemstack or an nbt compound tag that will be added to the central item
	 * @param instability a number that represents the N in 1000 chance for the infusion altar to spawn an
	 * 		  instability effect each second while the crafting is in progress
	 * @param aspects the essentia cost per aspect. 
	 * @param aspects input the central item to be infused
	 * @param recipe An array of items required to craft this. Input itemstacks are NBT sensitive. 
	 * 				Infusion crafting components are automatically "fuzzy" and the oredict will be checked for possible matches.
	 * 
	 */
	public static InfusionRecipe addInfusionCraftingRecipe(String research, Object result, int instability, AspectList aspects, ItemStack input,ItemStack[] recipe)
    {
		if (!(result instanceof ItemStack || result instanceof NBTBase)) return null;
		InfusionRecipe r= new InfusionRecipe(research, result, instability, aspects, input, recipe);
        craftingRecipes.add(r);
		return r;
    }
	
	/**
	 * @param stack the recipe result
	 * @return the recipe
	 */
	public static InfusionRecipe getInfusionRecipe(ItemStack res) {
		for (Object r:getCraftingRecipes()) {
			if (r instanceof InfusionRecipe) {
				if (((InfusionRecipe)r).recipeOutput instanceof ItemStack) {
					if (((ItemStack) ((InfusionRecipe)r).recipeOutput).isItemEqual(res))
						return (InfusionRecipe)r;
				} 
			}
		}
		return null;
	}

    
    /**
     * @param key the research key required for this recipe to work. 
     * @param result the output result
     * @param cost the vis cost
     * @param tags the aspects required to craft this
     */
    public static CrucibleRecipe addCrucibleRecipe(String key, ItemStack result, Object catalyst, AspectList tags) {
    	CrucibleRecipe rc = new CrucibleRecipe(key, result, catalyst, tags);
    	getCraftingRecipes().add(rc);
		return rc;
	}
    
	
	/**
	 * @param stack the recipe result
	 * @return the recipe
	 */
	public static CrucibleRecipe getCrucibleRecipe(ItemStack stack) {
		for (Object r:getCraftingRecipes()) {
			if (r instanceof CrucibleRecipe) {
				if (((CrucibleRecipe)r).recipeOutput.isItemEqual(stack))
					return (CrucibleRecipe)r;
			}
		}
		return null;
	}
	
	/**
	 * Used by the thaumonomicon drilldown feature.
	 * @param stack the item
	 * @return the thaumcraft recipe key that produces that item. 
	 */
	private static HashMap<int[],Object[]> keyCache = new HashMap<int[],Object[]>();
	public static Object[] getCraftingRecipeKey(EntityPlayer player, ItemStack stack) {
		int[] key = new int[] {stack.itemID,stack.getItemDamage()};
		if (keyCache.containsKey(key)) {
			if (keyCache.get(key)==null) return null;
			if (ThaumcraftApiHelper.isResearchComplete(player.username, (String)(keyCache.get(key))[0]))
				return keyCache.get(key);
			else 
				return null;
		}
		for (ResearchCategoryList rcl:ResearchCategories.researchCategories.values()) {
			for (ResearchItem ri:rcl.research.values()) {
				if (ri.getPages()==null) continue;
				for (int a=0;a<ri.getPages().length;a++) {
					ResearchPage page = ri.getPages()[a];
					if (page.recipeOutput!=null && stack !=null && page.recipeOutput.isItemEqual(stack)) {
						keyCache.put(key,new Object[] {ri.key,a});
						if (ThaumcraftApiHelper.isResearchComplete(player.username, ri.key))
							return new Object[] {ri.key,a};
						else 
							return null;
					}
				}
			}
		}
		keyCache.put(key,null);
		return null;
	}
	
	//ASPECTS////////////////////////////////////////
	
	public static Map<List,AspectList> objectTags = new HashMap<List,AspectList>();
	
	/**
	 * Checks to see if the passed item/block already has aspects associated with it.
	 * @param id
	 * @param meta
	 * @return 
	 */
	public static boolean exists(int id, int meta) {
		AspectList tmp = ThaumcraftApi.objectTags.get(Arrays.asList(id,meta));
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
	public static void registerObjectTag(int id, int meta, AspectList aspects) {
		objectTags.put(Arrays.asList(id,meta), aspects);
	}	
	
	/**
	 * Used to assign apsects to the given item/block. Here is an example of the declaration for cobblestone:<p>
	 * <i>ThaumcraftApi.registerObjectTag(Block.cobblestone.blockID, new int[]{0,1}, (new ObjectTags()).add(EnumTag.ROCK, 1).add(EnumTag.DESTRUCTION, 1));</i>
	 * @param id
	 * @param meta A range of meta values if you wish to lump several item meta's together as being the "same" item (i.e. stair orientations)
	 * @param aspects A ObjectTags object of the associated aspects
	 */
	public static void registerObjectTag(int id, int[] meta, AspectList aspects) {
		objectTags.put(Arrays.asList(id,meta), aspects);
	}
	
	/**
	 * Used to assign apsects to the given ore dictionary item. 
	 * @param oreDict the ore dictionary name
	 * @param aspects A ObjectTags object of the associated aspects
	 */
	public static void registerObjectTag(String oreDict, AspectList aspects) {
		ArrayList<ItemStack> ores = OreDictionary.getOres(oreDict);
		if (ores!=null && ores.size()>0) {
			for (ItemStack ore:ores) {
				int d = ore.getItemDamage();
				if (d==OreDictionary.WILDCARD_VALUE) d = -1;
				objectTags.put(Arrays.asList(ore.itemID, d), aspects);
			}
		}
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
	public static void registerComplexObjectTag(int id, int meta, AspectList aspects ) {
		if (!exists(id,meta)) {
			AspectList tmp = ThaumcraftApiHelper.generateTags(id, meta);
			if (tmp != null && tmp.size()>0) {
				for(Aspect tag:tmp.getAspects()) {
					aspects.add(tag, tmp.getAmount(tag));
				}
			}
			registerObjectTag(id,meta,aspects);
		} else {
			AspectList tmp = ThaumcraftApiHelper.getObjectAspects(new ItemStack(id,1,meta));
			for(Aspect tag:aspects.getAspects()) {
				tmp.merge(tag, tmp.getAmount(tag));
			}
			registerObjectTag(id,meta,tmp);
		}
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
	
}
