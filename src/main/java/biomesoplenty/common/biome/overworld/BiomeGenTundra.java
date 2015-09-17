package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenTundra extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.1F);
    
    public BiomeGenTundra(int id)
    {
        super(id);
        
        this.setHeight(biomeHeight);
        this.setColor(11371606);
        this.setTemperatureRainfall(0.2F, 0.5F);

        this.spawnableCreatureList.clear();

        this.theBiomeDecorator.treesPerChunk = 3;
        this.theBiomeDecorator.grassPerChunk = 8;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = 8;

        this.theBiomeDecorator.bopFeatures.gravelPerChunk = 9;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.rockpilesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 8;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 8), 4);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenShrub(0, 0);
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

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 11371606;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 12543566;
    }
}
