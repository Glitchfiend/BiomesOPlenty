/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockColoring
{
    public static final IBlockColor FOLIAGE_COLORING = new IBlockColor()
    {
        @Override
        public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex)
        {
            return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
        }
    };

    public static final IBlockColor GRASS_COLORING = new IBlockColor()
    {
        @Override
    	@SideOnly(Side.CLIENT)
        public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex)
        {
            return world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
        }
    };
    
    public static final IItemColor BLOCK_ITEM_COLORING = (stack, tintIndex) -> {
        IBlockState state = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
        IBlockColor blockColor = ((IBOPBlock)state.getBlock()).getBlockColor();
        return blockColor == null ? 0xFFFFFF : blockColor.colorMultiplier(state, null, null, tintIndex);
    };
}