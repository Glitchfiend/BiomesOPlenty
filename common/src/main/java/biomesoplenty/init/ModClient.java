/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.api.block.BOPWoodTypes;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.block.HangingSignBlockEntityBOP;
import biomesoplenty.block.entity.AnomalyBlockEntity;
import biomesoplenty.block.entity.SignBlockEntityBOP;
import biomesoplenty.client.renderer.AnomalyRenderer;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.particle.*;
import glitchcore.event.EventManager;
import glitchcore.event.client.RegisterColorsEvent;
import glitchcore.event.client.RegisterLayerDefinitionsEvent;
import glitchcore.event.client.RegisterParticleSpritesEvent;
import glitchcore.event.client.RegisterRenderersEvent;
import glitchcore.util.RenderHelper;
import glitchcore.util.SheetHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

import static biomesoplenty.api.block.BOPBlocks.*;

public class ModClient
{
    public static void setup()
    {
        setupRenderTypes();
        registerWoodTypes();
    }

    public static void addClientHandlers()
    {
        // Coloring
        EventManager.addListener(ModClient::registerBlockColors);

        // Particles
        EventManager.addListener(ModClient::registerParticleSprites);

        // Renderers
        EventManager.addListener(ModClient::registerLayerDefinitions);
        EventManager.addListener(ModClient::registerRenderers);
    }

