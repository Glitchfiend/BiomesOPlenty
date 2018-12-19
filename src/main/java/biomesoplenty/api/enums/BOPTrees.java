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

public enum BOPTrees implements IStringSerializable, IPagedVariants
{
	ORIGIN, FLOWERING, ORANGE_AUTUMN, YELLOW_AUTUMN, FIR, REDWOOD, WHITE_CHERRY, PINK_CHERRY, MAPLE, MAHOGANY, JACARANDA, PALM, WILLOW, DEAD, MAGIC, UMBRAN, HELLBARK, ETHEREAL;
    
    @Override
    public String getName() {
        return this.name().toLowerCase();
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
    
    public boolean hasSapling()
    {
        return true;
    }
    
    public static Predicate withSaplings = new Predicate<BOPTrees>()
    {
        @Override
        public boolean apply(BOPTrees tree) {return tree.hasSapling();}
    };
}