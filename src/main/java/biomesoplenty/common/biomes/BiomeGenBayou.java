package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBayou1;
import biomesoplenty.common.world.features.trees.WorldGenBayou2;
import biomesoplenty.common.world.features.trees.WorldGenBayou3;

public class BiomeGenBayou extends BOPBiome
{
    private static final Height biomeHeight = new Height(-0.1F, 0.1F);

	public BiomeGenBayou(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(9154411);
        this.setTemperatureRainfall(0.5F, 0.9F);

		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		this.waterColorMultiplier = 16767282;

		this.theBiomeDecorator.treesPerChunk = 15;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = 25;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
		this.bopWorldFeatures.waterLakesPerChunk = 5;
		this.bopWorldFeatures.mudPerChunk = 1;
		this.bopWorldFeatures.toadstoolsPerChunk = 2;
		this.bopWorldFeatures.cattailsPerChunk = 1;
		this.bopWorldFeatures.highCattailsPerChunk = 1;
		this.bopWorldFeatures.algaePerChunk = 1;
		this.bopWorldFeatures.shrubsPerChunk = 2;
		this.bopWorldFeatures.waterReedsPerChunk = 4;
		this.bopWorldFeatures.koruPerChunk = 1;
		this.bopWorldFeatures.generatePumpkins = false;
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(8) == 0 ? new WorldGenBayou3(BOPBlockHelper.get("logs3"), 1) : 
		(random.nextInt(2) == 0 ? new WorldGenBayou1(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves2"), 1, 0) : 
		new WorldGenBayou2(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves2"), 1, 0));
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        
        return grassMap;
    }

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			//TODO:				getBlock()
			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
			}
		}

        for (int i = 0; i < 20; i++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
            int z = chunkZ + random.nextInt(16) + 8;

            new WorldGenMoss().generate(world, random, x, y, z);
        }
	}

	@Override
	//TODO:		getBiomeGrassColor()
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 9154411;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 11591816;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 11322556;
		else return super.getSkyColorByTemp(par1);
	}

	/*@Override
	public int getFogColour()
	{
		return 9482133;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
	 */
}
