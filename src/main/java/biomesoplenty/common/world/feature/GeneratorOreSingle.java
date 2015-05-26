/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;

import com.google.common.base.Predicate;

public class GeneratorOreSingle extends GeneratorOreBase
{
    private IBlockState state;
    private Predicate replace;
    
    public GeneratorOreSingle()
    {
        // default
        this(Blocks.emerald_ore.getDefaultState(), 12, 4, 32);
    }
    
    public GeneratorOreSingle(IBlockState state, int amountPerChunk, int minHeight, int maxHeight)
    {
        super(amountPerChunk, minHeight, maxHeight);
        
        this.state = state;
        this.replace = BlockHelper.forBlock(Blocks.stone);
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock().isReplaceableOreGen(world, pos, this.replace))
        {
            return world.setBlockState(pos, this.state, 2);
        }
        
        return false;
    }
    
    @Override
    public void configure(WrappedJsonObject conf)
    {
        super.configure(conf);
        
        this.state = conf.getBlockState("state", this.state);
        this.replace = BlockHelper.forBlock(Blocks.stone);
    }
    
}
