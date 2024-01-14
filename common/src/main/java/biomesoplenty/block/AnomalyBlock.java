/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.block.entity.AnomalyBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AnomalyBlock extends BaseEntityBlock
{
    public static final EnumProperty<AnomalyType> ANOMALY_TYPE = EnumProperty.create("type", AnomalyType.class);
    public static final MapCodec<AnomalyBlock> CODEC = simpleCodec(AnomalyBlock::new);

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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand $$4, BlockHitResult $$5) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            state = state.cycle(ANOMALY_TYPE);
            level.setBlock(pos, state, 2);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return state.getValue(ANOMALY_TYPE) == AnomalyType.STABLE ? RenderShape.MODEL : RenderShape.INVISIBLE;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter getter, BlockPos pos)
    {
        return state.getValue(ANOMALY_TYPE) == AnomalyType.STABLE ? Shapes.block() : Shapes.empty();
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
        VOLATILE("volatile"), QURIRKY("quirky"), UNSTABLE("unstable"), STABLE("stable");

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
