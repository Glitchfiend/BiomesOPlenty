/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import com.google.common.base.Predicate;

import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public abstract class BlockBOPLog extends BlockLog implements IBOPBlock
{
    
    
    // store the variant properties for each page in this array
    private static PropertyEnum[] variantProperties;
    // number of variants per page
    public static final int variantsPerPage = 4;
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
                return (wood.ordinal() >= (variantsPerPage * pageNum)) && (wood.ordinal() < (variantsPerPage * (pageNum+1)));
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
    
    
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { LOG_AXIS, getMyVariantProperty() });}
    
    
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
        AllWoods wood = (AllWoods) state.getValue(getMyVariantProperty());
        switch (wood)
        {
            case GIANT_FLOWER:
                return wood.getName() + "_stem";
            default:
                return wood.getName() + "_log";
        }
    }

    
    public BlockBOPLog()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.setHarvestLevel("axe", 0);
    }
    
    // map from state to meta and vice verca - use high 2 bits for LOG_AXIS, low 2 bits for VARIANT
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]).withProperty(getMyVariantProperty(), variantFromMeta(meta & 3));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal() * 4 + metaFromVariant((AllWoods) state.getValue(getMyVariantProperty()));
    }

    // discard the axis information - otherwise logs facing different directions would not stack together
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }
    
    
}
