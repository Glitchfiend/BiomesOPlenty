/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
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
        Color foliage = Color.getHSBColor((((float)pos.getX() + Mth.sin(((float)pos.getZ() + (float)pos.getX()) / 35) * 35) % 150) / 150, 0.6F, 1.0F);

        return foliage.getRGB();
    }
}
