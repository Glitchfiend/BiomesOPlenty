/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.handler;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.client.renderer.BoatRendererBOP;
import biomesoplenty.common.block.SignBlockEntityBOP;
import biomesoplenty.common.entity.BoatBOP;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityRendererHandler
{
    @SubscribeEvent
    public void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        // Register block entity renderers
        event.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>)BOPBlockEntities.SIGN.get(), SignRenderer::new);

        // Register entity renderers
        event.registerEntityRenderer((EntityType<BoatBOP>) BOPEntities.BOAT.get(), BoatRendererBOP::new);
    }

    @SubscribeEvent
    public void onAddLayers(EntityRenderersEvent.AddLayers event)
    {
        // Register layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel(false);
        for (BoatBOP.ModelType type : BoatBOP.ModelType.values())
        {
            ForgeHooksClient.registerLayerDefinition(BoatRendererBOP.createBoatModelName(type), () -> boatLayerDefinition);
        }
    }
}
