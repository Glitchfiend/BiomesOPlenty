/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.api.block.BOPWoodEnums.SixteenWoods;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockBOPPlanks extends Block implements IBOPBlock
{
    
    // add properties (note we inherit LOG_AXIS property from parent BlockLog)
    // all 4 bits available for VARIANT, which means we can have 16 woods per instance
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", SixteenWoods.class );
    protected int pageNum;
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getRenderProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((SixteenWoods) state.getValue(VARIANT)).map(this.pageNum).getName() + "_planks";
    }
    
    
    public BlockBOPPlanks(int pageNum)
    {
        super(Material.wood);
        this.pageNum = pageNum;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, SixteenWoods.A));
        this.setHarvestLevel("axe", 0);
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, SixteenWoods.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((SixteenWoods) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    public IBlockState getStateByWood(AllWoods wood)
    {
        return this.getDefaultState().withProperty(VARIANT, SixteenWoods.mapFrom(wood));
    }
    
}