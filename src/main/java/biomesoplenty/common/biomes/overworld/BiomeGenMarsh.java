package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenMarsh extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenMarsh(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(6725742);
        this.setTemperatureRainfall(0.5F, 0.9F);

		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 50;
		
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.setFeature("koruPerChunk", 1);
        this.bopWorldFeatures.setFeature("mudPerChunk", 1);
        this.bopWorldFeatures.setFeature("waterLakesPerChunk", 100);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 10);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 15);
        this.bopWorldFeatures.setFeature("algaePerChunk", 1);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 50);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.25D);
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
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 12, 2);
			}
		}
	}
	
	/*@Override
	public int getFogColour()
	{
		return 12638463;
	}
	
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
    */
}
