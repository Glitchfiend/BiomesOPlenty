package biomesoplenty.core;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class RenderHelper
{
  public static void setRenderType(Block block, RenderType type)
  {
    throw new UnsupportedOperationException();
  }

  public static void setRenderType(Fluid fluid, RenderType type)
  {
    throw new UnsupportedOperationException();
  }

  public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider)
  {
    throw new UnsupportedOperationException();
  }

  public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererFactory)
  {
    throw new UnsupportedOperationException();
  }

  public static void registerLayerDefinition(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier)
  {
    throw new UnsupportedOperationException();
  }
}