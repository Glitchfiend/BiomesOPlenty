package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.generation.WorldGenFieldAssociation;

import java.util.HashMap;
import java.util.Set;

public class BOPWorldFeatures
{
    private HashMap<String, Object> featureValueMap = new HashMap<String, Object>();

    static
    {
        WorldGenFieldAssociation.init();
    }

    protected BOPWorldFeatures()
    {
        setFeature("generatePumpkins", true);
        setFeature("generateQuicksand", false);
        setFeature("generateCanyon", false);
        setFeature("generateStoneInGrass", false);
        setFeature("generateStoneInGrass2", false);
        setFeature("generateGrass", false);
        setFeature("generateSand", false);
        setFeature("generateQuagmire", false);
        setFeature("generateAsh", false);
        setFeature("generateMelons", false);

        setFeature("waterPoolsPerChunk", 50);
        setFeature("lavaPoolsPerChunk", 20);

        setFeature("waterLakesPerChunk", 0);
        setFeature("lavaLakesPerChunk", 0);

        setFeature("mudPerChunk", 0);
        setFeature("riverCanePerChunk", 0);
        setFeature("shrubsPerChunk", 0);
        setFeature("bushesPerChunk", 0);
        setFeature("cloverPatchesPerChunk", 0);
        setFeature("seaweedPerChunk", 0);
        setFeature("leafPilesPerChunk", 0);
        setFeature("deadLeafPilesPerChunk", 0);
        setFeature("lavenderPerChunk", 0);
        setFeature("thornsPerChunk", 0);
        setFeature("stalagmitesPerChunk", 3);
        setFeature("stalactitesPerChunk", 6);
        setFeature("desertSproutsPerChunk", 0);
        setFeature("bromeliadsPerChunk", 0);
        setFeature("waterReedsPerChunk", 0);
        setFeature("wildCarrotsPerChunk", 0);
        setFeature("poisonIvyPerChunk", 0);
        setFeature("berryBushesPerChunk", 0);
        setFeature("portobellosPerChunk", 0);
        setFeature("koruPerChunk", 0);
        setFeature("toadstoolsPerChunk", 0);
        setFeature("blueMilksPerChunk", 0);
        setFeature("cattailsPerChunk", 0);
        setFeature("highCattailsPerChunk", 0);
        setFeature("algaePerChunk", 0);
        setFeature("sproutsPerChunk", 0);
        setFeature("tinyCactiPerChunk", 0);
        setFeature("oasesPerChunk", 0);
        setFeature("minersDelightPerChunk", 2);
        setFeature("rootsPerChunk", 9);
        setFeature("grassSplatterPerChunk", 0);
        setFeature("rockpilesPerChunk", 0);
        setFeature("logsPerChunk", 0);
        setFeature("lavaSpoutsPerChunk", 0);
        setFeature("cobwebsPerChunk", 0);
        setFeature("cobwebNestsPerChunk", 0);
        setFeature("wasteland1PerChunk", 0);
        setFeature("wasteland2PerChunk", 0);
        setFeature("wasteland3PerChunk", 0);
        setFeature("wasteland4PerChunk", 0);

        setFeature("bopFlowersPerChunk", 0);
    }

    public <T extends Object> void setFeature(String name, T value)
    {
        featureValueMap.put(name, value);
    }

    public Object getFeature(String name)
    {
        return featureValueMap.get(name);
    }

    public Set<String> getFeatureNames()
    {
        return featureValueMap.keySet();
    }
}
