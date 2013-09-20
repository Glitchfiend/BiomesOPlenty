package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.tree.WorldGenAcacia;
import biomesoplenty.worldgen.tree.WorldGenDeadTree3;

public class BiomeGenLushDesert extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenLushDesert(int par1)
	{
		super(par1);
		topBlock = (byte)Blocks.redRock.get().blockID;
		fillerBlock = (byte)Blocks.redRock.get().blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 12;
		customBiomeDecorator.grassPerChunk = 8;
		customBiomeDecorator.wheatGrassPerChunk = 4;
		customBiomeDecorator.oasesPerChunk = 999;
		customBiomeDecorator.oasesPerChunk2 = 999;
		customBiomeDecorator.deadBushPerChunk = 2;
		customBiomeDecorator.purpleFlowersPerChunk = 5;
		customBiomeDecorator.desertGrassPerChunk = 10;
		customBiomeDecorator.cactiPerChunk = 20;
		customBiomeDecorator.tinyCactiPerChunk = 5;
		customBiomeDecorator.waterLakesPerChunk = 5;
		customBiomeDecorator.aloePerChunk = 3;
		customBiomeDecorator.generateGrass = true;
		customBiomeDecorator.generateSand = true;
		customBiomeDecorator.generatePumpkins = false;
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return par1Random.nextInt(4) == 0 ? new WorldGenAcacia(false) : (par1Random.nextInt(24) == 0 ? new WorldGenDeadTree3(false) : (par1Random.nextInt(2) == 0 ? worldGeneratorTrees : new WorldGenShrub(0,0)));
	 }

	 @Override
	 public void decorate(World par1World, Random par2Random, int par3, int par4)
	 {
		 super.decorate(par1World, par2Random, par3, par4);
		 int var5 = par2Random.nextInt(50);
		 
		int var55 = 12 + par2Random.nextInt(6);

		for (int var66 = 0; var66 < var55; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16);
			int var88 = par2Random.nextInt(28) + 4;
			int var99 = par4 + par2Random.nextInt(16);
			int var100 = par1World.getBlockId(var77, var88, var99);

			if (var100 == Block.stone.blockID)
			{
				par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 2, 2);
			}
		}

		 for (int var6 = 0; var6 < var5; ++var6)
		 {
			 int var7 = par3 + par2Random.nextInt(16);
			 int var8 = par2Random.nextInt(53) + 75;
			 int var9 = par4 + par2Random.nextInt(16);
			 int var10 = par1World.getBlockId(var7, var8, var9);

			 if (var10 == Block.stone.blockID || var10 == Blocks.redRock.get().blockID)
			 {
				 par1World.setBlock(var7, var8, var9, Block.waterMoving.blockID, 0, 2);
			 }
		 }
	 }
}
