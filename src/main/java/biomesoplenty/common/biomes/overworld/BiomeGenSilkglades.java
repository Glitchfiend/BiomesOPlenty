package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenSilkglades extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

    public BiomeGenSilkglades(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(13420973);
        this.setTemperatureRainfall(0.5F, 0.9F);

        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntitySpider.class, 7, 1, 2));

        this.waterColorMultiplier = 16777079;

        this.theBiomeDecorator.treesPerChunk = 6;
        this.theBiomeDecorator.grassPerChunk = 2;
        this.theBiomeDecorator.mushroomsPerChunk = 4;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.setFeature("sproutsPerChunk", 2);
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 2);
        this.bopWorldFeatures.setFeature("cobwebsPerChunk", 9);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
        this.bopWorldFeatures.setFeature("koruPerChunk", 1);
        this.bopWorldFeatures.setFeature("cobwebNestsPerChunk", 2);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 15);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 2);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 1D);
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenBOPSwampTree(Blocks.log, BOPBlockHelper.get("leaves2"), 0, 0, 6, 9, BOPBlockHelper.get("leaves2"), 0) : 
            (random.nextInt(7) == 0 ? new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("mud")) : 
                new WorldGenBOPSwampTree(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves2"), 1, 0, 6, 9, BOPBlockHelper.get("colorizedLeaves2"), 0));
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
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
            }
        }
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return 13420973;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 14146486;
    }


    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 13553096;
        else return super.getSkyColorByTemp(par1);
    }

    /*
	@Override
	public int getFogColour()
	{
		return 10062450;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
     */
}
