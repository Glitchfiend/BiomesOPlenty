package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldcore.interfaces.IWCFog;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.tree.WorldGenSequoia;
import biomesoplenty.worldgen.tree.WorldGenSequoiaOrange;
import biomesoplenty.worldgen.tree.WorldGenSequoiaYellow;

public class BiomeGenFungiForest extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenFungiForest(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 8;
		customBiomeDecorator.grassPerChunk = 4;
		customBiomeDecorator.sproutsPerChunk = 2;
		customBiomeDecorator.bushesPerChunk = 1;
		customBiomeDecorator.highGrassPerChunk = 1;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.bigMushroomsPerChunk = 8;
		customBiomeDecorator.toadstoolsPerChunk = 5;
		customBiomeDecorator.portobellosPerChunk = 7;
		customBiomeDecorator.blueMilksPerChunk = 2;
		customBiomeDecorator.glowshroomsPerChunk = 1;
		customBiomeDecorator.blueFlowersPerChunk = 3;
		customBiomeDecorator.reedsBOPPerChunk = 1;
		customBiomeDecorator.wheatGrassPerChunk = 3;
		customBiomeDecorator.shrubsPerChunk = 1;
		customBiomeDecorator.cloverPatchesPerChunk = 20;
		customBiomeDecorator.generateMycelium = true;
		customBiomeDecorator.generatePumpkins = true;
		waterColorMultiplier = 65326;
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 3, 4, 8));
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenMoss var5 = new WorldGenMoss();

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
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return par1Random.nextInt(3) == 0 ? new WorldGenSequoiaOrange(false) : ((par1Random.nextInt(5) == 0 ? new WorldGenSequoiaYellow(false) : (par1Random.nextInt(2) == 0 ? new WorldGenShrub(0, 0) : new WorldGenSequoia(false))));
	 }

	 /**
	  * Gets a WorldGen appropriate for this biome.
	  */
	 @Override
	 public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	 {
		 return (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : (par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1))));
	 }

	 /**
	  * Provides the basic grass color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 15792496;
	 }

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 11139946;
	 }
	 
	/**
	 * Fog Color
	 */
	@Override
	public int getFogColour()
	{
		return 16050295;
	}

	 /**
	  * takes temperature, returns color
	  */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors)
			 return 11513806;
		 else
		 {
			 par1 /= 3.0F;

			 if (par1 < -1.0F)
			 {
				 par1 = -1.0F;
			 }

			 if (par1 > 1.0F)
			 {
				 par1 = 1.0F;
			 }

			 return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		 }
	 }

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
}
