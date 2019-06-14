/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BOPPlants implements IStringSerializable, IPagedVariants
{
    
    SHORTGRASS, MEDIUMGRASS, BUSH, SPROUT, POISONIVY, BERRYBUSH, SHRUB, WHEATGRASS, DAMPGRASS, KORU, CLOVERPATCH, LEAFPILE, DEADLEAFPILE, DEADGRASS, DESERTGRASS, DESERTSPROUTS, DUNEGRASS, SPECTRALFERN, THORN, WILDRICE, CATTAIL, RIVERCANE, TINYCACTUS, DEVILWEED, REED, ROOT, RAFFLESIA, BARLEY;
    
    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
}