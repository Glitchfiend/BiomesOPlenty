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

import java.util.List;
import java.util.Optional;

import static biomesoplenty.api.entity.BOPVillagerTrades.*;

public class ModVillagerTrades
{
    public static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        // Common trades
        context.register(WANDERING_TRADER_EMERALD_FLOWERING_OAK_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.FLOWERING_OAK_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_CYPRESS_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.CYPRESS_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_SNOWBLOSSOM_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.SNOWBLOSSOM_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_FIR_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.FIR_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PINE_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.PINE_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_RED_MAPLE_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.RED_MAPLE_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ORANGE_MAPLE_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.ORANGE_MAPLE_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_YELLOW_MAPLE_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.YELLOW_MAPLE_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_REDWOOD_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.REDWOOD_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_MAHOGANY_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.MAHOGANY_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_JACARANDA_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.JACARANDA_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PALM_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.PALM_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WILLOW_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.WILLOW_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_DEAD_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.DEAD_SAPLING, 1), 8, 1, 0.05F, Optional.empty(), List.of()));

        context.register(WANDERING_TRADER_EMERALD_FIR_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.FIR_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PINE_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PINE_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_MAPLE_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MAPLE_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_REDWOOD_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.REDWOOD_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_MAHOGANY_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MAHOGANY_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_JACARANDA_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.JACARANDA_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PALM_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PALM_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WILLOW_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WILLOW_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_DEAD_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DEAD_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_FLOWER_BUD, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.FLOWER_BUD, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_MARIGOLD, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MARIGOLD, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_VIOLET, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.VIOLET, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_LAVENDER, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.LAVENDER, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WHITE_LAVENDER, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WHITE_LAVENDER, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PURPLE_WILDFLOWERS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PURPLE_WILDFLOWERS, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WHITE_PETALS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WHITE_PETALS, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ORANGE_COSMOS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORANGE_COSMOS, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PINK_DAFFODIL, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PINK_DAFFODIL, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_PINK_HIBISCUS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.PINK_HIBISCUS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WATERLILY, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WATERLILY, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WILTED_LILY, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WILTED_LILY, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_TALL_LAVENDER, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.TALL_LAVENDER, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_TALL_WHITE_LAVENDER, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.TALL_WHITE_LAVENDER, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_BLUE_HYDRANGEA, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.BLUE_HYDRANGEA, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_GOLDENROD, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.GOLDENROD, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_CLOVER, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.CLOVER, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_TOADSTOOL, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.TOADSTOOL, 3), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_GLOWSHROOM, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.GLOWSHROOM, 3), 3, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_GLOWING_MOSS_BLOCK, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.GLOWING_MOSS_BLOCK, 2), 5, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WILLOW_VINE, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WILLOW_VINE, 3), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_SPANISH_MOSS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.SPANISH_MOSS, 3), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_HANGING_COBWEB, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HANGING_COBWEB, 3), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_HIGH_GRASS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HIGH_GRASS, 1), 12, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_DUNE_GRASS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DUNE_GRASS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_DESERT_GRASS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DESERT_GRASS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_DEAD_GRASS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DEAD_GRASS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_TUNDRA_SHRUB, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.TUNDRA_SHRUB, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_HUGE_CLOVER_PETAL, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HUGE_CLOVER_PETAL, 4), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_HUGE_LILY_PAD, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.HUGE_LILY_PAD, 2), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_CATTAIL, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.CATTAIL, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_SEA_OATS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.SEA_OATS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_REED, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.REED, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WATERGRASS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WATERGRASS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_TINY_CACTUS, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.TINY_CACTUS, 1), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_WHITE_SAND, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.WHITE_SAND, 4), 6, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ORANGE_SAND, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORANGE_SAND, 4), 6, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_BLACK_SAND, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.BLACK_SAND, 4), 6, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_DRIED_SALT, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.DRIED_SALT, 6), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_THERMAL_CALCITE, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.THERMAL_CALCITE, 6), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_OAK_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.ORIGIN_OAK_SAPLING, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_MAGIC_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.MAGIC_SAPLING, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_MAGIC_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.MAGIC_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_UMBRAN_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.UMBRAN_SAPLING, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_UMBRAN_LOG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.UMBRAN_LOG, 8), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_HELLBARK_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.HELLBARK_SAPLING, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_EMPYREAL_SAPLING, new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(BOPItems.EMPYREAL_SAPLING, 1), 4, 1, 0.05F, Optional.empty(), List.of()));

        // Rare trades
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_GRASS_BLOCK, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.ORIGIN_GRASS_BLOCK, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_DANDELION, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORIGIN_DANDELION, 1), 6, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ORIGIN_ROSE, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.ORIGIN_ROSE, 1), 6, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_GLOWFLOWER, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.GLOWFLOWER, 2), 5, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_BURNING_BLOSSOM, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.BURNING_BLOSSOM, 1), 6, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_ICY_IRIS, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.ICY_IRIS, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_BRIMSTONE, new VillagerTrade(new TradeCost(Items.EMERALD, 2), new ItemStackTemplate(BOPItems.BRIMSTONE, 4), 8, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_BRAMBLE, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(BOPItems.BRAMBLE, 1), 4, 1, 0.05F, Optional.empty(), List.of()));
        context.register(WANDERING_TRADER_EMERALD_GLOWWORM_SILK, new VillagerTrade(new TradeCost(Items.EMERALD, 6), new ItemStackTemplate(BOPItems.GLOWWORM_SILK, 1), 2, 1, 0.05F, Optional.empty(), List.of()));
        return context.register(WANDERING_TRADER_EMERALD_SPIDER_EGG, new VillagerTrade(new TradeCost(Items.EMERALD, 6), new ItemStackTemplate(BOPItems.SPIDER_EGG, 1), 1, 1, 0.05F, Optional.empty(), List.of()));
    }
}