/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.generation;

import java.util.EnumMap;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

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
    @SerializedName("desert_well")
    DESERT_WELL(Decorate.EventType.DESERT_WELL), 
    @SerializedName("lilypad")
    LILYPAD(Decorate.EventType.LILYPAD), 
    @SerializedName("flowers")
    FLOWERS(Decorate.EventType.FLOWERS), 
    @SerializedName("fossil")
    FOSSIL(Decorate.EventType.FOSSIL), 
    @SerializedName("grass")
    GRASS(Decorate.EventType.GRASS), 
    @SerializedName("ice")
    ICE(Decorate.EventType.ICE), 
    @SerializedName("lake_water")
    LAKE_WATER(Decorate.EventType.LAKE_WATER), 
    @SerializedName("lake_lava")
    LAKE_LAVA(Decorate.EventType.LAKE_LAVA), 
    @SerializedName("pumpkin")
    PUMPKIN(Decorate.EventType.PUMPKIN), 
    @SerializedName("reed")
    REED(Decorate.EventType.REED), 
    @SerializedName("rock")
    ROCK(Decorate.EventType.ROCK), 
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
    
    private static final EnumMap<Decorate.EventType, GeneratorStage> decorateTypeMapper = Maps.newEnumMap(Decorate.EventType.class);
    static
    {
        decorateTypeMapper.put(Decorate.EventType.BIG_SHROOM, BIG_SHROOM);
        decorateTypeMapper.put(Decorate.EventType.CACTUS, CACTUS);
        decorateTypeMapper.put(Decorate.EventType.CLAY, CLAY);
        decorateTypeMapper.put(Decorate.EventType.DEAD_BUSH, DEAD_BUSH);
        decorateTypeMapper.put(Decorate.EventType.DESERT_WELL, DESERT_WELL);
        decorateTypeMapper.put(Decorate.EventType.LILYPAD, LILYPAD);
        decorateTypeMapper.put(Decorate.EventType.FLOWERS, FLOWERS);
        decorateTypeMapper.put(Decorate.EventType.FOSSIL, FOSSIL);
        decorateTypeMapper.put(Decorate.EventType.GRASS, GRASS);
        decorateTypeMapper.put(Decorate.EventType.ICE, ICE);
        decorateTypeMapper.put(Decorate.EventType.LAKE_WATER, LAKE_WATER);
        decorateTypeMapper.put(Decorate.EventType.LAKE_LAVA, LAKE_LAVA);
        decorateTypeMapper.put(Decorate.EventType.PUMPKIN, PUMPKIN);
        decorateTypeMapper.put(Decorate.EventType.REED, REED);
        decorateTypeMapper.put(Decorate.EventType.ROCK, ROCK);
        decorateTypeMapper.put(Decorate.EventType.SAND, SAND);
        decorateTypeMapper.put(Decorate.EventType.SAND_PASS2, SAND_PASS2);
        decorateTypeMapper.put(Decorate.EventType.SHROOM, SHROOM);
        decorateTypeMapper.put(Decorate.EventType.TREE, TREE);
    }
    
    public static GeneratorStage mapDecorateType(Decorate.EventType decorateType)
    {
        //Somewhat of a hack, requires the ordering of our enum to be the same as the decorate event
    	return decorateType != Decorate.EventType.CUSTOM ? decorateTypeMapper.get(decorateType) : null;
    }
}
