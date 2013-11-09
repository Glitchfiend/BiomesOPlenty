package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldcore.interfaces.IWCFog;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMain;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.worldgen.realtree.WorldGenRealMagic;
import biomesoplenty.worldgen.tree.WorldGenJacaranda;
import biomesoplenty.worldgen.tree.WorldGenMystic2;
import biomesoplenty.worldgen.tree.WorldGenSwampTall;

public class BiomeGenMysticGroveThin extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenMysticGroveThin(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		if (BOPConfigurationMain.realisticTrees)
		{
			customBiomeDecorator.treesPerChunk = 1;
		}
		else
		{
			customBiomeDecorator.treesPerChunk = 3;
		}
		
		customBiomeDecorator.grassPerChunk = 7;
		customBiomeDecorator.wheatGrassPerChunk = 3;
		customBiomeDecorator.flowersPerChunk = 8;
		customBiomeDecorator.pinkFlowersPerChunk = 6;
		customBiomeDecorator.glowFlowersPerChunk = 15;
		customBiomeDecorator.rosesPerChunk = 8;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.sproutsPerChunk = 1;
		customBiomeDecorator.hydrangeasPerChunk = 3;
		customBiomeDecorator.blueMilksPerChunk = 1;
		customBiomeDecorator.lilyflowersPerChunk = 3;
		customBiomeDecorator.poisonWaterPerChunk = 1;
		waterColorMultiplier = 16715898;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 4, 4));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		if (BOPConfigurationMain.realisticTrees)
		{
			return new WorldGenRealMagic();
		}
		
		return par1Random.nextInt(4) == 0 ? new WorldGenMystic2(false) : (par1Random.nextInt(2) == 0 ? new WorldGenJacaranda(false) : ((par1Random.nextInt(6) == 0 ? this.worldGeneratorBigTree : ((par1Random.nextInt(5) == 0 ? new WorldGenSwampTall() : this.worldGeneratorTrees)))));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return (par1Random.nextInt(5) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 1) : (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1)));
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 6934491;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 7397529;
	}
	
	/**
	 * Fog Color
	 */
	@Override
	public int getFogColour()
	{
		return 16755401;
	}

	/**
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors)
			return 8972496;
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

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
}
