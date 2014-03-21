package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenRainforestTree1;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class BiomeGenTropicalRainforest extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);

    public BiomeGenTropicalRainforest(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(8970560);
        this.setTemperatureRainfall(1.2F, 0.9F);

        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        
        this.waterColorMultiplier = 6160128;

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.grassPerChunk = 9;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.waterlilyPerChunk = 2;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 10);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);
        this.bopWorldFeatures.setFeature("generateMelons", true);
        this.bopWorldFeatures.setFeature("sproutsPerChunk", 2);
        this.bopWorldFeatures.setFeature("generateQuicksand", true);
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 4);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 15);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 15);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 9);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 5), 12);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.75D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3), 1D);
    }
    
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, true) : 
        new WorldGenRainforestTree1(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("colorizedLeaves2"), 3, 2, false, 8, 8);
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

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 11002176;
    }


    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 8970560;
    }
    
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 11128415;
        else return super.getSkyColorByTemp(par1);
    }

    /**
     * Fog Color
     */
    /*
    @Override
    public int getFogColour()
    {
        return 16228194;
    }
    */

    /*@Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
    */
}
