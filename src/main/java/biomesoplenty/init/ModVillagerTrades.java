package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModVillagerTrades
{
    @SubscribeEvent
    public static void addWanderingVillagerTrades(WandererTradesEvent event)
    {
        //Cost, Amount, Trades Until Disabled, Villager XP
        VillagerTrades.ItemListing[] WANDERING_TRADER_GENERIC = new VillagerTrades.ItemListing[]{
            new ItemsForEmeralds(BOPBlocks.FLOWERING_OAK_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.SNOWBLOSSOM_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.YELLOW_AUTUMN_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.ORANGE_AUTUMN_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.MAPLE_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.FIR_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.PINE_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.REDWOOD_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.MAHOGANY_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.JACARANDA_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.PALM_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WILLOW_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.DEAD_SAPLING.get(), 5, 1, 8, 1),

            new ItemsForEmeralds(BOPBlocks.VIOLET.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.LAVENDER.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.WILDFLOWER.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.ORANGE_COSMOS.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.PINK_DAFFODIL.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.PINK_HIBISCUS.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WATERLILY.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WILTED_LILY.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.TALL_LAVENDER.get(), 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.BLUE_HYDRANGEA.get(), 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.GOLDENROD.get(), 2, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.CLOVER.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.TOADSTOOL.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWSHROOM.get(), 2, 1, 6, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWING_MOSS_BLOCK.get(), 2, 2, 5, 1),
            new ItemsForEmeralds(BOPBlocks.WILLOW_VINE.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.SPANISH_MOSS.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.HANGING_COBWEB.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.HIGH_GRASS.get(), 1, 1, 12, 1),
            new ItemsForEmeralds(BOPBlocks.DUNE_GRASS.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.DESERT_GRASS.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.DEAD_GRASS.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.TUNDRA_SHRUB.get(), 1, 1, 8, 1),

            new ItemsForEmeralds(BOPBlocks.HUGE_CLOVER_PETAL.get(), 1, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.HUGE_LILY_PAD.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.CATTAIL.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.SEA_OATS.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.REED.get(), 1, 1, 8, 1),
            new ItemsForEmeralds(BOPBlocks.WATERGRASS.get(), 1, 1, 8, 1),

            new ItemsForEmeralds(BOPBlocks.WHITE_SAND.get(), 1, 4, 6, 1),
            new ItemsForEmeralds(BOPBlocks.ORANGE_SAND.get(), 1, 4, 6, 1),
            new ItemsForEmeralds(BOPBlocks.BLACK_SAND.get(), 1, 4, 6, 1),
            new ItemsForEmeralds(BOPBlocks.DRIED_SALT.get(), 1, 6, 8, 1)
        };

        VillagerTrades.ItemListing[] WANDERING_TRADER_RARE = new VillagerTrades.ItemListing[]{
            new ItemsForEmeralds(BOPBlocks.ORIGIN_SAPLING.get(), 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.RAINBOW_BIRCH_SAPLING.get(), 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.MAGIC_SAPLING.get(), 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.UMBRAN_SAPLING.get(), 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.HELLBARK_SAPLING.get(), 5, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.ORIGIN_GRASS_BLOCK.get(), 2, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.ROSE.get(), 1, 1, 6, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWFLOWER.get(), 1, 2, 5, 1),
            new ItemsForEmeralds(BOPBlocks.BURNING_BLOSSOM.get(), 1, 1, 6, 1),
            new ItemsForEmeralds(BOPBlocks.ICY_IRIS.get(), 2, 1, 4, 1),
            new ItemsForEmeralds(BOPBlocks.GLOWWORM_SILK.get(), 6, 1, 2, 1),
            new ItemsForEmeralds(BOPBlocks.SPIDER_EGG.get(), 6, 1, 1, 1)};

        for (VillagerTrades.ItemListing trade : WANDERING_TRADER_GENERIC)
        {
            event.getGenericTrades().add(trade);
        }
        for (VillagerTrades.ItemListing trade : WANDERING_TRADER_RARE)
        {
            event.getRareTrades().add(trade);
        }
    }

    static class EmeraldForItems implements VillagerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_) {
            this.item = p_35657_.asItem();
            this.cost = p_35658_;
            this.maxUses = p_35659_;
            this.villagerXp = p_35660_;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_219682_, RandomSource p_219683_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
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

        public ItemsForEmeralds(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public ItemsForEmeralds(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
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