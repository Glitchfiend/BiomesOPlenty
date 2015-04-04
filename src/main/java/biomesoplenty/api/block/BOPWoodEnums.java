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
    
    public static enum allWoods implements IStringSerializable {
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

    public static enum twoWoods implements IStringSerializable {
        A, B;
        public allWoods map(int page)
        {
            return allWoods.values()[page * 2 + this.ordinal()];
        }
        public static twoWoods mapFrom(allWoods e)
        {
            return twoWoods.values()[ e.ordinal() % 2 ];
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
    
    public static enum fourWoods implements IStringSerializable {
        A, B, C, D;
        public allWoods map(int page)
        {
            return allWoods.values()[page * 4 + this.ordinal()];
        }
        public static fourWoods mapFrom(allWoods e)
        {
            return fourWoods.values()[ e.ordinal() % 4 ];
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
    
    public static enum eightWoods implements IStringSerializable {
        A, B, C, D, E, F, G, H;
        public allWoods map(int page)
        {
            return allWoods.values()[page * 8 + this.ordinal()];
        }
        public static eightWoods mapFrom(allWoods e)
        {
            return eightWoods.values()[ e.ordinal() % 8 ];
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
    
    public static enum sixteenWoods implements IStringSerializable {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P;
        public allWoods map(int page)
        {
            return allWoods.values()[page * 16 + this.ordinal()];
        }
        public static sixteenWoods mapFrom(allWoods e)
        {
            return sixteenWoods.values()[ e.ordinal() % 16 ];
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