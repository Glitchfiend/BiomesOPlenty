package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.IItemProvider;

public class ModVanillaCompat
{
    public static void setup()
    {
        //Flammability
        registerFlammable(BOPBlocks.origin_leaves, 30, 60);
        registerFlammable(BOPBlocks.flowering_oak_leaves, 30, 60);
        registerFlammable(BOPBlocks.yellow_autumn_leaves, 30, 60);
        registerFlammable(BOPBlocks.orange_autumn_leaves, 30, 60);
        registerFlammable(BOPBlocks.maple_leaves, 30, 60);
        registerFlammable(BOPBlocks.fir_leaves, 30, 60);
        registerFlammable(BOPBlocks.fir_log, 5, 5);
        registerFlammable(BOPBlocks.fir_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_fir_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_fir_wood, 5, 5);
        registerFlammable(BOPBlocks.fir_planks, 5, 20);
        registerFlammable(BOPBlocks.fir_slab, 5, 20);
        registerFlammable(BOPBlocks.fir_stairs, 5, 20);
        registerFlammable(BOPBlocks.fir_fence, 5, 20);
        registerFlammable(BOPBlocks.fir_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.redwood_leaves, 30, 60);
        registerFlammable(BOPBlocks.redwood_log, 5, 5);
        registerFlammable(BOPBlocks.redwood_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_redwood_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_redwood_wood, 5, 5);
        registerFlammable(BOPBlocks.redwood_planks, 5, 20);
        registerFlammable(BOPBlocks.redwood_slab, 5, 20);
        registerFlammable(BOPBlocks.redwood_stairs, 5, 20);
        registerFlammable(BOPBlocks.redwood_fence, 5, 20);
        registerFlammable(BOPBlocks.redwood_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.white_cherry_leaves, 30, 60);
        registerFlammable(BOPBlocks.pink_cherry_leaves, 30, 60);
        registerFlammable(BOPBlocks.cherry_log, 5, 5);
        registerFlammable(BOPBlocks.cherry_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_cherry_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_cherry_wood, 5, 5);
        registerFlammable(BOPBlocks.cherry_planks, 5, 20);
        registerFlammable(BOPBlocks.cherry_slab, 5, 20);
        registerFlammable(BOPBlocks.cherry_stairs, 5, 20);
        registerFlammable(BOPBlocks.cherry_fence, 5, 20);
        registerFlammable(BOPBlocks.cherry_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.mahogany_leaves, 30, 60);
        registerFlammable(BOPBlocks.mahogany_log, 5, 5);
        registerFlammable(BOPBlocks.mahogany_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_mahogany_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_mahogany_wood, 5, 5);
        registerFlammable(BOPBlocks.mahogany_planks, 5, 20);
        registerFlammable(BOPBlocks.mahogany_slab, 5, 20);
        registerFlammable(BOPBlocks.mahogany_stairs, 5, 20);
        registerFlammable(BOPBlocks.mahogany_fence, 5, 20);
        registerFlammable(BOPBlocks.mahogany_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_leaves, 30, 60);
        registerFlammable(BOPBlocks.jacaranda_log, 5, 5);
        registerFlammable(BOPBlocks.jacaranda_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_jacaranda_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_jacaranda_wood, 5, 5);
        registerFlammable(BOPBlocks.jacaranda_planks, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_slab, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_stairs, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_fence, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.palm_leaves, 30, 60);
        registerFlammable(BOPBlocks.palm_log, 5, 5);
        registerFlammable(BOPBlocks.palm_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_palm_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_palm_wood, 5, 5);
        registerFlammable(BOPBlocks.palm_planks, 5, 20);
        registerFlammable(BOPBlocks.palm_slab, 5, 20);
        registerFlammable(BOPBlocks.palm_stairs, 5, 20);
        registerFlammable(BOPBlocks.palm_fence, 5, 20);
        registerFlammable(BOPBlocks.palm_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.willow_leaves, 30, 60);
        registerFlammable(BOPBlocks.willow_log, 5, 5);
        registerFlammable(BOPBlocks.willow_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_willow_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_willow_wood, 5, 5);
        registerFlammable(BOPBlocks.willow_planks, 5, 20);
        registerFlammable(BOPBlocks.willow_slab, 5, 20);
        registerFlammable(BOPBlocks.willow_stairs, 5, 20);
        registerFlammable(BOPBlocks.willow_fence, 5, 20);
        registerFlammable(BOPBlocks.willow_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.dead_leaves, 30, 60);
        registerFlammable(BOPBlocks.dead_log, 5, 5);
        registerFlammable(BOPBlocks.dead_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_dead_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_dead_wood, 5, 5);
        registerFlammable(BOPBlocks.dead_planks, 5, 20);
        registerFlammable(BOPBlocks.dead_slab, 5, 20);
        registerFlammable(BOPBlocks.dead_stairs, 5, 20);
        registerFlammable(BOPBlocks.dead_fence, 5, 20);
        registerFlammable(BOPBlocks.dead_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.magic_leaves, 30, 60);
        registerFlammable(BOPBlocks.magic_log, 5, 5);
        registerFlammable(BOPBlocks.magic_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_magic_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_magic_wood, 5, 5);
        registerFlammable(BOPBlocks.magic_planks, 5, 20);
        registerFlammable(BOPBlocks.magic_slab, 5, 20);
        registerFlammable(BOPBlocks.magic_stairs, 5, 20);
        registerFlammable(BOPBlocks.magic_fence, 5, 20);
        registerFlammable(BOPBlocks.magic_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.umbran_leaves, 30, 60);
        registerFlammable(BOPBlocks.umbran_log, 5, 5);
        registerFlammable(BOPBlocks.umbran_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_umbran_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_umbran_wood, 5, 5);
        registerFlammable(BOPBlocks.umbran_planks, 5, 20);
        registerFlammable(BOPBlocks.umbran_slab, 5, 20);
        registerFlammable(BOPBlocks.umbran_stairs, 5, 20);
        registerFlammable(BOPBlocks.umbran_fence, 5, 20);
        registerFlammable(BOPBlocks.umbran_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.rose, 60, 100);
        registerFlammable(BOPBlocks.violet, 60, 100);
        registerFlammable(BOPBlocks.lavender, 60, 100);
        registerFlammable(BOPBlocks.wildflower, 60, 100);
        registerFlammable(BOPBlocks.pink_daffodil, 60, 100);
        registerFlammable(BOPBlocks.pink_hibiscus, 60, 100);
        registerFlammable(BOPBlocks.glowflower, 60, 100);
        registerFlammable(BOPBlocks.wilted_lily, 60, 100);
        registerFlammable(BOPBlocks.blue_hydrangea, 60, 100);
        registerFlammable(BOPBlocks.goldenrod, 60, 100);
        registerFlammable(BOPBlocks.willow_vine, 15, 100);
        registerFlammable(BOPBlocks.spanish_moss, 60, 100);
        registerFlammable(BOPBlocks.spanish_moss_plant, 60, 100);
        registerFlammable(BOPBlocks.tree_roots, 15, 20);
        registerFlammable(BOPBlocks.tree_roots_stem, 15, 20);
        registerFlammable(BOPBlocks.glowworm_silk, 60, 100);
        registerFlammable(BOPBlocks.glowworm_silk_strand, 60, 100);
        registerFlammable(BOPBlocks.hanging_cobweb, 60, 100);
        registerFlammable(BOPBlocks.hanging_cobweb_strand, 60, 100);
        registerFlammable(BOPBlocks.sprout, 60, 100);
        registerFlammable(BOPBlocks.bush, 60, 100);
        registerFlammable(BOPBlocks.clover, 60, 100);
        registerFlammable(BOPBlocks.huge_clover_petal, 60, 100);
        registerFlammable(BOPBlocks.dune_grass, 60, 100);
        registerFlammable(BOPBlocks.desert_grass, 60, 100);
        registerFlammable(BOPBlocks.dead_grass, 60, 100);
        registerFlammable(BOPBlocks.dead_branch, 60, 100);
        registerFlammable(BOPBlocks.barley, 60, 100);

        //Log Stripping
        registerStrippable(BOPBlocks.fir_log, BOPBlocks.stripped_fir_log);
        registerStrippable(BOPBlocks.fir_wood, BOPBlocks.stripped_fir_wood);
        registerStrippable(BOPBlocks.redwood_log, BOPBlocks.stripped_redwood_log);
        registerStrippable(BOPBlocks.redwood_wood, BOPBlocks.stripped_redwood_wood);
        registerStrippable(BOPBlocks.cherry_log, BOPBlocks.stripped_cherry_log);
        registerStrippable(BOPBlocks.cherry_wood, BOPBlocks.stripped_cherry_wood);
        registerStrippable(BOPBlocks.mahogany_log, BOPBlocks.stripped_mahogany_log);
        registerStrippable(BOPBlocks.mahogany_wood, BOPBlocks.stripped_mahogany_wood);
        registerStrippable(BOPBlocks.jacaranda_log, BOPBlocks.stripped_jacaranda_log);
        registerStrippable(BOPBlocks.jacaranda_wood, BOPBlocks.stripped_jacaranda_wood);
        registerStrippable(BOPBlocks.palm_log, BOPBlocks.stripped_palm_log);
        registerStrippable(BOPBlocks.palm_wood, BOPBlocks.stripped_palm_wood);
        registerStrippable(BOPBlocks.willow_log, BOPBlocks.stripped_willow_log);
        registerStrippable(BOPBlocks.willow_wood, BOPBlocks.stripped_willow_wood);
        registerStrippable(BOPBlocks.dead_log, BOPBlocks.stripped_dead_log);
        registerStrippable(BOPBlocks.dead_wood, BOPBlocks.stripped_dead_wood);
        registerStrippable(BOPBlocks.magic_log, BOPBlocks.stripped_magic_log);
        registerStrippable(BOPBlocks.magic_wood, BOPBlocks.stripped_magic_wood);
        registerStrippable(BOPBlocks.umbran_log, BOPBlocks.stripped_umbran_log);
        registerStrippable(BOPBlocks.umbran_wood, BOPBlocks.stripped_umbran_wood);
        registerStrippable(BOPBlocks.hellbark_log, BOPBlocks.stripped_hellbark_log);
        registerStrippable(BOPBlocks.hellbark_wood, BOPBlocks.stripped_hellbark_wood);

        //Tilling and Flattening
        registerTillable(BOPBlocks.origin_grass_block, Blocks.FARMLAND.defaultBlockState());
        registerFlattenable(BOPBlocks.origin_grass_block, Blocks.GRASS_PATH.defaultBlockState());

        //Compostable Blocks
        registerCompostable(0.85F, BOPBlocks.glowshroom_block);
        registerCompostable(0.85F, BOPBlocks.toadstool_block);

        registerCompostable(0.3F, BOPBlocks.origin_sapling);
        registerCompostable(0.3F, BOPBlocks.origin_leaves);
        registerCompostable(0.3F, BOPBlocks.flowering_oak_sapling);
        registerCompostable(0.3F, BOPBlocks.flowering_oak_leaves);
        registerCompostable(0.3F, BOPBlocks.yellow_autumn_sapling);
        registerCompostable(0.3F, BOPBlocks.yellow_autumn_leaves);
        registerCompostable(0.3F, BOPBlocks.orange_autumn_sapling);
        registerCompostable(0.3F, BOPBlocks.orange_autumn_leaves);
        registerCompostable(0.3F, BOPBlocks.maple_sapling);
        registerCompostable(0.3F, BOPBlocks.maple_leaves);
        registerCompostable(0.3F, BOPBlocks.fir_sapling);
        registerCompostable(0.3F, BOPBlocks.fir_leaves);
        registerCompostable(0.3F, BOPBlocks.redwood_sapling);
        registerCompostable(0.3F, BOPBlocks.redwood_leaves);
        registerCompostable(0.3F, BOPBlocks.white_cherry_sapling);
        registerCompostable(0.3F, BOPBlocks.white_cherry_leaves);
        registerCompostable(0.3F, BOPBlocks.pink_cherry_sapling);
        registerCompostable(0.3F, BOPBlocks.pink_cherry_leaves);
        registerCompostable(0.3F, BOPBlocks.mahogany_sapling);
        registerCompostable(0.3F, BOPBlocks.mahogany_leaves);
        registerCompostable(0.3F, BOPBlocks.jacaranda_sapling);
        registerCompostable(0.3F, BOPBlocks.jacaranda_leaves);
        registerCompostable(0.3F, BOPBlocks.palm_sapling);
        registerCompostable(0.3F, BOPBlocks.palm_leaves);
        registerCompostable(0.3F, BOPBlocks.willow_sapling);
        registerCompostable(0.3F, BOPBlocks.willow_leaves);
        registerCompostable(0.3F, BOPBlocks.dead_sapling);
        registerCompostable(0.3F, BOPBlocks.dead_leaves);
        registerCompostable(0.3F, BOPBlocks.magic_sapling);
        registerCompostable(0.3F, BOPBlocks.magic_leaves);
        registerCompostable(0.3F, BOPBlocks.umbran_sapling);
        registerCompostable(0.3F, BOPBlocks.umbran_leaves);
        registerCompostable(0.3F, BOPBlocks.hellbark_sapling);
        registerCompostable(0.3F, BOPBlocks.hellbark_leaves);

        registerCompostable(0.65F, BOPBlocks.rose);
        registerCompostable(0.65F, BOPBlocks.violet);
        registerCompostable(0.65F, BOPBlocks.lavender);
        registerCompostable(0.65F, BOPBlocks.wildflower);
        registerCompostable(0.65F, BOPBlocks.orange_cosmos);
        registerCompostable(0.65F, BOPBlocks.pink_daffodil);
        registerCompostable(0.65F, BOPBlocks.pink_hibiscus);
        registerCompostable(0.65F, BOPBlocks.glowflower);
        registerCompostable(0.65F, BOPBlocks.wilted_lily);
        registerCompostable(0.65F, BOPBlocks.burning_blossom);

        registerCompostable(0.65F, BOPBlocks.blue_hydrangea);
        registerCompostable(0.65F, BOPBlocks.goldenrod);

        registerCompostable(0.5F, BOPBlocks.willow_vine);
        registerCompostable(0.5F, BOPBlocks.spanish_moss);
        registerCompostable(0.5F, BOPBlocks.spanish_moss_plant);

        registerCompostable(0.5F, BOPBlocks.sprout);
        registerCompostable(0.5F, BOPBlocks.bush);
        registerCompostable(0.5F, BOPBlocks.clover);
        registerCompostable(0.5F, BOPBlocks.huge_clover_petal);
        registerCompostable(0.5F, BOPBlocks.dune_grass);
        registerCompostable(0.5F, BOPBlocks.desert_grass);
        registerCompostable(0.5F, BOPBlocks.dead_grass);

        registerCompostable(0.5F, BOPBlocks.cattail);
        registerCompostable(0.5F, BOPBlocks.barley);
        registerCompostable(0.5F, BOPBlocks.reed);
        registerCompostable(0.5F, BOPBlocks.watergrass);
        registerCompostable(0.5F, BOPBlocks.mangrove_root);

        registerCompostable(0.3F, BOPBlocks.dead_branch);
        registerCompostable(0.3F, BOPBlocks.bramble);

        registerCompostable(0.65F, BOPBlocks.toadstool);
        registerCompostable(0.65F, BOPBlocks.glowshroom);
    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);
        AxeItem.STRIPABLES.put(log, stripped_log);
    }

    public static void registerTillable(Block block, BlockState tilled_block) {
        HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
        HoeItem.TILLABLES.put(block, tilled_block);
    }

    public static void registerFlattenable(Block block, BlockState flattened_block) {
        ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
        ShovelItem.FLATTENABLES.put(block, flattened_block);
    }

    public static void registerCompostable(float chance, IItemProvider itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFlammable(blockIn, encouragement, flammability);
    }
}
