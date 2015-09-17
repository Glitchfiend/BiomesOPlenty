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
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenMangrove;

public class BiomeGenMangrove extends BOPSubBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.2F);
	
	public BiomeGenMangrove(int biomeID) 
	{
		super(biomeID);
		
        this.zoom = 0.01D;
		this.threshold = 0.5D;
        this.setHeight(biomeHeight);
        this.setColor(7251289);
        this.setTemperatureRainfall(0.8F, 0.9F);
        
		this.spawnableCreatureList.clear();
		
		this.topBlock = Blocks.sand;
		this.fillerBlock = Blocks.sand;
		this.theBiomeDecorator.treesPerChunk = 6;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.cactiPerChunk = -999;
		
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.desertSproutsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 10;
        
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 9;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.plants, 0), 1D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenBOPShrub(BOPCBlocks.logs2, BOPCBlocks.colorizedLeaves1, 2, 1, Blocks.sand) : new WorldGenMangrove();
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(12))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 12, 2);
            }
        }
    }
}
