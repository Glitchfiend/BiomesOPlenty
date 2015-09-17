package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.entities.EntityRosester;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenGiantFlower;

public class BiomeGenGarden extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenGarden(int biomeID) 
	{
		super(biomeID);

        this.setHeight(biomeHeight);
        this.setColor(7656308);
        this.setTemperatureRainfall(0.7F, 0.8F);

        this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityRosester.class, 10, 4, 4));
		
		this.topBlock = BOPCBlocks.longGrass;
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.generateMelons = true;
		
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 25;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 0), 15);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 20);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 4), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(0, 3), 2);
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 1), 0.25D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 2), 0.25D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.75D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(6) == 0 ? new WorldGenGiantFlower(0) : (random.nextInt(6) == 0 ? new WorldGenGiantFlower(1) : new WorldGenShrub(0, 0));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(4))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 4, 2);
			}
		}
	}

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 7656308;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 6742630;
	}
}
