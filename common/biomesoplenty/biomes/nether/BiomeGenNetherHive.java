package biomesoplenty.biomes.nether;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import biomesoplenty.api.Blocks;
import biomesoplenty.biomes.BiomeDecoratorBOP;
import biomesoplenty.worldgen.WorldGenHive;

public class BiomeGenNetherHive extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenNetherHive(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Block.netherrack.blockID;
		fillerBlock = (byte)Block.netherrack.blockID;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		customBiomeDecorator.generateHive = true;
		spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);

		if (par2Random.nextInt(2) == 0)
		{
			int var5 = par3 + par2Random.nextInt(16) + 8;
			int var99 = par2Random.nextInt(128);
			int var6 = par4 + par2Random.nextInt(16) + 8;
			WorldGenHive var7 = new WorldGenHive();
			var7.generate(par1World, par2Random, var5, var99, var6);
		}
	}
}
