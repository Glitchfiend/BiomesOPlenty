/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPBamboo extends BlockBOPDecoration
{
    protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);

    // add properties
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { AGE });}
    
    
    public BlockBOPBamboo()
    {
        super(Material.wood);
        this.setHardness(0.2F);
        this.setSoundType(SoundType.WOOD);
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));     
    }
    
    
    // bamboo doesn't sustain plants (except more bamboo on top)
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        return direction == EnumFacing.UP && plantable.getPlant(world, pos.offset(direction)).getBlock() == this;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        return BOUNDING_BOX;
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
        if (groundBlock == Blocks.dirt || groundBlock == Blocks.farmland || groundBlock == BOPBlocks.farmland_0 || groundBlock == BOPBlocks.farmland_1 || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.grass)
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
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        //We don't want item drops to have metadata for the age
        return 0;
    }

    // restore collision box removed by BlockDecoration
/*    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        return new AxisAlignedBB((double) pos.getX() + this.minX, (double) pos.getY() + this.minY, (double) pos.getZ() + this.minZ, (double) pos.getX() + this.maxX, (double) pos.getY() + this.maxY, (double) pos.getZ() + this.maxZ);
    }*/
    
    // check this block is still able to remain on update ticks
    // if it is the bamboo also grows upward
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.checkAndDropBlock(worldIn, pos, state) && worldIn.isAirBlock(pos.up()))
        {
            int age = ((Integer)state.getValue(AGE)).intValue();
            int treeHeight = 1;
            while (worldIn.getBlockState(pos.down(treeHeight)).getBlock() == this) {++treeHeight;}

            if (treeHeight < 4)
            {                
                if (age == 15)
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
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.NONE;
    }
    
/*    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        this.setBlockBoundsByRadiusAndHeight(0.2F, 1.0F);
    }*/
    
    
    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }
    

    
}
