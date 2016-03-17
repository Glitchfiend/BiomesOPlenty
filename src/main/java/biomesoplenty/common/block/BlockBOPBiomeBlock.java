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

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.util.biome.BiomeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockBOPBiomeBlock extends BlockBOPGeneric
{   
    
    public BlockBOPBiomeBlock() {
        super(Material.glass, SoundType.GLASS);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GLASS);
    }
    
    // list of biomes which have an associated essence (possible drops when this block is broken)
    private static List<BiomeGenBase> biomesWithEssence;
    
    // generate the list of biomes which have an associated essence
    public List<BiomeGenBase> getBiomesWithEssence()
    {
        if (biomesWithEssence != null) {return biomesWithEssence;}
        
        biomesWithEssence = new ArrayList<BiomeGenBase>();
        
        List<BiomeGenBase> vanillaBiomesToExclude = Arrays.asList(
            new BiomeGenBase[] {
                Biomes.sky,
                Biomes.hell,
                Biomes.beach,
                Biomes.coldBeach,
                Biomes.stoneBeach,
                Biomes.ocean,
                Biomes.frozenOcean,
                Biomes.deepOcean,
                Biomes.river,
                Biomes.frozenRiver
            }
        );
        
        for (BiomeGenBase biome : BiomeUtils.getRegisteredBiomes())
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

        List<BiomeGenBase> biomes = this.getBiomesWithEssence();
        int numToDrop = rand.nextInt(fortune + 2) + 1;
        int numChoices = biomes.size();
        BiomeGenBase biome;
        
        for (int i = 0; i < numToDrop; i++)
        {
            biome = biomes.get(rand.nextInt(numChoices));
            ItemStack biome_essence = new ItemStack(BOPItems.biome_essence);
            biome_essence.setTagCompound(new NBTTagCompound());
            biome_essence.getTagCompound().setInteger("biomeID", BiomeGenBase.getIdForBiome(biome));
            ret.add(biome_essence);              
        }

        return ret;
    }

    
}