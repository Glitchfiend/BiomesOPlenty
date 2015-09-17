package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenMixedTree;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenMysticGrove extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenMysticGrove(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(6934491);
        this.setTemperatureRainfall(0.7F, 1.0F);
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 4, 4));
		
		this.waterColorMultiplier = 16715898;
		
		this.theBiomeDecorator.treesPerChunk = 9;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.flowersPerChunk = 8;
		this.theBiomeDecorator.waterlilyPerChunk = 2;

		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.poisonLakesPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 6), 12);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 3), 10);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 4), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 3), 6);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 6);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 4);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(4) == 0 ? new WorldGenOriginalTree(BOPCBlocks.logs2, BOPCBlocks.leaves1, 1, 2, false, 5, 3, false) : 
		(random.nextInt(3) == 0 ? new WorldGenOriginalTree(BOPCBlocks.logs4, BOPCBlocks.leaves4, 2, 1) : 
		((random.nextInt(3) == 0 ? this.worldGeneratorBigTree : 
		((random.nextInt(5) == 0 ? new WorldGenBOPSwampTree(Blocks.log, Blocks.leaves, 0, 0, 8, 6, BOPCBlocks.flowerVine, -1) : new WorldGenMixedTree(Blocks.log, Blocks.leaves, 0, 0, BOPCBlocks.colorizedLeaves2, 3, -1))))));
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
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6934491;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 7397529;
	}
	
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 8972496;
		else return super.getSkyColorByTemp(par1);
	}
	
	/**
	 * Fog Color
	 */
	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 16755401;
	}

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 1.0F;
    }
    
}
