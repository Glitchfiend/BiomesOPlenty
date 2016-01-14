/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;


public class VariantPagingHelper<B extends Block, V extends Enum<V> & IStringSerializable & VariantPagingHelper.IPagedVariants>
{
    
    public interface IPagedVariants
    {
        
    }

    public class PageIndex
    {
        private int pageNum;
        private int index;
        public PageIndex(int pageNum, int index)
        {
            this.pageNum = pageNum;
            this.index = index;
        }
        public int getPageNum()
        {
            return this.pageNum;
        }
        public int getIndex()
        {
            return this.index;
        }
        @Override
        public String toString()
        {
            return "page:"+this.pageNum+" index:"+this.index;
        }
    }
    
    protected int variantsPerPage;
    protected Class<V> variantsEnumClass;
    protected Map<Integer, PropertyEnum> pageNumToProperty = new HashMap<Integer, PropertyEnum>();
    protected Map<V, PageIndex> variantToPageIndex = new HashMap<V, PageIndex>();
    protected Map<Integer, V> masterIndexToVariant = new HashMap<Integer, V>();
    protected Map<Integer, B> pageNumToBlock = new HashMap<Integer, B>();
    protected Map<B, Integer> blockToPageNum = new HashMap<B, Integer>();
    
    public VariantPagingHelper(int variantsPerPage, Class<V> variantsEnumClass)
    {
        this(variantsPerPage, variantsEnumClass, Predicates.<V>alwaysTrue());
    }
    
    public VariantPagingHelper(int variantsPerPage, Class<V> variantsEnumClass, Predicate<V> filter)
    {
 
        this.variantsPerPage = variantsPerPage;
        this.variantsEnumClass = variantsEnumClass;
        
        Object[] variants = variantsEnumClass.getEnumConstants();
        if (variants == null) { throw new IllegalArgumentException("Failed creating PagedBlock - variantsEnumClass has no enum constants - is it an enum class?"); }
        
        ArrayList<V> currentPage = new ArrayList<V>();
        int currentPageNum = 0;
        int index = 0;
        
        for (Object obj : variants)
        {
            V variant = (V)obj;
            if (!filter.apply(variant)) {continue;}
            currentPage.add(variant);
            if (currentPage.size() == variantsPerPage)
            {
                this.addVariantPage(currentPageNum, currentPage);
                currentPage = new ArrayList<V>();
                currentPageNum++;
            }
        }
        if (!currentPage.isEmpty())
        {
            this.addVariantPage(currentPageNum, currentPage);
        }
                
    }
    
    protected void addVariantPage(int pageNum, ArrayList<V> variants)
    {
        for (int index = 0; index < variants.size(); ++index)
        {
            V variant = variants.get(index);
            this.variantToPageIndex.put(variant, new PageIndex(pageNum, index));
            this.masterIndexToVariant.put(Integer.valueOf(pageNum * this.variantsPerPage + index), variant);           
        }
        this.pageNumToProperty.put(Integer.valueOf(pageNum), PropertyEnum.create("variant", this.variantsEnumClass, variants));
    }
    
    public void addBlock(int pageNum, B block)
    {
        this.pageNumToBlock.put(pageNum, block);
        this.blockToPageNum.put(block, pageNum);
    }
        
    
    public int getVariantsPerPage()
    {
        return this.variantsPerPage;
    }
    public int getNumPages()
    {
        return this.pageNumToProperty.size();
    }
    public PropertyEnum getVariantProperty(int pageNum)
    {
        return this.pageNumToProperty.get(pageNum);
    }
    public int getIndex(V variant)
    {
        return this.variantToPageIndex.get(variant).getIndex();
    }
    public int getPageNum(V variant)
    {
        return this.variantToPageIndex.get(variant).getPageNum();
    }
    public int getPageNum(B block)
    {
        return this.blockToPageNum.get(block);
    }
    public V getVariant(int pageNum, int index)
    {
        V value = this.masterIndexToVariant.get(pageNum * this.variantsPerPage + index);
        
        //Use the default value if we can't find one for this index
        if (value == null)
            value = (V)this.getBlock(pageNum).getDefaultState().getValue(this.getVariantProperty(pageNum));
            
        return value;
    }
    public V getVariant(B block, int index)
    {
        return this.getVariant(this.getPageNum(block), index);
    }
    public B getBlock(int pageNum)
    {
        return this.pageNumToBlock.get(pageNum);
    }
    public B getBlock(V variant)
    {
        return this.pageNumToBlock.get(this.getPageNum(variant));
    }
    public IBlockState getVariantState(V variant)
    {
        return this.getBlock(variant).getDefaultState().withProperty(this.getVariantProperty(this.getPageNum(variant)), variant);
    }
    public ItemStack getVariantItem(V variant)
    {
        return this.getVariantItem(variant, 1);
    }
    public ItemStack getVariantItem(V variant, int howMany)
    {
        return new ItemStack(this.getBlock(variant), howMany, this.getBlock(variant).getMetaFromState(this.getVariantState(variant)));
    }
    
    
}