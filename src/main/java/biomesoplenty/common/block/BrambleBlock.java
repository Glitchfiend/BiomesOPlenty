/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BrambleBlock extends SixWayBlock
{
    public BrambleBlock(Block.Properties builder)
    {
        super(0.25F, builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)));
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.makeConnections(context.getWorld(), context.getPos());
    }

    public BlockState makeConnections(IBlockReader reader, BlockPos pos)
    {
        BlockState block = reader.getBlockState(pos.down());
        BlockState block1 = reader.getBlockState(pos.up());
        BlockState block2 = reader.getBlockState(pos.north());
        BlockState block3 = reader.getBlockState(pos.east());
        BlockState block4 = reader.getBlockState(pos.south());
        BlockState block5 = reader.getBlockState(pos.west());
        return this.getDefaultState()
        		.with(DOWN, Boolean.valueOf(block.getBlock() == this || Block.isOpaque(block.getCollisionShape(reader, pos.down()))))
        		.with(UP, Boolean.valueOf(block1.getBlock() == this || Block.isOpaque(block1.getCollisionShape(reader, pos.up()))))
        		.with(NORTH, Boolean.valueOf(block2.getBlock() == this || Block.isOpaque(block2.getCollisionShape(reader, pos.north()))))
        		.with(EAST, Boolean.valueOf(block3.getBlock() == this || Block.isOpaque(block3.getCollisionShape(reader, pos.east()))))
        		.with(SOUTH, Boolean.valueOf(block4.getBlock() == this || Block.isOpaque(block4.getCollisionShape(reader, pos.south()))))
        		.with(WEST, Boolean.valueOf(block5.getBlock() == this || Block.isOpaque(block5.getCollisionShape(reader, pos.west()))));
    }

     @Override
     public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
     {
    	Block block = facingState.getBlock();
     	boolean flag = block == this || Block.isOpaque(facingState.getCollisionShape(worldIn, facingPos));
     	return stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(flag));
     }

     @Override
     public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
     {
         if (entityIn instanceof PlayerEntity)
         {
             PlayerEntity playerEntity = (PlayerEntity) entityIn;
             playerEntity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
         }
      }

     @Override
     public BlockRenderLayer getRenderLayer()
     {
        return BlockRenderLayer.CUTOUT;
     }

     @Override
     protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
     {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
     }

     @Override
     public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
     {
        return false;
     }
}
