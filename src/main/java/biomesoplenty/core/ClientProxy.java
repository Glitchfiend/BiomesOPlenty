/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.client.renderer.BoatRendererBOP;
import biomesoplenty.common.block.SignBlockEntityBOP;
import biomesoplenty.common.entity.BoatBOP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ForgeHooksClient;

import java.awt.*;

public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {

    }

    @Override
    public void registerRenderers()
    {
        // Register block entity renderers
        BlockEntityRenderers.register((BlockEntityType< SignBlockEntityBOP>)BOPBlockEntities.SIGN, SignRenderer::new);

        // Register entity renderers
        EntityRenderers.register((EntityType<BoatBOP>)BOPEntities.BOAT, BoatRendererBOP::new);

        // Register layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel();
        for (BoatBOP.ModelType type : BoatBOP.ModelType.values())
        {
            ForgeHooksClient.registerLayerDefinition(BoatRendererBOP.createBoatModelName(type), () -> boatLayerDefinition);
        }
    }
}
