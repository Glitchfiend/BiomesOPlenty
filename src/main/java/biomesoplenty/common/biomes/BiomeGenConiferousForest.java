package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga3;

public class BiomeGenConiferousForest extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.4F);

	public BiomeGenConiferousForest(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(5410656);
        this.setTemperatureRainfall(0.5F, 0.5F);

		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));

		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		//                     gravelPerChunk
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

		this.bopWorldFeatures.toadstoolsPerChunk = 3;
		this.bopWorldFeatures.blueMilksPerChunk = 1;
		this.bopWorldFeatures.poisonIvyPerChunk = 1;
		this.bopWorldFeatures.berryBushesPerChunk = 1;
		this.bopWorldFeatures.shrubsPerChunk = 8;
		this.bopWorldFeatures.waterReedsPerChunk = 2;
		this.bopWorldFeatures.cloverPatchesPerChunk = 10;
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(3) == 0 ? new WorldGenBOPTaiga3(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 35, 10, 0): 
		(random.nextInt(5) == 0 ? new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 20, 15, 4) : 
		new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 10, 10, 5));
	}

    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        grassMap.put(new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 3, 9, 64), 0.5D);

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
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 0, 2);
            }
        }
    }
}
