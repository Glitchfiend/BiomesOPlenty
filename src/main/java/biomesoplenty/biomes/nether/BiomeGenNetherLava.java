package biomesoplenty.biomes.nether;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.biomes.BiomeDecoratorBOP;
import biomesoplenty.worldgen.WorldGenLavaSpring;

public class BiomeGenNetherLava extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenNetherLava(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Blocks.ash.get().blockID;
		fillerBlock = (byte)Blocks.ash.get().blockID;
		customBiomeDecorator.grassPerChunk = 8;
		customBiomeDecorator.netherLavaPerChunk = 20;
		customBiomeDecorator.smolderingGrassPerChunk = 5;
		customBiomeDecorator.gravesPerChunk = 1;
		customBiomeDecorator.burningBlossomsPerChunk = 4;
		customBiomeDecorator.waspHivesPerChunk = 1;
		customBiomeDecorator.generateUndergroundLakes = false;
		customBiomeDecorator.generateAsh = true;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 4, 4, 4));
		theWorldGenerator = new WorldGenLavaSpring(Block.lavaMoving.blockID, 4);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	 {
		 return new WorldGenFire();
	 }

	 @Override
	 public void decorate(World par1World, Random par2Random, int par3, int par4)
	 {
		 super.decorate(par1World, par2Random, par3, par4);
		 int var5 = 100;
		 int var6;
		 int var7;
		 int var8;

		 for (var5 = 0; var5 < 3; ++var5)
		 {
			 var6 = par3 + par2Random.nextInt(16);
			 var7 = 64 + par2Random.nextInt(64);
			 var8 = par4 + par2Random.nextInt(16);
			 theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
		 }
	 }
}
