package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenLandOfLakesMarsh extends BOPSubBiome
{
	private static final Height biomeHeight = new Height(-0.1F, 0.05F);
	
	public BiomeGenLandOfLakesMarsh(int id)
	{
		super(id);
		
		this.zoom = 0.5D;
		this.threshold = 0.25D;

        this.setHeight(biomeHeight);
        this.setColor(6725742);
        this.setTemperatureRainfall(0.5F, 0.9F);

		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = 3;
		this.theBiomeDecorator.grassPerChunk = 25;
		
		this.theBiomeDecorator.sandPerChunk = 1;
		this.theBiomeDecorator.sandPerChunk2 = 1;

		this.theBiomeDecorator.bopFeatures.wildRicePerChunk = 8;
		this.theBiomeDecorator.bopFeatures.mudPerChunk = 9;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 15;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 25;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}
	
	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(3) == 0 ? new WorldGenTaiga2(false) : 
			new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, false, 5, 3, false);
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(10))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
			}
		}
	}
	
	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 13414508 : 13419628;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 12766316 : 10730594;
	}
	
	/*@Override
	public int getFogColour()
	{
		return 12638463;
	}
	
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
    */
}
