package biomesoplenty.common.biomes.overworld.subbiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;
import biomesoplenty.common.world.features.trees.WorldGenPalmTree1;
import biomesoplenty.common.world.features.trees.WorldGenTropicsShrub;

public class BiomeGenOasis extends BOPSubBiome
{
	private static final Height biomeHeight = new Height(-0.2F, 0.0F);
	
	public BiomeGenOasis(int biomeID)
	{
		super(biomeID);

		this.zoom = 0.5D;
		this.threshold = 0.5D;
		
        this.setHeight(biomeHeight);
		this.setColor(7712283);
        this.setTemperatureRainfall(0.9F, 0.7F);
        
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        
        this.theBiomeDecorator.treesPerChunk = 3;
        this.theBiomeDecorator.cactiPerChunk = 7;
        this.theBiomeDecorator.reedsPerChunk = 100;
        
        this.bopWorldFeatures.setFeature("tinyCactiPerChunk", 2);
        this.bopWorldFeatures.setFeature("desertSproutsPerChunk", 3);
        this.bopWorldFeatures.setFeature("bromeliadsPerChunk", 4);
        this.bopWorldFeatures.setFeature("waterLakesPerChunk", 4);
        this.bopWorldFeatures.setFeature("generateQuicksand", true);
        this.bopWorldFeatures.setFeature("generateMelons", true);
		this.bopWorldFeatures.setFeature("generatePumpkins", false);
		
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 11);
		
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
	}
	
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenPalmTree1();
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
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 6, 2);
			}
		}
	}
}
