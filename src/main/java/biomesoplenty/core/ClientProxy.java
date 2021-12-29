/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {

    }

    @Override
    public void init()
    {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        //Grass Coloring
        blockColors.register((state, world, pos, tintIndex) ->
            world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D),
            BOPBlocks.SPROUT, BOPBlocks.CLOVER, BOPBlocks.HUGE_CLOVER_PETAL, BOPBlocks.WATERGRASS, BOPBlocks.POTTED_SPROUT, BOPBlocks.POTTED_CLOVER);
        
        //Foliage Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
	        BOPBlocks.BUSH, BOPBlocks.FLOWERING_OAK_LEAVES, BOPBlocks.MAHOGANY_LEAVES, BOPBlocks.PALM_LEAVES,
	        BOPBlocks.WILLOW_LEAVES, BOPBlocks.WILLOW_VINE);

        //Rainbow Birch Leaf Coloring
        blockColors.register((state, world, pos, tintIndex) ->
            world != null && pos != null ? getRainbowBirchColor(world, pos) : FoliageColor.getDefaultColor(),
            BOPBlocks.RAINBOW_BIRCH_LEAVES);
        
        //Item Coloring
        itemColors.register((stack, tintIndex) -> {
            BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(state, null, null, tintIndex); },
        	BOPBlocks.SPROUT, BOPBlocks.BUSH, BOPBlocks.CLOVER, BOPBlocks.HUGE_CLOVER_PETAL, BOPBlocks.FLOWERING_OAK_LEAVES,
            BOPBlocks.MAHOGANY_LEAVES, BOPBlocks.PALM_LEAVES, BOPBlocks.WILLOW_LEAVES, BOPBlocks.WILLOW_VINE);
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
