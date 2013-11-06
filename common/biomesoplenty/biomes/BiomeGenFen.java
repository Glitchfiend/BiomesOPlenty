package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldcore.interfaces.IWCFog;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.tree.WorldGenDeadTree;
import biomesoplenty.worldgen.tree.WorldGenFen1;
import biomesoplenty.worldgen.tree.WorldGenFen2;

public class BiomeGenFen extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenFen(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 10;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.highGrassPerChunk = 1;
		customBiomeDecorator.waterlilyPerChunk = 1;
		customBiomeDecorator.cattailsPerChunk = 1;
		customBiomeDecorator.highCattailsPerChunk = 1;
		customBiomeDecorator.pondsPerChunk = 99;
		customBiomeDecorator.toadstoolsPerChunk = 2;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.mudPerChunk = 1;
		customBiomeDecorator.mudPerChunk2 = 1;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.reedsBOPPerChunk = 5;
		customBiomeDecorator.algaePerChunk = 1;
		customBiomeDecorator.portobellosPerChunk = 1;
		customBiomeDecorator.wheatGrassPerChunk = 8;
		customBiomeDecorator.waterReedsPerChunk = 10;
		customBiomeDecorator.koruPerChunk = 1;
		customBiomeDecorator.shrubsPerChunk = 7;
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenFen2(false) : (par1Random.nextInt(20) == 0 ? new WorldGenDeadTree(false) : new WorldGenFen1());
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
				par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 10, 2);
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
		 return (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1)));
	 }

	 /**
	  * Provides the basic grass color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 12240001;
	 }

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 13547897;
	 }
	 
	@Override
	public int getFogColour()
	{
		return 12638463;
	}
	
	@Override
	public float getFogCloseness()
	{
	    // TODO Auto-generated method stub
	    return 0.8F;
	}
}
