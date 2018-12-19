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
	FIR, REDWOOD, CHERRY, MAHOGANY, JACARANDA, PALM, WILLOW, DEAD, MAGIC, UMBRAN, HELLBARK, ETHEREAL;
    
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
            case DEAD:
                return false;
            default:
                return true;
        }
    }
    public boolean canMakeCharcoal()
    {
        return true;
    }
    
    public static Predicate withPlanks = new Predicate<BOPWoods>()
    {
        @Override
        public boolean apply(BOPWoods wood) {return wood.hasPlanks();}
    };
}