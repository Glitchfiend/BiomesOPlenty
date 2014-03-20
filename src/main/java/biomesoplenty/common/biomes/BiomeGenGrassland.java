package biomesoplenty.common.biomes;

import biomesoplenty.api.BOPBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;

import java.util.Random;

public class BiomeGenGrassland extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenGrassland(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO:	setColor()
        this.setColor(8379261);
        this.setTemperatureRainfall(0.7F, 0.7F);

		this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 14, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 12, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 12, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 2;
		this.theBiomeDecorator.reedsPerChunk = 35;
		this.theBiomeDecorator.mushroomsPerChunk = 20;

        this.bopWorldFeatures.setFeature("waterLakesPerChunk", 15);
        this.bopWorldFeatures.setFeature("portobellosPerChunk", 3);
        this.bopWorldFeatures.setFeature("riverCanePerChunk", 5);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 2);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 1), 0.25D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 2), 0.25D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
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
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
			}
		}
	}

	@Override
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
	{
		return 8379261;
	}

	 @Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		 return 8379261;
	 }
}
