package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenLushSwamp extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.1F);

    public BiomeGenLushSwamp(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(5746228);
        this.setTemperatureRainfall(0.7F, 1.0F);
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
        
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 16;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 1);
        this.bopWorldFeatures.setFeature("cattailsPerChunk", 10);
        this.bopWorldFeatures.setFeature("highCattailsPerChunk", 5);
        this.bopWorldFeatures.setFeature("riverCanePerChunk", 5);
        this.bopWorldFeatures.setFeature("algaePerChunk", 3);
        //TODO: FEATURE this.bopWorldFeatures.poisonWaterPerChunk = 2;
        this.bopWorldFeatures.setFeature("wildCarrotsPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 5);
        this.bopWorldFeatures.setFeature("koruPerChunk", 1);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 6);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 10);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 10);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 4);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 8);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 4);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPSwampTree(Blocks.log, Blocks.leaves, 0, 0, 8, 6, BOPBlockHelper.get("ivy"), -1);
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
                //TODO: setBlock()
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
            }
        }
    }
}
