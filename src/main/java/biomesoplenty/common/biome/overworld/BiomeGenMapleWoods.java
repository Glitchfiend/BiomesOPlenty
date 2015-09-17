package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenMapleWoods extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);
    
	public BiomeGenMapleWoods(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(6988649);
        this.setTemperatureRainfall(0.2F, 0.8F);
		
		this.theBiomeDecorator.treesPerChunk = 9;
		this.theBiomeDecorator.grassPerChunk = 1;

		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 8;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 8), 1);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(6) == 0 ? new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 9, 9, 6, 4) : 
		new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves3, 0, 2, false, 5, 3, false);
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
}
