/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPWoodTypes;
import biomesoplenty.particle.*;
import glitchcore.event.client.RegisterColorsEvent;
import glitchcore.event.client.RegisterParticleSpritesEvent;
import glitchcore.util.RenderTypeHelper;
import glitchcore.util.SheetHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

import static biomesoplenty.api.block.BOPBlocks.*;

public class ModClient
{
    public static void setupRenderTypes()
    {
        RenderType transparentRenderType = RenderType.cutoutMipped();
        RenderType cutoutRenderType = RenderType.cutout();
        RenderType translucentRenderType = RenderType.translucent();

        RenderTypeHelper.setRenderType(MOSSY_BLACK_SAND, transparentRenderType);

        RenderTypeHelper.setRenderType(ORIGIN_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(FLOWERING_OAK_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(SNOWBLOSSOM_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(RAINBOW_BIRCH_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(FIR_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(PINE_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(RED_MAPLE_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(ORANGE_MAPLE_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(YELLOW_MAPLE_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(REDWOOD_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(MAHOGANY_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(JACARANDA_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(PALM_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(WILLOW_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(DEAD_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(MAGIC_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(UMBRAN_LEAVES, transparentRenderType);
        RenderTypeHelper.setRenderType(HELLBARK_LEAVES, transparentRenderType);

        RenderTypeHelper.setRenderType(ORIGIN_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(FLOWERING_OAK_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(SNOWBLOSSOM_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(RAINBOW_BIRCH_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(FIR_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(PINE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(RED_MAPLE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(ORANGE_MAPLE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(YELLOW_MAPLE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(REDWOOD_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAHOGANY_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(JACARANDA_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(PALM_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(WILLOW_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(DEAD_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAGIC_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(UMBRAN_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(HELLBARK_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(ROSE, cutoutRenderType);
        RenderTypeHelper.setRenderType(VIOLET, cutoutRenderType);
        RenderTypeHelper.setRenderType(LAVENDER, cutoutRenderType);
        RenderTypeHelper.setRenderType(WILDFLOWER, cutoutRenderType);
        RenderTypeHelper.setRenderType(ORANGE_COSMOS, cutoutRenderType);
        RenderTypeHelper.setRenderType(PINK_DAFFODIL, cutoutRenderType);
        RenderTypeHelper.setRenderType(PINK_HIBISCUS, cutoutRenderType);
        RenderTypeHelper.setRenderType(WATERLILY, cutoutRenderType);
        RenderTypeHelper.setRenderType(WHITE_PETALS, cutoutRenderType);
        RenderTypeHelper.setRenderType(GLOWFLOWER, cutoutRenderType);
        RenderTypeHelper.setRenderType(WILTED_LILY, cutoutRenderType);
        RenderTypeHelper.setRenderType(BURNING_BLOSSOM, cutoutRenderType);
        RenderTypeHelper.setRenderType(TALL_LAVENDER, cutoutRenderType);
        RenderTypeHelper.setRenderType(BLUE_HYDRANGEA, cutoutRenderType);
        RenderTypeHelper.setRenderType(GOLDENROD, cutoutRenderType);
        RenderTypeHelper.setRenderType(ICY_IRIS, cutoutRenderType);
        RenderTypeHelper.setRenderType(WILLOW_VINE, cutoutRenderType);
        RenderTypeHelper.setRenderType(SPANISH_MOSS, cutoutRenderType);
        RenderTypeHelper.setRenderType(SPANISH_MOSS_PLANT, cutoutRenderType);
        RenderTypeHelper.setRenderType(GLOWWORM_SILK, cutoutRenderType);
        RenderTypeHelper.setRenderType(GLOWWORM_SILK_STRAND, cutoutRenderType);
        RenderTypeHelper.setRenderType(HANGING_COBWEB, cutoutRenderType);
        RenderTypeHelper.setRenderType(HANGING_COBWEB_STRAND, cutoutRenderType);
        RenderTypeHelper.setRenderType(STRINGY_COBWEB, cutoutRenderType);
        RenderTypeHelper.setRenderType(WEBBING, cutoutRenderType);
        RenderTypeHelper.setRenderType(SPROUT, cutoutRenderType);
        RenderTypeHelper.setRenderType(BUSH, cutoutRenderType);
        RenderTypeHelper.setRenderType(HIGH_GRASS, cutoutRenderType);
        RenderTypeHelper.setRenderType(HIGH_GRASS_PLANT, cutoutRenderType);
        RenderTypeHelper.setRenderType(CLOVER, cutoutRenderType);
        RenderTypeHelper.setRenderType(HUGE_CLOVER_PETAL, cutoutRenderType);
        RenderTypeHelper.setRenderType(HUGE_LILY_PAD, cutoutRenderType);
        RenderTypeHelper.setRenderType(RED_MAPLE_LEAF_PILE, cutoutRenderType);
        RenderTypeHelper.setRenderType(ORANGE_MAPLE_LEAF_PILE, cutoutRenderType);
        RenderTypeHelper.setRenderType(YELLOW_MAPLE_LEAF_PILE, cutoutRenderType);
        RenderTypeHelper.setRenderType(DUNE_GRASS, cutoutRenderType);
        RenderTypeHelper.setRenderType(DESERT_GRASS, cutoutRenderType);
        RenderTypeHelper.setRenderType(DEAD_GRASS, cutoutRenderType);
        RenderTypeHelper.setRenderType(TUNDRA_SHRUB, cutoutRenderType);
        RenderTypeHelper.setRenderType(CATTAIL, cutoutRenderType);
        RenderTypeHelper.setRenderType(BARLEY, cutoutRenderType);
        RenderTypeHelper.setRenderType(SEA_OATS, cutoutRenderType);
        RenderTypeHelper.setRenderType(REED, cutoutRenderType);
        RenderTypeHelper.setRenderType(WATERGRASS, cutoutRenderType);
        RenderTypeHelper.setRenderType(DEAD_BRANCH, cutoutRenderType);
        RenderTypeHelper.setRenderType(TINY_CACTUS, cutoutRenderType);
        RenderTypeHelper.setRenderType(BRAMBLE, cutoutRenderType);
        RenderTypeHelper.setRenderType(BRAMBLE_LEAVES, cutoutRenderType);
        RenderTypeHelper.setRenderType(TOADSTOOL, cutoutRenderType);
        RenderTypeHelper.setRenderType(GLOWSHROOM, cutoutRenderType);
        RenderTypeHelper.setRenderType(PUS_BUBBLE, cutoutRenderType);
        RenderTypeHelper.setRenderType(FLESH_TENDONS, cutoutRenderType);
        RenderTypeHelper.setRenderType(FLESH_TENDONS_STRAND, cutoutRenderType);
        RenderTypeHelper.setRenderType(EYEBULB, cutoutRenderType);
        RenderTypeHelper.setRenderType(HAIR, cutoutRenderType);
        RenderTypeHelper.setRenderType(BRIMSTONE_BUD, cutoutRenderType);
        RenderTypeHelper.setRenderType(BRIMSTONE_CLUSTER, cutoutRenderType);
        RenderTypeHelper.setRenderType(ROSE_QUARTZ_CLUSTER, cutoutRenderType);
        RenderTypeHelper.setRenderType(LARGE_ROSE_QUARTZ_BUD, cutoutRenderType);
        RenderTypeHelper.setRenderType(MEDIUM_ROSE_QUARTZ_BUD, cutoutRenderType);
        RenderTypeHelper.setRenderType(SMALL_ROSE_QUARTZ_BUD, cutoutRenderType);
        RenderTypeHelper.setRenderType(BLACKSTONE_SPINES, cutoutRenderType);
        RenderTypeHelper.setRenderType(BLACKSTONE_BULB, cutoutRenderType);
        RenderTypeHelper.setRenderType(SPIDER_EGG, cutoutRenderType);

        RenderTypeHelper.setRenderType(FIR_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(PINE_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAPLE_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(REDWOOD_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAHOGANY_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(JACARANDA_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(PALM_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(WILLOW_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(DEAD_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAGIC_DOOR, translucentRenderType);
        RenderTypeHelper.setRenderType(UMBRAN_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(HELLBARK_DOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(FIR_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(PINE_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAPLE_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(REDWOOD_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAHOGANY_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(JACARANDA_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(PALM_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(WILLOW_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(DEAD_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(MAGIC_TRAPDOOR, translucentRenderType);
        RenderTypeHelper.setRenderType(UMBRAN_TRAPDOOR, cutoutRenderType);
        RenderTypeHelper.setRenderType(HELLBARK_TRAPDOOR, cutoutRenderType);

        RenderTypeHelper.setRenderType(POTTED_ORIGIN_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_FLOWERING_OAK_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_SNOWBLOSSOM_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_RAINBOW_BIRCH_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_FIR_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_PINE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_RED_MAPLE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_ORANGE_MAPLE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_YELLOW_MAPLE_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_REDWOOD_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_MAHOGANY_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_JACARANDA_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_PALM_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_WILLOW_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_DEAD_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_MAGIC_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_UMBRAN_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_HELLBARK_SAPLING, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_ROSE, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_VIOLET, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_LAVENDER, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_WILDFLOWER, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_ORANGE_COSMOS, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_PINK_DAFFODIL, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_PINK_HIBISCUS, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_GLOWFLOWER, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_WILTED_LILY, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_BURNING_BLOSSOM, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_SPROUT, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_TINY_CACTUS, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_TOADSTOOL, cutoutRenderType);
        RenderTypeHelper.setRenderType(POTTED_GLOWSHROOM, cutoutRenderType);

        RenderTypeHelper.setRenderType(BLOOD, translucentRenderType);
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
