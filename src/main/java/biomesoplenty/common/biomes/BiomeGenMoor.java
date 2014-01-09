package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenMoor extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.7F, 0.8F);
    
	public BiomeGenMoor(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(6394725);
        this.setTemperatureRainfall(0.5F, 1.0F);

		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
	    this.waterColorMultiplier = 5800566;

		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.mushroomsPerChunk = 2;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
		this.bopWorldFeatures.mudPerChunk = 1;
		this.bopWorldFeatures.waterLakesPerChunk = 10;
		this.bopWorldFeatures.bopFlowersPerChunk = 6;
		this.bopWorldFeatures.koruPerChunk = 1;
		this.bopWorldFeatures.generatePumpkins = false;
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenerator, Double> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 1D);
        
        return flowerMap;
    }
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        
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

	@Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6394725;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
		return 6394725;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 10536403;
		else return super.getSkyColorByTemp(par1);
	}
}
