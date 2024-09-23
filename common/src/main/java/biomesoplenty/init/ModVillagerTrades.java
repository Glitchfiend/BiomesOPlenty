package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableMap;
import biomesoplenty.glitch.event.village.WandererTradesEvent;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ModVillagerTrades
{
    public static void addWanderingVillagerTrades(WandererTradesEvent event)
    {
        //Cost, Amount, Trades Until Disabled, Villager XP
        VillagerTrades.ItemListing[] WANDERING_TRADER_GENERIC = new VillagerTrades.ItemListing[]{
            new ItemsForEmeralds(BOPBlocks.FLOWERING_OAK_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.CYPRESS_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.SNOWBLOSSOM_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.FIR_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.PINE_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.RED_MAPLE_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.ORANGE_MAPLE_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.YELLOW_MAPLE_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.REDWOOD_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.MAHOGANY_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.JACARANDA_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.PALM_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WILLOW_SAPLING, 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.DEAD_SAPLING, 5, 1, 8, 1),

            new ItemsForEmeralds(BOPBlocks.VIOLET, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.LAVENDER, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.WHITE_LAVENDER, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.WILDFLOWER, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.WHITE_PETALS, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.ORANGE_COSMOS, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.PINK_DAFFODIL, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.PINK_HIBISCUS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WATERLILY, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WILTED_LILY, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.TALL_LAVENDER, 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.TALL_WHITE_LAVENDER, 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.BLUE_HYDRANGEA, 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.GOLDENROD, 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.CLOVER, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.TOADSTOOL, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWSHROOM, 2, 1, 6, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWING_MOSS_BLOCK, 2, 2, 5, 1),
            new ItemsForEmeralds(BOPBlocks.WILLOW_VINE, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.SPANISH_MOSS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.HANGING_COBWEB, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.HIGH_GRASS, 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.DUNE_GRASS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.DESERT_GRASS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.DEAD_GRASS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.TUNDRA_SHRUB, 1, 1, 8, 1),

            new ItemsForEmeralds(BOPBlocks.HUGE_CLOVER_PETAL, 1, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.HUGE_LILY_PAD, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.CATTAIL, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.SEA_OATS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.REED, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WATERGRASS, 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.TINY_CACTUS, 1, 1, 8, 1),

            new ItemsForEmeralds(BOPBlocks.WHITE_SAND, 1, 4, 6, 1),
            new ItemsForEmeralds(BOPBlocks.ORANGE_SAND, 1, 4, 6, 1),
            new ItemsForEmeralds(BOPBlocks.BLACK_SAND, 1, 4, 6, 1),
            new ItemsForEmeralds(BOPBlocks.DRIED_SALT, 1, 6, 8, 1),
            new ItemsForEmeralds(BOPBlocks.THERMAL_CALCITE, 1, 6, 8, 1)
        };

        VillagerTrades.ItemListing[] WANDERING_TRADER_RARE = new VillagerTrades.ItemListing[]{
            new ItemsForEmeralds(BOPBlocks.ORIGIN_SAPLING, 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.RAINBOW_BIRCH_SAPLING, 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.MAGIC_SAPLING, 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.UMBRAN_SAPLING, 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.HELLBARK_SAPLING, 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.EMPYREAL_SAPLING, 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.ORIGIN_GRASS_BLOCK, 2, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.ROSE, 1, 1, 6, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWFLOWER, 1, 2, 5, 1),
            new ItemsForEmeralds(BOPBlocks.BURNING_BLOSSOM, 1, 1, 6, 1),
            new ItemsForEmeralds(BOPBlocks.ICY_IRIS, 2, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.BRIMSTONE, 2, 4, 8, 1),
            new ItemsForEmeralds(BOPBlocks.BRAMBLE, 1, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWWORM_SILK, 6, 1, 2, 1),
            new ItemsForEmeralds(BOPBlocks.SPIDER_EGG, 6, 1, 1, 1)};

        for (VillagerTrades.ItemListing trade : WANDERING_TRADER_GENERIC)
        {
            event.addGenericTrade(trade);
        }
        for (VillagerTrades.ItemListing trade : WANDERING_TRADER_RARE)
        {
            event.addRareTrade(trade);
        }
    }

    static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public ItemsForEmeralds(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public ItemsForEmeralds(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_) {
        return new Int2ObjectOpenHashMap<>(p_35631_);
    }
}