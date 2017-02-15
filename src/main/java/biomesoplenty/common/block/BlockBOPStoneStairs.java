/*******************************************************************************
 * Copyright 2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPStoneStairs extends BlockStairs implements IBOPBlock
{
    // implement IBOPBlock
    // inherited properties from BlockStairs : FACING, HALF, SHAPE
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() { return null; }
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() { return null; }

    public BlockBOPStoneStairs(IBlockState modelState)
    {
        super(modelState);
        this.setHarvestLevel("pickaxe", 0);
        this.useNeighborBrightness = true;
    }
}
