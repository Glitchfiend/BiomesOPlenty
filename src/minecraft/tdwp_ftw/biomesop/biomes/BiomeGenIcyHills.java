package tdwp_ftw.biomesop.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.declarations.BOPConfiguration;
import tdwp_ftw.biomesop.worldgen.WorldGenIceTree;

public class BiomeGenIcyHills extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenIcyHills(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.blockSnow.blockID;
        this.fillerBlock = (byte)Block.blockSnow.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 2;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = -999;
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySnowman.class, 30, 2, 4));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenIceTree(false);
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 16777215;
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
