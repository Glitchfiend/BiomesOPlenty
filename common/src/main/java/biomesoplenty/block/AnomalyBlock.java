/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.block.entity.AnomalyBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AnomalyBlock extends BaseEntityBlock
{
    public static final EnumProperty<AnomalyType> ANOMALY_TYPE = EnumProperty.create("type", AnomalyType.class);
    public static final MapCodec<AnomalyBlock> CODEC = simpleCodec(AnomalyBlock::new);
    protected static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public AnomalyBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ANOMALY_TYPE, AnomalyType.STABLE));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49479_)
    {
        AnomalyType type;
        switch (p_49479_.getLevel().random.nextInt(4))
        {
            default:
            case 0:
                type = AnomalyBlock.AnomalyType.VOLATILE;
                break;

            case 1:
                type = AnomalyBlock.AnomalyType.QUIRKY;
                break;

            case 2:
                type = AnomalyBlock.AnomalyType.UNSTABLE;
                break;

            case 3:
                type = AnomalyBlock.AnomalyType.STABLE;
                break;
        }
        return this.defaultBlockState().setValue(ANOMALY_TYPE, type);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos)
    {
        return state.getValue(ANOMALY_TYPE) != AnomalyType.STABLE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return state.getValue(ANOMALY_TYPE) == AnomalyType.STABLE ? RenderShape.MODEL : RenderShape.INVISIBLE;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter getter, BlockPos pos)
    {
        if (state.getValue(ANOMALY_TYPE) == AnomalyType.STABLE) return Shapes.block();
        else return Shapes.empty();
    }

    @Override
    public void entityInside(BlockState p_51148_, Level p_51149_, BlockPos p_51150_, Entity p_51151_)
    {
        LivingEntity entity = (LivingEntity)p_51151_;
        if (!p_51149_.isClientSide && entity instanceof LivingEntity && !entity.fireImmune())
        {
            for (int $$4 = 0; $$4 < 16; ++$$4) {
                double $$5 = entity.getX() + (p_51149_.getRandom().nextDouble() - p_51149_.getRandom().nextDouble()) * 2048.0;
                double $$6 = Mth.clamp(entity.getY() + (double) (p_51149_.getRandom().nextInt(16) - 8), (double) p_51149_.getMinBuildHeight(), (double) (p_51149_.getMinBuildHeight() + ((ServerLevel) p_51149_).getLogicalHeight() - 1));
                double $$7 = entity.getZ() + (p_51149_.getRandom().nextDouble() - p_51149_.getRandom().nextDouble()) * 2048.0;
                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                Vec3 $$8 = entity.position();
                if (entity.randomTeleport($$5, $$6, $$7, true)) {
                    p_51149_.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of(entity));
                    SoundSource $$12;
                    SoundEvent $$11;
                    if (entity instanceof Fox)
                    {
                        $$11 = SoundEvents.FOX_TELEPORT;
                        $$12 = SoundSource.NEUTRAL;
                    }
                    else
                    {
                        $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        $$12 = SoundSource.PLAYERS;
                    }

                    p_51149_.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), $$11, $$12);
                    entity.resetFallDistance();
                    break;
                }
            }
        }
    }

    @Override
    public boolean isPathfindable(BlockState p_154341_, BlockGetter p_154342_, BlockPos p_154343_, PathComputationType p_154344_)
    {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(ANOMALY_TYPE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new AnomalyBlockEntity(pos, state);
    }

    public enum AnomalyType implements StringRepresentable
    {
        VOLATILE("volatile"), QUIRKY("quirky"), UNSTABLE("unstable"), STABLE("stable");

        private final String name;

        AnomalyType(String $$0) {
            this.name = $$0;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
