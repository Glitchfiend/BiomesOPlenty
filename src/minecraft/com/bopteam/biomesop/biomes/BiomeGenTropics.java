package com.bopteam.biomesop.biomes;

import java.awt.Color;
import java.util.Random;

import com.bopteam.biomesop.configuration.BOPConfiguration;
import com.bopteam.biomesop.mobs.EntityJungleSpider;
import com.bopteam.biomesop.worldgen.WorldGenPalmTree1;
import com.bopteam.biomesop.worldgen.WorldGenPalmTree3;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTropics extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    @SuppressWarnings("unchecked")
	public BiomeGenTropics(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 8;
        this.customBiomeDecorator.grassPerChunk = 7;
        this.customBiomeDecorator.flowersPerChunk = 10;
        this.customBiomeDecorator.sandPerChunk = 50;
        this.customBiomeDecorator.sandPerChunk2 = 50;
        this.customBiomeDecorator.orangeFlowersPerChunk = 10;
		this.customBiomeDecorator.whiteFlowersPerChunk = 4;
		this.customBiomeDecorator.generatePumpkins = false;
		this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        this.spawnableCreatureList.clear();
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenPalmTree1() : new WorldGenPalmTree3());
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 3333631;
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
