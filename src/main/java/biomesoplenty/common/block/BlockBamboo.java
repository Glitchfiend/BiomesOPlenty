/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBamboo extends BlockDecoration
{
    
    // add properties
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { AGE });}  
    
    public BlockBamboo()
    {
        this.setHardness(0.2F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockBoundsByRadiusAndHeight(0.1875F , 1.0F); 
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));     
    }
    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();
        // can place on top of more bamboo
        if (groundBlock == this)
        {
            return true;
        }
        // can place on various soil blocks
        if (groundBlock == Blocks.dirt || groundBlock == Blocks.farmland || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.grass)
        {
            return true;
        }
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
                case LOAMY: case SANDY: case SILTY:
                    return true;
                default:
                    break;
            }
        }
        return false;   
    }
    
    
    // map from state to meta and vice verca
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    

    // restore collision box removed by BlockDecoration
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        return new AxisAlignedBB((double) pos.getX() + this.minX, (double) pos.getY() + this.minY, (double) pos.getZ() + this.minZ, (double) pos.getX() + this.maxX, (double) pos.getY() + this.maxY, (double) pos.getZ() + this.maxZ);
    }
    
    // check this block is still able to remain on update ticks
    // if it is the bamboo also grows upward
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.checkAndDropBlock(worldIn, pos, state) && worldIn.isAirBlock(pos.up()))
        {
            int treeHeight = 1;
            while (worldIn.getBlockState(pos.down(treeHeight)).getBlock() == this) {++treeHeight;}
            if (treeHeight < 3)
            {
                int age = ((Integer)state.getValue(AGE)).intValue();
                if (true)
                //if (age == 15)
                {
                    // it's old enough to grow
                    worldIn.setBlockState(pos.up(), this.getDefaultState());
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 4);
                }
                else
                {
                    // increase AGE
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 4);
                }
            }
        }
    }
    
}
