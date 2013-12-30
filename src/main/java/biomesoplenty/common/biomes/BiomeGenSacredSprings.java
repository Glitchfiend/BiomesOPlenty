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
import biomesoplenty.common.world.generators.trees.WorldGenSacredOak;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenSacredSprings extends BiomeGenBase
{
    //private BiomeDecoratorBOP customBiomeDecorator;

    @SuppressWarnings("unchecked")
    public BiomeGenSacredSprings(int par1)
    {
        super(par1);
        theBiomeDecorator.treesPerChunk = 30;
        theBiomeDecorator.grassPerChunk = 4;
        theBiomeDecorator.waterlilyPerChunk = 5;
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
    }

    //Trees
    public WorldGenAbstractTree func_150567_a(Random rand)
    {
    	return (WorldGenAbstractTree)(rand.nextInt(150) == 0 ? new WorldGenSacredOak(false) : new WorldGenShrub(0, 0));
    }
    
    //Grasses
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10) : (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11) : new WorldGenTallGrass(Blocks.tallgrass, 1));
    }
    
    //Grass Color
    @SideOnly(Side.CLIENT)
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
    	return 39259;
    }
    
    //Foliage Color
    @SideOnly(Side.CLIENT)
    public int func_150571_c(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
        return 39259;
    }
}
