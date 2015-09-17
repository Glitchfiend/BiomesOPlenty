package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenOriginValley extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);

	public BiomeGenOriginValley(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(10341485);
        this.setTemperatureRainfall(0.7F, 0.8F);
		
		this.topBlock = BOPCBlocks.originGrass;
		this.theBiomeDecorator.treesPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.sandPerChunk2 = 0;
		this.theBiomeDecorator.clayPerChunk = 0;
		this.theBiomeDecorator.flowersPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.rootsPerChunk = -999;
        this.theBiomeDecorator.bopFeatures.stalagmitesPerChunk = -999;
        this.theBiomeDecorator.bopFeatures.stalactitesPerChunk = -999;
        this.theBiomeDecorator.bopFeatures.minersDelightPerChunk = -999;
        //TODO: FEATURE this.theBiomeDecorator.generateUndergroundLakes = false;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers2, 8), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.yellow_flower, 0), 10);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves3, 0, 0, false, 5, 3, false);
	}

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 10682207;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 3866368;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 8441086;
		else return super.getSkyColorByTemp(par1);
	}
}
