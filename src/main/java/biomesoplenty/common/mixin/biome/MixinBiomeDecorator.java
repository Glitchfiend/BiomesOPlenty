/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.mixin.biome;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.biome.BiomeDecorator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import biomesoplenty.api.biome.IExtendedDecorator;
import biomesoplenty.api.biome.IGenerator;

@Mixin(BiomeDecorator.class)
public class MixinBiomeDecorator implements IExtendedDecorator
{
	private Map<String, IGenerator<?>> generatorMap;
	
	@Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructed(CallbackInfo callbackInfo) 
    {
		this.generatorMap = new HashMap();
    }
	
	@Override
	public void addGenerator(String key, IGenerator<?> generator) 
	{
		this.generatorMap.put(key, generator);
	}
	
	@Override
	public void configureGenerators(Map<String, IGenerator<?>> generatorMap)
	{
		this.generatorMap.putAll(generatorMap);
	}
	
	@Override
	public Map<String, IGenerator<?>> getGeneratorMap()
	{
		return Collections.unmodifiableMap(this.generatorMap);
	}
}


