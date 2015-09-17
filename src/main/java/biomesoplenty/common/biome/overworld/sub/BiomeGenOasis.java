package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenPalmTree1;

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
        this.setTemperatureRainfall(2.0F, 0.3F);
        
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        
        this.theBiomeDecorator.treesPerChunk = 3;
        this.theBiomeDecorator.cactiPerChunk = 7;
        this.theBiomeDecorator.reedsPerChunk = 100;
        
        this.theBiomeDecorator.bopFeatures.tinyCactiPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.desertSproutsPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.bromeliadsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.generateQuicksand = true;
        this.theBiomeDecorator.bopFeatures.generateMelons = true;
		this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
		
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 11;
		
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
	}
	
    @Override
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(2))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 2, 2);
			}
		}
	}
}
