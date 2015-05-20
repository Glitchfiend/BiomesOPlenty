/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import biomesoplenty.common.util.block.VariantPagingHelper.IPagedVariants;
import net.minecraft.util.IStringSerializable;

public class BOPPlantEnums
{
    
    public static enum AllPlants implements IStringSerializable, IPagedVariants
    {
        
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
    
}