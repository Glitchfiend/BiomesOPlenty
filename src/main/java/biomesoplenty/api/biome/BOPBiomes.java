/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.BiomeGenBase;

public class BOPBiomes
{
    public static final IBiomeRegistry REG_INSTANCE = createRegistry();
    
    // normal biomes which have weights
    public static Optional<BiomeGenBase> alps = Optional.absent();
    public static Optional<BiomeGenBase> bamboo_forest = Optional.absent();
    public static Optional<BiomeGenBase> bayou = Optional.absent();
    public static Optional<BiomeGenBase> bog = Optional.absent();
    public static Optional<BiomeGenBase> boreal_forest = Optional.absent();
    public static Optional<BiomeGenBase> brushland = Optional.absent();
    public static Optional<BiomeGenBase> chaparral = Optional.absent();
    public static Optional<BiomeGenBase> cherry_blossom_grove = Optional.absent();
    public static Optional<BiomeGenBase> cold_desert = Optional.absent();
    public static Optional<BiomeGenBase> coniferous_forest = Optional.absent();
    public static Optional<BiomeGenBase> crag = Optional.absent();
    public static Optional<BiomeGenBase> dead_forest = Optional.absent();
    public static Optional<BiomeGenBase> dead_swamp = Optional.absent();
    public static Optional<BiomeGenBase> eucalyptus_forest = Optional.absent();
    public static Optional<BiomeGenBase> fen = Optional.absent();
    public static Optional<BiomeGenBase> flower_field = Optional.absent();
    public static Optional<BiomeGenBase> grassland = Optional.absent();    
    public static Optional<BiomeGenBase> grove = Optional.absent();
    public static Optional<BiomeGenBase> heathland = Optional.absent();    
    public static Optional<BiomeGenBase> highland = Optional.absent();
    public static Optional<BiomeGenBase> land_of_lakes = Optional.absent();
    public static Optional<BiomeGenBase> lavender_fields = Optional.absent();
    public static Optional<BiomeGenBase> lush_desert = Optional.absent();
    public static Optional<BiomeGenBase> lush_swamp = Optional.absent();
    public static Optional<BiomeGenBase> maple_woods = Optional.absent();
    public static Optional<BiomeGenBase> marsh = Optional.absent();
    public static Optional<BiomeGenBase> meadow = Optional.absent();
    public static Optional<BiomeGenBase> moor = Optional.absent();
    public static Optional<BiomeGenBase> mountain = Optional.absent();
    public static Optional<BiomeGenBase> mystic_grove = Optional.absent();
    public static Optional<BiomeGenBase> ominous_woods = Optional.absent();
    public static Optional<BiomeGenBase> orchard = Optional.absent();
    public static Optional<BiomeGenBase> outback = Optional.absent();
    public static Optional<BiomeGenBase> overgrown_cliffs = Optional.absent();
    public static Optional<BiomeGenBase> prairie = Optional.absent();
    public static Optional<BiomeGenBase> quagmire = Optional.absent();
    public static Optional<BiomeGenBase> rainforest = Optional.absent();
    public static Optional<BiomeGenBase> redwood_forest = Optional.absent();
    public static Optional<BiomeGenBase> sacred_springs = Optional.absent();
    public static Optional<BiomeGenBase> seasonal_forest = Optional.absent();
    public static Optional<BiomeGenBase> shield = Optional.absent();
    public static Optional<BiomeGenBase> shrubland = Optional.absent();
    public static Optional<BiomeGenBase> snowy_coniferous_forest = Optional.absent();
    public static Optional<BiomeGenBase> snowy_forest = Optional.absent();
    public static Optional<BiomeGenBase> steppe = Optional.absent();
    public static Optional<BiomeGenBase> temperate_rainforest = Optional.absent();
    public static Optional<BiomeGenBase> tropical_rainforest = Optional.absent();
    public static Optional<BiomeGenBase> tundra = Optional.absent();
    public static Optional<BiomeGenBase> wasteland = Optional.absent();
    public static Optional<BiomeGenBase> wetland = Optional.absent();
    public static Optional<BiomeGenBase> woodland = Optional.absent();
    public static Optional<BiomeGenBase> xeric_shrubland = Optional.absent();
    
    // edge-biomes, sub-biomes and mutated-biomes
    public static Optional<BiomeGenBase> mountain_foothills = Optional.absent();
    public static Optional<BiomeGenBase> glacier = Optional.absent();
    public static Optional<BiomeGenBase> oasis = Optional.absent();
    public static Optional<BiomeGenBase> coral_reef = Optional.absent();
    public static Optional<BiomeGenBase> kelp_forest = Optional.absent();
    public static Optional<BiomeGenBase> origin_island = Optional.absent();
    public static Optional<BiomeGenBase> tropical_island = Optional.absent();
    public static Optional<BiomeGenBase> volcanic_island = Optional.absent();
    public static Optional<BiomeGenBase> flower_island = Optional.absent();
    public static Optional<BiomeGenBase> gravel_beach = Optional.absent();

    //Biome extensions
    public static IExtendedBiome end_extension;
    public static IExtendedBiome mushroom_island_extension;
    public static IExtendedBiome plains_extension;
    public static IExtendedBiome forest_extension;
    public static IExtendedBiome forest_hills_extension;
    public static IExtendedBiome jungle_extension;
    public static IExtendedBiome jungle_hills_extension;
    public static IExtendedBiome desert_extension;
    public static IExtendedBiome desert_hills_extension;
    public static IExtendedBiome taiga_extension;
    public static IExtendedBiome taiga_hills_extension;
    public static IExtendedBiome mesa_extension;
    public static IExtendedBiome mesa_plateau_extension;
    public static IExtendedBiome ice_plains_extension;
    public static IExtendedBiome ice_mountains_extension;
    public static IExtendedBiome extreme_hills_extension;
    public static IExtendedBiome extreme_hills_plus_extension;
    public static IExtendedBiome swampland_extension;
    public static IExtendedBiome birch_forest_extension;
    public static IExtendedBiome birch_forest_hills_extension;
    public static IExtendedBiome roofed_forest_extension;
    public static IExtendedBiome savanna_extension;
    public static IExtendedBiome savanna_plateau_extension;
    public static IExtendedBiome ocean_extension;
    public static IExtendedBiome cold_taiga_extension;
    public static IExtendedBiome cold_taiga_hills_extension;
    public static IExtendedBiome mega_taiga_extension;
    public static IExtendedBiome mega_taiga_hills_extension;
    
    private static IBiomeRegistry createRegistry()
    {
        IBiomeRegistry instance = null;
        
        try 
        {
            instance = (IBiomeRegistry)Class.forName("biomesoplenty.common.init.ModBiomes").newInstance();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return instance;
    }
    
    public static interface IBiomeRegistry
    {
        public IExtendedBiome registerBiome(IExtendedBiome biome, String idName);
        public IExtendedBiome getExtendedBiome(BiomeGenBase biome);
        public ImmutableSet<BiomeGenBase> getPresentBiomes();
    }
}
