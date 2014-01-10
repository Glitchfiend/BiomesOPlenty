package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenOutback extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.3F, 0.4F);

	public BiomeGenOutback(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(10843716);
        this.setTemperatureRainfall(0.8F, 0.05F);

		this.spawnableCreatureList.clear();
		
		this.topBlock = BOPBlockHelper.get("hardSand");
		this.fillerBlock = BOPBlockHelper.get("hardSand");
		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = -999;
	    this.theBiomeDecorator.deadBushPerChunk = 7;
	    this.theBiomeDecorator.cactiPerChunk = 4;
		
		this.bopWorldFeatures.grassSplatterPerChunk = 10;
		this.bopWorldFeatures.tinyCactiPerChunk = 2;
		this.bopWorldFeatures.bushesPerChunk = 5;
		this.bopWorldFeatures.generatePumpkins = false;
	}
	
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenBOPShrub(Blocks.log2, Blocks.leaves2, 0, 0, BOPBlockHelper.get("hardSand")) : 
        new WorldGenMiniShrub(Blocks.log2, Blocks.leaves2, 0, 0, BOPBlockHelper.get("hardSand"));
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
            
            //TODO:             getBlock()
            Block block = world.func_147439_a(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 2, 2);
            }
        }
    }
}
