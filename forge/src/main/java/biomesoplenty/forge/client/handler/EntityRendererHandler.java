/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.client.handler;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.client.renderer.BoatRendererBOP;
import biomesoplenty.block.HangingSignBlockEntityBOP;
import biomesoplenty.block.SignBlockEntityBOP;
import biomesoplenty.entity.BoatBOP;
import biomesoplenty.entity.ChestBoatBOP;
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

@Mod.EventBusSubscriber(modid = BiomesOPlenty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
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
        event.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>)BOPBlockEntities.SIGN, SignRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<HangingSignBlockEntityBOP>)BOPBlockEntities.HANGING_SIGN, HangingSignRenderer::new);

        // Register entity renderers
        event.registerEntityRenderer((EntityType<BoatBOP>) BOPEntities.BOAT, context -> new BoatRendererBOP(context, false));
        event.registerEntityRenderer((EntityType<ChestBoatBOP>) BOPEntities.CHEST_BOAT, context -> new BoatRendererBOP(context, true));
    }
}
