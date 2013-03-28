package tdwp_ftw.biomesop.biomes;

import java.util.Random;
import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.worldgen.WorldGenPromisedTree;
import tdwp_ftw.biomesop.worldgen.WorldGenPromisedTree2;
import tdwp_ftw.biomesop.worldgen.WorldGenPromisedTree3;
import tdwp_ftw.biomesop.worldgen.WorldGenPromisedShrub;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeGenPromisedLand extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenPromisedLand(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)mod_BiomesOPlenty.holyGrass.blockID;
        this.fillerBlock = (byte)mod_BiomesOPlenty.holyStone.blockID;
        this.customBiomeDecorator.treesPerChunk = 8;
        this.customBiomeDecorator.grassPerChunk = -999;
		this.customBiomeDecorator.holyTallGrassPerChunk = 50;
		this.customBiomeDecorator.promisedWillowPerChunk = 80;
		this.customBiomeDecorator.pinkFlowersPerChunk = 6;
        this.customBiomeDecorator.glowFlowersPerChunk = 3;
		this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.customBiomeDecorator.generatePumpkins = false;
		this.theWorldGenerator = new WorldGenMinable(Block.waterMoving.blockID, 8);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenPromisedShrub(0, 0) : (par1Random.nextInt(4) == 0 ? new WorldGenPromisedTree3(false) : (par1Random.nextInt(8) == 0 ? new WorldGenPromisedTree2(false) : new WorldGenPromisedTree(false))));
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 4583331;
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 100;
        int var6;
        int var7;
        int var8;

        for (var6 = 0; var6 < var5; ++var6)
        {
            var7 = par3 + par2Random.nextInt(16);
            var8 = par2Random.nextInt(30) + 30;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID)
            {
                par1World.setBlockAndMetadataWithNotify(var7, var8, var9, mod_BiomesOPlenty.amethystOre.blockID, 0, 2);
            }
        }

        for (var5 = 0; var5 < 12; ++var5)
        {
            var6 = par3 + par2Random.nextInt(16);
            var7 = par2Random.nextInt(60);
            var8 = par4 + par2Random.nextInt(16);
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (mod_BiomesOPlenty.skyColors = true)
		{
        return 50175;
		}	
		else
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
    }
}
