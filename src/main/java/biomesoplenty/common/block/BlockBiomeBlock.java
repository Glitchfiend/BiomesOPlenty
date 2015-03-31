/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
/*
package biomesoplenty.common.block;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockBiomeBlock extends BOPBlock
{
    public BlockBiomeBlock() {
        super(Material.glass);
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeGlass);
    }
    
    // TODO: don't understand this method at all.
    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
        {
            for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
            {
                if (biome == null) {continue;}
                
                // TODO: add the other biomes
                // TODO: add areBiomesEqual function for comparing these
                if ( biome.biomeID == BOPBiomes.alps.biomeID )
                {
                    if (worldIn.rand.nextInt(75) == 0)
                    {
                        ItemStack biome_essence = new ItemStack(BOPItems.biome_essence);
                        biome_essence.setTagCompound(new NBTTagCompound());
                        biome_essence.getTagCompound().setInteger("biomeID", biome.biomeID);
                        spawnAsEntity(worldIn, pos, biome_essence);
                    }
                }
            }
        }
    } 
    
}
*/