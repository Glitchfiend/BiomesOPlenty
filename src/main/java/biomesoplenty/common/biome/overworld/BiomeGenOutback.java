package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenOutback extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);

	public BiomeGenOutback(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(10843716);
        this.setTemperatureRainfall(1.3F, 0.05F);

		this.spawnableCreatureList.clear();
		
		this.topBlock = BOPCBlocks.hardSand;
		this.fillerBlock = BOPCBlocks.hardSand;
		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = -999;
	    this.theBiomeDecorator.deadBushPerChunk = 7;
	    this.theBiomeDecorator.cactiPerChunk = 4;

        this.theBiomeDecorator.bopFeatures.grassSplatterPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.tinyCactiPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.redSandSplatterPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
	}
	
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenBOPShrub(Blocks.log2, Blocks.leaves2, 0, 0, BOPCBlocks.hardSand) : 
        new WorldGenMiniShrub(Blocks.log2, Blocks.leaves2, 0, 0, BOPCBlocks.hardSand);
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
