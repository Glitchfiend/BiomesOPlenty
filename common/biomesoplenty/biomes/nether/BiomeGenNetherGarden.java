package biomesoplenty.biomes.nether;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.biomes.BiomeDecoratorBOP;
import biomesoplenty.worldgen.WorldGenNetherMushroom;

public class BiomeGenNetherGarden extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenNetherGarden(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Block.netherrack.blockID;
		fillerBlock = (byte)Block.netherrack.blockID;
		customBiomeDecorator.treesPerChunk = 80;
		customBiomeDecorator.netherVinesPerChunk = 60;
		customBiomeDecorator.mushroomsPerChunk = 30;
		customBiomeDecorator.bigMushroomsPerChunk = 30;
		customBiomeDecorator.netherWartPerChunk = 8;
		customBiomeDecorator.netherGrassPerChunk = 10;
		customBiomeDecorator.glowshroomsPerChunk = 3;
		customBiomeDecorator.toadstoolsPerChunk = 5;
		customBiomeDecorator.gravesPerChunk = 1;
		customBiomeDecorator.burningBlossomsPerChunk = 8;
		customBiomeDecorator.waspHivesPerChunk = 1;
		customBiomeDecorator.generateUndergroundLakes = false;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return new WorldGenNetherMushroom();
	 }
}
