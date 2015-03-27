/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockCrystal extends BOPBlock
{
    public BlockCrystal() {
        super(Material.glass);
        this.setHardness(0.15F);
        this.setResistance(5.0F);
        this.setLightLevel(1.0F);
        this.setStepSound(Block.soundTypeGlass);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.crystal_shard;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }
    
}