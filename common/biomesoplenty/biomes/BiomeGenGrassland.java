package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class BiomeGenGrassland extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenGrassland(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = 2;
		customBiomeDecorator.reedsPerChunk = 35;
		customBiomeDecorator.mushroomsPerChunk = 20;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.waterLakesPerChunk = 15;
		customBiomeDecorator.portobellosPerChunk = 3;
		customBiomeDecorator.reedsBOPPerChunk = 5;
		customBiomeDecorator.generatePumpkins = false;
		spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 14, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 12, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 12, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 10, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
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

			if (Block.blocksList[var10].isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
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
		return par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 8379261;
	 }

	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 8379261;
	 }
}
