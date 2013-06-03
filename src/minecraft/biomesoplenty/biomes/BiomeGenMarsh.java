package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenMarsh;

public class BiomeGenMarsh extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMarsh(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = 65;
		customBiomeDecorator.highGrassPerChunk = 25;
		customBiomeDecorator.generatePumpkins = false;
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenMarsh var5 = new WorldGenMarsh();
		
		 int var55 = 12 + par2Random.nextInt(6);

		for (int var66 = 0; var66 < var55; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16);
			int var88 = par2Random.nextInt(28) + 4;
			int var99 = par4 + par2Random.nextInt(16);
			int var100 = par1World.getBlockId(var77, var88, var99);

			if (var100 == Block.stone.blockID)
			{
				par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 12, 2);
			}
		}

		for (int var6 = 0; var6 < 25; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16) + 8;
			byte var8 = 62;
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
		 return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
	 }
}
