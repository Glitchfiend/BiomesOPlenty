package biomesoplenty.common.world.generation;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenCobwebNest;
import biomesoplenty.common.world.features.WorldGenGrassSplatter;
import biomesoplenty.common.world.features.WorldGenLavaSpout;
import biomesoplenty.common.world.features.WorldGenLog;
import biomesoplenty.common.world.features.WorldGenRiverCane;
import biomesoplenty.common.world.features.WorldGenRockpile;
import biomesoplenty.common.world.features.WorldGenSplotches;
import biomesoplenty.common.world.features.WorldGenWasteland;
import biomesoplenty.common.world.features.WorldGenWasteland2;
import biomesoplenty.common.world.features.WorldGenWasteland3;
import biomesoplenty.common.world.features.WorldGenWasteland4;
import biomesoplenty.common.world.features.WorldGenWaterReeds;
import biomesoplenty.common.world.features.WorldGenWaterside;

public class WorldGenFieldAssociation 
{
	public static HashMap<String, WorldGenerator> worldGeneratorMap = new HashMap();
	
	public static void init()
	{
		associateFieldsWithGenerators();
	}
	
	private static void associateFieldsWithGenerators()
	{
	    associateField("generateQuicksand", new WorldGenSplotches(BOPBlockHelper.get("mud"), 1, 24, Blocks.grass, Blocks.dirt, Blocks.sand));
	    associateField("generateCanyon", new WorldGenSplotches(BOPBlockHelper.get("redRock"), 0, 48, Blocks.stone));
	    associateField("generateStoneInGrass", new WorldGenSplotches(Blocks.stone, 0, 32, Blocks.grass, BOPBlockHelper.get("holyGrass")));
	    associateField("generateStoneInGrass2", new WorldGenSplotches(Blocks.stone, 0, 48, Blocks.grass, Blocks.dirt));
	    associateField("generateGrass", new WorldGenSplotches(Blocks.grass, 0, 48, BOPBlockHelper.get("redRock")));
	    associateField("generateSand", new WorldGenSplotches(Blocks.sand, 0, 32, BOPBlockHelper.get("redRock")));
	    associateField("generateQuagmire", new WorldGenSplotches(Blocks.grass, 0, 48, BOPBlockHelper.get("mud")));
	    associateField("generateAsh", new WorldGenSplotches(BOPBlockHelper.get("ash"), 0, 32, BOPBlockHelper.get("ashStone"), Blocks.netherrack));
	    associateField("generateMelons", new WorldGenMelon());

	    associateField("waterPoolsPerChunk", new WorldGenLiquids(Blocks.flowing_water));
	    associateField("lavaPoolsPerChunk", new WorldGenLiquids(Blocks.flowing_lava));
	    
	    associateField("waterLakesPerChunk", new WorldGenLakes(Blocks.water));
	    associateField("lavaLakesPerChunk", new WorldGenLakes(Blocks.lava));
	    
	    associateField("mudPerChunk", new WorldGenWaterside(BOPBlockHelper.get("mud"), 7, Blocks.dirt, Blocks.grass));
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
		associateField("poisonIvyPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 7));
		associateField("berryBushesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 8));
		associateField("portobellosPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("mushrooms"), 1));
		associateField("koruPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 12));
		associateField("toadstoolsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("mushrooms"), 0));
		associateField("blueMilksPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("mushrooms"), 2));
	    associateField("cattailsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 7));
	    associateField("highCattailsPerChunk", new WorldGenBOPDoubleFlora(BOPBlockHelper.get("plants"), BOPBlockHelper.get("plants"), 10, 9));
	    associateField("algaePerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 0));
	    associateField("sproutsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 5));
	    associateField("tinyCactiPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 12));
	    associateField("oasesPerChunk", new WorldGenWaterside(Blocks.grass, 7, Blocks.sand, BOPBlockHelper.get("redRock")));
	    associateField("minersDelightPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("flowers2"), 6));
	    associateField("rootsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 15));
	    associateField("grassSplatterPerChunk", new WorldGenGrassSplatter());
	    associateField("rockpilesPerChunk", new WorldGenRockpile());
	    associateField("logsPerChunk", new WorldGenLog());
	    associateField("lavaSpoutsPerChunk", new WorldGenLavaSpout());
	    associateField("cobwebsPerChunk", new WorldGenBOPFlora(Blocks.web, 0));
	    associateField("cobwebNestsPerChunk", new WorldGenCobwebNest());
	    associateField("wasteland1PerChunk", new WorldGenWasteland());
	    associateField("wasteland2PerChunk", new WorldGenWasteland2());
	    associateField("wasteland3PerChunk", new WorldGenWasteland3());
	    associateField("wasteland4PerChunk", new WorldGenWasteland4());

	    associateField("doubleTallGrassPerChunk", new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 2, 8));
	    associateField("sunflowersPerChunk", new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 0, 8));
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
