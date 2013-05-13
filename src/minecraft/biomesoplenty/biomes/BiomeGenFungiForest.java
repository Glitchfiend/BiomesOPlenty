package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.WorldGenSwampTall;
import biomesoplenty.worldgen.WorldGenPrairie;
import biomesoplenty.worldgen.WorldGenThickTree;
import biomesoplenty.worldgen.WorldGenWillow;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenFungiForest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenFungiForest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 8;
        this.customBiomeDecorator.grassPerChunk = 6;
		this.customBiomeDecorator.sproutsPerChunk = 2;
		this.customBiomeDecorator.bushesPerChunk = 2;
		this.customBiomeDecorator.highGrassPerChunk = 1;
        this.customBiomeDecorator.mushroomsPerChunk = 8;
        this.customBiomeDecorator.bigMushroomsPerChunk = 8;
		this.customBiomeDecorator.toadstoolsPerChunk = 5;
		this.customBiomeDecorator.portobellosPerChunk = 7;
		this.customBiomeDecorator.blueMilksPerChunk = 4;
		this.customBiomeDecorator.glowshroomsPerChunk = 2;
        this.customBiomeDecorator.blueFlowersPerChunk = 3;
		this.customBiomeDecorator.cattailsPerChunk = 1;
		this.customBiomeDecorator.highCattailsPerChunk = 2;
		this.customBiomeDecorator.reedsBOPPerChunk = 1;
        this.customBiomeDecorator.generateMycelium = true;
		this.customBiomeDecorator.generatePumpkins = true;
        this.waterColorMultiplier = 65326;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 3, 4, 8));
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
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenThickTree(false) : (par1Random.nextInt(5) == 0 ? new WorldGenWillow() : (par1Random.nextInt(3) == 0 ? new WorldGenPrairie(false) : new WorldGenSwampTall())));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		return (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : (par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1))));
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 5359235;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 5359235;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 5888980;
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
