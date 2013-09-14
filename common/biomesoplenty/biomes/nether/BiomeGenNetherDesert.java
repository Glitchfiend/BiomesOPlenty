package biomesoplenty.biomes.nether;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import biomesoplenty.biomes.BiomeDecoratorBOP;

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
		customBiomeDecorator.gravesPerChunk = 1;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	}
}
