package ttftcuts.atg.api.events;

import ttftcuts.atg.api.ATGBiomes.BiomeType;

import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.Event;

public class ATGBiomeEvent extends Event {

	public static enum ResponseType { NONE, ADDED, REPLACED, BAD_GROUP, FAILED };
	
	public BiomeType type;
	public ResponseType response;
	public String group;
	public BiomeGenBase biome;
	public BiomeGenBase replaced;
	public double weight;
	
	public ATGBiomeEvent(BiomeType type, String group, BiomeGenBase biome, BiomeGenBase replaced, double weight) {
		this.type = type;
		this.group = group;
		this.biome = biome;
		this.replaced = replaced;
		this.weight = weight;
		this.response = ResponseType.NONE;
	}
}
