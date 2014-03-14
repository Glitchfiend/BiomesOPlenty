package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMarsh;
import biomesoplenty.common.world.features.trees.WorldGenBogBush;
import biomesoplenty.common.world.features.trees.WorldGenCypress;

public class BiomeGenBog extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);
    
    public BiomeGenBog(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(14193503);
        this.setTemperatureRainfall(0.8F, 0.9F);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.bushesPerChunk = 6;
        this.bopWorldFeatures.mudPerChunk = 2;
        this.bopWorldFeatures.algaePerChunk = 2;
        this.bopWorldFeatures.riverCanePerChunk = 8;
        this.bopWorldFeatures.blueMilksPerChunk = 1;
        this.bopWorldFeatures.waterLakesPerChunk = 6;
        //TODO: FEATURE this.bopWorldFeatures.poisonWaterPerChunk = 2;
        this.bopWorldFeatures.waterReedsPerChunk = 8;
        this.bopWorldFeatures.koruPerChunk = 1;
        this.bopWorldFeatures.shrubsPerChunk = 10;
        this.bopWorldFeatures.leafPilesPerChunk = 15;
        this.bopWorldFeatures.deadLeafPilesPerChunk = 8;
        this.bopWorldFeatures.generatePumpkins = false;
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
            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 6, 2);
            }
        }

		for (int var6 = 0; var6 < 10; ++var6)
		{
			int var7 = chunkX + random.nextInt(16) + 8;
			short var8 = 62;
			int var9 = chunkZ + random.nextInt(16) + 8;
			
			new WorldGenMarsh().generate(world, random, var7, var8, var9);
		}
	}

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 1, 1, false, 8, 5, 0) : (random.nextInt(6) == 0 ? new WorldGenCypress(Blocks.log, Blocks.leaves, 2, 2, false, 4, 4, 1) : new WorldGenBogBush());
    }

    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        
        return grassMap;
    }

    @Override
    //TODO:     getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 14193503;
    }

    @Override
    //TODO:     getBiomeFoliageColor()
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 14345593;
    }
}
