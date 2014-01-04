package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenSacredOak;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenSacredSprings extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.4F, 1.2F);
	
    public BiomeGenSacredSprings(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        
        this.theBiomeDecorator.treesPerChunk = 30;
        this.theBiomeDecorator.grassPerChunk = 4;
        this.theBiomeDecorator.waterlilyPerChunk = 5;
        
        this.bopWorldFeatures.perChunk.bopFlowersPerChunk = 2;
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
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
    	return random.nextInt(3) == 0 ? new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 6) : new WorldGenBOPFlora(Blocks.red_flower, 1);
    }
    
    @Override
    //TODO:		getBiomeGrassColor()
	public int func_150558_b(int x, int y, int z)
    {
    	return 39259;
    }
    
    @SideOnly(Side.CLIENT)
    //TODO:		getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
        return 39259;
    }
}
