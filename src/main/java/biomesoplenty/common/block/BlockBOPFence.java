/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.Map;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBOPFence extends BlockFence implements IBOPBlock
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
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    // Map from woods to BlockBOPFence instance and back
    private static Map<BOPWoods, BlockBOPFence> variantToBlock = new HashMap<BOPWoods, BlockBOPFence>();
    public static BlockBOPFence getBlock(BOPWoods wood)
    {
        return variantToBlock.get(wood);
    }
    protected BOPWoods wood;
    public BOPWoods getWood()
    {
        return this.wood;
    }
    
    public BlockBOPFence(BOPWoods wood)
    {
        super(Material.wood, MapColor.woodColor);
        this.setHarvestLevel("axe", 0);
        
        this.wood = wood;
        variantToBlock.put(wood, this);
        
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return Blocks.planks.getFlammability(world, pos, face);
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return Blocks.planks.getFireSpreadSpeed(world, pos, face);
    }
}
    