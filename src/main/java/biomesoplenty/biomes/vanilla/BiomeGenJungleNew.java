package biomesoplenty.biomes.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldcore.interfaces.IWCFog;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.biomes.BiomeDecoratorBOP;
import biomesoplenty.entities.EntityJungleSpider;

public class BiomeGenJungleNew extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenJungleNew(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 45;
		customBiomeDecorator.grassPerChunk = 25;
		customBiomeDecorator.wheatGrassPerChunk = 10;
		customBiomeDecorator.flowersPerChunk = 4;
		customBiomeDecorator.orangeFlowersPerChunk = 5;
		customBiomeDecorator.poisonIvyPerChunk = 1;
		customBiomeDecorator.shrubsPerChunk = 10;
		customBiomeDecorator.generateMelons = true;
		waterColorMultiplier = 10745289;
		spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
		waterColorMultiplier = 5242687;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		//return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new WorldGenBrazilNut1() : (par1Random.nextInt(10) == 0 ? new WorldGenSandboxTree1() : (par1Random.nextInt(2) == 0 ? new WorldGenBrazilNut2() : (par1Random.nextInt(3) == 0 ? new WorldGenSandboxTree2() : new WorldGenShrub(3, 0)))));
		return par1Random.nextInt(10) == 0 ? worldGeneratorBigTree : (par1Random.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (par1Random.nextInt(3) == 0 ? new WorldGenHugeTrees(false, 10 + par1Random.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true)));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenVines var5 = new WorldGenVines();
		
		int var55 = 12 + par2Random.nextInt(6);

		for (int var66 = 0; var66 < var55; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16);
			int var88 = par2Random.nextInt(28) + 4;
			int var99 = par4 + par2Random.nextInt(16);
			int var100 = par1World.getBlockId(var77, var88, var99);

			if (var100 == Block.stone.blockID)
			{
				par1World.setBlock(var77, var88, var99, BOPBlocks.amethystOre.get().blockID, 6, 2);
			}
		}

		for (int var6 = 0; var6 < 50; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16) + 8;
			byte var8 = 32;
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
		 return 6463547;
	 }

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 7060540;
	 }
	 
	@Override
	public int getFogColour()
	{
		return 9225359;
	}

	@Override
	public float getFogCloseness()
	{
		return 0.7F;
	}
}
