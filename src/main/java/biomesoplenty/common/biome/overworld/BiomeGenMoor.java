package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenMoor extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(1.5F, 0.025F);
    
	public BiomeGenMoor(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(6394725);
        this.setTemperatureRainfall(0.5F, 1.0F);

		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
	    this.waterColorMultiplier = 5800566;

		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.mushroomsPerChunk = 2;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.theBiomeDecorator.bopFeatures.mudPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 1), 14);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
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
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6394725;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 6394725;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 10536403;
		else return super.getSkyColorByTemp(par1);
	}
}
