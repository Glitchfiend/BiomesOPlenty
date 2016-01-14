/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPVine extends BlockVine implements IBOPBlock
{  
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    

    // if set to true, (the default), use BlockVine getBlockColor(), getRenderColor() and colorMultiplier() functions to color the texture based on biome
    // if set to false, use 0xFFFFFF for all the color functions so that the texture is used as it is
    protected boolean useGreyScaleTextures;

    public BlockBOPVine(boolean useGreyScaleTextures)
    {
        super();
        this.setHardness(0.2F);
        this.useGreyScaleTextures = useGreyScaleTextures;
    }
    
    public BlockBOPVine()
    {
        this(true);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return (this.useGreyScaleTextures ? super.getBlockColor() : 0xFFFFFF);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return (this.useGreyScaleTextures ? super.getRenderColor(state) : 0xFFFFFF);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return (this.useGreyScaleTextures ? super.colorMultiplier(worldIn, pos, renderPass) : 0xFFFFFF);
    }
    
    
}
    