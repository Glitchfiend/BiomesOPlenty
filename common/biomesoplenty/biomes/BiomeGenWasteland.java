package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.interfaces.IBOPFog;
import biomesoplenty.worldgen.WorldGenWasteland;
import biomesoplenty.worldgen.WorldGenWasteland2;
import biomesoplenty.worldgen.tree.WorldGenDeadTree3;

public class BiomeGenWasteland extends BiomeGenBase implements IBOPFog
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenWasteland(int par1)
    {
        super(par1);
        topBlock = (byte) Blocks.driedDirt.get().blockID;
        fillerBlock = (byte) Blocks.driedDirt.get().blockID;
        theBiomeDecorator = new BiomeDecoratorBOP(this);
        customBiomeDecorator = (BiomeDecoratorBOP) theBiomeDecorator;
        customBiomeDecorator.treesPerChunk = 1;
        customBiomeDecorator.deadGrassPerChunk = 14;
        customBiomeDecorator.poisonWaterPerChunk = 10;
        waterColorMultiplier = 15073024;
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new WorldGenWasteland()
                : (par1Random.nextInt(4) == 0 ? new WorldGenWasteland2()
                        : new WorldGenDeadTree3(false));
    }

    /**
     * Provides the basic grass color based on the biome temperature and
     * rainfall
     */
    @Override
    public int getBiomeGrassColor()
    {
        return 10330232;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and
     * rainfall
     */
    @Override
    public int getBiomeFoliageColor()
    {
        return 10067541;
    }

    /**
     * Fog Color
     */
    @Override
    public int getFogColour()
    {
        return 5662280;
    }

    /**
     * takes temperature, returns color
     */
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors)
        {
            return 10465942;
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

            return Color.getHSBColor(0.62222224F - par1 * 0.05F,
                    0.5F + par1 * 0.1F, 1.0F).getRGB();
        }
    }

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
}
