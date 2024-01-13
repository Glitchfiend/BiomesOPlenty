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
import biomesoplenty.client.renderer.BoatRendererBOP;
import biomesoplenty.client.renderer.AnomalyRenderer;
import biomesoplenty.entity.BoatBOP;
import biomesoplenty.entity.ChestBoatBOP;
import biomesoplenty.particle.*;
import glitchcore.event.EventManager;
import glitchcore.event.client.RegisterColorsEvent;
import glitchcore.event.client.RegisterParticleSpritesEvent;
import glitchcore.util.RenderHelper;
import glitchcore.util.SheetHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
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
        registerRenderers();
        registerWoodTypes();
    }

    public static void addClientHandlers()
    {
        // Coloring
        EventManager.addListener(ModClient::registerBlockColors);
        EventManager.addListener(ModClient::registerItemColors);

        // Particles
        EventManager.addListener(ModClient::registerParticleSprites);
    }

    public static void setupRenderTypes()
    {
        RenderType transparentRenderType = RenderType.cutoutMipped();
        RenderType cutoutRenderType = RenderType.cutout();
        RenderType translucentRenderType = RenderType.translucent();

        RenderHelper.setRenderType(MOSSY_BLACK_SAND, transparentRenderType);

        RenderHelper.setRenderType(ORIGIN_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(FLOWERING_OAK_LEAVES, transparentRenderType);
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

        RenderHelper.setRenderType(ORIGIN_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(FLOWERING_OAK_SAPLING, cutoutRenderType);
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
        RenderHelper.setRenderType(ROSE, cutoutRenderType);
        RenderHelper.setRenderType(VIOLET, cutoutRenderType);
        RenderHelper.setRenderType(LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(WILDFLOWER, cutoutRenderType);
        RenderHelper.setRenderType(ORANGE_COSMOS, cutoutRenderType);
        RenderHelper.setRenderType(PINK_DAFFODIL, cutoutRenderType);
        RenderHelper.setRenderType(PINK_HIBISCUS, cutoutRenderType);
        RenderHelper.setRenderType(WATERLILY, cutoutRenderType);
        RenderHelper.setRenderType(WHITE_PETALS, cutoutRenderType);
        RenderHelper.setRenderType(GLOWFLOWER, cutoutRenderType);
        RenderHelper.setRenderType(WILTED_LILY, cutoutRenderType);
        RenderHelper.setRenderType(BURNING_BLOSSOM, cutoutRenderType);
        RenderHelper.setRenderType(TALL_LAVENDER, cutoutRenderType);
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
        RenderHelper.setRenderType(BUSH, cutoutRenderType);
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
        RenderHelper.setRenderType(ALGAE_BLOOM, cutoutRenderType);
        RenderHelper.setRenderType(ENDERPHYTE, cutoutRenderType);
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

        RenderHelper.setRenderType(POTTED_ORIGIN_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_FLOWERING_OAK_SAPLING, cutoutRenderType);
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
        RenderHelper.setRenderType(POTTED_ROSE, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_VIOLET, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_LAVENDER, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_WILDFLOWER, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_ORANGE_COSMOS, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_PINK_DAFFODIL, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_PINK_HIBISCUS, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_GLOWFLOWER, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_WILTED_LILY, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_BURNING_BLOSSOM, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_SPROUT, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_TINY_CACTUS, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_TOADSTOOL, cutoutRenderType);
        RenderHelper.setRenderType(POTTED_GLOWSHROOM, cutoutRenderType);

        RenderHelper.setRenderType(BLOOD, translucentRenderType);
        RenderHelper.setRenderType(BOPFluids.BLOOD, translucentRenderType);
        RenderHelper.setRenderType(BOPFluids.FLOWING_BLOOD, translucentRenderType);
    }

    public static void registerRenderers()
    {
        // Register boat layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel();
        LayerDefinition chestBoatLayerDefinition = ChestBoatModel.createBodyModel();

        for (BoatBOP.ModelType type : BoatBOP.ModelType.values())
        {
            RenderHelper.registerLayerDefinition(BoatRendererBOP.createBoatModelName(type), () -> boatLayerDefinition);
            RenderHelper.registerLayerDefinition(BoatRendererBOP.createChestBoatModelName(type), () -> chestBoatLayerDefinition);
        }

        // Register block entity renderers
        RenderHelper.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>) BOPBlockEntities.SIGN, SignRenderer::new);
        RenderHelper.registerBlockEntityRenderer((BlockEntityType<HangingSignBlockEntityBOP>)BOPBlockEntities.HANGING_SIGN, HangingSignRenderer::new);
        RenderHelper.registerBlockEntityRenderer((BlockEntityType<AnomalyBlockEntity>)BOPBlockEntities.ANOMALY, AnomalyRenderer::new);

        // Register entity renderers
        RenderHelper.registerEntityRenderer((EntityType<BoatBOP>) BOPEntities.BOAT, context -> new BoatRendererBOP(context, false));
        RenderHelper.registerEntityRenderer((EntityType<ChestBoatBOP>) BOPEntities.CHEST_BOAT, context -> new BoatRendererBOP(context, true));
    }

    public static void registerItemColors(RegisterColorsEvent.Item event)
    {
        event.register((stack, tintIndex) -> {
            BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            return blockColors.getColor(state, null, null, tintIndex);
        },
        BOPBlocks.MOSSY_BLACK_SAND, BOPBlocks.SPROUT, BOPBlocks.BUSH, BOPBlocks.HIGH_GRASS, BOPBlocks.HIGH_GRASS_PLANT,
        BOPBlocks.CLOVER, BOPBlocks.HUGE_CLOVER_PETAL, BOPBlocks.HUGE_LILY_PAD, BOPBlocks.FLOWERING_OAK_LEAVES,
        BOPBlocks.PINE_LEAVES, BOPBlocks.MAHOGANY_LEAVES, BOPBlocks.PALM_LEAVES, BOPBlocks.WILLOW_LEAVES,
        BOPBlocks.WILLOW_VINE, BOPBlocks.BRAMBLE_LEAVES);
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
                        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
                BOPBlocks.BUSH, BOPBlocks.FLOWERING_OAK_LEAVES, BOPBlocks.PINE_LEAVES, BOPBlocks.MAHOGANY_LEAVES,
                BOPBlocks.PALM_LEAVES, BOPBlocks.WILLOW_LEAVES, BOPBlocks.WILLOW_VINE, BOPBlocks.BRAMBLE_LEAVES);

        //Rainbow Birch Leaf Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? getRainbowBirchColor(world, pos) : FoliageColor.getDefaultColor(),
                BOPBlocks.RAINBOW_BIRCH_LEAVES);

        //Flowerbed Coloring
        event.register((state, world, pos, tintIndex) -> {
                    if (tintIndex != 0) { return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(); }
                    else { return -1; }},
                BOPBlocks.WHITE_PETALS);

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
