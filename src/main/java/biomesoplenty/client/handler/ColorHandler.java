/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.handler;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

public class ColorHandler
{
    @SubscribeEvent
    public void registerItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register((stack, tintIndex) -> {
                    BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
                    return event.getBlockColors().getColor(state, null, null, tintIndex); },
                BOPBlocks.SPROUT.get(), BOPBlocks.BUSH.get(), BOPBlocks.CLOVER.get(), BOPBlocks.HUGE_CLOVER_PETAL.get(), BOPBlocks.FLOWERING_OAK_LEAVES.get(),
                BOPBlocks.MAHOGANY_LEAVES.get(), BOPBlocks.PALM_LEAVES.get(), BOPBlocks.WILLOW_LEAVES.get(), BOPBlocks.WILLOW_VINE.get());
    }

    @SubscribeEvent
    public void registerBlockColors(ColorHandlerEvent.Block event)
    {
        //Grass Coloring
        event.getBlockColors().register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D),
                BOPBlocks.SPROUT.get(), BOPBlocks.CLOVER.get(), BOPBlocks.HUGE_CLOVER_PETAL.get(), BOPBlocks.WATERGRASS.get(), BOPBlocks.POTTED_SPROUT.get(), BOPBlocks.POTTED_CLOVER.get());

        //Foliage Coloring
        event.getBlockColors().register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
                BOPBlocks.BUSH.get(), BOPBlocks.FLOWERING_OAK_LEAVES.get(), BOPBlocks.MAHOGANY_LEAVES.get(), BOPBlocks.PALM_LEAVES.get(),
                BOPBlocks.WILLOW_LEAVES.get(), BOPBlocks.WILLOW_VINE.get());

        //Rainbow Birch Leaf Coloring
        event.getBlockColors().register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? getRainbowBirchColor(world, pos) : FoliageColor.getDefaultColor(),
                BOPBlocks.RAINBOW_BIRCH_LEAVES.get());
    }

    public static int getRainbowBirchColor(BlockAndTintGetter world, BlockPos pos)
    {
        float saturation;
        if (world.getBlockState(pos.above()).is(BlockTags.SNOW))
        {
            saturation = 0.25F;
        }
        else
        {
            saturation = 0.5F;
        }

        Color foliage = Color.getHSBColor((((float)pos.getX() + Mth.sin(((float)pos.getZ() + (float)pos.getX()) / 35) * 35) % 150) / 150, saturation, 1.0F);

        return foliage.getRGB();
    }
}
