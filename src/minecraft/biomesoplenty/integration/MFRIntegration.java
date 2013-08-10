package biomesoplenty.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import powercrystals.minefactoryreloaded.api.FarmingRegistry;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.MobDrop;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.BlockReferences;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Entities;
import biomesoplenty.api.Items;
import biomesoplenty.integration.minefactoryreloaded.Fertilizable;
import biomesoplenty.integration.minefactoryreloaded.FruitLeaves;
import biomesoplenty.integration.minefactoryreloaded.Grindable;
import biomesoplenty.integration.minefactoryreloaded.Harvestable;
import biomesoplenty.integration.minefactoryreloaded.Plantable;

import com.google.common.base.Optional;

public class MFRIntegration
{
    protected static void init()
    {
        registerRubberTreeBiomes();
    }
    
    protected static void postInit()
    {
        registerFarmables();
        registerSludgeDrops();
        registerGrindables();
    }

    private static void registerRubberTreeBiomes()
    {
        FarmingRegistry.registerRubberTreeBiome(Biomes.bayou.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.birchForest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.bog.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.borealForest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.deciduousForest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.forestNew.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.fungiForest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.grove.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.highland.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.jungleNew.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.lushSwamp.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.mapleWoods.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.marsh.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.rainforest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.seasonalForest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.shield.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.swamplandNew.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.temperateRainforest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.thicket.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.tropicalRainforest.get().biomeName);
        FarmingRegistry.registerRubberTreeBiome(Biomes.woodland.get().biomeName);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void registerFarmables()
    {
        Optional[] bopLeaves = { Blocks.leaves1, Blocks.leaves2, Blocks.leavesColorized, Blocks.treeMoss, Blocks.willow, Blocks.ivy, Blocks.moss };
        Optional[] bopFruitLeaves = { Blocks.leavesFruit };
        Optional[] bopLogs = { Blocks.logs1, Blocks.logs2, Blocks.logs3, Blocks.logs4, Blocks.bamboo };
        Optional[] bopMiscStandardHarvestables = { Blocks.flowers, Blocks.plants, Blocks.foliage, Blocks.mushrooms };
        Optional[] bopSaplings = { Blocks.saplings, Blocks.colorizedSaplings };
        
        for(Optional<? extends Block> leaves : bopLeaves)
        {
            FarmingRegistry.registerHarvestable(new Harvestable(leaves.get().blockID, HarvestType.TreeLeaf));
        }
        
        for(Optional<? extends Block> log : bopLogs)
        {
            FarmingRegistry.registerHarvestable(new Harvestable(log.get().blockID, HarvestType.Tree));
        }
        
        for(Optional<? extends Block> harvestable : bopMiscStandardHarvestables)
        {
            FarmingRegistry.registerHarvestable(new Harvestable(harvestable.get().blockID, HarvestType.Normal));
        }
        
        for(Optional<? extends Block> sapling : bopSaplings)
        {
            FarmingRegistry.registerPlantable(new Plantable(sapling.get().blockID, sapling.get().blockID));
            FarmingRegistry.registerFertilizable(new Fertilizable(sapling.get().blockID));
        }
        
        for(Optional<? extends Block> leaves : bopFruitLeaves)
        {
            FarmingRegistry.registerHarvestable(new Harvestable(leaves.get().blockID, HarvestType.TreeLeaf));
            FarmingRegistry.registerFruit(new FruitLeaves(leaves.get().blockID));
        }
    }

    private static void registerSludgeDrops()
    {
        FarmingRegistry.registerSludgeDrop(15, BlockReferences.getBlockItemStack("driedDirt"));
        FarmingRegistry.registerSludgeDrop(15, BlockReferences.getBlockItemStack("hardSand"));
        FarmingRegistry.registerSludgeDrop(15, BlockReferences.getBlockItemStack("hardDirt"));
        FarmingRegistry.registerSludgeDrop(15, new ItemStack(Items.miscItems.get(), 4, 1));
        FarmingRegistry.registerSludgeDrop(25, new ItemStack(Items.mudball.get(), 4));
    }
    
    private static void registerGrindables()
    {
        FarmingRegistry.registerGrindable(new Grindable(Entities.JungleSpider, new MobDrop[]
                {
                new MobDrop(3, new ItemStack(Item.silk)),
                new MobDrop(1, new ItemStack(Item.spiderEye))
                }));
        FarmingRegistry.registerGrindable(new Grindable(Entities.Rosester, new MobDrop[]
                {
                new MobDrop(1, new ItemStack(Item.chickenRaw)),
                new MobDrop(1, new ItemStack(Item.dyePowder, 1, 1))
                }));
        FarmingRegistry.registerGrindable(new Grindable(Entities.Glob, new MobDrop[]
                {
                new MobDrop(1, new ItemStack(Item.slimeBall)),
                new MobDrop(4, new ItemStack(Items.mudball.get()))
                }));
    }
}
