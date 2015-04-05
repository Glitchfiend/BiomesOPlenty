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

    public static enum TwoWoods implements IStringSerializable {
        A, B;
        public AllWoods map(int page)
        {
            return AllWoods.values()[page * 2 + this.ordinal()];
        }
        public static TwoWoods mapFrom(AllWoods e)
        {
            return TwoWoods.values()[ e.ordinal() % 2 ];
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
    
    public static enum FourWoods implements IStringSerializable {
        A, B, C, D;
        public AllWoods map(int page)
        {
            return AllWoods.values()[page * 4 + this.ordinal()];
        }
        public static FourWoods mapFrom(AllWoods e)
        {
            return FourWoods.values()[ e.ordinal() % 4 ];
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
    
    public static enum EightWoods implements IStringSerializable {
        A, B, C, D, E, F, G, H;
        public AllWoods map(int page)
        {
            return AllWoods.values()[page * 8 + this.ordinal()];
        }
        public static EightWoods mapFrom(AllWoods e)
        {
            return EightWoods.values()[ e.ordinal() % 8 ];
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
    
    public static enum SixteenWoods implements IStringSerializable {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P;
        public AllWoods map(int page)
        {
            return AllWoods.values()[page * 16 + this.ordinal()];
        }
        public static SixteenWoods mapFrom(AllWoods e)
        {
            return SixteenWoods.values()[ e.ordinal() % 16 ];
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