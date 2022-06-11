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
import biomesoplenty.common.entity.ChestBoatBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BiomesOPlenty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRendererHandler
{
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        // Register boat layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel(false);
        LayerDefinition chestBoatLayerDefinition = BoatModel.createBodyModel(true);

        for (BoatBOP.ModelType type : BoatBOP.ModelType.values())
        {
            ForgeHooksClient.registerLayerDefinition(BoatRendererBOP.createBoatModelName(type), () -> boatLayerDefinition);
            ForgeHooksClient.registerLayerDefinition(BoatRendererBOP.createChestBoatModelName(type), () -> chestBoatLayerDefinition);
        }

        // Register block entity renderers
        event.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>)BOPBlockEntities.SIGN.get(), SignRenderer::new);

        // Register entity renderers
        event.registerEntityRenderer((EntityType<BoatBOP>) BOPEntities.BOAT.get(), context -> new BoatRendererBOP(context, false));
        event.registerEntityRenderer((EntityType<ChestBoatBOP>) BOPEntities.CHEST_BOAT.get(), context -> new BoatRendererBOP(context, true));
    }
}
