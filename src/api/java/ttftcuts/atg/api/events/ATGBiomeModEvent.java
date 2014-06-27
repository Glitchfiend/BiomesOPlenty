package ttftcuts.atg.api.events;

import ttftcuts.atg.api.IGenMod;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.Event;

public class ATGBiomeModEvent extends Event {

	public static enum EventType { GENMOD, SUBBIOME }
	
	public EventType type;
	public BiomeGenBase biome;
	public IGenMod mod;
	public BiomeGenBase subBiome;
	public double weight;
	
	public ATGBiomeModEvent( EventType type, BiomeGenBase biome, IGenMod mod, BiomeGenBase subBiome, double weight ) {
		
		this.type = type;
		this.biome = biome;
		this.subBiome = subBiome;
		this.mod = mod;
		this.weight = weight;
	}
}
