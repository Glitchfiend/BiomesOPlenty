/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

import com.google.gson.annotations.SerializedName;

public enum GeneratorStage
{
    @SerializedName("pre")
    PRE(null), 
    @SerializedName("big_shroom")
    BIG_SHROOM(Decorate.EventType.BIG_SHROOM), 
    @SerializedName("cactus")
    CACTUS(Decorate.EventType.CACTUS), 
    @SerializedName("clay")
    CLAY(Decorate.EventType.CLAY), 
    @SerializedName("dead_bush")
    DEAD_BUSH(Decorate.EventType.DEAD_BUSH), 
    @SerializedName("lilypad")
    LILYPAD(Decorate.EventType.LILYPAD), 
    @SerializedName("flowers")
    FLOWERS(Decorate.EventType.FLOWERS), 
    @SerializedName("grass")
    GRASS(Decorate.EventType.GRASS), 
    @SerializedName("lake_water")
    LAKE_WATER(Decorate.EventType.LAKE_WATER), 
    @SerializedName("lake_lava")
    LAKE_LAVA(Decorate.EventType.LAKE_LAVA), 
    @SerializedName("pumpkin")
    PUMPKIN(Decorate.EventType.PUMPKIN), 
    @SerializedName("reed")
    REED(Decorate.EventType.REED), 
    @SerializedName("sand")
    SAND(Decorate.EventType.SAND), 
    @SerializedName("sand_pass_2")
    SAND_PASS2(Decorate.EventType.SAND_PASS2), 
    @SerializedName("shroom")
    SHROOM(Decorate.EventType.SHROOM), 
    @SerializedName("tree")
    TREE(Decorate.EventType.TREE), 
    @SerializedName("post")
    POST(null),
    @SerializedName("parent")
    PARENT(null),
    @SerializedName("ore_pre")
    ORE_PRE(null),
    @SerializedName("ore_post")
    ORE_POST(null);
    
    private Decorate.EventType decorateType;
    
    private GeneratorStage(Decorate.EventType decorateType)
    {
        this.decorateType = decorateType;
    }
    
    public Decorate.EventType getDecorateType()
    {
        return this.decorateType;
    }
    
    public static GeneratorStage mapDecorateType(Decorate.EventType decorateType)
    {
        //Somewhat of a hack, requires the ordering of our enum to be the s
        return decorateType != Decorate.EventType.CUSTOM ? GeneratorStage.values()[decorateType.ordinal() + 1] : null;
    }
}
