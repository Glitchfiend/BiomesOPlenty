package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.configuration.BOPConfiguration;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.WorldGenTaiga5;
import biomesoplenty.worldgen.WorldGenWillow;

public class BiomeGenWetland extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenWetland(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 10;
		customBiomeDecorator.grassPerChunk = 10;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.mushroomsPerChunk = 5;
		customBiomeDecorator.toadstoolsPerChunk = 1;
		customBiomeDecorator.reedsPerChunk = 15;
		customBiomeDecorator.reedsBOPPerChunk = 15;
		customBiomeDecorator.clayPerChunk = 2;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.mudPerChunk = 5;
		customBiomeDecorator.mudPerChunk2 = 5;
		customBiomeDecorator.waterlilyPerChunk = 4;
		customBiomeDecorator.lilyflowersPerChunk = 4;
		customBiomeDecorator.cattailsPerChunk = 20;
		customBiomeDecorator.highCattailsPerChunk = 10;
		customBiomeDecorator.blueFlowersPerChunk = 6;
		customBiomeDecorator.blueMilksPerChunk = 1;
		customBiomeDecorator.portobellosPerChunk = 1;
		customBiomeDecorator.berryBushesPerChunk = 1;
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
		waterColorMultiplier = 6512772;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 //return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenWillow2() : (par1Random.nextInt(4) == 0 ? new WorldGenLarch1() : (par1Random.nextInt(2) == 0 ? new WorldGenLarch2() : new WorldGenWillow1())));
		 return par1Random.nextInt(2) == 0 ? new WorldGenTaiga5(false) : new WorldGenWillow();
	 }

	 @Override
	 public void decorate(World par1World, Random par2Random, int par3, int par4)
	 {
		 super.decorate(par1World, par2Random, par3, par4);
		 WorldGenMoss var5 = new WorldGenMoss();

         if (BOPConfiguration.Misc.generateAmethystOres)
         {
             int var55 = 12 + par2Random.nextInt(6);

            for (int var66 = 0; var66 < var55; ++var66)
            {
                int var77 = par3 + par2Random.nextInt(16);
                int var88 = par2Random.nextInt(28) + 4;
                int var99 = par4 + par2Random.nextInt(16);
                int var100 = par1World.getBlockId(var77, var88, var99);

                if (var100 == Block.stone.blockID)
                {
                    par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 10, 2);
                }
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
	  * Gets a WorldGen appropriate for this biome.
	  */
	 @Override
	 public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	 {
		 return par1Random.nextInt(6) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	 }

	 /**
	  * Provides the basic grass color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 5935967;
	 }

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 5215831;
	 }
}
