/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.api.entity.BOPVillagerTrades;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class BOPVillagerTradesTagsProvider extends KeyTagProvider<VillagerTrade>
{
    public BOPVillagerTradesTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.VILLAGER_TRADE, lookupProvider, BiomesOPlenty.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries)
    {
        this.tag(VillagerTradeTags.WANDERING_TRADER_COMMON)
            .add(
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_REDWOOD_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_MAHOGANY_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_JACARANDA_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_PALM_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WILLOW_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_DEAD_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_FLOWER_BUD,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_MARIGOLD,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_VIOLET,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_LAVENDER,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WHITE_LAVENDER,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_PURPLE_WILDFLOWERS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WHITE_PETALS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ORANGE_COSMOS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_PINK_DAFFODIL,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_PINK_HIBISCUS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WATERLILY,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WILTED_LILY,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_TALL_LAVENDER,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_TALL_WHITE_LAVENDER,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_BLUE_HYDRANGEA,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_GOLDENROD,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_CLOVER,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_TOADSTOOL,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_GLOWSHROOM,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_GLOWING_MOSS_BLOCK,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WILLOW_VINE,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_SPANISH_MOSS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_HANGING_COBWEB,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_HIGH_GRASS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_DUNE_GRASS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_DESERT_GRASS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_DEAD_GRASS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_TUNDRA_SHRUB,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_HUGE_CLOVER_PETAL,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_HUGE_LILY_PAD,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_CATTAIL,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_SEA_OATS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_REED,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WATERGRASS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_TINY_CACTUS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_WHITE_SAND,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ORANGE_SAND,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_BLACK_SAND,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_DRIED_SALT,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_THERMAL_CALCITE,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ORIGIN_OAK_SAPLING,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_MAGIC_SAPLING,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_MAGIC_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_UMBRAN_SAPLING,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_UMBRAN_LOG,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_HELLBARK_SAPLING,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_EMPYREAL_SAPLING
            );

        this.tag(VillagerTradeTags.WANDERING_TRADER_UNCOMMON)
            .add(
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ORIGIN_GRASS_BLOCK,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ORIGIN_DANDELION,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ORIGIN_ROSE,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_GLOWFLOWER,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_BURNING_BLOSSOM,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_ICY_IRIS,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_BRIMSTONE,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_BRAMBLE,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_GLOWWORM_SILK,
                BOPVillagerTrades.WANDERING_TRADER_EMERALD_SPIDER_EGG
            );
    }
}
