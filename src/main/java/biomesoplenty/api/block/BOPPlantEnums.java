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

public class BOPPlantEnums
{
    
    public static enum AllPlants implements IStringSerializable {
        
        SHORTGRASS, MEDIUMGRASS, BUSH, SPROUT, POISONIVY, BERRYBUSH, SHRUB, WHEATGRASS, DAMPGRASS, KORU, CLOVERPATCH, LEAFPILE, DEADLEAFPILE, DEADGRASS, DESERTGRASS, DESERTSPROUTS, DUNEGRASS, SPECTRALFERN, THORN, WILDRICE, CATTAIL, RIVERCANE, WILDCARROT, TINYCACTUS, WITHERWART, REED, ROOT;
        
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
    
    public static enum PlantsFilterType {
        ALL;
        public Predicate<AllPlants> getPredicate(final int pageNum, final int numPerPage)
        {
            // final PlantsFilterType filterType = this;
            return new Predicate<AllPlants>()
            {
                @Override
                public boolean apply(AllPlants plant)
                {
                    return (plant.ordinal() >= (numPerPage * pageNum)) && (plant.ordinal() < (numPerPage * (pageNum+1)));
                }
            };
         }
    }
    
    private static Map<Integer, PropertyEnum[]> propCache = new HashMap<Integer, PropertyEnum[]>();

    public static PropertyEnum getVariantProperty(int pageNum, int numPerPage)
    {
        return getVariantProperty(pageNum, numPerPage, PlantsFilterType.ALL);
    }
    
    public static PropertyEnum getVariantProperty(int pageNum, int numPerPage, PlantsFilterType filterType)
    {
        // check length and make sure things are in bounds
        int len = AllPlants.values().length;
        int numPages = (int) Math.ceil( (double)len / numPerPage);
        pageNum = Math.max(0, Math.min(pageNum, numPages - 1));
        
        // look up the array of properties for pages of size numPerPage and the given filter - if it doesn't exist yet, create it
        Integer index = new Integer(numPerPage * PlantsFilterType.values().length + filterType.ordinal() );
        if (!propCache.containsKey(index)) {propCache.put(index, new PropertyEnum[numPages]);}
        
        // look up the property for page pageNum - if it doesn't exist yet, create it
        PropertyEnum[] propArr = propCache.get(index);
        if (propArr[pageNum] == null)
        {
            propArr[pageNum] = PropertyEnum.create("variant", AllPlants.class, filterType.getPredicate(pageNum, numPerPage));
        }
        return propArr[pageNum];
    }
    
}