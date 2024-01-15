/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.block.entity.AnomalyBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

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
