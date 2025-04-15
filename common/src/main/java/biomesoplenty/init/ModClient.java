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
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
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
        RenderType transparentRenderType = RenderType.cutoutMipped();
        RenderType cutoutRenderType = RenderType.cutout();
        RenderType translucentRenderType = RenderType.translucent();

        RenderHelper.setRenderType(MOSSY_BLACK_SAND, transparentRenderType);
        RenderHelper.setRenderType(WISPJELLY, translucentRenderType);
        RenderHelper.setRenderType(NULL_END_STONE, transparentRenderType);
        RenderHelper.setRenderType(NULL_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(NULL_PLANT, cutoutRenderType);
        RenderHelper.setRenderType(GLOWING_MOSS_BLOCK, cutoutRenderType);
        RenderHelper.setRenderType(GLOWING_MOSS_CARPET, cutoutRenderType);

        RenderHelper.setRenderType(ORIGIN_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(FLOWERING_OAK_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(CYPRESS_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(SNOWBLOSSOM_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(RAINBOW_BIRCH_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(FIR_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(PINE_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(RED_MAPLE_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(ORANGE_MAPLE_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(YELLOW_MAPLE_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(REDWOOD_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(MAHOGANY_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(JACARANDA_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(PALM_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(WILLOW_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(DEAD_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(MAGIC_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(UMBRAN_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(HELLBARK_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(EMPYREAL_LEAVES, transparentRenderType);

        RenderHelper.setRenderType(ORIGIN_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(FLOWERING_OAK_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(CYPRESS_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(SNOWBLOSSOM_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(RAINBOW_BIRCH_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(FIR_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(PINE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(RED_MAPLE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(ORANGE_MAPLE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(YELLOW_MAPLE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(REDWOOD_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(MAHOGANY_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(JACARANDA_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(PALM_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(WILLOW_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(DEAD_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(MAGIC_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(UMBRAN_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(HELLBARK_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(EMPYREAL_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(ROSE, cutoutRenderType);
        RenderHelper.setRenderType(VIOLET, cutoutRenderType);
        RenderHelper.setRenderType(LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(WHITE_LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(PURPLE_WILDFLOWERS, cutoutRenderType);
        RenderHelper.setRenderType(ORANGE_COSMOS, cutoutRenderType);
        RenderHelper.setRenderType(PINK_DAFFODIL, cutoutRenderType);
        RenderHelper.setRenderType(PINK_HIBISCUS, cutoutRenderType);
        RenderHelper.setRenderType(WATERLILY, cutoutRenderType);
        RenderHelper.setRenderType(WHITE_PETALS, cutoutRenderType);
        RenderHelper.setRenderType(GLOWFLOWER, cutoutRenderType);
        RenderHelper.setRenderType(WILTED_LILY, cutoutRenderType);
        RenderHelper.setRenderType(BURNING_BLOSSOM, cutoutRenderType);
        RenderHelper.setRenderType(TALL_LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(TALL_WHITE_LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(BLUE_HYDRANGEA, cutoutRenderType);
        RenderHelper.setRenderType(GOLDENROD, cutoutRenderType);
        RenderHelper.setRenderType(ICY_IRIS, cutoutRenderType);
        RenderHelper.setRenderType(WILLOW_VINE, cutoutRenderType);
        RenderHelper.setRenderType(SPANISH_MOSS, cutoutRenderType);
        RenderHelper.setRenderType(SPANISH_MOSS_PLANT, cutoutRenderType);
        RenderHelper.setRenderType(GLOWWORM_SILK, cutoutRenderType);
        RenderHelper.setRenderType(GLOWWORM_SILK_STRAND, cutoutRenderType);
        RenderHelper.setRenderType(HANGING_COBWEB, cutoutRenderType);
        RenderHelper.setRenderType(HANGING_COBWEB_STRAND, cutoutRenderType);
        RenderHelper.setRenderType(STRINGY_COBWEB, cutoutRenderType);
        RenderHelper.setRenderType(WEBBING, cutoutRenderType);
        RenderHelper.setRenderType(SPROUT, cutoutRenderType);
        RenderHelper.setRenderType(HIGH_GRASS, cutoutRenderType);
        RenderHelper.setRenderType(HIGH_GRASS_PLANT, cutoutRenderType);
        RenderHelper.setRenderType(CLOVER, cutoutRenderType);
        RenderHelper.setRenderType(HUGE_CLOVER_PETAL, cutoutRenderType);
        RenderHelper.setRenderType(HUGE_LILY_PAD, cutoutRenderType);
        RenderHelper.setRenderType(RED_MAPLE_LEAF_PILE, cutoutRenderType);
        RenderHelper.setRenderType(ORANGE_MAPLE_LEAF_PILE, cutoutRenderType);
        RenderHelper.setRenderType(YELLOW_MAPLE_LEAF_PILE, cutoutRenderType);
        RenderHelper.setRenderType(DUNE_GRASS, cutoutRenderType);
        RenderHelper.setRenderType(DESERT_GRASS, cutoutRenderType);
        RenderHelper.setRenderType(DEAD_GRASS, cutoutRenderType);
        RenderHelper.setRenderType(TUNDRA_SHRUB, cutoutRenderType);
        RenderHelper.setRenderType(CATTAIL, cutoutRenderType);
        RenderHelper.setRenderType(BARLEY, cutoutRenderType);
        RenderHelper.setRenderType(SEA_OATS, cutoutRenderType);
        RenderHelper.setRenderType(REED, cutoutRenderType);
        RenderHelper.setRenderType(WATERGRASS, cutoutRenderType);
        RenderHelper.setRenderType(DEAD_BRANCH, cutoutRenderType);
        RenderHelper.setRenderType(TINY_CACTUS, cutoutRenderType);
        RenderHelper.setRenderType(BRAMBLE, cutoutRenderType);
        RenderHelper.setRenderType(BRAMBLE_LEAVES, cutoutRenderType);
        RenderHelper.setRenderType(TOADSTOOL, cutoutRenderType);
        RenderHelper.setRenderType(GLOWSHROOM, cutoutRenderType);
        RenderHelper.setRenderType(PUS_BUBBLE, cutoutRenderType);
        RenderHelper.setRenderType(FLESH_TENDONS, cutoutRenderType);
        RenderHelper.setRenderType(FLESH_TENDONS_STRAND, cutoutRenderType);
        RenderHelper.setRenderType(EYEBULB, cutoutRenderType);
        RenderHelper.setRenderType(HAIR, cutoutRenderType);
        RenderHelper.setRenderType(BRIMSTONE_BUD, cutoutRenderType);
        RenderHelper.setRenderType(BRIMSTONE_CLUSTER, cutoutRenderType);
        RenderHelper.setRenderType(ROSE_QUARTZ_CLUSTER, cutoutRenderType);
        RenderHelper.setRenderType(LARGE_ROSE_QUARTZ_BUD, cutoutRenderType);
        RenderHelper.setRenderType(MEDIUM_ROSE_QUARTZ_BUD, cutoutRenderType);
        RenderHelper.setRenderType(SMALL_ROSE_QUARTZ_BUD, cutoutRenderType);
        RenderHelper.setRenderType(BLACKSTONE_SPINES, cutoutRenderType);
        RenderHelper.setRenderType(BLACKSTONE_BULB, cutoutRenderType);
        RenderHelper.setRenderType(ENDBLOOM, cutoutRenderType);
        RenderHelper.setRenderType(ENDERPHYTE, cutoutRenderType);
        RenderHelper.setRenderType(LUMALOOP, cutoutRenderType);
        RenderHelper.setRenderType(LUMALOOP_PLANT, cutoutRenderType);
        RenderHelper.setRenderType(SPIDER_EGG, cutoutRenderType);

        RenderHelper.setRenderType(FIR_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(PINE_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(MAPLE_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(REDWOOD_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(MAHOGANY_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(JACARANDA_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(PALM_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(WILLOW_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(DEAD_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(MAGIC_DOOR, translucentRenderType);
        RenderHelper.setRenderType(UMBRAN_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(HELLBARK_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(EMPYREAL_DOOR, cutoutRenderType);
        RenderHelper.setRenderType(FIR_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(PINE_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(MAPLE_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(REDWOOD_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(MAHOGANY_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(JACARANDA_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(PALM_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(WILLOW_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(DEAD_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(MAGIC_TRAPDOOR, translucentRenderType);
        RenderHelper.setRenderType(UMBRAN_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(HELLBARK_TRAPDOOR, cutoutRenderType);
        RenderHelper.setRenderType(EMPYREAL_TRAPDOOR, cutoutRenderType);

        RenderHelper.setRenderType(POTTED_ORIGIN_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_FLOWERING_OAK_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_CYPRESS_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_SNOWBLOSSOM_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_RAINBOW_BIRCH_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_FIR_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_PINE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_RED_MAPLE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_ORANGE_MAPLE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_YELLOW_MAPLE_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_REDWOOD_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_MAHOGANY_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_JACARANDA_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_PALM_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_WILLOW_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_DEAD_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_MAGIC_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_UMBRAN_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_HELLBARK_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_EMPYREAL_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_ROSE, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_VIOLET, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_WHITE_LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_ORANGE_COSMOS, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_PINK_DAFFODIL, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_PINK_HIBISCUS, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_GLOWFLOWER, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_WILTED_LILY, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_BURNING_BLOSSOM, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_ENDBLOOM, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_SPROUT, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_TINY_CACTUS, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_TOADSTOOL, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_GLOWSHROOM, cutoutRenderType);

        RenderHelper.setRenderType(BLOOD, translucentRenderType);
        RenderHelper.setRenderType(BOPFluids.BLOOD, translucentRenderType);
        RenderHelper.setRenderType(BOPFluids.FLOWING_BLOOD, translucentRenderType);

        RenderHelper.setRenderType(LIQUID_NULL, translucentRenderType);
        RenderHelper.setRenderType(BOPFluids.LIQUID_NULL, translucentRenderType);
        RenderHelper.setRenderType(BOPFluids.LIQUID_NULL, translucentRenderType);
    }

    public static void registerLayerDefinitions(RegisterLayerDefinitionsEvent event)
    {
        // Register boat layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBoatModel();
        LayerDefinition chestBoatLayerDefinition = BoatModel.createChestBoatModel();

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
                BOPBlocks.MOSSY_BLACK_SAND, BOPBlocks.SPROUT, BOPBlocks.HIGH_GRASS, BOPBlocks.HIGH_GRASS_PLANT, BOPBlocks.CLOVER,
                BOPBlocks.HUGE_CLOVER_PETAL, BOPBlocks.BARLEY, BOPBlocks.WATERGRASS, BOPBlocks.POTTED_SPROUT);

        //Foliage Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.FOLIAGE_DEFAULT,
                BOPBlocks.FLOWERING_OAK_LEAVES, BOPBlocks.PINE_LEAVES, BOPBlocks.MAHOGANY_LEAVES, BOPBlocks.PALM_LEAVES, BOPBlocks.WILLOW_LEAVES,
                BOPBlocks.WILLOW_VINE, BOPBlocks.BRAMBLE_LEAVES);

        //Dry Foliage Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageDryFoliageColor(world, pos) : -10732494,
                BOPBlocks.DEAD_LEAVES, BOPBlocks.DESERT_GRASS);

        //Rainbow Birch Leaf Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? getRainbowBirchColor(world, pos) : FoliageColor.FOLIAGE_DEFAULT,
                BOPBlocks.RAINBOW_BIRCH_LEAVES);

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
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.SNOWBLOSSOM_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.RED_MAPLE_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.ORANGE_MAPLE_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.YELLOW_MAPLE_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.FIR_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.REDWOOD_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.CYPRESS_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.MAGIC_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.UMBRAN_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.HELLBARK_LEAVES, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new NormalLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
    }

    public static void registerWoodTypes()
    {
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

    public static int getRainbowBirchColor(BlockAndTintGetter world, BlockPos pos)
    {
        float saturation;
        if (world.getBlockState(pos.above()).is(BlockTags.SNOW))
        {
            saturation = 0.3F;
        }
        else if (world.getBlockState(pos.above(2)).is(BlockTags.SNOW))
        {
            saturation = 0.45F;
        }
        else
        {
            saturation = 0.6F;
        }

        Color foliage = Color.getHSBColor(((float)pos.getX() + (float)pos.getY() + (float)pos.getZ() + (Mth.sin(((float)pos.getX() + (float)pos.getY() + (float)pos.getZ()) / 16) * 16) % 100) / 100, saturation, 1.0F);

        return foliage.getRGB();
    }
}
