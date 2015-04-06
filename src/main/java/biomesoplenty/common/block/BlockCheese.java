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
import net.minecraft.util.IStringSerializable;

public abstract class BlockCheese extends Block
{
    
    public enum AllCheeses implements IStringSerializable
    {
        CHEDDAR, EDAM, MOZARELLA, STILTON, PARMESAN, BRIE, LANCS, WENSLEYDALE, GRUYERE, COMTE;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    }
    
    // store the variant properties for each page in this array
    private static PropertyEnum[] variantProperties;
    // number of variants per page
    public static final int variantsPerPage = 4;
    // fetch a particular page's variant property
    // the first time this is called, it also sets up the array of variant properties
    protected static PropertyEnum getVariantProperty(int pageNum)
    {
        int len = AllCheeses.values().length;
        int numPages = (int) Math.ceil( (double)len / variantsPerPage);
        if (variantProperties == null)
        {
            variantProperties = new PropertyEnum[numPages];
        }
        pageNum = Math.max(0, Math.min(pageNum, numPages - 1));
        if (variantProperties[pageNum] == null)
        {
            variantProperties[pageNum] = PropertyEnum.create("variant", AllCheeses.class, getVariantEnumFilter(pageNum));
        }
        return variantProperties[pageNum];
    }
    // child classes must implement to define their page number
    abstract public int getPageNum();
    // fetch the current instance's variant property
    public PropertyEnum getMyVariantProperty()
    {
        return getVariantProperty(this.getPageNum());
    }
    // define the filter function used to reduce the set of enum values to the subset for the given page
    protected static Predicate<AllCheeses> getVariantEnumFilter(final int pageNum)
    {
        return new Predicate<AllCheeses>()
        {
            @Override
            public boolean apply(AllCheeses cheese)
            {
                return (cheese.ordinal() >= (variantsPerPage * pageNum)) && (cheese.ordinal() < (variantsPerPage * (pageNum+1)));
            }
        };
    }
    public int metaFromVariant(AllCheeses type)
    {
        return type.ordinal() % variantsPerPage;
    }
    public AllCheeses variantFromMeta(int meta)
    {
        int i = Math.max(0, Math.min(meta + (this.getPageNum() * variantsPerPage), AllCheeses.values().length)); // clamp to 
        return AllCheeses.values()[i];
    }
    
        
    
    
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { getMyVariantProperty() });
    }
    
    public BlockCheese()
    {
        super(Material.cake);
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(getMyVariantProperty(), variantFromMeta(meta & 3)); // in addition to other properties
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return metaFromVariant((AllCheeses) state.getValue(getMyVariantProperty())); // plus bits from other properties
    }
        
    
}