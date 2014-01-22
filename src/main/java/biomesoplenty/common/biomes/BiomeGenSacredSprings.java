package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenSacredOak;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenSacredSprings extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.0F, 0.6F);
	
    public BiomeGenSacredSprings(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        
        this.theBiomeDecorator.treesPerChunk = 30;
        this.theBiomeDecorator.grassPerChunk = 4;
        this.theBiomeDecorator.waterlilyPerChunk = 5;
        
        this.bopWorldFeatures.bopFlowersPerChunk = 2;
    }

    @Override
    //TODO:						getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random rand)
    {
    	return (WorldGenAbstractTree)(rand.nextInt(200) == 0 ? new WorldGenSacredOak(false) : new WorldGenShrub(0, 0));
    }
    
    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10) : (random.nextInt(4) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11) : new WorldGenTallGrass(Blocks.tallgrass, 1));
    }
    
    @Override
	public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
    	HashMap<WorldGenerator, Double> flowerMap = new HashMap();
    	
    	flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 6), 10D);
    	flowerMap.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6D);
    	flowerMap.put(new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 5, 11, 5), 5D);
    	
    	return flowerMap;
    }
    
	@Override
	//TODO:		getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		double d0 = field_150606_ad.func_151601_a((double)p_150558_1_ * 0.0225D, (double)p_150558_3_ * 0.0225D);
        return d0 < -0.1D ? 39285 : 39259;
    }
    
    @SideOnly(Side.CLIENT)
    //TODO:		getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
    	double d0 = field_150606_ad.func_151601_a((double)x * 0.0225D, (double)z* 0.0225D);
        return d0 < -0.1D ? 39285 : 39259;
    }
}
