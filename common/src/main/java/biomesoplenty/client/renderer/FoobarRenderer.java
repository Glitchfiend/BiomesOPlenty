/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.block.entity.FoobarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class FoobarRenderer implements BlockEntityRenderer<FoobarBlockEntity>
{
    private final BlockRenderDispatcher dispatcher;

    public FoobarRenderer(BlockEntityRendererProvider.Context context)
    {
        this.dispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(FoobarBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay)
    {
        Level level = blockEntity.getLevel();
        BlockPos pos = blockEntity.getBlockPos();
        BlockState renderState = getRenderState(RandomSource.create(Mth.getSeed(pos)), blockEntity);
        this.dispatcher.getModelRenderer().tesselateBlock(level, this.dispatcher.getBlockModel(renderState), renderState, pos, poseStack, buffer.getBuffer(ItemBlockRenderTypes.getRenderType(renderState, true)), false, RandomSource.create(), renderState.getSeed(pos), OverlayTexture.NO_OVERLAY);
    }

    private BlockState getRenderState(RandomSource random, FoobarBlockEntity blockEntity)
    {
        Level level = blockEntity.getLevel();
        Registry<Block> blockRegistry = level.registryAccess().registryOrThrow(Registries.BLOCK);

        int index = (random.nextInt(blockRegistry.size()) + (int) (level.getGameTime() / 5L)) % blockRegistry.size();
        BlockState state = Blocks.AIR.defaultBlockState();

        while (state.getRenderShape() != RenderShape.MODEL)
        {
            state = blockRegistry.entrySet().stream().skip(index).map(Map.Entry::getValue).findFirst().orElseThrow().defaultBlockState();
            index = (index + 1) % blockRegistry.size();
        }

        return state;
    }
}
