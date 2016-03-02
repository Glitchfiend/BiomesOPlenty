/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.enums;

import biomesoplenty.common.util.block.VariantPagingHelper.IPagedVariants;
import net.minecraft.util.IStringSerializable;

public enum BOPPlants implements IStringSerializable, IPagedVariants
{
    
    SHORTGRASS, MEDIUMGRASS, BUSH, SPROUT, POISONIVY, BERRYBUSH, SHRUB, WHEATGRASS, DAMPGRASS, KORU, CLOVERPATCH, LEAFPILE, DEADLEAFPILE, DEADGRASS, DESERTGRASS, DESERTSPROUTS, DUNEGRASS, SPECTRALFERN, THORN, WILDRICE, CATTAIL, RIVERCANE, TINYCACTUS, WITHERWART, REED, ROOT, RAFFLESIA;
    
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