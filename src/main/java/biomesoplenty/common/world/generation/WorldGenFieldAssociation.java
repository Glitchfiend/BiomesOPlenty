package biomesoplenty.common.world.generation;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.*;
import biomesoplenty.common.world.features.managers.WorldGenBOPFlowerManager;
import biomesoplenty.common.world.features.managers.WorldGenBOPGrassManager;
import biomesoplenty.common.world.forcedgenerators.LakesForcedGenerator;
import biomesoplenty.common.world.forcedgenerators.MelonForcedGenerator;
import biomesoplenty.common.world.forcedgenerators.PondForcedGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;

public class WorldGenFieldAssociation
{
    public static HashMap<String, WorldFeature> featureMap = new HashMap<String, WorldFeature>();

    public static void init()
    {
        associateFeatures();
        associateFeaturesForced();
    }

    private static void associateFeatures()
    {
        associateFeature("generateQuicksand", new WorldGenSplotches(BOPBlockHelper.get("mud"), 1, 24, Blocks.grass, Blocks.dirt, Blocks.sand));
        associateFeature("generateCanyon", new WorldGenSplotches(BOPBlockHelper.get("redRock"), 0, 48, Blocks.stone));
        associateFeature("generateStoneInGrass", new WorldGenSplotches(Blocks.stone, 0, 32, Blocks.grass, BOPBlockHelper.get("grass")));
        associateFeature("generateStoneInGrass2", new WorldGenSplotches(Blocks.stone, 0, 48, Blocks.grass, Blocks.dirt));
        associateFeature("generateGrass", new WorldGenSplotches(Blocks.grass, 0, 48, BOPBlockHelper.get("redRock")));
        associateFeature("generateSand", new WorldGenSplotches(Blocks.sand, 0, 32, BOPBlockHelper.get("redRock")));
        associateFeature("generateQuagmire", new WorldGenSplotches(Blocks.grass, 0, 48, BOPBlockHelper.get("mud")));
        associateFeature("generateAsh", new WorldGenSplotches(BOPBlockHelper.get("ash"), 0, 32, BOPBlockHelper.get("ashStone"), Blocks.netherrack));

        associateFeature("mudPerChunk", new WorldGenWaterside(BOPBlockHelper.get("mud"), 7, Blocks.dirt, Blocks.grass));
        associateFeature("riverCanePerChunk", new WorldGenRiverCane());
        associateFeature("shrubsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 9));
        associateFeature("bushesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 4));
        associateFeature("cloverPatchesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 13, 128));
        associateFeature("leafPilesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 14, 256));
        associateFeature("deadLeafPilesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 15, 256));
        associateFeature("lavenderPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("flowers2"), 3));
        associateFeature("thornsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 5));
        associateFeature("stalagmitesPerChunk", new WorldGenBOPUndergroundDecoration(BOPBlockHelper.get("stoneFormations"), 0));
        associateFeature("stalactitesPerChunk", new WorldGenBOPUndergroundDecoration(BOPBlockHelper.get("stoneFormations"), 1));
        associateFeature("desertSproutsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 2));
        associateFeature("bromeliadsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 12));
        associateFeature("waterReedsPerChunk", new WorldGenWaterReeds());
        associateFeature("seaweedPerChunk", new WorldGenBOPCoral(BOPBlockHelper.get("coral"), 8, 256));
        associateFeature("wildCarrotsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 11));
        associateFeature("poisonIvyPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 7));
        associateFeature("berryBushesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 8));
        associateFeature("portobellosPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("mushrooms"), 1));
        associateFeature("koruPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 12));
        associateFeature("toadstoolsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("mushrooms"), 0));
        associateFeature("blueMilksPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("mushrooms"), 2));
        associateFeature("cattailsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 7));
        associateFeature("highCattailsPerChunk", new WorldGenBOPDoubleFlora(BOPBlockHelper.get("plants"), BOPBlockHelper.get("plants"), 10, 9));
        associateFeature("algaePerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 0, 256));
        associateFeature("sproutsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 5));
        associateFeature("tinyCactiPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 12));
        associateFeature("oasesPerChunk", new WorldGenWaterside(Blocks.grass, 7, Blocks.sand, Blocks.hardened_clay));
        associateFeature("minersDelightPerChunk", new WorldGenBOPUndergroundDecoration(BOPBlockHelper.get("flowers2"), 6));
        associateFeature("rootsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("plants"), 15));
        associateFeature("grassSplatterPerChunk", new WorldGenGrassSplatter());
        associateFeature("rockpilesPerChunk", new WorldGenBOPBlob(Blocks.cobblestone, 0));
        associateFeature("wastelandRockPilesPerChunk", new WorldGenBOPBlob(BOPBlockHelper.get("driedDirt"), 0));
        associateFeature("logsPerChunk", new WorldGenLog());
        associateFeature("lavaSpoutsPerChunk", new WorldGenLavaSpout());
        associateFeature("cobwebsPerChunk", new WorldGenBOPFlora(Blocks.web, 0));
        associateFeature("cobwebNestsPerChunk", new WorldGenCobwebNest());
        associateFeature("wasteland1PerChunk", new WorldGenWasteland());
        associateFeature("wasteland2PerChunk", new WorldGenWasteland2());
        associateFeature("wasteland3PerChunk", new WorldGenWasteland3());
        associateFeature("wasteland4PerChunk", new WorldGenWasteland4());
        associateFeature("cookiesPerChunk", new WorldGenCookie());

        associateFeature("bopFlowersPerChunk", new WorldGenBOPFlowerManager());
        associateFeature("bopGrassPerChunk", new WorldGenBOPGrassManager());
    }
    private static void associateFeaturesForced()
    {
        associateFeatureForced("waterPoolsPerChunk", new WorldGenLiquids(Blocks.flowing_water), PondForcedGenerator.class);
        associateFeatureForced("lavaPoolsPerChunk", new WorldGenLiquids(Blocks.flowing_lava), PondForcedGenerator.class);

        associateFeatureForced("waterLakesPerChunk", new WorldGenLakes(Blocks.water), LakesForcedGenerator.class);
        associateFeatureForced("lavaLakesPerChunk", new WorldGenLakes(Blocks.lava), LakesForcedGenerator.class);

        associateFeatureForced("generateMelons", new WorldGenMelon(), MelonForcedGenerator.class);
    }

    public static void associateFeature(String name, WorldFeature feature)
    {
        featureMap.put(name, feature);
    }

    public static void associateFeature(String name, WorldGenerator generator)
    {
        featureMap.put(name, new WorldFeature(generator));
    }

    public static void associateFeatureForced(String name, WorldGenerator generator, Class<? extends ForcedWorldFeatureBOP> forcedFeature)
    {
        associateFeature(name, new WorldFeature(generator, forcedFeature));
    }

    public static WorldFeature getAssociatedFeature(String name)
    {
        return featureMap.get(name);
    }

    public static class WorldFeature
    {
        private WorldGenerator worldGenerator;
        private Class<? extends ForcedWorldFeatureBOP> forcedFeature;

        protected WorldFeature(WorldGenerator worldGenerator, Class<? extends ForcedWorldFeatureBOP> forcedFeature)
        {
            this.worldGenerator = worldGenerator;
            this.forcedFeature = forcedFeature;
        }

        protected WorldFeature(WorldGenerator worldGenerator)
        {
            this(worldGenerator, null);
        }

        public IBOPWorldGenerator getBOPWorldGenerator()
        {
            if (this.worldGenerator instanceof IBOPWorldGenerator)
            {
                return (IBOPWorldGenerator)this.worldGenerator;
            }
            else if (forcedFeature != null)
            {
                try
                {
                   return (IBOPWorldGenerator)forcedFeature.getConstructor(WorldGenerator.class).newInstance(worldGenerator);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}