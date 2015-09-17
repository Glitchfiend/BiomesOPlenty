package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenSpruceWoods extends BOPSubBiome
{
    public BiomeGenSpruceWoods(int id)
    {
        super(id);
        
        this.zoom = 0.25D;
		this.threshold = 0.25D;

        this.setColor(6396257);
        this.setTemperatureRainfall(0.7F, 0.8F);

        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));

        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 6;
        this.theBiomeDecorator.mushroomsPerChunk = 4;

        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 5;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 4), 8);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? worldGeneratorTrees : new WorldGenTaiga2(false);
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
}
