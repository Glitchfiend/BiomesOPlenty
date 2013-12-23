package biomesoplenty.biomes.vanilla;

import java.awt.Color;
import java.util.Random;

import worldcore.interfaces.IWCFog;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.biomes.BiomeDecoratorBOP;
import biomesoplenty.configuration.BOPConfigurationMisc;
import biomesoplenty.worldgen.WorldGenLog;
import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.tree.WorldGenWillow;

public class BiomeGenSwampNew extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenSwampNew(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 4;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.deadBushPerChunk = 1;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.reedsPerChunk = 10;
		customBiomeDecorator.clayPerChunk = 1;
		customBiomeDecorator.waterlilyPerChunk = 4;
		customBiomeDecorator.wheatGrassPerChunk = 1;
		customBiomeDecorator.mudPerChunk = 9;
		customBiomeDecorator.mudPerChunk2 = 9;
		customBiomeDecorator.portobellosPerChunk = 1;
		customBiomeDecorator.waterReedsPerChunk = 4;
		customBiomeDecorator.koruPerChunk = 1;
		waterColorMultiplier = 14745456;
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenLog() : new WorldGenWillow();
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenMoss var5 = new WorldGenMoss();
		
		 int var55 = 12 + par2Random.nextInt(6);

		for (int var66 = 0; var66 < var55; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16);
			int var88 = par2Random.nextInt(28) + 4;
			int var99 = par4 + par2Random.nextInt(16);
			int var100 = par1World.getBlockId(var77, var88, var99);

			if (var100 == Block.stone.blockID)
			{
				par1World.setBlock(var77, var88, var99, BOPBlocks.amethystOre.get().blockID, 10, 2);
			}
		}

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
	@Override
	public int getBiomeGrassColor()
	{
		double var1 = this.getFloatTemperature();
		double var3 = this.getFloatRainfall();
		return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		double var1 = this.getFloatTemperature();
		double var3 = this.getFloatRainfall();
		return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
	}
	
	@Override
	public int getFogColour()
	{
		return 7246218;
	}
	
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.4F;
    }
	
	 /**
	  * takes temperature, returns color
	  */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors)
			 return 4149332;
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
