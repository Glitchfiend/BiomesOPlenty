package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModVanillaCompat
{
    public static void setup()
    {
        //Flammability
        registerFlammable(BOPBlocks.ORIGIN_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.FLOWERING_OAK_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.YELLOW_AUTUMN_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.ORANGE_AUTUMN_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.MAPLE_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.FIR_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.FIR_LOG, 5, 5);
        registerFlammable(BOPBlocks.FIR_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_FIR_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_FIR_WOOD, 5, 5);
        registerFlammable(BOPBlocks.FIR_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.FIR_SLAB, 5, 20);
        registerFlammable(BOPBlocks.FIR_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.FIR_FENCE, 5, 20);
        registerFlammable(BOPBlocks.FIR_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.REDWOOD_LOG, 5, 5);
        registerFlammable(BOPBlocks.REDWOOD_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_REDWOOD_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_REDWOOD_WOOD, 5, 5);
        registerFlammable(BOPBlocks.REDWOOD_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_SLAB, 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_FENCE, 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.WHITE_CHERRY_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.PINK_CHERRY_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.CHERRY_LOG, 5, 5);
        registerFlammable(BOPBlocks.CHERRY_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_CHERRY_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_CHERRY_WOOD, 5, 5);
        registerFlammable(BOPBlocks.CHERRY_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.CHERRY_SLAB, 5, 20);
        registerFlammable(BOPBlocks.CHERRY_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.CHERRY_FENCE, 5, 20);
        registerFlammable(BOPBlocks.CHERRY_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.MAHOGANY_LOG, 5, 5);
        registerFlammable(BOPBlocks.MAHOGANY_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAHOGANY_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAHOGANY_WOOD, 5, 5);
        registerFlammable(BOPBlocks.MAHOGANY_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_SLAB, 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_FENCE, 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.JACARANDA_LOG, 5, 5);
        registerFlammable(BOPBlocks.JACARANDA_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
        registerFlammable(BOPBlocks.JACARANDA_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_SLAB, 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_FENCE, 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.PALM_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.PALM_LOG, 5, 5);
        registerFlammable(BOPBlocks.PALM_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_PALM_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_PALM_WOOD, 5, 5);
        registerFlammable(BOPBlocks.PALM_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.PALM_SLAB, 5, 20);
        registerFlammable(BOPBlocks.PALM_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.PALM_FENCE, 5, 20);
        registerFlammable(BOPBlocks.PALM_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.WILLOW_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.WILLOW_LOG, 5, 5);
        registerFlammable(BOPBlocks.WILLOW_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_WILLOW_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_WILLOW_WOOD, 5, 5);
        registerFlammable(BOPBlocks.WILLOW_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.WILLOW_SLAB, 5, 20);
        registerFlammable(BOPBlocks.WILLOW_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.WILLOW_FENCE, 5, 20);
        registerFlammable(BOPBlocks.WILLOW_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.DEAD_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.DEAD_LOG, 5, 5);
        registerFlammable(BOPBlocks.DEAD_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_DEAD_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_DEAD_WOOD, 5, 5);
        registerFlammable(BOPBlocks.DEAD_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.DEAD_SLAB, 5, 20);
        registerFlammable(BOPBlocks.DEAD_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.DEAD_FENCE, 5, 20);
        registerFlammable(BOPBlocks.DEAD_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.MAGIC_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.MAGIC_LOG, 5, 5);
        registerFlammable(BOPBlocks.MAGIC_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAGIC_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAGIC_WOOD, 5, 5);
        registerFlammable(BOPBlocks.MAGIC_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.MAGIC_SLAB, 5, 20);
        registerFlammable(BOPBlocks.MAGIC_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.MAGIC_FENCE, 5, 20);
        registerFlammable(BOPBlocks.MAGIC_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_LEAVES, 30, 60);
        registerFlammable(BOPBlocks.UMBRAN_LOG, 5, 5);
        registerFlammable(BOPBlocks.UMBRAN_WOOD, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_UMBRAN_LOG, 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_UMBRAN_WOOD, 5, 5);
        registerFlammable(BOPBlocks.UMBRAN_PLANKS, 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_SLAB, 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_STAIRS, 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_FENCE, 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_FENCE_GATE, 5, 20);
        registerFlammable(BOPBlocks.ROSE, 60, 100);
        registerFlammable(BOPBlocks.VIOLET, 60, 100);
        registerFlammable(BOPBlocks.LAVENDER, 60, 100);
        registerFlammable(BOPBlocks.WILDFLOWER, 60, 100);
        registerFlammable(BOPBlocks.PINK_DAFFODIL, 60, 100);
        registerFlammable(BOPBlocks.PINK_HIBISCUS, 60, 100);
        registerFlammable(BOPBlocks.GLOWFLOWER, 60, 100);
        registerFlammable(BOPBlocks.WILTED_LILY, 60, 100);
        registerFlammable(BOPBlocks.BLUE_HYDRANGEA, 60, 100);
        registerFlammable(BOPBlocks.GOLDENROD, 60, 100);
        registerFlammable(BOPBlocks.WILLOW_VINE, 15, 100);
        registerFlammable(BOPBlocks.SPANISH_MOSS, 60, 100);
        registerFlammable(BOPBlocks.SPANISH_MOSS_PLANT, 60, 100);
        registerFlammable(BOPBlocks.TREE_ROOTS, 15, 20);
        registerFlammable(BOPBlocks.TREE_ROOTS_STEM, 15, 20);
        registerFlammable(BOPBlocks.GLOWWORM_SILK, 60, 100);
        registerFlammable(BOPBlocks.GLOWWORM_SILK_STRAND, 60, 100);
        registerFlammable(BOPBlocks.HANGING_COBWEB, 60, 100);
        registerFlammable(BOPBlocks.HANGING_COBWEB_STRAND, 60, 100);
        registerFlammable(BOPBlocks.SPROUT, 60, 100);
        registerFlammable(BOPBlocks.BUSH, 60, 100);
        registerFlammable(BOPBlocks.CLOVER, 60, 100);
        registerFlammable(BOPBlocks.HUGE_CLOVER_PETAL, 60, 100);
        registerFlammable(BOPBlocks.DUNE_GRASS, 60, 100);
        registerFlammable(BOPBlocks.DESERT_GRASS, 60, 100);
        registerFlammable(BOPBlocks.DEAD_GRASS, 60, 100);
        registerFlammable(BOPBlocks.DEAD_BRANCH, 60, 100);
        registerFlammable(BOPBlocks.BARLEY, 60, 100);

        //Log Stripping
        registerStrippable(BOPBlocks.FIR_LOG, BOPBlocks.STRIPPED_FIR_LOG);
        registerStrippable(BOPBlocks.FIR_WOOD, BOPBlocks.STRIPPED_FIR_WOOD);
        registerStrippable(BOPBlocks.REDWOOD_LOG, BOPBlocks.STRIPPED_REDWOOD_LOG);
        registerStrippable(BOPBlocks.REDWOOD_WOOD, BOPBlocks.STRIPPED_REDWOOD_WOOD);
        registerStrippable(BOPBlocks.CHERRY_LOG, BOPBlocks.STRIPPED_CHERRY_LOG);
        registerStrippable(BOPBlocks.CHERRY_WOOD, BOPBlocks.STRIPPED_CHERRY_WOOD);
        registerStrippable(BOPBlocks.MAHOGANY_LOG, BOPBlocks.STRIPPED_MAHOGANY_LOG);
        registerStrippable(BOPBlocks.MAHOGANY_WOOD, BOPBlocks.STRIPPED_MAHOGANY_WOOD);
        registerStrippable(BOPBlocks.JACARANDA_LOG, BOPBlocks.STRIPPED_JACARANDA_LOG);
        registerStrippable(BOPBlocks.JACARANDA_WOOD, BOPBlocks.STRIPPED_JACARANDA_WOOD);
        registerStrippable(BOPBlocks.PALM_LOG, BOPBlocks.STRIPPED_PALM_LOG);
        registerStrippable(BOPBlocks.PALM_WOOD, BOPBlocks.STRIPPED_PALM_WOOD);
        registerStrippable(BOPBlocks.WILLOW_LOG, BOPBlocks.STRIPPED_WILLOW_LOG);
        registerStrippable(BOPBlocks.WILLOW_WOOD, BOPBlocks.STRIPPED_WILLOW_WOOD);
        registerStrippable(BOPBlocks.DEAD_LOG, BOPBlocks.STRIPPED_DEAD_LOG);
        registerStrippable(BOPBlocks.DEAD_WOOD, BOPBlocks.STRIPPED_DEAD_WOOD);
        registerStrippable(BOPBlocks.MAGIC_LOG, BOPBlocks.STRIPPED_MAGIC_LOG);
        registerStrippable(BOPBlocks.MAGIC_WOOD, BOPBlocks.STRIPPED_MAGIC_WOOD);
        registerStrippable(BOPBlocks.UMBRAN_LOG, BOPBlocks.STRIPPED_UMBRAN_LOG);
        registerStrippable(BOPBlocks.UMBRAN_WOOD, BOPBlocks.STRIPPED_UMBRAN_WOOD);
        registerStrippable(BOPBlocks.HELLBARK_LOG, BOPBlocks.STRIPPED_HELLBARK_LOG);
        registerStrippable(BOPBlocks.HELLBARK_WOOD, BOPBlocks.STRIPPED_HELLBARK_WOOD);

        //Tilling and Flattening
        registerTillable(BOPBlocks.ORIGIN_GRASS_BLOCK, Blocks.FARMLAND.defaultBlockState());
        registerFlattenable(BOPBlocks.ORIGIN_GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState());

        //Compostable Blocks
        registerCompostable(0.85F, BOPBlocks.GLOWSHROOM_BLOCK);
        registerCompostable(0.85F, BOPBlocks.TOADSTOOL_BLOCK);

        registerCompostable(0.3F, BOPBlocks.ORIGIN_SAPLING);
        registerCompostable(0.3F, BOPBlocks.ORIGIN_LEAVES);
        registerCompostable(0.3F, BOPBlocks.FLOWERING_OAK_SAPLING);
        registerCompostable(0.3F, BOPBlocks.FLOWERING_OAK_LEAVES);
        registerCompostable(0.3F, BOPBlocks.YELLOW_AUTUMN_SAPLING);
        registerCompostable(0.3F, BOPBlocks.YELLOW_AUTUMN_LEAVES);
        registerCompostable(0.3F, BOPBlocks.ORANGE_AUTUMN_SAPLING);
        registerCompostable(0.3F, BOPBlocks.ORANGE_AUTUMN_LEAVES);
        registerCompostable(0.3F, BOPBlocks.MAPLE_SAPLING);
        registerCompostable(0.3F, BOPBlocks.MAPLE_LEAVES);
        registerCompostable(0.3F, BOPBlocks.FIR_SAPLING);
        registerCompostable(0.3F, BOPBlocks.FIR_LEAVES);
        registerCompostable(0.3F, BOPBlocks.REDWOOD_SAPLING);
        registerCompostable(0.3F, BOPBlocks.REDWOOD_LEAVES);
        registerCompostable(0.3F, BOPBlocks.WHITE_CHERRY_SAPLING);
        registerCompostable(0.3F, BOPBlocks.WHITE_CHERRY_LEAVES);
        registerCompostable(0.3F, BOPBlocks.PINK_CHERRY_SAPLING);
        registerCompostable(0.3F, BOPBlocks.PINK_CHERRY_LEAVES);
        registerCompostable(0.3F, BOPBlocks.MAHOGANY_SAPLING);
        registerCompostable(0.3F, BOPBlocks.MAHOGANY_LEAVES);
        registerCompostable(0.3F, BOPBlocks.JACARANDA_SAPLING);
        registerCompostable(0.3F, BOPBlocks.JACARANDA_LEAVES);
        registerCompostable(0.3F, BOPBlocks.PALM_SAPLING);
        registerCompostable(0.3F, BOPBlocks.PALM_LEAVES);
        registerCompostable(0.3F, BOPBlocks.WILLOW_SAPLING);
        registerCompostable(0.3F, BOPBlocks.WILLOW_LEAVES);
        registerCompostable(0.3F, BOPBlocks.DEAD_SAPLING);
        registerCompostable(0.3F, BOPBlocks.DEAD_LEAVES);
        registerCompostable(0.3F, BOPBlocks.MAGIC_SAPLING);
        registerCompostable(0.3F, BOPBlocks.MAGIC_LEAVES);
        registerCompostable(0.3F, BOPBlocks.UMBRAN_SAPLING);
        registerCompostable(0.3F, BOPBlocks.UMBRAN_LEAVES);
        registerCompostable(0.3F, BOPBlocks.HELLBARK_SAPLING);
        registerCompostable(0.3F, BOPBlocks.HELLBARK_LEAVES);

        registerCompostable(0.65F, BOPBlocks.ROSE);
        registerCompostable(0.65F, BOPBlocks.VIOLET);
        registerCompostable(0.65F, BOPBlocks.LAVENDER);
        registerCompostable(0.65F, BOPBlocks.WILDFLOWER);
        registerCompostable(0.65F, BOPBlocks.ORANGE_COSMOS);
        registerCompostable(0.65F, BOPBlocks.PINK_DAFFODIL);
        registerCompostable(0.65F, BOPBlocks.PINK_HIBISCUS);
        registerCompostable(0.65F, BOPBlocks.GLOWFLOWER);
        registerCompostable(0.65F, BOPBlocks.WILTED_LILY);
        registerCompostable(0.65F, BOPBlocks.BURNING_BLOSSOM);

        registerCompostable(0.65F, BOPBlocks.BLUE_HYDRANGEA);
        registerCompostable(0.65F, BOPBlocks.GOLDENROD);

        registerCompostable(0.5F, BOPBlocks.WILLOW_VINE);
        registerCompostable(0.5F, BOPBlocks.SPANISH_MOSS);
        registerCompostable(0.5F, BOPBlocks.SPANISH_MOSS_PLANT);

        registerCompostable(0.5F, BOPBlocks.SPROUT);
        registerCompostable(0.5F, BOPBlocks.BUSH);
        registerCompostable(0.5F, BOPBlocks.CLOVER);
        registerCompostable(0.5F, BOPBlocks.HUGE_CLOVER_PETAL);
        registerCompostable(0.5F, BOPBlocks.DUNE_GRASS);
        registerCompostable(0.5F, BOPBlocks.DESERT_GRASS);
        registerCompostable(0.5F, BOPBlocks.DEAD_GRASS);

        registerCompostable(0.5F, BOPBlocks.CATTAIL);
        registerCompostable(0.5F, BOPBlocks.BARLEY);
        registerCompostable(0.5F, BOPBlocks.REED);
        registerCompostable(0.5F, BOPBlocks.WATERGRASS);
        registerCompostable(0.5F, BOPBlocks.MANGROVE_ROOT);

        registerCompostable(0.3F, BOPBlocks.DEAD_BRANCH);
        registerCompostable(0.3F, BOPBlocks.BRAMBLE);

        registerCompostable(0.65F, BOPBlocks.TOADSTOOL);
        registerCompostable(0.65F, BOPBlocks.GLOWSHROOM);
    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, stripped_log);
    }

    public static void registerTillable(Block block, BlockState tilled_block) {
        HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
        HoeItem.TILLABLES.put(block, Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(tilled_block)));
    }

    public static void registerFlattenable(Block block, BlockState flattened_block) {
        ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
        ShovelItem.FLATTENABLES.put(block, flattened_block);
    }

    public static void registerCompostable(float chance, ItemLike itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFlammable(blockIn, encouragement, flammability);
    }
}
