package biomesoplenty.integration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.api.BlockReferences;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.GlobalManager;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;

public class ForestryIntegration
{
    private static final int MINER = 0;
    private static final int DIGGER = 1;
    private static final int FORESTER = 2;
    
    protected static void init()
    {
        addClimateInfo();
        addFermenterRecipes();
        addFlowers();
        addBlocksToManager();
        addBlocksToBackpacks();
    }
    
    private static void addClimateInfo()
    {
        //Hot - Arid
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.badlandsID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.deadlandsID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.drylandsID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.dunesID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.mesaID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.steppeID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.volcanoID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.wastelandID);
        
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.badlandsID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.deadlandsID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.drylandsID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.dunesID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.mesaID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.steppeID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.volcanoID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.wastelandID);
        
        //Hot - Damp
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.oasisID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.promisedLandID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.rainforestID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.tropicsID);
        
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.oasisID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.promisedLandID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.rainforestID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.tropicsID);
        
        //Warm - Damp
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.bambooForestID);
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.sacredSpringsID);
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.tropicalRainforestID);
        
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.bambooForestID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.sacredSpringsID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.tropicalRainforestID);
        
        //Warm - Arid
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.deadForestID);
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.savannaID);
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.scrublandID);
        EnumTemperature.warmBiomeIds.add(BOPConfiguration.woodlandID);
        
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.deadForestID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.savannaID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.scrublandID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.woodlandID);
        
        //Normal - Damp
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.bayouID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.bogID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.deadSwampID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.fungiForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.lushSwampID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.mangroveID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.marshID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.moorID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.mysticGroveID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.ominousWoodsID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.quagmireID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.swampwoodsID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.wetlandID);
        
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.bayouID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.bogID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.deadSwampID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.fungiForestID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.lushSwampID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.mangroveID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.marshID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.moorID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.mysticGroveID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.ominousWoodsID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.quagmireID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.swampwoodsID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.wetlandID);
        
        //Normal
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.birchForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.borealForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.canyonID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.chaparralID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.cherryBlossomGroveID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.coniferousForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.deciduousForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.fieldID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.gardenID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.grasslandID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.groveID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.highlandID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.lushDesertID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.mapleWoodsID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.meadowID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.orchardID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.originValleyID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.pastureID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.prairieID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.redwoodForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.seasonalForestID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.shieldID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.shoreID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.spruceWoodsID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.temperateRainforestID);

        EnumHumidity.normalBiomeIds.add(BOPConfiguration.birchForestID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.borealForestID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.canyonID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.chaparralID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.cherryBlossomGroveID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.coniferousForestID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.deciduousForestID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.fieldID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.gardenID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.grasslandID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.groveID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.highlandID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.lushDesertID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.mapleWoodsID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.meadowID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.orchardID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.originValleyID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.pastureID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.prairieID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.redwoodForestID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.seasonalForestID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.shieldID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.shoreID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.spruceWoodsID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.temperateRainforestID);
        
        //Normal - Arid
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.cragID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.fenID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.heathlandID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.jadeCliffsID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.mountainID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.outbackID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.shrublandID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.thicketID);
        
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.cragID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.fenID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.heathlandID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.jadeCliffsID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.mountainID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.outbackID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.shrublandID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.thicketID);
        
        //Cold
        EnumTemperature.coldBiomeIds.add(BOPConfiguration.tundraID);
        EnumTemperature.coldBiomeIds.add(BOPConfiguration.snowyWoodsID);
        
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.tundraID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.snowyWoodsID);
        
        //Icy - Arid
        EnumTemperature.icyBiomeIds.add(BOPConfiguration.alpsID);
        EnumTemperature.icyBiomeIds.add(BOPConfiguration.arcticID);
        EnumTemperature.icyBiomeIds.add(BOPConfiguration.frostForestID);
        EnumTemperature.icyBiomeIds.add(BOPConfiguration.glacierID);
        EnumTemperature.icyBiomeIds.add(BOPConfiguration.iceSheetID);
        EnumTemperature.icyBiomeIds.add(BOPConfiguration.icyHillsID);
        
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.alpsID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.arcticID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.frostForestID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.glacierID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.iceSheetID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.icyHillsID);
        
        //New vanilla biomes
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.plainsNewID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.desertNewID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.extremeHillsNewID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.forestNewID);
        EnumTemperature.coldBiomeIds.add(BOPConfiguration.taigaNewID);
        EnumTemperature.normalBiomeIds.add(BOPConfiguration.swamplandNewID);
        EnumTemperature.hotBiomeIds.add(BOPConfiguration.jungleNewID);
        
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.plainsNewID);
        EnumHumidity.aridBiomeIds.add(BOPConfiguration.desertNewID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.extremeHillsNewID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.forestNewID);
        EnumHumidity.normalBiomeIds.add(BOPConfiguration.taigaNewID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.swamplandNewID);
        EnumHumidity.dampBiomeIds.add(BOPConfiguration.jungleNewID);
    }
    
    private static void addFermenterRecipes()
    {
		addFermenterRecipeSapling(new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		addFermenterRecipeSapling(new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));
    }
    
	private static void addFermenterRecipeSapling(ItemStack resource) {
		RecipeManagers.fermenterManager.addRecipe(resource, 250, 1.0f, 
				new LiquidStack(ItemInterface.getItem("liquidBiomass").itemID, 1, ItemInterface.getItem("liquidBiomass").getItemDamage()), 
				new LiquidStack(Block.waterStill, 1));
		RecipeManagers.fermenterManager.addRecipe(resource, 250, 1.0f, 
				new LiquidStack(ItemInterface.getItem("liquidBiomass").itemID, 1, ItemInterface.getItem("liquidBiomass").getItemDamage()), 
				new LiquidStack(ItemInterface.getItem("liquidJuice").itemID, 1, ItemInterface.getItem("liquidJuice").getItemDamage()));
		RecipeManagers.fermenterManager.addRecipe(resource, 250, 1.0f, 
				new LiquidStack(ItemInterface.getItem("liquidBiomass").itemID, 1, ItemInterface.getItem("liquidBiomass").getItemDamage()), 
				new LiquidStack(ItemInterface.getItem("liquidHoney").itemID, 1, ItemInterface.getItem("liquidHoney").getItemDamage()));
	}
	
	private static void addFlowers()
	{
	    for (int i = 0; i < 10; ++i)
	        FlowerManager.plainFlowers.add(new ItemStack(Blocks.flowers.get(), 1, i));
	}
	
	private static void addBlocksToManager()
	{
	    GlobalManager.leafBlockIds.add(Blocks.leaves1.get().blockID);
        GlobalManager.leafBlockIds.add(Blocks.leaves2.get().blockID);
        GlobalManager.leafBlockIds.add(Blocks.leavesColorized.get().blockID);
        GlobalManager.leafBlockIds.add(Blocks.leavesFruit.get().blockID);
        
        GlobalManager.dirtBlockIds.add(Blocks.ash.get().blockID);
        GlobalManager.dirtBlockIds.add(Blocks.hardDirt.get().blockID);
        GlobalManager.dirtBlockIds.add(Blocks.holyGrass.get().blockID);
        GlobalManager.dirtBlockIds.add(Blocks.mud.get().blockID);
        GlobalManager.dirtBlockIds.add(Blocks.originGrass.get().blockID);
        GlobalManager.dirtBlockIds.add(Blocks.redRock.get().blockID);
        
        GlobalManager.sandBlockIds.add(Blocks.hardSand.get().blockID);
        
        GlobalManager.snowBlockIds.add(Blocks.hardIce.get().blockID);
	}
	
	private static void addBlocksToBackpacks()
	{
	    // Miner's Backpack
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 0));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.holyStone.get(), 1, 0));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.redRock.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.driedDirt.get(), 1, 0));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.holyGrass.get(), 1, 0));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.cragRock.get(), 1, 0));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.ashStone.get(), 1, 0));
	    BackpackManager.backpackItems[MINER].add(new ItemStack(Items.miscItems.get(), 1, 2));
	    
	    // Digger's Backpack
	    BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.holyGrass.get(), 1, 1));
	    BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.mud.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.hardDirt.get(), 1, 0));
	    BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.hardSand.get(), 1, 0));
	    BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.originGrass.get(), 1, 0));
	    BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.ash.get(), 1, 0));
	    
	    // Forester's Backpack
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs1.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs2.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs3.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leaves1.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leaves2.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesColorized.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesFruit.get(), 1, OreDictionary.WILDCARD_VALUE));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.bamboo.get(), 1, 0));
	    BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.petals.get(), 1, OreDictionary.WILDCARD_VALUE));
	}
}
