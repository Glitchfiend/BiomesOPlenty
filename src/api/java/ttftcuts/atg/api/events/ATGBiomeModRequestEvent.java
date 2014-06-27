package ttftcuts.atg.api.events;

import ttftcuts.atg.api.IGenMod;

import com.google.common.base.Optional;

import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.Event;

public class ATGBiomeModRequestEvent extends Event {

	public BiomeGenBase biome;
	public Optional<IGenMod> mod;
	
	public ATGBiomeModRequestEvent(BiomeGenBase biome) {
		this.biome = biome;
		this.mod = Optional.absent();
	}
}
