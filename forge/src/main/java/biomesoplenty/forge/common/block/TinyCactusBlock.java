/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

public class TinyCactusBlock extends BushBlock implements IPlantable
{
    public static final MapCodec<TinyCactusBlock> CODEC = simpleCodec(TinyCactusBlock::new);
    protected static final VoxelShape NORMAL = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D);

    public TinyCactusBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public MapCodec<TinyCactusBlock> codec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return NORMAL;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());

        return groundState.is(BlockTags.DIRT) || groundState.is(BlockTags.SAND);
    }

    @Override
    public void entityInside(BlockState p_51148_, Level p_51149_, BlockPos p_51150_, Entity p_51151_)
    {
        if (p_51151_ instanceof Player)
        {
            Player playerEntity = (Player) p_51151_;
            playerEntity.hurt(p_51149_.damageSources().cactus(), 1.0F);
        }
    }

//    @Override
//    public PlantType getPlantType(BlockGetter world, BlockPos pos)
//    {
//        return PlantType.PLAINS;
//    }
}
