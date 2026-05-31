/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPWoodTypes;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.block.entity.AnomalyBlockEntity;
import biomesoplenty.client.renderer.AnomalyRenderer;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.particle.*;
import glitchcore.event.EventManager;
import glitchcore.event.client.RegisterColorsEvent;
import glitchcore.event.client.RegisterLayerDefinitionsEvent;
import glitchcore.event.client.RegisterParticleSpritesEvent;
import glitchcore.event.client.RegisterRenderersEvent;
import glitchcore.util.RenderHelper;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.List;

public class ModClient
{
    public static void setup()
    {
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
        event.register(List.of(
                new BlockTintSource()
                {
                    @Override
                    public int color(BlockState state) {
                        return GrassColor.get(0.5D, 1.0D);
                    }

                    @Override
                    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                        return BiomeColors.getAverageGrassColor(level, pos);
                    }
                }
            ),
            BOPBlocks.FLOWER_STEM, BOPBlocks.MOSSY_BLACK_SAND, BOPBlocks.SPROUT, BOPBlocks.HIGH_GRASS, BOPBlocks.HIGH_GRASS_PLANT,
            BOPBlocks.HUGE_CLOVER_PETAL, BOPBlocks.BARLEY, BOPBlocks.WATERGRASS, BOPBlocks.POTTED_SPROUT);

        //Foliage Coloring
        event.register(List.of(BlockTintSources.foliage()),
                BOPBlocks.FLOWERING_OAK_LEAVES, BOPBlocks.PINE_LEAVES, BOPBlocks.MAHOGANY_LEAVES, BOPBlocks.PALM_LEAVES, BOPBlocks.WILLOW_LEAVES,
                BOPBlocks.WILLOW_VINE, BOPBlocks.BRAMBLE_LEAVES);

        //Dry Foliage Coloring
        event.register(List.of(BlockTintSources.dryFoliage()),
                BOPBlocks.DEAD_LEAVES, BOPBlocks.DESERT_GRASS);

        //Flowerbed Coloring
        event.register(List.of(BlockTintSources.constant(-1), BlockTintSources.grass()),
                BOPBlocks.CLOVER, BOPBlocks.WHITE_PETALS, BOPBlocks.PURPLE_WILDFLOWERS);

        //Lily Pad Coloring
        event.register(List.of(
                new BlockTintSource()
                {
                    @Override
                    public int color(BlockState state) {
                        return 0xFF71C11C;
                    }

                    @Override
                    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                        return 0xFF208030;
                    }
                }
        ),
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

        event.registerSpriteSet(ModParticles.WHITE_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.LIGHT_GRAY_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.GRAY_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.BLACK_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.BROWN_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.RED_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.ORANGE_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.YELLOW_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.LIME_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.GREEN_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.CYAN_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.LIGHT_BLUE_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.BLUE_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.PURPLE_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.MAGENTA_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });
        event.registerSpriteSet(ModParticles.PINK_FLOWER_PETAL, (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_,  random) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_, p_277215_.get(random));
            };
        });

        /////////////

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
        addWoodType(BOPWoodTypes.ORIGIN_OAK);
        addWoodType(BOPWoodTypes.FIR);
        addWoodType(BOPWoodTypes.PINE);
        addWoodType(BOPWoodTypes.MAPLE);
        addWoodType(BOPWoodTypes.REDWOOD);
        addWoodType(BOPWoodTypes.MAHOGANY);
        addWoodType(BOPWoodTypes.JACARANDA);
        addWoodType(BOPWoodTypes.PALM);
        addWoodType(BOPWoodTypes.WILLOW);
        addWoodType(BOPWoodTypes.DEAD);
        addWoodType(BOPWoodTypes.MAGIC);
        addWoodType(BOPWoodTypes.UMBRAN);
        addWoodType(BOPWoodTypes.HELLBARK);
        addWoodType(BOPWoodTypes.EMPYREAL);
    }

    private static void addWoodType(WoodType woodType)
    {
        Sheets.SIGN_SPRITES.put(woodType, createSignSprite(woodType));
        Sheets.HANGING_SIGN_SPRITES.put(woodType, createHangingSignSprite(woodType));
    }

    private static SpriteId createSignSprite(WoodType type)
    {
        return Sheets.SIGN_MAPPER.apply(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, type.name()));
    }

    private static SpriteId createHangingSignSprite(WoodType type)
    {
        return Sheets.HANGING_SIGN_MAPPER.apply(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, type.name()));
    }
}
