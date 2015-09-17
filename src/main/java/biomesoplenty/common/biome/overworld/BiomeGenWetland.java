package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenWetland extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(-0.1F, 0.2F);

    public BiomeGenWetland(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(5215831);
        this.setTemperatureRainfall(0.6F, 0.9F);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

        this.waterColorMultiplier = 6512772;

        this.theBiomeDecorator.treesPerChunk = 6;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.reedsPerChunk = 15;
        this.theBiomeDecorator.clayPerChunk = 2;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 15;
        this.theBiomeDecorator.bopFeatures.mudPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.cattailsPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.highCattailsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 5;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 1), 10);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3), 0.75D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 9, 9, 6, -1) : 
        new WorldGenBOPSwampTree(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves2, 1, 0, 6, 9, BOPCBlocks.colorizedLeaves2, 0);
    }


    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);

        for (int i = 0; i < var5; ++i)
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

        for (int i = 0; i < 20; i++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
            int z = chunkZ + random.nextInt(16) + 8;

            new WorldGenMoss().generate(world, random, x, y, z);
        }
    }
    
    @Override
    public void genTerrainBlocks(World world, Random random, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.topBlock = BOPCBlocks.newBopGrass;
        this.field_150604_aj = 0;
        this.fillerBlock = BOPCBlocks.newBopDirt;

        this.genBiomeTerrain(world, random, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return 5935967;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 5215831;
    }

    /*@Override
	public int getFogColour()
	{
		return 6189472;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
     */
}
