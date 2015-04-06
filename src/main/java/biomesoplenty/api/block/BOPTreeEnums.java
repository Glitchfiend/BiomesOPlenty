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

public class BOPTreeEnums
{
    
    public static enum AllTrees implements IStringSerializable {
        
        YELLOW_AUTUMN, ORANGE_AUTUMN, BAMBOO, MAGIC, DARK, DEAD, FIR, ETHEREAL, ORIGIN, PINK_CHERRY, WHITE_CHERRY, MAPLE, HELLBARK, FLOWERING, JACARANDA, SACRED_OAK, MANGROVE, PALM, REDWOOD, WILLOW, PINE, MAHOGANY, RED_BIG_FLOWER, YELLOW_BIG_FLOWER;
        
        @Override
        public String getName() {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    }
    
    public static enum TreesFilterType {
        ALL, SAPLINGS;
        public Predicate<AllTrees> getPredicate(final int pageNum, final int numPerPage)
        {
            final TreesFilterType filterType = this;
            return new Predicate<AllTrees>()
            {
                @Override
                public boolean apply(AllTrees tree)
                {
                    if (filterType == SAPLINGS && (tree == AllTrees.YELLOW_BIG_FLOWER || tree == AllTrees.RED_BIG_FLOWER) )
                    {
                        return false;
                    }
                    return (tree.ordinal() >= (numPerPage * pageNum)) && (tree.ordinal() < (numPerPage * (pageNum+1)));
                }
            };
         }
    }
    
    private static Map<Integer, PropertyEnum[]> propCache = new HashMap<Integer, PropertyEnum[]>();

    public static PropertyEnum getVariantProperty(int pageNum, int numPerPage)
    {
        return getVariantProperty(pageNum, numPerPage, TreesFilterType.ALL);
    }
    
    public static PropertyEnum getVariantProperty(int pageNum, int numPerPage, TreesFilterType filterType)
    {
        // check length and make sure things are in bounds
        int len = AllTrees.values().length;
        int numPages = (int) Math.ceil( (double)len / numPerPage);
        pageNum = Math.max(0, Math.min(pageNum, numPages - 1));
        
        // look up the array of properties for pages of size numPerPage and the given filter - if it doesn't exist yet, create it
        Integer index = new Integer(numPerPage * TreesFilterType.values().length + filterType.ordinal() );
        if (!propCache.containsKey(index)) {propCache.put(index, new PropertyEnum[numPages]);}
        
        // look up the property for page pageNum - if it doesn't exist yet, create it
        PropertyEnum[] propArr = propCache.get(index);
        if (propArr[pageNum] == null)
        {
            propArr[pageNum] = PropertyEnum.create("variant", AllTrees.class, filterType.getPredicate(pageNum, numPerPage));
        }
        return propArr[pageNum];
    }
    
}