package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenRainforestTree1;

public class BiomeGenTropicalRainforest extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);

    public BiomeGenTropicalRainforest(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(8970560);
        this.setTemperatureRainfall(1.2F, 0.9F);

        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        
        this.waterColorMultiplier = 6160128;

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.grassPerChunk = 9;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.waterlilyPerChunk = 2;
        
        this.bopWorldFeatures.bopFlowersPerChunk = 10;
        this.bopWorldFeatures.doubleTallGrassPerChunk = 8;
        this.bopWorldFeatures.generatePumpkins = false;
        this.bopWorldFeatures.generateMelons = true;
        this.bopWorldFeatures.sproutsPerChunk = 2;
        this.bopWorldFeatures.generateQuicksand = true;
        this.bopWorldFeatures.poisonIvyPerChunk = 4;
        this.bopWorldFeatures.shrubsPerChunk = 15;
    }
    
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, true) : 
        new WorldGenRainforestTree1(Blocks.log, Blocks.leaves, 3, 3, false, 8, 8);
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenerator, Double> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 5), 12D);
        
        return flowerMap;
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.75D);
        grassMap.put(new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 3, 9, 64), 1D);
        
        return grassMap;
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
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 6, 2);
            }
        }
    }

    @Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 11002176;
    }


    @Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
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
