package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenDunes extends BOPBiome
{

	public BiomeGenDunes(int id)
	{
		super(id);

		spawnableCreatureList.clear();
		
		topBlock = Blocks.sand;
		fillerBlock = Blocks.sand;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 5;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 75;
		this.theBiomeDecorator.generateLakes = false;
		
		this.bopWorldFeatures.perChunk.desertSproutsPerChunk = 25;
		this.bopWorldFeatures.perChunk.bromeliadsPerChunk = 5;
		this.bopWorldFeatures.perChunk.waterReedsPerChunk = 4;
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 1);
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
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 2, 2);
			}
		}
	}
}
