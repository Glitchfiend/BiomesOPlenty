package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenDunes extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.5F, 1.3F);
	
	public BiomeGenDunes(int id)
	{
		super(id);

        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO:	setColor()
        this.setColor(15064744);
        this.setDisableRain();
        this.setTemperatureRainfall(2.0F, 0.05F);
		
		spawnableCreatureList.clear();
		
		topBlock = Blocks.sand;
		fillerBlock = Blocks.sand;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 5;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 75;
		this.theBiomeDecorator.generateLakes = false;

        this.bopWorldFeatures.setFeature("desertSproutsPerChunk", 25);
        this.bopWorldFeatures.setFeature("bromeliadsPerChunk", 5);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
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

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 2, 2);
			}
		}
	}
}
