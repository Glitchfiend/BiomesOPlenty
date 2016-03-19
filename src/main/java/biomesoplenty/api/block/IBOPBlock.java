/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBOPBlock {
    
    public Class<? extends ItemBlock> getItemClass();
    public IProperty[] getPresetProperties();
    public IProperty[] getNonRenderingProperties();
    public String getStateName(IBlockState state);

	@SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor();
	@SideOnly(Side.CLIENT)
    public IItemColor getItemColor();
}