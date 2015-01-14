/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.mixin.biome;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.biome.BiomeGenBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.BiomeProperty;
import biomesoplenty.api.biome.IExtendedBiome;

@Mixin(BiomeGenBase.class)
public abstract class MixinBiomeGenBase implements IExtendedBiome
{
	private BiomeOwner biomeOwner;
	private HashMap<BiomeProperty, Object> properties;
	
	@Inject(method = "<init>(IZ)V", at = @At("RETURN"))
    private void onConstructed(int biomeId, boolean register, CallbackInfo callbackInfo) 
    {
		this.biomeOwner = BiomeOwner.OTHER;
		this.properties = new HashMap();
    }
	
	@Override
	public BiomeOwner getBiomeOwner()
	{
		return biomeOwner;
	}
	
	@Override
	public void setProperty(BiomeProperty property, Object value)
	{
		if (property.getDefaultValue() != value) 
			this.properties.put(property, value);
	}
	
	@Override
	public Object getProperty(BiomeProperty property)
	{
		return this.properties.get(property);
	}
	
	@Override
	public Map<BiomeProperty, Object> getPropertyMap()
	{
		return properties;
	}
}
