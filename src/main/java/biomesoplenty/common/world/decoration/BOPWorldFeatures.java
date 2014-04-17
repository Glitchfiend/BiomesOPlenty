package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.generation.WorldGenFieldAssociation;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;
import java.util.Set;

public class BOPWorldFeatures
{
    public HashMap<WorldGenerator, Double> weightedGrassGen = new HashMap<WorldGenerator, Double>();
    public HashMap<WorldGenBOPFlora, Integer> weightedFlowerGen = new HashMap<WorldGenBOPFlora, Integer>();

    private HashMap<String, Object> featureValueMap = new HashMap<String, Object>();

    static
    {
        WorldGenFieldAssociation.init();
    }

    protected BOPWorldFeatures()
    {
        addFeature("generatePumpkins", true);
        addFeature("generateQuicksand", false);
        addFeature("generateCanyon", false);
        addFeature("generateStoneInGrass", false);
        addFeature("generateStoneInGrass2", false);
        addFeature("generateGrass", false);
        addFeature("generateSand", false);
        addFeature("generateQuagmire", false);
        addFeature("generateAsh", false);
        addFeature("generateMelons", false);

        addFeature("waterPoolsPerChunk", 50);
        addFeature("lavaPoolsPerChunk", 20);

        addFeature("waterLakesPerChunk", 0);
        addFeature("lavaLakesPerChunk", 0);

        addFeature("mudPerChunk", 0);
        addFeature("riverCanePerChunk", 0);
        addFeature("shrubsPerChunk", 0);
        addFeature("bushesPerChunk", 0);
        addFeature("cloverPatchesPerChunk", 0);
        addFeature("seaweedPerChunk", 0);
        addFeature("leafPilesPerChunk", 0);
        addFeature("deadLeafPilesPerChunk", 0);
        addFeature("lavenderPerChunk", 0);
        addFeature("thornsPerChunk", 0);
        addFeature("stalagmitesPerChunk", 3);
        addFeature("stalactitesPerChunk", 6);
        addFeature("desertSproutsPerChunk", 0);
        addFeature("bromeliadsPerChunk", 0);
        addFeature("waterReedsPerChunk", 0);
        addFeature("wildCarrotsPerChunk", 0);
        addFeature("poisonIvyPerChunk", 0);
        addFeature("berryBushesPerChunk", 0);
        addFeature("portobellosPerChunk", 0);
        addFeature("koruPerChunk", 0);
        addFeature("toadstoolsPerChunk", 0);
        addFeature("blueMilksPerChunk", 0);
        addFeature("cattailsPerChunk", 0);
        addFeature("highCattailsPerChunk", 0);
        addFeature("algaePerChunk", 0);
        addFeature("sproutsPerChunk", 0);
        addFeature("tinyCactiPerChunk", 0);
        addFeature("oasesPerChunk", 0);
        addFeature("minersDelightPerChunk", 2);
        addFeature("rootsPerChunk", 9);
        addFeature("grassSplatterPerChunk", 0);
        addFeature("rockpilesPerChunk", 0);
        addFeature("logsPerChunk", 0);
        addFeature("lavaSpoutsPerChunk", 0);
        addFeature("cobwebsPerChunk", 0);
        addFeature("cobwebNestsPerChunk", 0);
        addFeature("wasteland1PerChunk", 0);
        addFeature("wasteland2PerChunk", 0);
        addFeature("wasteland3PerChunk", 0);
        addFeature("wasteland4PerChunk", 0);
        addFeature("wastelandRockPilesPerChunk", 0);

        //Nether Features
        addFeature("waspHivesPerChunk", 0);
        
        addFeature("bopFlowersPerChunk", 0);
        addFeature("bopGrassPerChunk", 0);
    }

    private <T extends Object> void setFeature(String name, T value, boolean initialize)
    {
        if (!initialize)
        {
            if (!featureValueMap.containsKey(name)) throw new NoSuchFeatureException(name);
        }

        featureValueMap.put(name, value);
    }

    public <T extends Object> void setFeature(String name, T value)
    {
        this.setFeature(name, value, false);
    }

    protected <T extends Object> void addFeature(String name, T value)
    {
        this.setFeature(name, value, true);
    }

    public Object getFeature(String name)
    {
        return featureValueMap.get(name);
    }

    public Set<String> getFeatureNames()
    {
        return featureValueMap.keySet();
    }

    public class NoSuchFeatureException extends RuntimeException
    {
        public NoSuchFeatureException(String name)
        {
            super("Feature " + name + " does not exist!");
        }
    }
}
