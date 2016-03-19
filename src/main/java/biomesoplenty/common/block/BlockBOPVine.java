/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks.Coloring;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.init.ModBlocks;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPVine extends BlockVine implements IBOPBlock
{  
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    @Override
    public IBlockColor getBlockColor() { return useGreyScaleTextures ? Coloring.FOLIAGE_COLORING : null; }
    @Override
    public IItemColor getItemColor() { return Coloring.BLOCK_ITEM_COLORING; }
    
    // if set to true, (the default), use BlockVine getBlockColor(), getRenderColor() and colorMultiplier() functions to color the texture based on biome
    // if set to false, use 0xFFFFFF for all the color functions so that the texture is used as it is
    protected boolean useGreyScaleTextures;

    public BlockBOPVine(boolean useGreyScaleTextures)
    {
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.2F);
        this.useGreyScaleTextures = useGreyScaleTextures;
    }
    
    public BlockBOPVine()
    {
        this(true);
    }

    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return Blocks.vine.getFlammability(world, pos, face);
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return Blocks.vine.getFlammability(world, pos, face);
    }
}
    