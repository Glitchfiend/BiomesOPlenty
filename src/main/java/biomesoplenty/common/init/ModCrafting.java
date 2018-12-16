/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import com.google.common.base.CaseFormat;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPFence;
import biomesoplenty.common.block.BlockBOPFenceGate;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPFoliage;
import biomesoplenty.common.block.BlockBOPHalfWoodSlab;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPPlanks;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.block.BlockBOPWoodStairs;
import biomesoplenty.common.handler.FurnaceFuelHandler;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModCrafting
{
    
    public static void init()
    {
        addOreRegistration();
        addSmeltingRecipes();
    }
    
    private static void addSmeltingRecipes()
    {
        // Register smelting recipes
        GameRegistry.addSmelting(new ItemStack(BOPBlocks.mud), new ItemStack(Blocks.DIRT), 0.1F);
        GameRegistry.addSmelting(new ItemStack(BOPBlocks.white_sand), new ItemStack(Blocks.GLASS), 0.1F);
        GameRegistry.addSmelting(BlockBOPPlant.paging.getVariantItem(BOPPlants.TINYCACTUS), new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
        GameRegistry.addSmelting(BOPItems.mudball, new ItemStack(BOPItems.mud_brick), 0.1F);
        for (BOPWoods wood : BOPWoods.values())
        {
            if (wood.canMakeCharcoal())
            {
                GameRegistry.addSmelting(BlockBOPLog.paging.getVariantItem(wood), new ItemStack(Items.COAL, 1, 1), 0.15F);
            }
        }
        
        // Register items which can be used as fuel
        FurnaceFuelHandler bopFuel = new FurnaceFuelHandler();
        GameRegistry.registerFuelHandler(bopFuel);

        bopFuel.addFuel(BOPBlocks.sapling_0, 100);
        bopFuel.addFuel(BOPBlocks.sapling_1, 100);
        bopFuel.addFuel(BOPBlocks.sapling_2, 100);
        bopFuel.addFuel(BOPBlocks.wood_slab_0, 150);
        bopFuel.addFuel(BOPBlocks.wood_slab_1, 150);
        
        bopFuel.addFuel(BOPItems.boat_cherry, 400);
        bopFuel.addFuel(BOPItems.boat_umbran, 400);
        bopFuel.addFuel(BOPItems.boat_fir, 400);
        bopFuel.addFuel(BOPItems.boat_ethereal, 400);
        bopFuel.addFuel(BOPItems.boat_magic, 400);
        bopFuel.addFuel(BOPItems.boat_palm, 400);
        bopFuel.addFuel(BOPItems.boat_redwood, 400);
        bopFuel.addFuel(BOPItems.boat_willow, 400);
        bopFuel.addFuel(BOPItems.boat_hellbark, 400);
        bopFuel.addFuel(BOPItems.boat_jacaranda, 400);
        bopFuel.addFuel(BOPItems.boat_mahogany, 400);
        bopFuel.addFuel(BOPItems.boat_ebony, 400);

        bopFuel.addFuel(BOPItems.ash, 400);
    }
    
    private static void addOreRegistration()
    {
        //Registration in Ore Dictionary
        OreDictionary.registerOre("ballMud", new ItemStack(BOPItems.mudball));

        OreDictionary.registerOre("blockMeatRaw", new ItemStack(BOPBlocks.flesh, 1, 0));

        OreDictionary.registerOre("grass", BOPBlocks.grass);
        OreDictionary.registerOre("dirt", BOPBlocks.dirt);

        OreDictionary.registerOre("blockMud", new ItemStack(BOPBlocks.mud));
        
        OreDictionary.registerOre("sand", new ItemStack(BOPBlocks.white_sand));
        OreDictionary.registerOre("sandstone", new ItemStack(BOPBlocks.white_sandstone));
        
        OreDictionary.registerOre("cropPeach", new ItemStack(BOPItems.peach));
        OreDictionary.registerOre("cropPersimmon", new ItemStack(BOPItems.persimmon));
        OreDictionary.registerOre("cropPear", new ItemStack(BOPItems.pear));
        
        OreDictionary.registerOre("listAllfruit", new ItemStack(BOPItems.peach));
        OreDictionary.registerOre("listAllfruit", new ItemStack(BOPItems.persimmon));
        OreDictionary.registerOre("listAllfruit", new ItemStack(BOPItems.pear));
        
        OreDictionary.registerOre("record", new ItemStack(BOPItems.record_wanderer));
        
        for (BOPFlowers flower : BOPFlowers.values())
        {
            String flowerName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flower.name());
            OreDictionary.registerOre("flower"+flowerName, BlockBOPFlower.paging.getVariantItem(flower));
        }
        
        for (BOPFoliage plant : BOPFoliage.values())
        {
            String plantName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, plant.name());
            OreDictionary.registerOre("plant"+plantName, BlockBOPFoliage.paging.getVariantItem(plant));
        }
        
        for (BOPPlants plant : BOPPlants.values())
        {
            String plantName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, plant.name());
            OreDictionary.registerOre("plant"+plantName, BlockBOPPlant.paging.getVariantItem(plant));
        }
        OreDictionary.registerOre("plantFlax", ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.FLAX));
        OreDictionary.registerOre("plantCattail", ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL));
        
        for (BOPTrees tree : BOPTrees.values())
        {
            OreDictionary.registerOre("treeLeaves", BlockBOPLeaves.paging.getVariantItem(tree));
            if (tree.hasSapling())
            {
                OreDictionary.registerOre("treeSapling", BlockBOPSapling.paging.getVariantItem(tree));
            }
        }
        
        for (BOPWoods wood : BOPWoods.values())
        {
            OreDictionary.registerOre("logWood", BlockBOPLog.paging.getVariantItem(wood));   
            if (wood.hasPlanks())
            {
                OreDictionary.registerOre("plankWood", BlockBOPPlanks.paging.getVariantItem(wood));
                OreDictionary.registerOre("slabWood", BlockBOPHalfWoodSlab.paging.getVariantItem(wood));
                OreDictionary.registerOre("stairWood", new ItemStack(BlockBOPWoodStairs.getBlock(wood)));
                OreDictionary.registerOre("fenceWood", new ItemStack(BlockBOPFence.getBlock(wood)));
                OreDictionary.registerOre("fenceGateWood", new ItemStack(BlockBOPFenceGate.getBlock(wood)));
            }
        }

    }

    private static void registerCustomRecipe(IRecipe recipe, String name)
    {
        recipe.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        ForgeRegistries.RECIPES.register(recipe);
    }
}