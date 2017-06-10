package biomesoplenty.common.biome.vanilla;

import java.util.HashMap;
import java.util.Map;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerator;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.GenerationManager;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class ExtendedBiomeWrapper implements IExtendedBiome
{
    public final Biome biome;
    private GenerationManager generationManager = new GenerationManager();
    private Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    
    public ResourceLocation beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.BEACH);
    
    public ExtendedBiomeWrapper(Biome biome)
    {
        this.biome = biome;
        
        // roots
        this.addGenerator("roots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(4.0F).with(BOPPlants.ROOT).create());
    
        IBlockPosQuery suitableStonePosition = BlockQuery.buildAnd().withAltitudeBetween(0, 55).blocks(Blocks.STONE).create();
        this.addGenerator("miners_delight", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).generationAttempts(64).with(BOPFlowers.MINERS_DELIGHT).placeOn(suitableStonePosition).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("glowshrooms", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).generationAttempts(64).placeOn(suitableStonePosition).with(BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, BlockBOPMushroom.MushroomType.GLOWSHROOM)).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("stone_formations", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(30.0F).generationAttempts(32).placeOn(suitableStonePosition).with(BOPBlocks.stone_formations.getDefaultState()).minHeight(1).maxHeight(7).randomDirection(true).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
    }

    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("roots");}
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
    }
    
    @Override
    public void configure(IConfigObj conf) 
    {
        this.beachBiomeLocation = conf.getResourceLocation("beachBiomeLocation", this.beachBiomeLocation);
        
        // Allow generators to be configured
        IConfigObj confGenerators = conf.getObject("generators");
        this.generationManager.configure(confGenerators);

        // write default values to a file
        ModBiomes.writeDefaultConfigFile(ModBiomes.VANILLA_DEFAULTS_DIR, this.getResourceLocation().getResourcePath(), conf);
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
    
    public IGenerator getGenerator(String name)
    {
    	return this.generationManager.getGenerator(name);
    }
    
    public void removeGenerator(String name)
    {
        this.generationManager.removeGenerator(name);
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
    public ResourceLocation getBeachLocation() 
    {
        return this.beachBiomeLocation;
    }
    
    @Override
    public ResourceLocation getResourceLocation() 
    {
        return BiomeUtils.getLocForBiome(this.biome);
    }

    @Override
    public Biome getBaseBiome() 
    {
        return this.biome;
    }
}
