/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

public class BOPBiomes
{
    public static final IBiomeRegistry REG_INSTANCE = createRegistry();
    
    /**A list of world types where BoP biome decoration does not occur**/
    public static List<WorldType> excludedDecoratedWorldTypes = Lists.newArrayList();
    
    // normal biomes which have weights
    public static Optional<Biome> alps = Optional.absent();
    public static Optional<Biome> bamboo_forest = Optional.absent();
    public static Optional<Biome> bayou = Optional.absent();
    public static Optional<Biome> bog = Optional.absent();
    public static Optional<Biome> boreal_forest = Optional.absent();
    public static Optional<Biome> brushland = Optional.absent();
    public static Optional<Biome> chaparral = Optional.absent();
    public static Optional<Biome> cherry_blossom_grove = Optional.absent();
    public static Optional<Biome> cold_desert = Optional.absent();
    public static Optional<Biome> coniferous_forest = Optional.absent();
    public static Optional<Biome> crag = Optional.absent();
    public static Optional<Biome> dead_forest = Optional.absent();
    public static Optional<Biome> dead_swamp = Optional.absent();
    public static Optional<Biome> eucalyptus_forest = Optional.absent();
    public static Optional<Biome> fen = Optional.absent();
    public static Optional<Biome> flower_field = Optional.absent();
    public static Optional<Biome> grassland = Optional.absent();    
    public static Optional<Biome> grove = Optional.absent();
    public static Optional<Biome> heathland = Optional.absent();    
    public static Optional<Biome> highland = Optional.absent();
    public static Optional<Biome> land_of_lakes = Optional.absent();
    public static Optional<Biome> lavender_fields = Optional.absent();
    public static Optional<Biome> lush_desert = Optional.absent();
    public static Optional<Biome> lush_swamp = Optional.absent();
    public static Optional<Biome> maple_woods = Optional.absent();
    public static Optional<Biome> marsh = Optional.absent();
    public static Optional<Biome> meadow = Optional.absent();
    public static Optional<Biome> moor = Optional.absent();
    public static Optional<Biome> mountain = Optional.absent();
    public static Optional<Biome> mystic_grove = Optional.absent();
    public static Optional<Biome> ominous_woods = Optional.absent();
    public static Optional<Biome> orchard = Optional.absent();
    public static Optional<Biome> outback = Optional.absent();
    public static Optional<Biome> overgrown_cliffs = Optional.absent();
    public static Optional<Biome> prairie = Optional.absent();
    public static Optional<Biome> quagmire = Optional.absent();
    public static Optional<Biome> rainforest = Optional.absent();
    public static Optional<Biome> redwood_forest = Optional.absent();
    public static Optional<Biome> sacred_springs = Optional.absent();
    public static Optional<Biome> seasonal_forest = Optional.absent();
    public static Optional<Biome> shield = Optional.absent();
    public static Optional<Biome> shrubland = Optional.absent();
    public static Optional<Biome> snowy_coniferous_forest = Optional.absent();
    public static Optional<Biome> snowy_forest = Optional.absent();
    public static Optional<Biome> steppe = Optional.absent();
    public static Optional<Biome> temperate_rainforest = Optional.absent();
    public static Optional<Biome> tropical_rainforest = Optional.absent();
    public static Optional<Biome> tundra = Optional.absent();
    public static Optional<Biome> wasteland = Optional.absent();
    public static Optional<Biome> wetland = Optional.absent();
    public static Optional<Biome> woodland = Optional.absent();
    public static Optional<Biome> xeric_shrubland = Optional.absent();
    
    // edge-biomes, sub-biomes and mutated-biomes
    public static Optional<Biome> mountain_foothills = Optional.absent();
    public static Optional<Biome> glacier = Optional.absent();
    public static Optional<Biome> oasis = Optional.absent();
    public static Optional<Biome> coral_reef = Optional.absent();
    public static Optional<Biome> kelp_forest = Optional.absent();
    public static Optional<Biome> mangrove = Optional.absent();
    public static Optional<Biome> origin_island = Optional.absent();
    public static Optional<Biome> tropical_island = Optional.absent();
    public static Optional<Biome> volcanic_island = Optional.absent();
    public static Optional<Biome> flower_island = Optional.absent();
    public static Optional<Biome> gravel_beach = Optional.absent();

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
        public IExtendedBiome getExtendedBiome(Biome biome);
        public ImmutableSet<Biome> getPresentBiomes();
    }
}
