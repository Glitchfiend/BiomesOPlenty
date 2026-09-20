package biomesoplenty.init;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;


import static biomesoplenty.api.entity.BOPVillagerTrades.*;

public class ModVillagerTrades
{
    public static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        // Common trades
        context.register(WANDERING_TRADER_EMERALD_FLOWERING_OAK_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.FLOWERING_OAK_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_CYPRESS_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.CYPRESS_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_SNOWBLOSSOM_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.SNOWBLOSSOM_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_FIR_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.FIR_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PINE_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.PINE_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MAPLE_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.MAPLE_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_REDWOOD_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.REDWOOD_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MAHOGANY_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.MAHOGANY_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_JACARANDA_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.JACARANDA_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PALM_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.PALM_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WILLOW_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.WILLOW_SAPLING, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_DEAD_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.DEAD_SAPLING, 1), 8, 1, 0.05F).build());

        context.register(WANDERING_TRADER_EMERALD_FIR_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.FIR_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PINE_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PINE_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MAPLE_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MAPLE_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_REDWOOD_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.REDWOOD_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MAHOGANY_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MAHOGANY_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_JACARANDA_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.JACARANDA_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PALM_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PALM_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WILLOW_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WILLOW_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_DEAD_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DEAD_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_FLOWER_BUD, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.FLOWER_BUD, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MARIGOLD, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MARIGOLD, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_VIOLET, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.VIOLET, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_LAVENDER, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.LAVENDER, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WHITE_LAVENDER, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WHITE_LAVENDER, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PURPLE_WILDFLOWERS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PURPLE_WILDFLOWERS, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WHITE_PETALS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WHITE_PETALS, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ORANGE_COSMOS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORANGE_COSMOS, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PINK_DAFFODIL, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PINK_DAFFODIL, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_PINK_HIBISCUS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PINK_HIBISCUS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WATERLILY, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WATERLILY, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WILTED_LILY, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WILTED_LILY, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_TALL_LAVENDER, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.TALL_LAVENDER, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_TALL_WHITE_LAVENDER, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.TALL_WHITE_LAVENDER, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_BLUE_HYDRANGEA, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.BLUE_HYDRANGEA, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_GOLDENROD, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.GOLDENROD, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_CLOVER, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.CLOVER, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_TOADSTOOL, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.TOADSTOOL, 3), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_GLOWSHROOM, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.GLOWSHROOM, 3), 3, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_GLOWING_MOSS_BLOCK, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.GLOWING_MOSS_BLOCK, 2), 5, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WILLOW_VINE, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WILLOW_VINE, 3), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_SPANISH_MOSS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.SPANISH_MOSS, 3), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_HANGING_COBWEB, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HANGING_COBWEB, 3), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_HIGH_GRASS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HIGH_GRASS, 1), 12, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_DUNE_GRASS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DUNE_GRASS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_DESERT_GRASS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DESERT_GRASS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_DEAD_GRASS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DEAD_GRASS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_TUNDRA_SHRUB, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.TUNDRA_SHRUB, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_HUGE_CLOVER_PETAL, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HUGE_CLOVER_PETAL, 4), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_HUGE_LILY_PAD, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HUGE_LILY_PAD, 2), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_CATTAIL, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.CATTAIL, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_SEA_OATS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.SEA_OATS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_REED, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.REED, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WATERGRASS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WATERGRASS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_TINY_CACTUS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.TINY_CACTUS, 1), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_WHITE_SAND, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WHITE_SAND, 4), 6, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ORANGE_SAND, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORANGE_SAND, 4), 6, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_BLACK_SAND, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.BLACK_SAND, 4), 6, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_DRIED_SALT, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DRIED_SALT, 6), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_SPHALERITE, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.SPHALERITE, 6), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_OAK_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.ORIGIN_OAK_SAPLING, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MAGIC_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.MAGIC_SAPLING, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_MAGIC_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MAGIC_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_UMBRAN_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.UMBRAN_SAPLING, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_UMBRAN_LOG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.UMBRAN_LOG, 8), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_HELLBARK_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.HELLBARK_SAPLING, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_EMPYREAL_SAPLING, VillagerTrade.builder(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.EMPYREAL_SAPLING, 1), 4, 1, 0.05F).build());

        // Rare trades
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_GRASS_BLOCK, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.ORIGIN_GRASS_BLOCK, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_DANDELION, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORIGIN_DANDELION, 1), 6, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_ROSE, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORIGIN_ROSE, 1), 6, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_GLOWFLOWER, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.GLOWFLOWER, 2), 5, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_BURNING_BLOSSOM, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.BURNING_BLOSSOM, 1), 6, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ICY_IRIS, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.ICY_IRIS, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_ORPIMENT, VillagerTrade.builder(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.ORPIMENT, 4), 8, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_BRAMBLE, VillagerTrade.builder(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.BRAMBLE, 1), 4, 1, 0.05F).build());
        context.register(WANDERING_TRADER_EMERALD_GLOWWORM_SILK, VillagerTrade.builder(new TradeCost(Items.EMERALD, 6), new ItemStackTemplate(BOPItems.GLOWWORM_SILK, 1), 2, 1, 0.05F).build());
        return context.register(WANDERING_TRADER_EMERALD_SPIDER_EGG, VillagerTrade.builder(new TradeCost(Items.EMERALD, 6), new ItemStackTemplate(BOPItems.SPIDER_EGG, 1), 1, 1, 0.05F).build());
    }
}