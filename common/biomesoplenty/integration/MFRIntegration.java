package biomesoplenty.integration;

import biomesoplenty.api.Biomes;
import biomesoplenty.api.BlockReferences;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.integration.minefactoryreloaded.Fertilizable;
import biomesoplenty.integration.minefactoryreloaded.FruitLeaves;
import biomesoplenty.integration.minefactoryreloaded.Harvestable;
import biomesoplenty.integration.minefactoryreloaded.Plantable;

import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.HarvestType;

public class MFRIntegration
{
    protected static void init()
    {
        registerRubberTreeBiomes();
        registerFarmables();
        registerSludgeDrops();
    }

    private static void registerRubberTreeBiomes()
    {
        FactoryRegistry.registerRubberTreeBiome(Biomes.bayou.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.birchForest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.bog.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.borealForest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.deciduousForest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.forestNew.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.grove.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.jungleNew.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.lushSwamp.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.mapleWoods.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.rainforest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.seasonalForest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.shield.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.swamplandNew.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.temperateRainforest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.thicket.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.tropicalRainforest.get().biomeName);
        FactoryRegistry.registerRubberTreeBiome(Biomes.woodland.get().biomeName);
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
            FactoryRegistry.registerHarvestable(new Harvestable(leaves.get().blockID, HarvestType.TreeLeaf));
        }
        
        for(Optional<? extends Block> log : bopLogs)
        {
            FactoryRegistry.registerHarvestable(new Harvestable(log.get().blockID, HarvestType.Tree));
        }
        
        for(Optional<? extends Block> harvestable : bopMiscStandardHarvestables)
        {
            FactoryRegistry.registerHarvestable(new Harvestable(harvestable.get().blockID, HarvestType.Normal));
        }
        
        for(Optional<? extends Block> sapling : bopSaplings)
        {
            FactoryRegistry.registerFertilizable(new Fertilizable(sapling.get().blockID));
            FactoryRegistry.registerPlantable(new Plantable(sapling.get().blockID));
        }
        
        for(Optional<? extends Block> leaves : bopFruitLeaves)
        {
            FactoryRegistry.registerHarvestable(new Harvestable(leaves.get().blockID, HarvestType.TreeLeaf));
            FactoryRegistry.registerFruit(new FruitLeaves(leaves.get().blockID));
        }
    }

    private static void registerSludgeDrops()
    {
        FactoryRegistry.registerSludgeDrop(15, BlockReferences.getBlockItemStack("driedDirt"));
        FactoryRegistry.registerSludgeDrop(15, BlockReferences.getBlockItemStack("hardSand"));
        FactoryRegistry.registerSludgeDrop(15, BlockReferences.getBlockItemStack("hardDirt"));
        FactoryRegistry.registerSludgeDrop(15, new ItemStack(Items.miscItems.get(), 4, 1));
        FactoryRegistry.registerSludgeDrop(25, new ItemStack(Items.mudball.get(), 4));
    }
}
