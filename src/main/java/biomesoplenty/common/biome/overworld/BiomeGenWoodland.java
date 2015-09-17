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
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenWoodland extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);
    
	public BiomeGenWoodland(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(8694061);
        this.setTemperatureRainfall(0.6F, 0.4F);

		this.theBiomeDecorator.treesPerChunk = 9;
		this.theBiomeDecorator.grassPerChunk = 7;
		this.theBiomeDecorator.mushroomsPerChunk = 4;

		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.logsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 3;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 7;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(4, 5), 6);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}
	

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(10) == 0 ? worldGeneratorBigTree : (random.nextInt(15) == 0 ? new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, 3) : worldGeneratorTrees);
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(14))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 14, 2);
			}
		}
	}
    
    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return 10729292;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 9348641;
    }
}
