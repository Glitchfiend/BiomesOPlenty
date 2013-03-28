package tdwp_ftw.biomesop.biomes;

import java.util.Random;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenShrub;
import tdwp_ftw.biomesop.worldgen.WorldGenMoss;
import tdwp_ftw.biomesop.worldgen.WorldGenTemperate;
import tdwp_ftw.biomesop.worldgen.WorldGenThickTree;
import tdwp_ftw.biomesop.worldgen.WorldGenWillow;
import tdwp_ftw.biomesop.worldgen.WorldGenGrandFir1;
import tdwp_ftw.biomesop.worldgen.WorldGenGrandFir2;
import tdwp_ftw.biomesop.worldgen.WorldGenAlaskanCedar1;
import tdwp_ftw.biomesop.worldgen.WorldGenAlaskanCedar2;

public class BiomeGenTemperateRainforest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenTemperateRainforest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 22;
        this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.generatePumpkins = false;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        //return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenGrandFir1() : (par1Random.nextInt(4) == 0 ? new WorldGenAlaskanCedar2() : (par1Random.nextInt(8) == 0 ? new WorldGenAlaskanCedar1() : (par1Random.nextInt(2) == 0 ? new WorldGenShrub(0,0) : new WorldGenGrandFir2()))));
		return (WorldGenerator)(par1Random.nextInt(10) == 0 ? new WorldGenWillow() : (par1Random.nextInt(6) == 0 ? new WorldGenThickTree(false) : (par1Random.nextInt(2) == 0 ? new WorldGenTemperate(false) : new WorldGenShrub(0, 0))));
	}
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		return (par1Random.nextInt(6) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(mod_BiomesOPlenty.mediumGrass.blockID, 1) : new WorldGenTallGrass(mod_BiomesOPlenty.shortGrass.blockID, 1))));
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
        return 11981671;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 12311907;
    }
}
