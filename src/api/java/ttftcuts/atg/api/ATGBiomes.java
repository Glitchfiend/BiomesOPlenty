package ttftcuts.atg.api;

import java.util.List;

import ttftcuts.atg.api.events.*;
import ttftcuts.atg.api.events.listenable.ATGBiomeGroupAssignmentEvent.ATGGroupActivationEvent;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.base.Optional;

/**
 * 
 * @author TTFTCUTS
 * 
 * Biome related API things! Biome groups, adding biomes to those groups and more.
 *
 */
public abstract class ATGBiomes {
	
	public static enum BiomeType { LAND, COAST, SEA }
	
	/**
	 * Gets an ATG biome by name.
	 * 
	 * @param biomeName
	 * 				The name of the biome you want to get.
	 * 
	 * @return the corresponding biome.
	 */
	public static BiomeGenBase getBiome(String biomeName) {
		final ATGBiomeRequestEvent event = new ATGBiomeRequestEvent(biomeName);
		MinecraftForge.EVENT_BUS.post(event);
		if ( !event.biome.isPresent() ) {
			return null;
		}
		return event.biome.get();
	}
	
	/**
	 * Gets a list of names corresponding to the Biome Groups which contain a specified biome.
	 * 
	 * @param biome
	 * 			The biome you want to find groups for.
	 * 
	 * @return a list of names of containing Biome Groups.
	 */
	public static List<String> getGroupFromBiome(BiomeGenBase biome) {
		final ATGBiomeGroupRequestEvent event = new ATGBiomeGroupRequestEvent(biome);
		MinecraftForge.EVENT_BUS.post(event);
		return event.groups;
	}
	
	
	/**
	 * Gets the raw height, temperature and moisture values from the generator for a specific pair of x/z coordinates.
	 * 
	 * WARNING: This is a VERY expensive calculation and the result is NOT cached, so please use as little as possible!
	 * 
	 * @param world
	 * 			The world that you want to get the information for.
	 * 
	 * @param x
	 * 			X coordinate of the point to query.
	 * 
	 * @param z
	 * 			Z coordinate of the point to query.
	 * 
	 * @return an array of three doubles corresponding to the height, temperature and moisture at the specified point in the ranges 0.0-1.0.
	 */
	public static double[] getGeneratorInfo(World world, double x, double z) {
		final ATGGeneratorInfoEvent event = new ATGGeneratorInfoEvent(world,x,z);
		MinecraftForge.EVENT_BUS.post(event);
		return event.info;
	}

