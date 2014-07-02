package biomesoplenty.api.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import biomesoplenty.common.utils.RandomForcedPositiveOwned;
import biomesoplenty.common.world.generation.IBOPWorldGenerator;
import biomesoplenty.common.world.generation.WorldGenFieldAssociation;

public class BOPBiomeDecorator<T extends BiomeFeatures> extends BiomeDecorator
{
    public T bopFeatures;
    
    public BOPBiomeDecorator(Class<T> biomeFeaturesClass)
    {
    	super();
    	
		try 
		{
			this.bopFeatures = biomeFeaturesClass.newInstance();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException();
		} 
    }
    
    @Override
	public void decorateChunk(World world, Random random, BiomeGenBase biome, int chunkX, int chunkZ)
    {
        if (this.currentWorld != null)
        {
            return;
        }
        else
        {
            this.currentWorld = world;
            this.randomGenerator = new RandomForcedPositiveOwned(random);
            this.chunk_X = chunkX;
            this.chunk_Z = chunkZ;
            this.genDecorations(biome);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }
    
    @Override
    protected void genDecorations(BiomeGenBase biome)
    {
    	super.genDecorations(biome);
    	
    	BOPBiome bopBiome = (BOPBiome)biome;
    	
    	for (String featureName : bopFeatures.getFeatureNames())
    	{
            if (featureName.equals("bopFlowersPerChunk"))
            {
                if (!TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS)) continue;
            }
            else if (featureName.equals("bopGrassPerChunk"))
            {
                if (!TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS)) continue;
            }

            WorldGenFieldAssociation.WorldFeature worldFeature = WorldGenFieldAssociation.getAssociatedFeature(featureName);

            if (worldFeature != null)
            {
                IBOPWorldGenerator worldGenerator = worldFeature.getBOPWorldGenerator();

                if (worldGenerator != null)
                {
                    worldGenerator.setupGeneration(currentWorld, randomGenerator, bopBiome, featureName, chunk_X, chunk_Z);
                }
            }
    	}
    }
    
    public static <T extends WorldGenerator> T getRandomWeightedWorldGenerator(HashMap<T, ? extends Number> worldGeneratorMap)
    {
        double completeWeight = 0D;

        for (Number weight : worldGeneratorMap.values())
        {
            completeWeight += Double.parseDouble(weight.toString());
        }

        double random = Math.random() * completeWeight;
        double countWeight = 0D;

        for (Map.Entry<T, ? extends Number> entry : worldGeneratorMap.entrySet())
        {
            countWeight += Double.parseDouble(entry.getValue().toString());

            if (countWeight >= random) return entry.getKey();
        }

        return null;
    }

    protected int nextInt(int i) 
    {
        if (i <= 1) return 0;
        return this.randomGenerator.nextInt(i);
	}
}
