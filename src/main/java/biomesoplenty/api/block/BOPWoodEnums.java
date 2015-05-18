/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Predicate;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public class BOPWoodEnums
{
    
    public static enum AllWoods implements IStringSerializable {
        SACRED_OAK, CHERRY, DARK, FIR, ETHEREAL, MAGIC, MANGROVE, PALM, REDWOOD, WILLOW, PINE, HELLBARK, JACARANDA, MAHOGANY, GIANT_FLOWER, DEAD;
        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
        public boolean hasPlanks() {
            switch (this) {
                case GIANT_FLOWER: case DEAD:
                    return false;
                default:
                    return true;
            }
        }
    }
    
    
    public static enum WoodsFilterType {
        ALL, WITH_PLANKS;
        public Predicate<AllWoods> getPredicate(final int pageNum, final int numPerPage)
        {
            final WoodsFilterType filterType = this;
            return new Predicate<AllWoods>()
            {
                @Override
                public boolean apply(AllWoods wood)
                {
                    if (filterType == WITH_PLANKS && !wood.hasPlanks())
                    {
                        return false;
                    }
                    return (wood.ordinal() >= (numPerPage * pageNum)) && (wood.ordinal() < (numPerPage * (pageNum+1)));
                }
            };
         }
    }
    
    private static Map<Integer, PropertyEnum[]> propCache = new HashMap<Integer, PropertyEnum[]>();

    public static PropertyEnum getVariantProperty(int pageNum, int numPerPage)
    {
        return getVariantProperty(pageNum, numPerPage, WoodsFilterType.ALL);
    }
    
    public static PropertyEnum getVariantProperty(int pageNum, int numPerPage, WoodsFilterType filterType)
    {
        // check length and make sure things are in bounds
        int len = AllWoods.values().length;
        int numPages = (int) Math.ceil( (double)len / numPerPage);
        pageNum = Math.max(0, Math.min(pageNum, numPages - 1));
        
        // look up the array of properties for pages of size numPerPage and the given filter - if it doesn't exist yet, create it
        Integer index = new Integer(numPerPage * WoodsFilterType.values().length + filterType.ordinal() );
        if (!propCache.containsKey(index)) {propCache.put(index, new PropertyEnum[numPages]);}
        
        // look up the property for page pageNum - if it doesn't exist yet, create it
        PropertyEnum[] propArr = propCache.get(index);
        if (propArr[pageNum] == null)
        {
            propArr[pageNum] = PropertyEnum.create("variant", AllWoods.class, filterType.getPredicate(pageNum, numPerPage));
        }
        return propArr[pageNum];
    }
    
    
    

}