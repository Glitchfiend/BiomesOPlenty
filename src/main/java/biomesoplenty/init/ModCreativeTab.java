/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.List;

public class ModCreativeTab
{
    private static final List<RegistryObject<Item>> ITEM_BLACKLIST = ImmutableList.of(BOPItems.BOP_ICON);

    private static final List<RegistryObject<Block>> BLOCK_BLACKLIST = ImmutableList.of(BOPBlocks.BLOOD, BOPBlocks.HIGH_GRASS_PLANT, BOPBlocks.SPANISH_MOSS_PLANT,
            BOPBlocks.GLOWWORM_SILK_STRAND, BOPBlocks.HANGING_COBWEB_STRAND, BOPBlocks.FLESH_TENDONS_STRAND, BOPBlocks.POTTED_ORIGIN_SAPLING,
            BOPBlocks.POTTED_FLOWERING_OAK_SAPLING, BOPBlocks.POTTED_SNOWBLOSSOM_SAPLING, BOPBlocks.POTTED_RAINBOW_BIRCH_SAPLING,
            BOPBlocks.POTTED_YELLOW_AUTUMN_SAPLING, BOPBlocks.POTTED_ORANGE_AUTUMN_SAPLING, BOPBlocks.POTTED_MAPLE_SAPLING, BOPBlocks.POTTED_FIR_SAPLING,
            BOPBlocks.POTTED_REDWOOD_SAPLING, BOPBlocks.POTTED_MAHOGANY_SAPLING, BOPBlocks.POTTED_JACARANDA_SAPLING, BOPBlocks.POTTED_PALM_SAPLING,
            BOPBlocks.POTTED_WILLOW_SAPLING, BOPBlocks.POTTED_DEAD_SAPLING, BOPBlocks.POTTED_MAGIC_SAPLING, BOPBlocks.POTTED_UMBRAN_SAPLING,
            BOPBlocks.POTTED_HELLBARK_SAPLING, BOPBlocks.POTTED_ROSE, BOPBlocks.POTTED_VIOLET, BOPBlocks.POTTED_LAVENDER, BOPBlocks.POTTED_WILDFLOWER,
            BOPBlocks.POTTED_ORANGE_COSMOS, BOPBlocks.POTTED_PINK_DAFFODIL, BOPBlocks.POTTED_PINK_HIBISCUS, BOPBlocks.POTTED_GLOWFLOWER,
            BOPBlocks.POTTED_WILTED_LILY, BOPBlocks.POTTED_BURNING_BLOSSOM, BOPBlocks.POTTED_SPROUT, BOPBlocks.POTTED_TOADSTOOL, BOPBlocks.POTTED_GLOWSHROOM);

    public static void setup()
    {
        BiomesOPlenty.CREATIVE_TAB_REGISTER.register("main", () ->
            CreativeModeTab.builder()
                .icon(() -> new ItemStack(BOPItems.BOP_ICON.get()))
                .title(Component.translatable("itemGroup.biomesoplenty"))
                .displayItems((displayParameters, output) -> {
                    // Add blocks
                    for (Field field : BOPBlocks.class.getFields())
                    {
                        if (field.getType() != RegistryObject.class) continue;

                        try
                        {
                            RegistryObject<Block> block = (RegistryObject)field.get(null);
                            if (!BLOCK_BLACKLIST.contains(block))
                                output.accept(new ItemStack(block.get()));
                        }
                        catch (IllegalAccessException e) {}
                    }

                    // Add items
                    for (Field field : BOPItems.class.getFields())
                    {
                        if (field.getType() != RegistryObject.class) continue;

                        try
                        {
                            RegistryObject<Item> item = (RegistryObject)field.get(null);
                            if (!ITEM_BLACKLIST.contains(item))
                                output.accept(new ItemStack(item.get()));
                        }
                        catch (IllegalAccessException e) {}
                    }
                }).build()
        );
    }
}
