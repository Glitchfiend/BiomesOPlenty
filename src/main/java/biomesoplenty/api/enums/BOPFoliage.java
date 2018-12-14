/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.enums;

import net.minecraft.util.IStringSerializable;

public enum BOPFoliage implements IStringSerializable, IPagedVariants
{
	SHORTGRASS, BUSH, BERRYBUSH, KORU, DEVILWEED, DUNEGRASS, DESERTSPROUTS, DESERTGRASS, DEADGRASS, SPECTRALFERN, BARLEY;
	
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