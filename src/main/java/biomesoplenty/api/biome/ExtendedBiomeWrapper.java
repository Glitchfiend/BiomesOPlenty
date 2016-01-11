package biomesoplenty.api.biome;

import java.util.HashMap;
import java.util.Map;

import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.world.biome.BiomeGenBase;

public class ExtendedBiomeWrapper implements IExtendedBiome
{
    public final BiomeGenBase biome;
    private GenerationManager generationManager = new GenerationManager();
    private Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    
    public ExtendedBiomeWrapper(BiomeGenBase biome)
    {
        this.biome = biome;
    }

    @Override
    public void configure(IConfigObj conf) 
    {
        // Allow generators to be configured
        IConfigObj confGenerators = conf.getObject("generators");
        if (confGenerators != null)
        {
            for (String name : confGenerators.getKeys())
            {
                this.generationManager.configureWith(name, confGenerators.getObject(name));
            }
        }
    }
    
    @Override
    public BiomeOwner getBiomeOwner()
    {
        return BiomeOwner.OTHER;
    }

    @Override
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator)
    {
        this.generationManager.addGenerator(name, stage, generator);
    }
    
    @Override
    public GenerationManager getGenerationManager()
    {
        return this.generationManager;
    }
    
    @Override
    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }
    
    @Override
    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }
    
    @Override
    public void clearWeights()
    {
        this.weightMap.clear();
    }

    @Override
    public BiomeGenBase getBaseBiome() 
    {
        return this.biome;
    }
}
