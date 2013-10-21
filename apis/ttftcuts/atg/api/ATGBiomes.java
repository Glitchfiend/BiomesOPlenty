package ttftcuts.atg.api;

import java.lang.reflect.Method;

import ttftcuts.atg.api.events.ATGBiomeEvent;
import ttftcuts.atg.api.events.ATGBiomeGroupAddEvent;
import ttftcuts.atg.api.events.ATGBiomeGroupEvent;
import ttftcuts.atg.api.events.ATGBiomeModEvent;
import ttftcuts.atg.api.events.ATGBiomeModEvent.EventType;
import ttftcuts.atg.api.events.ATGBiomeModRequestEvent;
import ttftcuts.atg.api.events.ATGBiomeRequestEvent;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.base.Optional;

public abstract class ATGBiomes {
	
	public static enum BiomeType { LAND, COAST, SEA }
	
	// Get ATG biomes
	
	public static BiomeGenBase getBiome(String biomeName) {
		final ATGBiomeRequestEvent event = new ATGBiomeRequestEvent(biomeName);
		MinecraftForge.EVENT_BUS.post(event);
		if ( !event.biome.isPresent() ) {
			return null;
		}
		return event.biome.get();
	}
	
	// Add Biome Groups

	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height, double minHeight, double maxHeight, long salt) {
		ATGBiomeGroupAddEvent event = new ATGBiomeGroupAddEvent(type, name, temp, moisture, height, minHeight, maxHeight, salt);
		MinecraftForge.EVENT_BUS.post(event);
		if ( event.response == ATGBiomeGroupAddEvent.ResponseType.FAILED ) {
			// FAILED!
		}
	}
	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height, long salt) {
		addBiomeGroup(type, name, temp, moisture, height, 0.0, 1.0, salt);
	}
	public static void addBiomeGroup(BiomeType type, String name, double temp, double moisture, double height) {
		addBiomeGroup(type, name, temp, moisture, height, 0);
	}
	
	// Manipulate biome groups
	
	public static void modGroupSuitability(BiomeType type, String name, double modifier) {
		ATGBiomeGroupEvent event = new ATGBiomeGroupEvent( ATGBiomeGroupEvent.EventType.SUITABILITY, type, name, modifier );
		MinecraftForge.EVENT_BUS.post(event);
		if ( event.response == ATGBiomeGroupEvent.ResponseType.FAILED ) {
			// FAILED!
		}
	}
	
	
	// Registering Biomes
	
	public static void addBiome(BiomeType type, String group, BiomeGenBase biome, double weight) {
		ATGBiomeEvent event = new ATGBiomeEvent( type, group, biome, null, weight);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	public static void replaceBiome(BiomeType type, String group, BiomeGenBase toReplace, BiomeGenBase replacement, double weight) {
		ATGBiomeEvent event = new ATGBiomeEvent( type, group, replacement, toReplace, weight );
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	// Sub-biomes
	
	public static void addSubBiome(BiomeGenBase biome, BiomeGenBase subBiome, double weight) {
		ATGBiomeModEvent event = new ATGBiomeModEvent(ATGBiomeModEvent.EventType.SUBBIOME, biome, null, subBiome, weight);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	// Gen Mods
	
	public static void addGenMod(BiomeGenBase biome, IGenMod mod) {
		ATGBiomeModEvent event = new ATGBiomeModEvent(ATGBiomeModEvent.EventType.GENMOD, biome, mod, null, 0);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	public static Optional<IGenMod> getGenMod(BiomeGenBase biome) {
		ATGBiomeModRequestEvent event = new ATGBiomeModRequestEvent(biome);
		MinecraftForge.EVENT_BUS.post(event);
		return event.mod;
	}
}
