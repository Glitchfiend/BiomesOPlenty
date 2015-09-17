package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenMeadowForest extends BOPSubBiome
{
	public BiomeGenMeadowForest(int id)
	{
		super(id);
		
		this.zoom = 0.25D;
		this.threshold = 0.25D;
		
        this.setColor(5543515);
        this.setTemperatureRainfall(0.7F, 0.7F);

	    this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 7;
        this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 0), 10);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 4), 8);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}
	
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 6, 4, 0, 4);
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
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 6533741;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 6533741;
    }
}
