/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import net.minecraft.util.IStringSerializable;

public class BOPTreeEnums
{
    
    public static enum allTrees implements IStringSerializable {
        
        // UNUSED_A and UNUSED_B are present to make the number up to 24 so that we can have 3 'pages' of 8 saplings without out-of-range index errors
        YELLOW_AUTUMN, ORANGE_AUTUMN, BAMBOO, MAGIC, DARK, DEAD, FIR, ETHEREAL, ORIGIN, PINK_CHERRY, WHITE_CHERRY, MAPLE, HELLBARK, FLOWERING, JACARANDA, SACRED_OAK, MANGROVE, PALM, REDWOOD, WILLOW, PINE, MAHOGANY, UNUSED_A, UNUSED_B;
        
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

    public static enum twoTrees implements IStringSerializable {
        A, B;
        public allTrees map(int page)
        {
            return allTrees.values()[page * 2 + this.ordinal()];
        }
        public static twoTrees mapFrom(allTrees e)
        {
            return twoTrees.values()[ e.ordinal() % 2 ];
        }
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
    
    public static enum fourTrees implements IStringSerializable {
        A, B, C, D;
        public allTrees map(int page)
        {
            return allTrees.values()[page * 4 + this.ordinal()];
        }
        public static fourTrees mapFrom(allTrees e)
        {
            return fourTrees.values()[ e.ordinal() % 4 ];
        }
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
    
    public static enum eightTrees implements IStringSerializable {
        A, B, C, D, E, F, G, H;
        public allTrees map(int page)
        {
            return allTrees.values()[page * 8 + this.ordinal()];
        }
        public static eightTrees mapFrom(allTrees e)
        {
            return eightTrees.values()[ e.ordinal() % 8 ];
        }
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