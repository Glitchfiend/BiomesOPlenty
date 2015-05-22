/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.enums;

import net.minecraft.util.IStringSerializable;
import biomesoplenty.common.util.block.VariantPagingHelper.IPagedVariants;

import com.google.common.base.Predicate;

public enum BOPWoods implements IStringSerializable, IPagedVariants
{
    
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