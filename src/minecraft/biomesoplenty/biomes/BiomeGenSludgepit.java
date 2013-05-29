package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenBog1;
import biomesoplenty.worldgen.WorldGenBog2;

public class BiomeGenSludgepit extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    @SuppressWarnings("unchecked")
	public BiomeGenSludgepit(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 30;
        this.customBiomeDecorator.grassPerChunk = 30;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
        this.customBiomeDecorator.mudPerChunk = 5;
        this.customBiomeDecorator.mudPerChunk2 = 5;
        this.customBiomeDecorator.deadBushPerChunk = 5;
		this.customBiomeDecorator.algaePerChunk = 2;
		this.customBiomeDecorator.poisonWaterPerChunk = 5;
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
        this.waterColorMultiplier = 11506176;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenBog2() : new WorldGenBog1());
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(9) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 7627817;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 9539892;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 7039816;
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
