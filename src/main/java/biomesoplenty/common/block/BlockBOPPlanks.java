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
import biomesoplenty.api.block.BOPWoodEnums;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.common.item.ItemBOPBlock;

public abstract class BlockBOPPlanks extends Block implements IBOPBlock
{
    
    // setup paged variant property
    
    // All 4 meta bits available for the VARIANT which means we can have sixteen per instance
    public static final int VARIANTS_PER_PAGE = 16;
    // child classes must implement to define their page number
    abstract public int getPageNum();
    // fetch the variant property for a given page
    public static PropertyEnum getVariantProperty(int pageNum)
    {
        return BOPWoodEnums.getVariantProperty(pageNum, VARIANTS_PER_PAGE, BOPWoodEnums.WoodsFilterType.WITH_PLANKS);
    }
    // fetch the current instance's variant property
    public PropertyEnum getMyVariantProperty()
    {
        return getVariantProperty(getPageNum());
    }
    // get the meta bits from the variant
    public int metaFromVariant(AllWoods wood)
    {
        return wood.ordinal() % VARIANTS_PER_PAGE;
    }
    // get the variant from meta bits (safely)
    public AllWoods variantFromMeta(int meta)
    {
        int i = Math.max(0, Math.min(meta + (this.getPageNum() * VARIANTS_PER_PAGE), AllWoods.values().length));
        return AllWoods.values()[i];
    }
    
    // add properties (note we inherit LOG_AXIS property from parent BlockLog)
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { getMyVariantProperty() });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {getMyVariantProperty()}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((AllWoods) state.getValue(getMyVariantProperty())).getName() + "_planks";
    }
    
    
    public BlockBOPPlanks()
    {
        super(Material.wood);
        this.setDefaultState(this.blockState.getBaseState());
        this.setHarvestLevel("axe", 0);
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(getMyVariantProperty(), variantFromMeta(meta));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return metaFromVariant((AllWoods) state.getValue(getMyVariantProperty()));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    public IBlockState getStateByWood(AllWoods wood)
    {
        return this.getDefaultState().withProperty(getMyVariantProperty(), wood);
    }
    
}