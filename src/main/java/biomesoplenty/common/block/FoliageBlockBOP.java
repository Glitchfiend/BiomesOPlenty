/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPBaseFeatures;
import biomesoplenty.common.worldgen.feature.BOPVegetationFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class FoliageBlockBOP extends BushBlock implements BonemealableBlock, IPlantable
{
    protected static final VoxelShape NORMAL = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected static final VoxelShape SHORT = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);

    public FoliageBlockBOP(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.DESERT_GRASS || block == BOPBlocks.CLOVER)
        {
            return SHORT;
        }

        return NORMAL;
    }

    @Override
    public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack)
    {
        if (!worldIn.isClientSide && stack.getItem() == Items.SHEARS)
        {
            player.awardStat(Stats.BLOCK_MINED.get(this));
            player.causeFoodExhaustion(0.005F);
            this.popResource(worldIn, pos, new ItemStack(this));
        }
        else
        {
            super.playerDestroy(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());
        Block ground = groundState.getBlock();

        if (this == BOPBlocks.SPROUT)
        {
            return groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP) || super.canSurvive(state, worldIn, pos);
        }
        if (this == BOPBlocks.DUNE_GRASS)
        {
            return ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.WHITE_SAND || ground == BOPBlocks.ORANGE_SAND || ground == BOPBlocks.BLACK_SAND || ground == BOPBlocks.ROOTED_SAND;
        }
        if (this == BOPBlocks.DESERT_GRASS || this == BOPBlocks.DEAD_GRASS)
        {
            return ground == BOPBlocks.DRIED_SALT || ground == Blocks.GRAVEL || ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.WHITE_SAND || ground == BOPBlocks.ORANGE_SAND || ground == BOPBlocks.BLACK_SAND || ground == BOPBlocks.ROOTED_SAND || ground == Blocks.NETHERRACK || super.canSurvive(state, worldIn, pos);
        }

        return super.canSurvive(state, worldIn, pos);
    }

    @Override
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XYZ;
    }

//    @Override
//    public PlantType getPlantType(BlockGetter world, BlockPos pos)
//    {
//        return PlantType.PLAINS;
//    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.CLOVER)
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.CLOVER) { return (double)rand.nextFloat() < 0.4D; }

        return false;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.CLOVER) { this.growHugeClover(world, rand, pos, state); }
    }

    public boolean growHugeClover(ServerLevel world, Random rand, BlockPos pos, BlockState state)
    {
        world.removeBlock(pos, false);
        ConfiguredFeature<NoneFeatureConfiguration, ?> configuredfeature = BOPVegetationFeatures.HUGE_CLOVER.value();

        if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlock(pos, state, 3);
            return false;
        }
    }
}
