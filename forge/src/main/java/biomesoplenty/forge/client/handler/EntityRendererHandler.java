/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.client.handler;

import biomesoplenty.forge.api.block.BOPBlockEntities;
import biomesoplenty.forge.api.entity.BOPEntities;
import biomesoplenty.forge.client.renderer.BoatRendererBOP;
import biomesoplenty.forge.common.block.HangingSignBlockEntityBOP;
import biomesoplenty.forge.common.block.SignBlockEntityBOP;
import biomesoplenty.forge.common.entity.BoatBOP;
import biomesoplenty.forge.common.entity.ChestBoatBOP;
import biomesoplenty.forge.core.BiomesOPlentyForge;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BiomesOPlentyForge.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRendererHandler
{
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        // Register boat layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel();
        LayerDefinition chestBoatLayerDefinition = ChestBoatModel.createBodyModel();

        for (BoatBOP.ModelType type : BoatBOP.ModelType.values())
        {
            ForgeHooksClient.registerLayerDefinition(BoatRendererBOP.createBoatModelName(type), () -> boatLayerDefinition);
            ForgeHooksClient.registerLayerDefinition(BoatRendererBOP.createChestBoatModelName(type), () -> chestBoatLayerDefinition);
        }

        // Register block entity renderers
        event.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>)BOPBlockEntities.SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<HangingSignBlockEntityBOP>)BOPBlockEntities.HANGING_SIGN.get(), HangingSignRenderer::new);

        // Register entity renderers
        event.registerEntityRenderer((EntityType<BoatBOP>) BOPEntities.BOAT.get(), context -> new BoatRendererBOP(context, false));
        event.registerEntityRenderer((EntityType<ChestBoatBOP>) BOPEntities.CHEST_BOAT.get(), context -> new BoatRendererBOP(context, true));
    }
}
