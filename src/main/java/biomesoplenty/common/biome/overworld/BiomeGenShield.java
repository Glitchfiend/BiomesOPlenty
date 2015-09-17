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
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;

public class BiomeGenShield extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.2F);

	public BiomeGenShield(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(6586168);
        this.setTemperatureRainfall(0.5F, 0.8F);

		this.theBiomeDecorator.treesPerChunk = 7;
		this.theBiomeDecorator.grassPerChunk = 12;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 4;
        //this.theBiomeDecorator.bopFeatures.generateStoneInGrass2 = true;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 12;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(2) == 0 ? new WorldGenShrub(0, 0) : 
		(random.nextInt(4) == 0 ? new WorldGenPineTree() : 
		new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 9, 9, 6, 4));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(14))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 14, 2);
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
	
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {

        if (p_150573_7_ > 1.75D)
        {
            this.topBlock = Blocks.stone;
            this.fillerBlock = Blocks.stone;
            this.field_150604_aj = 0;
        }
        else if (p_150573_7_ > -0.95D)
        {
            this.topBlock = Blocks.grass;
            this.fillerBlock = Blocks.dirt;
            this.field_150604_aj = 0;
        }

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 6586168;
	}
	
	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 7902787;
	}
}
