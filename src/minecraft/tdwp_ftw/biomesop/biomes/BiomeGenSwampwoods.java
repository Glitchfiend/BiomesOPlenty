package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.worldgen.WorldGenMarsh;
import tdwp_ftw.biomesop.worldgen.WorldGenCypress;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class BiomeGenSwampwoods extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenSwampwoods(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 12;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = 10;
		this.customBiomeDecorator.highGrassPerChunk = 10;
		this.customBiomeDecorator.mudPerChunk = 2;
        this.customBiomeDecorator.mudPerChunk2 = 2;
		this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.customBiomeDecorator.algaePerChunk = 2;
		this.customBiomeDecorator.waterlilyPerChunk = 4;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenMarsh var5 = new WorldGenMarsh();

        for (int var6 = 0; var6 < 5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 62;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenShrub(0,0) : new WorldGenCypress(false));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1);
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 1660473;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 2324303;
    }
}
