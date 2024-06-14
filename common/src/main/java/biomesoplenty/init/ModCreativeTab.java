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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;

public class ModCreativeTab
{
    public static void registerCreativeTabs(BiConsumer<ResourceLocation, CreativeModeTab> func)
    {
        List<Item> ITEM_BLACKLIST = ImmutableList.of(BOPItems.BOP_ICON, BOPItems.BLOOD, BOPItems.LIQUID_NULL, BOPItems.HIGH_GRASS_PLANT, BOPItems.SPANISH_MOSS_PLANT,
                BOPItems.LUMALOOP_PLANT, BOPItems.GLOWWORM_SILK_STRAND, BOPItems.HANGING_COBWEB_STRAND, BOPItems.STRINGY_COBWEB, BOPItems.FLESH_TENDONS_STRAND,
                BOPItems.POTTED_ORIGIN_SAPLING, BOPItems.POTTED_FLOWERING_OAK_SAPLING, BOPItems.POTTED_CYPRESS_SAPLING, BOPItems.POTTED_SNOWBLOSSOM_SAPLING,
                BOPItems.POTTED_RAINBOW_BIRCH_SAPLING, BOPItems.POTTED_FIR_SAPLING, BOPItems.POTTED_PINE_SAPLING, BOPItems.POTTED_RED_MAPLE_SAPLING,
                BOPItems.POTTED_ORANGE_MAPLE_SAPLING, BOPItems.POTTED_YELLOW_MAPLE_SAPLING, BOPItems.POTTED_REDWOOD_SAPLING, BOPItems.POTTED_MAHOGANY_SAPLING,
                BOPItems.POTTED_JACARANDA_SAPLING, BOPItems.POTTED_PALM_SAPLING, BOPItems.POTTED_WILLOW_SAPLING, BOPItems.POTTED_DEAD_SAPLING,
                BOPItems.POTTED_MAGIC_SAPLING, BOPItems.POTTED_UMBRAN_SAPLING, BOPItems.POTTED_HELLBARK_SAPLING, BOPItems.POTTED_EMPYREAL_SAPLING,
                BOPItems.POTTED_ROSE, BOPItems.POTTED_VIOLET, BOPItems.POTTED_LAVENDER, BOPItems.POTTED_WHITE_LAVENDER, BOPItems.POTTED_ORANGE_COSMOS,
                BOPItems.POTTED_PINK_DAFFODIL, BOPItems.POTTED_PINK_HIBISCUS, BOPItems.POTTED_GLOWFLOWER, BOPItems.POTTED_WILTED_LILY,
                BOPItems.POTTED_BURNING_BLOSSOM, BOPItems.POTTED_ENDBLOOM, BOPItems.POTTED_SPROUT, BOPItems.POTTED_TINY_CACTUS, BOPItems.POTTED_TOADSTOOL,
                BOPItems.POTTED_GLOWSHROOM);

        var tab = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(BOPItems.BOP_ICON))
            .title(Component.translatable("itemGroup.biomesoplenty"))
            .displayItems((displayParameters, output) ->
            {
                for (Field field : BOPItems.class.getFields())
                {
                    if (field.getType() != Item.class) continue;

                    try
                    {
                        Item item = (Item) field.get(null);

                        if (item == null)
                            throw new IllegalStateException("Field " + field.getName() + " cannot be null!");

                        if (!ITEM_BLACKLIST.contains(item))
                            output.accept(new ItemStack(item));
                    }
                    catch (IllegalAccessException e)
                    {
                    }
                }
            }).build();

        register(func, "main", tab);
    }

    private static CreativeModeTab register(BiConsumer<ResourceLocation, CreativeModeTab> func, String name, CreativeModeTab tab)
    {
        func.accept(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), tab);
        return tab;
    }
}
