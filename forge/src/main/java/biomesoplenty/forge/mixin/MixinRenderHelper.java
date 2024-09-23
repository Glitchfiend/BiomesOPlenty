/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.mixin;

import biomesoplenty.glitch.util.RenderHelper;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = RenderHelper.class, remap = false)
public class MixinRenderHelper
{
  @Overwrite
  public static void setRenderType(Block block, RenderType type)
  {
    ItemBlockRenderTypes.setRenderLayer(block, type);
  }

  @Overwrite
  public static void setRenderType(Fluid fluid, RenderType type)
  {
    ItemBlockRenderTypes.setRenderLayer(fluid, type);
  }

  @Overwrite
  public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider)
  {
    BlockEntityRenderers.register(blockEntityType, blockEntityRendererProvider);
  }

  @Overwrite
  public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererProvider)
  {
    EntityRenderers.register(entityType, entityRendererProvider);
  }

  @Overwrite
  public static void registerLayerDefinition(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier)
  {
    ForgeHooksClient.registerLayerDefinition(layerLocation, supplier);
  }
}