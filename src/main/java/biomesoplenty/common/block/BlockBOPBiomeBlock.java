/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.biome.BOPBiome;
import biomesoplenty.common.util.biome.BiomeUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BlockBOPBiomeBlock extends BlockBOPGeneric
{   
    
    public BlockBOPBiomeBlock() {
        super(Material.GLASS, SoundType.GLASS);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GLASS);
    }
    
    // list of biomes which have an associated essence (possible drops when this block is broken)
    private static List<Biome> biomesWithEssence;
    
    // generate the list of biomes which have an associated essence
    public List<Biome> getBiomesWithEssence()
    {
        if (biomesWithEssence != null) {return biomesWithEssence;}
        
        biomesWithEssence = new ArrayList<Biome>();
        
        List<Biome> vanillaBiomesToExclude = Arrays.asList(
                Biomes.DEFAULT,
                Biomes.SKY,
                Biomes.HELL,
                Biomes.BEACH,
                Biomes.COLD_BEACH,
                Biomes.STONE_BEACH,
                Biomes.OCEAN,
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_OCEAN,
                Biomes.RIVER,
                Biomes.FROZEN_RIVER,
                Biomes.VOID,
                Biomes.MUSHROOM_ISLAND_SHORE,
                Biomes.DESERT_HILLS,
                Biomes.BIRCH_FOREST_HILLS,
                Biomes.COLD_TAIGA_HILLS,
                Biomes.EXTREME_HILLS_EDGE,
                Biomes.EXTREME_HILLS_WITH_TREES,
                Biomes.FOREST_HILLS,
                Biomes.ICE_MOUNTAINS,
                Biomes.JUNGLE_EDGE,
                Biomes.JUNGLE_HILLS,
                Biomes.MESA_CLEAR_ROCK,
                Biomes.MESA_ROCK,
                Biomes.MUTATED_BIRCH_FOREST,
                Biomes.MUTATED_BIRCH_FOREST_HILLS,
                Biomes.MUTATED_DESERT,
                Biomes.MUTATED_EXTREME_HILLS,
                Biomes.MUTATED_EXTREME_HILLS_WITH_TREES,
                Biomes.MUTATED_FOREST,
                Biomes.MUTATED_ICE_FLATS,
                Biomes.MUTATED_JUNGLE,
                Biomes.MUTATED_JUNGLE_EDGE,
                Biomes.MUTATED_MESA,
                Biomes.MUTATED_MESA_CLEAR_ROCK,
                Biomes.MUTATED_MESA_ROCK,
                Biomes.MUTATED_PLAINS,
                Biomes.MUTATED_REDWOOD_TAIGA,
                Biomes.MUTATED_REDWOOD_TAIGA_HILLS,
                Biomes.MUTATED_ROOFED_FOREST,
                Biomes.MUTATED_SAVANNA,
                Biomes.MUTATED_SAVANNA_ROCK,
                Biomes.MUTATED_SWAMPLAND,
                Biomes.MUTATED_TAIGA,
                Biomes.MUTATED_TAIGA_COLD,
                Biomes.REDWOOD_TAIGA_HILLS,
                Biomes.SAVANNA_PLATEAU,
                Biomes.TAIGA_HILLS);
        
        for (Biome biome : BiomeUtils.getRegisteredBiomes())
        {
            if (biome == null) {continue;}
            if (biome instanceof BOPBiome)
            {
                if (((BOPBiome)biome).hasBiomeEssence()) {biomesWithEssence.add(biome);}
            }
            else
            {
                if (!vanillaBiomesToExclude.contains(biome)) {biomesWithEssence.add(biome);}
            }
        }
        
        return biomesWithEssence;
    }
    
    // drops biome essence items for random biomes
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        List<Biome> biomes = this.getBiomesWithEssence();
        int numToDrop = rand.nextInt(fortune + 1) + 1;
        int numChoices = biomes.size();
        Biome biome;
        
        for (int i = 0; i < numToDrop; i++)
        {
            biome = biomes.get(rand.nextInt(numChoices));
            ItemStack biome_essence = new ItemStack(BOPItems.biome_essence);
            biome_essence.setTagCompound(new NBTTagCompound());
            biome_essence.getTagCompound().setInteger("biomeID", Biome.getIdForBiome(biome));
            ret.add(biome_essence);              
        }

        return ret;
    }

    
}