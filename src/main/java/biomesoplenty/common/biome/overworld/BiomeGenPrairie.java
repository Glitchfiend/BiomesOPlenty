package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenPrairie extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
    
	public BiomeGenPrairie(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(13165952);
        this.setTemperatureRainfall(0.8F, 0.3F);

		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));

		this.theBiomeDecorator.treesPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 30;
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 999;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers2, 4), 12);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 6);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}
	
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 0, 0, false, 6, 1, 7, -1);
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
		return 13165952;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 11395195;
	}
}
