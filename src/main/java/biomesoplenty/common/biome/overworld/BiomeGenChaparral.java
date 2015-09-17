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
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenChaparral3;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenChaparral extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);

    public BiomeGenChaparral(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(12638301);
        this.setTemperatureRainfall(0.8F, 0.6F);

		this.spawnableCreatureList.clear();
		
	    this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.generateStoneInGrass = true;
        this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 20;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(4, 5), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 4);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenMiniShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass, Blocks.sand) : (random.nextInt(5) == 0 ?  new WorldGenBOPShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass, Blocks.sand) : new WorldGenChaparral3());
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
        return 12638301;
    }
}
