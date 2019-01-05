package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import net.minecraft.init.Blocks;

public class BiomeGenLake extends BOPOverworldBiome
{
    
    public BiomeGenLake()
    {
        super("lake", new PropsBuilder("Lake").withGuiColour(0x41B3E0).withTemperature(0.8F).withRainfall(0.6F));

        // terrain
        this.terrainSettings.avgHeight(59).heightVariation(4, 2);
        
        this.canSpawnInBiome = false;
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();
        this.seaFloorBlock = Blocks.SAND.getDefaultState();
        
        this.beachBiomeLocation = null;

        this.addWeight(BOPClimates.MEDITERRANEAN, 3);
        this.addWeight(BOPClimates.WARM_TEMPERATE, 5);
        
        this.spawnableCreatureList.clear();

        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(11).maxRadius(7).with(Blocks.SAND.getDefaultState()).create());
        
        // water plants
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(5.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());   
    }
}
