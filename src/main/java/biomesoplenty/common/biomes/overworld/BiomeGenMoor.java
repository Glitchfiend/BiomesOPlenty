package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenMoor extends BOPBiome
{
    private static final Height biomeHeight = new Height(1.5F, 0.025F);
    
	public BiomeGenMoor(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(6394725);
        this.setTemperatureRainfall(0.5F, 1.0F);

		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
	    this.waterColorMultiplier = 5800566;

		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.mushroomsPerChunk = 2;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.setFeature("mudPerChunk", 1);
        this.bopWorldFeatures.setFeature("waterLakesPerChunk", 10);
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 6);
        this.bopWorldFeatures.setFeature("koruPerChunk", 6);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 5);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 15);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 14);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
            }
        }
	}

	@Override
    //TODO:     getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6394725;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
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
