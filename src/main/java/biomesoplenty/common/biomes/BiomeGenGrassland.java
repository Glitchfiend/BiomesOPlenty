package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;

public class BiomeGenGrassland extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenGrassland(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
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
		
		this.bopWorldFeatures.waterLakesPerChunk = 15;
		this.bopWorldFeatures.portobellosPerChunk = 3;
		this.bopWorldFeatures.riverCanePerChunk = 5;
		this.bopWorldFeatures.waterReedsPerChunk = 2;
		this.bopWorldFeatures.generatePumpkins = false;
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
			
			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
			}
		}
	}
	
    @Override
	public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
    	HashMap<WorldGenerator, Double> grassMap = new HashMap();

    	grassMap.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 1), 0.25D);
    	grassMap.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 2), 0.25D);
    	grassMap.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
    	grassMap.put(new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
    	grassMap.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
    	
    	return grassMap;
    }

	@Override
	//TODO:		getBiomeGrassColor()
	public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
	{
		return 8379261;
	}

	 @Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		 return 8379261;
	 }
}
