/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import com.google.gson.annotations.SerializedName;

public enum GeneratorStage
{
    @SerializedName("pre")
    PRE, 
    @SerializedName("big_shroom")
    BIG_SHROOM, 
    @SerializedName("cactus")
    CACTUS, 
    @SerializedName("clay")
    CLAY, 
    @SerializedName("dead_bush")
    DEAD_BUSH, 
    @SerializedName("lilypad")
    LILYPAD, 
    @SerializedName("flowers")
    FLOWERS, 
    @SerializedName("grass")
    GRASS, 
    @SerializedName("lake_water")
    LAKE_WATER, 
    @SerializedName("lake_lava")
    LAKE_LAVA, 
    @SerializedName("pumpkin")
    PUMPKIN, 
    @SerializedName("reed")
    REED, 
    @SerializedName("sand")
    SAND, 
    @SerializedName("sand_pass_2")
    SAND_PASS2, 
    @SerializedName("shroom")
    SHROOM, 
    @SerializedName("tree")
    TREE, 
    @SerializedName("post")
    POST;
}
