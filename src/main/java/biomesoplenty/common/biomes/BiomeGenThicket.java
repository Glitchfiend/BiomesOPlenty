package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;

public class BiomeGenThicket extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.2F, 0.2F);
	
	public BiomeGenThicket(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO:	setColor()
        this.setColor(7248193);
        this.setTemperatureRainfall(0.6F, 0.2F);

		this.theBiomeDecorator.treesPerChunk = 17;
		this.theBiomeDecorator.grassPerChunk = 1;

		this.bopWorldFeatures.thornsPerChunk = 25;
		this.bopWorldFeatures.shrubsPerChunk = 15;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(5) == 0 ? worldGeneratorTrees : new WorldGenShrub(0, 0);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10);
	}
}
