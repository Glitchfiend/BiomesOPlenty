package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.worldgen.WorldGenGrave;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenNetherDesert extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenNetherDesert(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Block.slowSand.blockID;
		fillerBlock = (byte)Block.slowSand.blockID;
		customBiomeDecorator.thornsPerChunk = 10;
		customBiomeDecorator.tinyCactiPerChunk = 3;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);

		if (par2Random.nextInt(5) == 0)
		{
			int var5 = par3 + par2Random.nextInt(16) + 8;
			int var6 = par4 + par2Random.nextInt(16) + 8;
			WorldGenGrave var7 = new WorldGenGrave();
			var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
		}
	}
}