	/**
	 * Adds a new biome GROUP to ATG. Not something that would usually need to be used.
	 * 
	 * @param type
	 * 			The biome type that this group belongs to. LAND, COAST or SEA.
	 * 
	 * @param name
	 * 			The name of this group.
	 * 
	 * @param temp
	 * 			Temperature value for this group. Same range as biome temperatures.
	 * 
	 * @param moisture
	 * 			Moisture value for this group. Same range as biome rainfall.
	 * 
	 * @param height
	 * 			Average height value for this group. Same range as biome heights.
	 * 
	 * @param minHeight
	 * 			Minimum height to generate this group. Above this value, it will be skipped.
	 * 
	 * @param maxHeight
	 * 			Maximum height to generate this group. Below this value, it will be skipped.
	 * 
	 * @param salt
	 * 			Biome blob generation salt. Used to offset biome boundaries from other groups to avoid strange artifacts.
	 * 
	 * @param generate
	 * 			Set to false to prevent this group generating in the default manner. Primarily for use with the biome group assignment events.
	 */
	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height, double minHeight, double maxHeight, long salt, boolean generate) {
		ATGBiomeGroupAddEvent event = new ATGBiomeGroupAddEvent(type, name, temp, moisture, height, minHeight, maxHeight, salt, generate);
		MinecraftForge.EVENT_BUS.post(event);
		if ( event.response == ATGBiomeGroupAddEvent.ResponseType.FAILED ) {
			// FAILED!
		}
	}
	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height, double minHeight, double maxHeight, long salt) {
		addBiomeGroup(type, name, temp, moisture, height, minHeight, maxHeight, salt, true);
	}
	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height, long salt) {
		addBiomeGroup(type, name, temp, moisture, height, 0.0, 1.0, salt);
	}
	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height) {
		addBiomeGroup(type, name, temp, moisture, height, 0);
	}
	

	/**
	 * Modifies a biome group to make it more or less likely to be chosen by the generator.
	 * Best used to ensure a height-constrained biome group generates in favour of an otherwise identically ranged group.
	 * 
	 * @param type
	 * 			Group type for the second parameter. LAND, COAST or SEA.
	 * 
	 * @param name
	 * 			Name of the group to modify.
	 * 
	 * @param modifier
	 * 			Modifier value. Positive makes the group more likely to be picked. Very small values can have a large effect.
	 */
	public static void modGroupSuitability(BiomeType type, String name, double modifier) {
		ATGBiomeGroupEvent event = new ATGBiomeGroupEvent( ATGBiomeGroupEvent.EventType.SUITABILITY, type, name, modifier );
		MinecraftForge.EVENT_BUS.post(event);
		if ( event.response == ATGBiomeGroupEvent.ResponseType.FAILED ) {
			// FAILED!
		}
	}
	
	
	/**
	 * Register a biome with ATG.
	 * 
	 * @param type
	 * 			Type of the biome group this biome will inhabit. LAND, COAST or SEA.
	 * 
	 * @param group
	 * 			Name of the biome group this biome will inhabit.
	 * 
	 * @param biome
	 * 			The biome to be registered.
	 * 
	 * @param weight
	 * 			Generation weight for this biome. All vanilla biomes are weighted 1.0 except mushroom island.
	 */
	public static void addBiome(BiomeType type, String group, BiomeGenBase biome, double weight) {
		ATGBiomeEvent event = new ATGBiomeEvent( type, group, biome, null, weight);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	
	/**
	 * Replace a biome in a group with a different biome.
	 * 
	 * @param type
	 * 			Type of the target biome group. LAND, COAST or SEA.
	 * 
	 * @param group
	 * 			Name of the target biome group.
	 * 
	 * @param toReplace
	 * 			Biome to replace in the specified group.
	 * 
	 * @param replacement
	 * 			Biome which will replace toReplace in the group.
	 * 
	 * @param weight
	 * 			Generation weight for the replacement biome.
	 */
	public static void replaceBiome(BiomeType type, String group, BiomeGenBase toReplace, BiomeGenBase replacement, double weight) {
		ATGBiomeEvent event = new ATGBiomeEvent( type, group, replacement, toReplace, weight );
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	
	/**
	 * Add a sub-biome to a biome. Sub-biomes appear as smaller patches within their parent biome.
	 * 
	 * @param biome
	 * 			Parent biome.
	 * 
	 * @param subBiome
	 * 			Biome that will appear as a sub-biome.
	 * 
	 * @param weight
	 * 			Generation weight for the sub-biome. The parent biome is always weighted at 1.0, so a 1.0 weight here with a single sub-biome would be a 50/50 split.
	 */
	public static void addSubBiome(BiomeGenBase biome, BiomeGenBase subBiome, double weight) {
		ATGBiomeModEvent event = new ATGBiomeModEvent(ATGBiomeModEvent.EventType.SUBBIOME, biome, null, subBiome, weight);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	
	/**
	 * Add an IGenMod to a biome to modify how it generates.
	 * 	
	 * @param biome
	 * 			Biome to attach the mod to.
	 * 
	 * @param mod
	 * 			IGenMod object that will modify the biome.
	 */
	public static void addGenMod(BiomeGenBase biome, IGenMod mod) {
		ATGBiomeModEvent event = new ATGBiomeModEvent(ATGBiomeModEvent.EventType.GENMOD, biome, mod, null, 0);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	/**
	 * Get the IGenMod assigned to a biome, or Optional.absent if there isn't one.
	 * 
	 * @param biome
	 * 			The biome to get the IGenMod for.
	 * 
	 * @return an Optional corresponding to the IGenMod for the biome, or Optional.absent.
	 */
	public static Optional<IGenMod> getGenMod(BiomeGenBase biome) {
		ATGBiomeModRequestEvent event = new ATGBiomeModRequestEvent(biome);
		MinecraftForge.EVENT_BUS.post(event);
		return event.mod;
	}
	
	
	/**
	 * Sets the rock parameters for a biome, to modify how ATG boulders generate there.
	 * 	
	 * @param biome
	 * 			The biome to set rock properties for.
	 *  
	 * @param rockChance
	 * 			1 in rockChance chunks will contain a rock.
	 * 
	 * @param bigRockChance
	 * 			1 in bigRockChance rocks will be large.
	 * 
	 * @param rocksPerChunk
	 * 			rockChance will be checked rocksPerChunk times per chunk.
	 */
	public static void setBiomeRocks(BiomeGenBase biome, int rockChance, int bigRockChance, int rocksPerChunk) {
		ATGBiomeRocksEvent event = new ATGBiomeRocksEvent(biome, rockChance, bigRockChance, rocksPerChunk);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	/**
	 * Use this to enable the posting of "ATGBiomeGroupAssignmentEvent"s at generation, to allow custom biome group overrides.
	 * If this is not called at least once, none of those events will be sent.
	 * 
	 * Listening for ATGBiomeGroupAssignmentEvent allows direct replacement of the biome group at every x/z coordinate pair.
	 * When enabled, it slows generation by about 10% due to event volume, so it's off by default.
	 * 
	 * Only call this if you intend to listen for those events.
	 */
	public static void enableBiomeGroupAssignmentEvent() {
		ATGGroupActivationEvent event = new ATGGroupActivationEvent();
		MinecraftForge.EVENT_BUS.post(event);
	}
}
