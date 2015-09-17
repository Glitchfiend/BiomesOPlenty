package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BiomeGenFrostForest extends BOPOverworldBiome implements IBiomeFog
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenFrostForest(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(11261628);
        this.setTemperatureRainfall(0.0F, 0.5F);
        this.setEnableSnow();
		
		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers2, 7), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 8), 8);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return worldGeneratorTrees;
	}
	
	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);
			
			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(8))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 8, 2);
			}
		}
	}

	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 11261628;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 11261628;
	}
	
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 13557994;
		else return super.getSkyColorByTemp(par1);
	}
	
	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 12239814;
	}
	
    @Override
   	public float getFogDensity(int x, int y, int z)
    {
        return 0.6F;
    }
    
}
