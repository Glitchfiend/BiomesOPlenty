package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenMixedTree;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenRainforest extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.2F, 0.9F);

	public BiomeGenRainforest(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(1368687);
        this.setTemperatureRainfall(1.0F, 1.0F);
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
		
		this.theBiomeDecorator.treesPerChunk = 14;
		this.theBiomeDecorator.grassPerChunk = 25;
		this.theBiomeDecorator.waterlilyPerChunk = 2;

		this.theBiomeDecorator.mushroomsPerChunk = 25;
		
		/*TODO: FEATURE customBiomeDecorator.pinkFlowersPerChunk = 2;
		customBiomeDecorator.rosesPerChunk = 10;
		customBiomeDecorator.orangeFlowersPerChunk = 6;*/

		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 25;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 6), 12);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(4, 5), 4);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 6);
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(15) == 0 ? new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 2, 2) : (random.nextInt(5) == 0 ? worldGeneratorBigTree : new WorldGenMixedTree(Blocks.log, Blocks.leaves, 0, 0, BOPCBlocks.colorizedLeaves2, 3));
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPCBlocks.foliage, 10) : new WorldGenTallGrass(BOPCBlocks.foliage, 11)) : (random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : new WorldGenTallGrass(Blocks.tallgrass, 1));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(6))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 6, 2);
			}
		}
	}



	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 1759340;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 1368687;
	}
}
