/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.enums;

import com.google.common.base.Predicate;

import net.minecraft.util.IStringSerializable;

public enum BOPWoods implements IStringSerializable, IPagedVariants
{
    
    SACRED_OAK, CHERRY, UMBRAN, FIR, ETHEREAL, MAGIC, MANGROVE, PALM, REDWOOD, WILLOW, PINE, HELLBARK, JACARANDA, MAHOGANY, EBONY, EUCALYPTUS, GIANT_FLOWER, DEAD;
    
    @Override
    public String getName() {
        return this.name().toLowerCase();
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
    public boolean hasPlanks()
    {
        switch (this) {
            case GIANT_FLOWER: case DEAD:
                return false;
            default:
                return true;
        }
    }
    public boolean canMakeCharcoal()
    {
        switch (this) {
            case GIANT_FLOWER:
                return false;
            default:
                return true;
        }
    }
    
    public static Predicate withPlanks = new Predicate<BOPWoods>()
    {
        @Override
        public boolean apply(BOPWoods wood) {return wood.hasPlanks();}
    };
}