    public static void setupRenderTypes()
    {
        RenderHelper.setRenderType(MOSSY_BLACK_SAND, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(WISPJELLY, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(NULL_END_STONE, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(NULL_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(NULL_PLANT, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GLOWING_MOSS_BLOCK, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GLOWING_MOSS_CARPET, ChunkSectionLayer.CUTOUT);

        RenderHelper.setRenderType(FLOWERING_OAK_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(CYPRESS_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(SNOWBLOSSOM_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(ORIGIN_OAK_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(FIR_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(PINE_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(RED_MAPLE_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(ORANGE_MAPLE_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(YELLOW_MAPLE_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(REDWOOD_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(MAHOGANY_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(JACARANDA_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(PALM_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(WILLOW_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(DEAD_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(MAGIC_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(UMBRAN_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(HELLBARK_LEAVES, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(EMPYREAL_LEAVES, ChunkSectionLayer.TRANSLUCENT);

        RenderHelper.setRenderType(FLOWERING_OAK_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(CYPRESS_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SNOWBLOSSOM_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORIGIN_OAK_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(FIR_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PINE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(RED_MAPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORANGE_MAPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(YELLOW_MAPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(REDWOOD_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAHOGANY_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(JACARANDA_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PALM_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WILLOW_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DEAD_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAGIC_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(UMBRAN_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HELLBARK_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(EMPYREAL_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(FLOWER_BUD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORIGIN_DANDELION, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORIGIN_ROSE, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MARIGOLD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(VIOLET, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(LAVENDER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WHITE_LAVENDER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PURPLE_WILDFLOWERS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORANGE_COSMOS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PINK_DAFFODIL, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PINK_HIBISCUS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WATERLILY, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WHITE_PETALS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GLOWFLOWER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WILTED_LILY, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BURNING_BLOSSOM, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(TALL_LAVENDER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(TALL_WHITE_LAVENDER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BLUE_HYDRANGEA, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GOLDENROD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ICY_IRIS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WILLOW_VINE, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SPANISH_MOSS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SPANISH_MOSS_PLANT, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GLOWWORM_SILK, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GLOWWORM_SILK_STRAND, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HANGING_COBWEB, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HANGING_COBWEB_STRAND, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(STRINGY_COBWEB, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WEBBING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SPROUT, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HIGH_GRASS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HIGH_GRASS_PLANT, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(CLOVER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HUGE_CLOVER_PETAL, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HUGE_LILY_PAD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(RED_MAPLE_LEAF_LITTER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORANGE_MAPLE_LEAF_LITTER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(YELLOW_MAPLE_LEAF_LITTER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DUNE_GRASS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DESERT_GRASS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DEAD_GRASS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(TUNDRA_SHRUB, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(CATTAIL, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BARLEY, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SEA_OATS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(REED, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WATERGRASS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DEAD_BRANCH, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(TINY_CACTUS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BRAMBLE, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BRAMBLE_LEAVES, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(TOADSTOOL, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(GLOWSHROOM, ChunkSectionLayer.CUTOUT);
        //RenderHelper.setRenderType(VOIDCAP, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PUS_BUBBLE, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(FLESH_TENDONS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(FLESH_TENDONS_STRAND, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(EYEBULB, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HAIR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BRIMSTONE_BUD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BRIMSTONE_CLUSTER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ROSE_QUARTZ_CLUSTER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(LARGE_ROSE_QUARTZ_BUD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MEDIUM_ROSE_QUARTZ_BUD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SMALL_ROSE_QUARTZ_BUD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BLACKSTONE_SPINES, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(BLACKSTONE_BULB, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ENDBLOOM, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ENDERPHYTE, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(LUMALOOP, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(LUMALOOP_PLANT, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(SPIDER_EGG, ChunkSectionLayer.CUTOUT);

        RenderHelper.setRenderType(ORIGIN_OAK_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(FIR_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PINE_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAPLE_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(REDWOOD_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAHOGANY_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(JACARANDA_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PALM_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WILLOW_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DEAD_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAGIC_DOOR, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(UMBRAN_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HELLBARK_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(EMPYREAL_DOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(ORIGIN_OAK_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(FIR_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PINE_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAPLE_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(REDWOOD_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAHOGANY_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(JACARANDA_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(PALM_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(WILLOW_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(DEAD_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(MAGIC_TRAPDOOR, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(UMBRAN_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(HELLBARK_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(EMPYREAL_TRAPDOOR, ChunkSectionLayer.CUTOUT);

        RenderHelper.setRenderType(POTTED_FLOWERING_OAK_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_CYPRESS_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_SNOWBLOSSOM_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_ORIGIN_OAK_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_FIR_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_PINE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_RED_MAPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_ORANGE_MAPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_YELLOW_MAPLE_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_REDWOOD_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_MAHOGANY_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_JACARANDA_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_PALM_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_WILLOW_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_DEAD_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_MAGIC_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_UMBRAN_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_HELLBARK_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_EMPYREAL_SAPLING, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_FLOWER_BUD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_ORIGIN_DANDELION, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_ORIGIN_ROSE, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_MARIGOLD, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_VIOLET, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_LAVENDER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_WHITE_LAVENDER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_ORANGE_COSMOS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_PINK_DAFFODIL, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_PINK_HIBISCUS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_GLOWFLOWER, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_WILTED_LILY, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_BURNING_BLOSSOM, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_ENDBLOOM, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_SPROUT, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_TINY_CACTUS, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_TOADSTOOL, ChunkSectionLayer.CUTOUT);
        RenderHelper.setRenderType(POTTED_GLOWSHROOM, ChunkSectionLayer.CUTOUT);
        //RenderHelper.setRenderType(POTTED_VOIDCAP, ChunkSectionLayer.CUTOUT);

        RenderHelper.setRenderType(BLOOD, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(BOPFluids.BLOOD, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(BOPFluids.FLOWING_BLOOD, ChunkSectionLayer.TRANSLUCENT);

        RenderHelper.setRenderType(LIQUID_NULL, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(BOPFluids.LIQUID_NULL, ChunkSectionLayer.TRANSLUCENT);
        RenderHelper.setRenderType(BOPFluids.LIQUID_NULL, ChunkSectionLayer.TRANSLUCENT);
    }

    public static void registerLayerDefinitions(RegisterLayerDefinitionsEvent event)
    {
        // Register boat layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBoatModel();
        LayerDefinition chestBoatLayerDefinition = BoatModel.createChestBoatModel();

        RenderHelper.registerLayerDefinition(ModModelLayers.ORIGIN_OAK_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.ORIGIN_OAK_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.FIR_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.FIR_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.PINE_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.PINE_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.MAPLE_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.MAPLE_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.REDWOOD_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.REDWOOD_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.MAHOGANY_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.MAHOGANY_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.JACARANDA_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.JACARANDA_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.PALM_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.PALM_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.WILLOW_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.WILLOW_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.DEAD_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.DEAD_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.MAGIC_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.MAGIC_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.UMBRAN_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.UMBRAN_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.HELLBARK_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.HELLBARK_CHEST_BOAT, () -> chestBoatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.EMPYREAL_BOAT, () -> boatLayerDefinition);
        RenderHelper.registerLayerDefinition(ModModelLayers.EMPYREAL_CHEST_BOAT, () -> chestBoatLayerDefinition);
    }

    public static void registerRenderers(RegisterRenderersEvent event)
    {
        // Register block entity renderers
        RenderHelper.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>) BOPBlockEntities.SIGN, SignRenderer::new);
        RenderHelper.registerBlockEntityRenderer((BlockEntityType<HangingSignBlockEntityBOP>)BOPBlockEntities.HANGING_SIGN, HangingSignRenderer::new);
        RenderHelper.registerBlockEntityRenderer((BlockEntityType<AnomalyBlockEntity>)BOPBlockEntities.ANOMALY, AnomalyRenderer::new);

        // Register entity renderers
        RenderHelper.registerEntityRenderer(BOPEntities.ORIGIN_OAK_BOAT, context -> new BoatRenderer(context, ModModelLayers.ORIGIN_OAK_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.ORIGIN_OAK_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.ORIGIN_OAK_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.FIR_BOAT, context -> new BoatRenderer(context, ModModelLayers.FIR_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.FIR_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.FIR_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.PINE_BOAT, context -> new BoatRenderer(context, ModModelLayers.PINE_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.PINE_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.PINE_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.MAPLE_BOAT, context -> new BoatRenderer(context, ModModelLayers.MAPLE_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.MAPLE_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.MAPLE_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.REDWOOD_BOAT, context -> new BoatRenderer(context, ModModelLayers.REDWOOD_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.REDWOOD_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.REDWOOD_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.MAHOGANY_BOAT, context -> new BoatRenderer(context, ModModelLayers.MAHOGANY_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.MAHOGANY_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.MAHOGANY_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.JACARANDA_BOAT, context -> new BoatRenderer(context, ModModelLayers.JACARANDA_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.JACARANDA_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.JACARANDA_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.PALM_BOAT, context -> new BoatRenderer(context, ModModelLayers.PALM_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.PALM_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.PALM_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.WILLOW_BOAT, context -> new BoatRenderer(context, ModModelLayers.WILLOW_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.WILLOW_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.WILLOW_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.DEAD_BOAT, context -> new BoatRenderer(context, ModModelLayers.DEAD_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.DEAD_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.DEAD_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.MAGIC_BOAT, context -> new BoatRenderer(context, ModModelLayers.MAGIC_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.MAGIC_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.MAGIC_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.UMBRAN_BOAT, context -> new BoatRenderer(context, ModModelLayers.UMBRAN_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.UMBRAN_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.UMBRAN_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.HELLBARK_BOAT, context -> new BoatRenderer(context, ModModelLayers.HELLBARK_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.HELLBARK_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.HELLBARK_CHEST_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.EMPYREAL_BOAT, context -> new BoatRenderer(context, ModModelLayers.EMPYREAL_BOAT));
        RenderHelper.registerEntityRenderer(BOPEntities.EMPYREAL_CHEST_BOAT, context -> new BoatRenderer(context, ModModelLayers.EMPYREAL_CHEST_BOAT));
    }

    public static void registerBlockColors(RegisterColorsEvent.Block event)
    {
        //Grass Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D),
                BOPBlocks.FLOWER_STEM, BOPBlocks.MOSSY_BLACK_SAND, BOPBlocks.SPROUT, BOPBlocks.HIGH_GRASS, BOPBlocks.HIGH_GRASS_PLANT,
                BOPBlocks.CLOVER, BOPBlocks.HUGE_CLOVER_PETAL, BOPBlocks.BARLEY, BOPBlocks.WATERGRASS, BOPBlocks.POTTED_SPROUT);

        //Foliage Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.FOLIAGE_DEFAULT,
                BOPBlocks.FLOWERING_OAK_LEAVES, BOPBlocks.PINE_LEAVES, BOPBlocks.MAHOGANY_LEAVES, BOPBlocks.PALM_LEAVES, BOPBlocks.WILLOW_LEAVES,
                BOPBlocks.WILLOW_VINE, BOPBlocks.BRAMBLE_LEAVES);

        //Dry Foliage Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageDryFoliageColor(world, pos) : -10732494,
                BOPBlocks.DEAD_LEAVES, BOPBlocks.DESERT_GRASS);

        //Flowerbed Coloring
        event.register((state, world, pos, tintIndex) -> {
                    if (tintIndex != 0) { return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(); }
                    else { return -1; }},
                BOPBlocks.WHITE_PETALS, BOPBlocks.PURPLE_WILDFLOWERS);

        //Lily Pad Coloring
        event.register((state, world, pos, tintIndex) -> {
                    return world != null && pos != null ? 2129968 : 7455580; },
                BOPBlocks.HUGE_LILY_PAD);
    }

    public static void registerParticleSprites(RegisterParticleSpritesEvent event)
    {
        event.registerSpriteSet(ModParticles.DRIPPING_BLOOD, DripParticleBOP.BloodHangProvider::new);
        event.registerSpriteSet(ModParticles.FALLING_BLOOD, DripParticleBOP.BloodFallProvider::new);
        event.registerSpriteSet(ModParticles.LANDING_BLOOD, DripParticleBOP.BloodLandProvider::new);
        event.registerSpriteSet(ModParticles.PUS, PusParticle.Provider::new);
        event.registerSpriteSet(ModParticles.GLOWWORM, GlowwormParticle.Provider::new);
        event.registerSpriteSet(ModParticles.STEAM, SteamParticle.Provider::new);
        event.registerSpriteSet(ModParticles.END_SPORE, EndSporeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.WISP_BUBBLE, WispBubbleParticle.Provider::new);
        event.registerSpriteSet(ModParticles.NULL, NullParticle.Provider::new);
        event.registerSpriteSet(ModParticles.BINARY, BinaryParticle.Provider::new);
        event.registerSpriteSet(ModParticles.JACARANDA_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.SNOWBLOSSOM_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.RED_MAPLE_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.ORANGE_MAPLE_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.YELLOW_MAPLE_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.FIR_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.REDWOOD_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.CYPRESS_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.MAGIC_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.UMBRAN_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.HELLBARK_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
    }

    public static void registerWoodTypes()
    {
        SheetHelper.addWoodType(BOPWoodTypes.ORIGIN_OAK);
        SheetHelper.addWoodType(BOPWoodTypes.FIR);
        SheetHelper.addWoodType(BOPWoodTypes.PINE);
        SheetHelper.addWoodType(BOPWoodTypes.MAPLE);
        SheetHelper.addWoodType(BOPWoodTypes.REDWOOD);
        SheetHelper.addWoodType(BOPWoodTypes.MAHOGANY);
        SheetHelper.addWoodType(BOPWoodTypes.JACARANDA);
        SheetHelper.addWoodType(BOPWoodTypes.PALM);
        SheetHelper.addWoodType(BOPWoodTypes.WILLOW);
        SheetHelper.addWoodType(BOPWoodTypes.DEAD);
        SheetHelper.addWoodType(BOPWoodTypes.MAGIC);
        SheetHelper.addWoodType(BOPWoodTypes.UMBRAN);
        SheetHelper.addWoodType(BOPWoodTypes.HELLBARK);
        SheetHelper.addWoodType(BOPWoodTypes.EMPYREAL);
    }
}
