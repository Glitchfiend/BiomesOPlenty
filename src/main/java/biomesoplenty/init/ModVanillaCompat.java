package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;

public class ModVanillaCompat
{
    public static void init()
    {
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
        registerStrippable(BOPBlocks.ethereal_log, BOPBlocks.stripped_ethereal_log);
        registerStrippable(BOPBlocks.ethereal_wood, BOPBlocks.stripped_ethereal_wood);

        //Compostable Blocks
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
        registerCompostable(0.3F, BOPBlocks.ethereal_sapling);
        registerCompostable(0.3F, BOPBlocks.ethereal_leaves);

        registerCompostable(0.65F, BOPBlocks.rose);
        registerCompostable(0.65F, BOPBlocks.blue_hydrangea);
        registerCompostable(0.65F, BOPBlocks.violet);
        registerCompostable(0.65F, BOPBlocks.lavender);
        registerCompostable(0.65F, BOPBlocks.goldenrod);
        registerCompostable(0.65F, BOPBlocks.wildflower);
        registerCompostable(0.65F, BOPBlocks.orange_cosmos);
        registerCompostable(0.65F, BOPBlocks.pink_daffodil);
        registerCompostable(0.65F, BOPBlocks.pink_hibiscus);
        registerCompostable(0.65F, BOPBlocks.glowflower);
        registerCompostable(0.65F, BOPBlocks.wilted_lily);
        registerCompostable(0.65F, BOPBlocks.burning_blossom);

        registerCompostable(0.5F, BOPBlocks.willow_vine);

        registerCompostable(0.5F, BOPBlocks.bush);
        registerCompostable(0.5F, BOPBlocks.barley);
        registerCompostable(0.5F, BOPBlocks.dune_grass);
        registerCompostable(0.5F, BOPBlocks.desert_grass);
        registerCompostable(0.5F, BOPBlocks.dead_grass);
        registerCompostable(0.5F, BOPBlocks.spectral_fern);

        registerCompostable(0.5F, BOPBlocks.cattail);
        registerCompostable(0.5F, BOPBlocks.tall_cattail);

        registerCompostable(0.5F, BOPBlocks.reed);
        registerCompostable(0.5F, BOPBlocks.watergrass);
        registerCompostable(0.5F, BOPBlocks.mangrove_root);

        registerCompostable(0.5F, BOPBlocks.bramble);

        registerCompostable(0.65F, BOPBlocks.toadstool);
        registerCompostable(0.65F, BOPBlocks.glowshroom);
    }

    public static void registerStrippable(Block log, Block stripped_log)
    {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped_log);
    }

    public static void registerCompostable(float chance, IItemProvider itemIn)
    {
        ComposterBlock.CHANCES.put(itemIn.asItem(), chance);
    }
}
