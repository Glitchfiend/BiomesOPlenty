/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.mixin.decoration;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenerator;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

import biomesoplenty.api.biome.IGenerator;
import biomesoplenty.common.decoration.extensions.IExtendedCactusGen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Mixin(WorldGenCactus.class)
@Implements(@Interface(iface = IExtendedCactusGen.class, prefix = "extendedCactus$"))
public abstract class MixinWorldGenCactus extends WorldGenerator //implements IExtendedCactusGen
{
    private int cactiPerChunk;
    
    public void extendedCactus$generate(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < cactiPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            int y = random.nextInt(world.getHeight(pos.add(x, 0, z)).getY() * 2);
            
            this.generate(world, random, pos.add(x, y, z));
        }
    }
    
    public void extendedCactus$setCactiPerChunk(int amount)
    {
        this.cactiPerChunk = amount;
    }

    public int extendedCactus$getCactiPerChunk()
    {
        return this.cactiPerChunk;
    }

    public JsonElement extendedCactus$serialize(IGenerator<WorldGenCactus> src)
    {
        JsonObject jsonCactusGen = new JsonObject();

        jsonCactusGen.addProperty("cacti_per_chunk", ((IExtendedCactusGen) src).getCactiPerChunk());

        return jsonCactusGen;
    }

    public IGenerator<WorldGenCactus> extendedCactus$deserialize(JsonElement json)
    {
        JsonObject jsonCactusGen = json.getAsJsonObject();
        WorldGenCactus cactusGen = new WorldGenCactus();
        IExtendedCactusGen extendedCactusGen = (IExtendedCactusGen) cactusGen;
        
        extendedCactusGen.setCactiPerChunk(jsonCactusGen.get("cacti_per_chunk").getAsInt());

        return extendedCactusGen;
    }
}
