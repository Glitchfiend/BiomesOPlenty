package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenLog;
import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.WorldGenWillow;

public class BiomeGenSwampNew extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    @SuppressWarnings("unchecked")
	public BiomeGenSwampNew(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 4;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 1;
        this.customBiomeDecorator.mushroomsPerChunk = 8;
        this.customBiomeDecorator.reedsPerChunk = 10;
        this.customBiomeDecorator.clayPerChunk = 1;
        this.customBiomeDecorator.waterlilyPerChunk = 4;
		this.customBiomeDecorator.mudPerChunk = 9;
        this.customBiomeDecorator.mudPerChunk2 = 9;
		this.customBiomeDecorator.portobellosPerChunk = 1;
        this.waterColorMultiplier = 14745456;
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenLog() : new WorldGenWillow());
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenMoss var5 = new WorldGenMoss();

        for (int var6 = 0; var6 < 20; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 58;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
    }
}
