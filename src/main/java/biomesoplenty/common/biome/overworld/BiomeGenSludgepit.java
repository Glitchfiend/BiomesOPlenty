package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBogTree1;
import biomesoplenty.common.world.features.trees.WorldGenBogTree2;

public class BiomeGenSludgepit extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.0F, 0.1F);

    public BiomeGenSludgepit(int id)
    {
        super(id);
        
        this.setHeight(biomeHeight);
        this.setColor(7627817);
        this.setTemperatureRainfall(0.8F, 0.9F);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.waterColorMultiplier = 11506176;

        this.theBiomeDecorator.treesPerChunk = 30;
        this.theBiomeDecorator.grassPerChunk = 15;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        this.theBiomeDecorator.deadBushPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.mudPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;
        this.theBiomeDecorator.bopFeatures.poisonLakesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 30;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenBogTree2(Blocks.log2, Blocks.leaves2, 1, 1, false, 7, 4) : 
        new WorldGenBogTree1(Blocks.log2, Blocks.leaves2, 1, 1, false, 7, 5);
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
		return 7627817;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 9539892;
	}
	
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 7039816;
        else return super.getSkyColorByTemp(par1);

    }

	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 10463856;
	}

	@Override
	public float getFogDensity(int x, int y, int z)
	{
	    return 0.6F;
	}
}
