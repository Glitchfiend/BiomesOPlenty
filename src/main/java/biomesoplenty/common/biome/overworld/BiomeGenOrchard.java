package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenMixedTree;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenOrchard extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenOrchard(int biomeID) 
	{
		super(biomeID);
		
		this.setHeight(biomeHeight);
        this.setColor(14024557);
        this.setTemperatureRainfall(0.7F, 0.7F);
        
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
		this.theBiomeDecorator.treesPerChunk = 2;
		
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;
		
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 15;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 0), 20);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 20);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(0, 3), 2);
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 10), 0.5D);
	}
	
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
    	return random.nextInt(15) == 0 ? new WorldGenMixedTree(Blocks.log, Blocks.leaves, 0, 0, BOPCBlocks.colorizedLeaves2, 3, 0) : (random.nextInt(15) == 0 ? new WorldGenMixedTree(Blocks.log, Blocks.leaves, 0, 0, BOPCBlocks.colorizedLeaves2, 3, 2) : (random.nextInt(15) == 0 ? new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, 3) : this.worldGeneratorTrees));
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
        return 14024557;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 14024557;
    }
}
