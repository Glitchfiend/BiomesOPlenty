package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.worldgen.WorldGenCobwebNest;
import biomesoplenty.worldgen.tree.WorldGenBirchWillow;
import biomesoplenty.worldgen.tree.WorldGenDeadTree;
import biomesoplenty.worldgen.tree.WorldGenWillow;

public class BiomeGenSilkglades extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenSilkglades(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 6;
		customBiomeDecorator.grassPerChunk = 2;
		customBiomeDecorator.wheatGrassPerChunk = 1;
		customBiomeDecorator.mushroomsPerChunk = 4;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.gravelPerChunk = 3;
		customBiomeDecorator.gravelPerChunk2 = 3;
		customBiomeDecorator.sproutsPerChunk = 2;
		customBiomeDecorator.poisonIvyPerChunk = 2;
		customBiomeDecorator.cobwebsPerChunk = 9;
		customBiomeDecorator.waterReedsPerChunk = 3;
		customBiomeDecorator.generatePumpkins = true;
		waterColorMultiplier = 16777079;
		spawnableWaterCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(EntitySpider.class, 7, 1, 2));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return par1Random.nextInt(5) == 0 ? new WorldGenBirchWillow() : (par1Random.nextInt(7) == 0 ? new WorldGenDeadTree(false) : (par1Random.nextInt(12) == 0 ? new WorldGenCobwebNest(0,0) : new WorldGenWillow()));
	 }
	 
		/**
		 * Gets a WorldGen appropriate for this biome.
		 */
		@Override
		public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
		{
			return new WorldGenTallGrass(Block.tallGrass.blockID, 0);
		}

		@Override
		public void decorate(World par1World, Random par2Random, int par3, int par4)
		{
			super.decorate(par1World, par2Random, par3, par4);
			int var5 = 12 + par2Random.nextInt(6);

			for (int var6 = 0; var6 < var5; ++var6)
			{
				int var7 = par3 + par2Random.nextInt(16);
				int var8 = par2Random.nextInt(28) + 4;
				int var9 = par4 + par2Random.nextInt(16);
				int var10 = par1World.getBlockId(var7, var8, var9);

				Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
				{
					par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 10, 2);
				}
			}
		}

	 /**
	  * Provides the basic grass color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 13420973;
	 }

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 14146486;
	 }

	 /**
	  * takes temperature, returns color
	  */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors)
			 return 13553096;
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
