/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import net.minecraft.util.IStringSerializable;

public class BOPWoodEnums
{
    
    public static enum AllWoods implements IStringSerializable {
        // TODO: change HELL_BARK to HELLBARK for consistency with BOPTreeEnums
        SACRED_OAK, CHERRY, DARK, FIR, ETHEREAL, MAGIC, MANGROVE, PALM, REDWOOD, WILLOW, PINE, HELL_BARK, JACARANDA, MAHOGANY, GIANT_FLOWER, DEAD;
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