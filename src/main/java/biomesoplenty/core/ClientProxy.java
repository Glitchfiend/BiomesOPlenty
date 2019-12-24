/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;

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
            world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D),
            BOPBlocks.sprout, BOPBlocks.watergrass, BOPBlocks.potted_sprout);
        
        //Foliage Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColors.getDefault(),
	        BOPBlocks.bush, BOPBlocks.flowering_oak_leaves, BOPBlocks.mahogany_leaves, BOPBlocks.palm_leaves,
	        BOPBlocks.willow_leaves, BOPBlocks.willow_vine);
        
        //Item Coloring
        itemColors.register((stack, tintIndex) -> {
            BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(BlockState, null, null, tintIndex); }, 
        	BOPBlocks.sprout, BOPBlocks.bush, BOPBlocks.flowering_oak_leaves, BOPBlocks.mahogany_leaves,
        	BOPBlocks.palm_leaves, BOPBlocks.willow_leaves, BOPBlocks.willow_vine);
    }
}
