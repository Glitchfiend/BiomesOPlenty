/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.common.item.ItemBOPBlock;

public abstract class BlockBOPPlanks extends Block implements IBOPBlock
{
    
    
    // store the variant properties for each page in this array
    private static PropertyEnum[] variantProperties;
    // number of variants per page - all 4 meta bits are available so we can have 16
    public static final int variantsPerPage = 16;
    // fetch a particular page's variant property
    // the first time this is called, it also sets up the array of variant properties
    protected static PropertyEnum getVariantProperty(int pageNum)
    {
        int len = AllWoods.values().length;
        int numPages = (int) Math.ceil( (double)len / variantsPerPage);
        if (variantProperties == null)
        {
            variantProperties = new PropertyEnum[numPages];
        }
        pageNum = Math.max(0, Math.min(pageNum, numPages - 1));
        if (variantProperties[pageNum] == null)
        {
            variantProperties[pageNum] = PropertyEnum.create("variant", AllWoods.class, getVariantEnumFilter(pageNum));
        }
        return variantProperties[pageNum];
    }
    // define the filter function used to reduce the set of enum values to the subset for the given page
    protected static Predicate<AllWoods> getVariantEnumFilter(final int pageNum)
    {
        return new Predicate<AllWoods>()
        {
            @Override
            public boolean apply(AllWoods wood)
            {
                switch (wood)
                {
                    case GIANT_FLOWER: case DEAD:
                        return false;
                    default:
                        return (wood.ordinal() >= (variantsPerPage * pageNum)) && (wood.ordinal() < (variantsPerPage * (pageNum+1)));
                }                
            }
        };
    }
    // child classes must implement to define their page number
    abstract public int getPageNum();
    // fetch the current instance's variant property
    public PropertyEnum getMyVariantProperty()
    {
        return getVariantProperty(this.getPageNum());
    }
    public int metaFromVariant(AllWoods wood)
    {
        return wood.ordinal() % variantsPerPage;
    }
    public AllWoods variantFromMeta(int meta)
    {
        int i = Math.max(0, Math.min(meta + (this.getPageNum() * variantsPerPage), AllWoods.values().length)); // clamp to 
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