package biomesoplenty.common.world;

import java.util.HashMap;

import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMud;
import biomesoplenty.common.world.features.WorldGenRiverCane;
import biomesoplenty.common.world.features.WorldGenWaterReeds;

public class WorldGenFieldAssociation 
{
	public static HashMap<String, WorldGenerator> worldGeneratorMap = new HashMap();
	
	public static void init()
	{
		associateFieldsWithGenerators();
	}
	
	private static void associateFieldsWithGenerators()
	{
		associateField("mudPerChunk", new WorldGenMud(7));
		associateField("riverCanePerChunk", new WorldGenRiverCane());
		associateField("shrubsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 9));
		associateField("bushesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 4));
		associateField("cloverPatchesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 13, 128));
		associateField("lavenderPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("flowers2"), 3));
		associateField("thornsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 5));
		associateField("stalagmitesPerChunk", new WorldGenBOPTallGrass(BOPBlockHelper.get("stoneFormations"), 0));
		associateField("stalactitesPerChunk", new WorldGenBOPTallGrass(BOPBlockHelper.get("stoneFormations"), 1));
		associateField("desertSproutsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 2));
		associateField("bromeliadsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 12));
		associateField("waterReedsPerChunk", new WorldGenWaterReeds());
		associateField("wildCarrotsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 11));
		
		WorldGenDoublePlant doubleTallGrass = new WorldGenDoublePlant();
		//TODO:			setMetadata() ?
		doubleTallGrass.func_150548_a(2);
		associateField("doubleTallGrassPerChunk", doubleTallGrass);
	}
	
	public static void associateField(String fieldName, WorldGenerator generator)
	{
		worldGeneratorMap.put(fieldName, generator);
	}
	
	public static WorldGenerator getAssociatedWorldGenerator(String fieldName)
	{
		return worldGeneratorMap.get(fieldName);
	}
}
