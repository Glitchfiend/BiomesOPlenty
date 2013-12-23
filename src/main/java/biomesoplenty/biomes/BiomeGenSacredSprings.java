package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldcore.interfaces.IWCFog;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.configuration.BOPConfigurationMisc;
import biomesoplenty.entities.EntityJungleSpider;
import biomesoplenty.worldgen.tree.WorldGenMassiveTree;

public class BiomeGenSacredSprings extends BiomeGenBase implements IWCFog
{
    private BiomeDecoratorBOP customBiomeDecorator;

    @SuppressWarnings("unchecked")
    public BiomeGenSacredSprings(int par1)
    {
        super(par1);
        theBiomeDecorator = new BiomeDecoratorBOP(this);
        customBiomeDecorator = (BiomeDecoratorBOP) theBiomeDecorator;
        customBiomeDecorator.treesPerChunk = 30;
        customBiomeDecorator.grassPerChunk = 4;
        customBiomeDecorator.wheatGrassPerChunk = 1;
        customBiomeDecorator.waterlilyPerChunk = 5;
        customBiomeDecorator.generatePumpkins = false;
        spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class,
                12, 6, 6));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return par1Random.nextInt(150) == 0 ? new WorldGenMassiveTree(false)
                : new WorldGenShrub(0, 0);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = par2Random.nextInt(75);

        int var55 = 12 + par2Random.nextInt(6);

        for (int var66 = 0; var66 < var55; ++var66)
        {
            int var77 = par3 + par2Random.nextInt(16);
            int var88 = par2Random.nextInt(28) + 4;
            int var99 = par4 + par2Random.nextInt(16);
            int var100 = par1World.getBlockId(var77, var88, var99);

            if (var100 == Block.stone.blockID)
            {
                par1World.setBlock(var77, var88, var99,
                        BOPBlocks.amethystOre.get().blockID, 12, 2);
            }
        }

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(53) + 75;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID || var10 == Block.dirt.blockID)
            {
                par1World.setBlock(var7, var8, var9, Block.waterMoving.blockID,
                        0, 2);
            }
        }
    }

    /**
     * Provides the basic grass color based on the biome temperature and
     * rainfall
     */
    @Override
    public int getBiomeGrassColor()
    {
        return 39259;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and
     * rainfall
     */
    @Override
    public int getBiomeFoliageColor()
    {
        return 39259;
    }

    /**
     * Fog Color
     */
    @Override
    public int getFogColour()
    {
        return 8707327;
    }

    /**
     * takes temperature, returns color
     */
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors)
        {
            return 1995007;
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
        return 1.0F;
    }
}
