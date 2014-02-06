package biomesoplenty.common.world;

import java.util.List;
import java.util.Random;

import biomesoplenty.common.configuration.BOPConfigurationMisc;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;

public class WorldChunkManagerBOP extends WorldChunkManager
{
	public WorldChunkManagerBOP(World world)
	{
		super(world);
	}
	
    @Override
	public ChunkPosition findBiomePosition(int x, int z, int radius, List biomesToSpawnIn, Random random)
    {
    	int spawnSearchRadius = BOPConfigurationMisc.spawnSearchRadius;
    	
    	return super.findBiomePosition(x, z, spawnSearchRadius, biomesToSpawnIn, random);
    }
}
