package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.entities.EntityRosester;
import biomesoplenty.worldgen.WorldGenBOPShrub;
import biomesoplenty.worldgen.WorldGenGiantFlowerRed;
import biomesoplenty.worldgen.WorldGenGiantFlowerYellow;

public class BiomeGenGarden extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenGarden(int par1)
	{
		super(par1);
		topBlock = (byte)Blocks.longGrass.get().blockID;
		fillerBlock = (byte)Block.dirt.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 2;
		customBiomeDecorator.flowersPerChunk = 20;
		customBiomeDecorator.whiteFlowersPerChunk = 25;
		customBiomeDecorator.highGrassPerChunk = 6;
		customBiomeDecorator.hydrangeasPerChunk = 3;
		customBiomeDecorator.sproutsPerChunk = 2;
		customBiomeDecorator.sunflowersPerChunk = 4;
		customBiomeDecorator.rosesPerChunk = 20;
		customBiomeDecorator.grassPerChunk = 25;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.lilyflowersPerChunk = 4;
		customBiomeDecorator.generatePumpkins = true;
		customBiomeDecorator.generateMelons = true;
		spawnableCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(EntityRosester.class, 10, 4, 4));
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
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 4, 2);
			}
		}
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2)));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(6) == 0 ? new WorldGenGiantFlowerRed() : (par1Random.nextInt(6) == 0 ? new WorldGenGiantFlowerYellow() : new WorldGenBOPShrub(0,0));
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 7656308;
	 }

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 6742630;
	 }
}
