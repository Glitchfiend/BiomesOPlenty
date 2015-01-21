/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.mixin.decoration;

import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenerator;

import org.spongepowered.asm.mixin.Mixin;

import biomesoplenty.api.biome.IGenerator;
import biomesoplenty.common.decoration.extensions.IExtendedCactusGen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Mixin(WorldGenCactus.class)
public abstract class MixinWorldGenCactus extends WorldGenerator implements IExtendedCactusGen
{
	private int cactiPerChunk;
	
	@Override
	public void setCactiPerChunk(int amount)
	{
		this.cactiPerChunk = amount;
	}
	
	@Override
	public int getCactiPerChunk()
	{
		return this.cactiPerChunk;
	}

	@Override
	public JsonElement serialize(IGenerator<WorldGenCactus> src) 
	{
		JsonObject jsonCactusGen = new JsonObject();

		jsonCactusGen.addProperty("cacti_per_chunk", ((IExtendedCactusGen)src).getCactiPerChunk());

		return jsonCactusGen;
	}

	@Override
	public IGenerator<WorldGenCactus> deserialize(JsonElement json) 
	{
		JsonObject jsonCactusGen = json.getAsJsonObject();
		WorldGenCactus cactusGen = new WorldGenCactus();
		IExtendedCactusGen extendedCactusGen = (IExtendedCactusGen)cactusGen;
		
		extendedCactusGen.setCactiPerChunk(jsonCactusGen.get("cacti_per_chunk").getAsInt());
		
		return (IGenerator<WorldGenCactus>)new WorldGenCactus();
	}
}
