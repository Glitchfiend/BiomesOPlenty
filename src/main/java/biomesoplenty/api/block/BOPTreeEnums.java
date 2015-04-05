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
    
    public static enum AllTrees implements IStringSerializable {
        
        YELLOW_AUTUMN, ORANGE_AUTUMN, BAMBOO, MAGIC, DARK, DEAD, FIR, ETHEREAL, ORIGIN, PINK_CHERRY, WHITE_CHERRY, MAPLE, HELLBARK, FLOWERING, JACARANDA, SACRED_OAK, MANGROVE, PALM, REDWOOD, WILLOW, PINE, MAHOGANY, REG_BIG_FLOWER, YELLOW_BIG_FLOWER;
        
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

    public static enum TwoTrees implements IStringSerializable {
        A, B;
        public AllTrees map(int page)
        {
            return AllTrees.values()[page * 2 + this.ordinal()];
        }
        public static TwoTrees mapFrom(AllTrees e)
        {
            return TwoTrees.values()[ e.ordinal() % 2 ];
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
    
    public static enum FourTrees implements IStringSerializable {
        A, B, C, D;
        public AllTrees map(int page)
        {
            return AllTrees.values()[page * 4 + this.ordinal()];
        }
        public static FourTrees mapFrom(AllTrees e)
        {
            return FourTrees.values()[ e.ordinal() % 4 ];
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
    
    public static enum EightTrees implements IStringSerializable {
        A, B, C, D, E, F, G, H;
        public AllTrees map(int page)
        {
            return AllTrees.values()[page * 8 + this.ordinal()];
        }
        public static EightTrees mapFrom(AllTrees e)
        {
            return EightTrees.values()[ e.ordinal() % 8 ];
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