/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSixWay;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockBramble extends BlockSixWay
{
    public BlockBramble(Block.Properties builder)
    {
        super(0.25F, builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)));
    }
    
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.makeConnections(context.getWorld(), context.getPos());
    }

    public IBlockState makeConnections(IBlockReader p_196497_1_, BlockPos p_196497_2_)
    {
        IBlockState block = p_196497_1_.getBlockState(p_196497_2_.down());
        IBlockState block1 = p_196497_1_.getBlockState(p_196497_2_.up());
        IBlockState block2 = p_196497_1_.getBlockState(p_196497_2_.north());
        IBlockState block3 = p_196497_1_.getBlockState(p_196497_2_.east());
        IBlockState block4 = p_196497_1_.getBlockState(p_196497_2_.south());
        IBlockState block5 = p_196497_1_.getBlockState(p_196497_2_.west());
        return this.getDefaultState()
        		.with(DOWN, Boolean.valueOf(block.getBlock() == this || block.isFullCube()))
        		.with(UP, Boolean.valueOf(block1.getBlock() == this || block1.isFullCube()))
        		.with(NORTH, Boolean.valueOf(block2.getBlock() == this || block2.isFullCube()))
        		.with(EAST, Boolean.valueOf(block3.getBlock() == this || block3.isFullCube()))
        		.with(SOUTH, Boolean.valueOf(block4.getBlock() == this || block4.isFullCube()))
        		.with(WEST, Boolean.valueOf(block5.getBlock() == this || block5.isFullCube()));
    }

     /**
      * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
      * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
      * returns its solidified counterpart.
      * Note that this method should ideally consider only the specific face passed in.
      */
     @Override
     public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
     {
    	Block block = facingState.getBlock();
     	boolean flag = block == this || block.isFullCube(facingState);
     	return (IBlockState)stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(flag));
     }

     /**
      * @deprecated call via {@link IBlockState#isFullCube()} whenever possible. Implementing/overriding is fine.
      */
     @Override
     public boolean isFullCube(IBlockState state)
     {
        return false;
     }
     
     @Override
     public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
         entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
      }

     /**
      * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
      * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
      */
     @Override
     public BlockRenderLayer getRenderLayer()
     {
        return BlockRenderLayer.CUTOUT;
     }

     @Override
     protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
     {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
     }

     /**
      * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
      * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
      * <p>
      * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that does
      * not fit the other descriptions and will generally cause other things not to connect to the face.
      * 
      * @return an approximation of the form of the given face
      * @deprecated call via {@link IBlockState#getBlockFaceShape(IBlockAccess,BlockPos,EnumFacing)} whenever possible.
      * Implementing/overriding is fine.
      */
     @Override
     public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face)
     {
        return BlockFaceShape.UNDEFINED;
     }

     @Override
     public boolean allowsMovement(IBlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
     {
        return false;
     }
}
