package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;

public class BiomeGenEucalyptusForest extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.0F, 0.2F);
	
	public BiomeGenEucalyptusForest(int biomeID)
	{
		super(biomeID);
		
        this.setHeight(biomeHeight);
        this.setColor(8704360);
        this.setTemperatureRainfall(0.9F, 1.0F);
		
		this.theBiomeDecorator.treesPerChunk = 5;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		
		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 2;
		this.theBiomeDecorator.bopFeatures.bushesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 1;
		
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 12;
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 2D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.25D);
	}

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(6))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 6, 2);
			}
		}

		for (int i = 0; i < 20; i++)
		{
			int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
			int z = chunkZ + random.nextInt(16) + 8;

			new WorldGenMoss().generate(world, random, x, y, z);
		}
	}

	 @Override
	 public WorldGenAbstractTree func_150567_a(Random random)
	 {
		 return random.nextInt(5) == 0 ? new WorldGenBulbTree(Blocks.log, Blocks.leaves, 3, 3, false, 15, 25, true) : new WorldGenBOPShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.dirt, Blocks.grass);
	 }

	 @Override
	 public int getBiomeGrassColor(int x, int y, int z)
	 {
		 return 8704360;
	 }

	 @Override
	 public int getBiomeFoliageColor(int x, int y, int z)
	 {
		 return 6803026;
	 }
	 
	public int getFogColour(int x, int y, int z)
	{
		return 14805212;
	}
	
    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.5F;
    }
}
