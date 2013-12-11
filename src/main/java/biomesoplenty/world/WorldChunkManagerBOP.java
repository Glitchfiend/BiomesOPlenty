package biomesoplenty.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import biomesoplenty.api.Biomes;
import biomesoplenty.world.layer.BiomeLayer;

import com.google.common.base.Optional;

public class WorldChunkManagerBOP extends WorldChunkManager
{
	private GenLayer genBiomes;

	/** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
	private GenLayer biomeIndexLayer;

	/** The BiomeCache object for this world. */
	private BiomeCache biomeCache;

	/** A list of biomes that the player can spawn in. */
	private List<BiomeGenBase> biomesToSpawnIn;

	protected WorldChunkManagerBOP()
	{
		biomeCache = new BiomeCache(this);
		biomesToSpawnIn = new ArrayList<BiomeGenBase>();
		biomesToSpawnIn.add(BiomeGenBase.forest);
		biomesToSpawnIn.add(BiomeGenBase.plains);
		biomesToSpawnIn.add(BiomeGenBase.taiga);
		biomesToSpawnIn.add(BiomeGenBase.taigaHills);
		biomesToSpawnIn.add(BiomeGenBase.forestHills);
		biomesToSpawnIn.add(BiomeGenBase.jungle);
		biomesToSpawnIn.add(BiomeGenBase.jungleHills);

		addSpawnBiomes(Biomes.alps);
		addSpawnBiomes(Biomes.arctic);
		addSpawnBiomes(Biomes.badlands);
		addSpawnBiomes(Biomes.bambooForest);
		addSpawnBiomes(Biomes.bayou);
		addSpawnBiomes(Biomes.birchForest);
		addSpawnBiomes(Biomes.bog);
		addSpawnBiomes(Biomes.borealForest);
		addSpawnBiomes(Biomes.brushland);
		addSpawnBiomes(Biomes.chaparral);
		addSpawnBiomes(Biomes.cherryBlossomGrove);
		addSpawnBiomes(Biomes.coniferousForest);
		addSpawnBiomes(Biomes.coniferousForestSnow);
		addSpawnBiomes(Biomes.crag);
		addSpawnBiomes(Biomes.deadForest);
		addSpawnBiomes(Biomes.deadForestSnow);
		addSpawnBiomes(Biomes.deciduousForest);
		addSpawnBiomes(Biomes.dunes);
		addSpawnBiomes(Biomes.frostForest);
		addSpawnBiomes(Biomes.glacier);
		addSpawnBiomes(Biomes.grassland);
		addSpawnBiomes(Biomes.grove);
		addSpawnBiomes(Biomes.heathland);
		addSpawnBiomes(Biomes.highland);
		addSpawnBiomes(Biomes.lushDesert);
		addSpawnBiomes(Biomes.lushSwamp);
		addSpawnBiomes(Biomes.mangrove);
		addSpawnBiomes(Biomes.mapleWoods);
		addSpawnBiomes(Biomes.marsh);
		addSpawnBiomes(Biomes.meadow);
		addSpawnBiomes(Biomes.mesa);
		addSpawnBiomes(Biomes.mountain);
		addSpawnBiomes(Biomes.pasture);
		addSpawnBiomes(Biomes.prairie);
		addSpawnBiomes(Biomes.quagmire);
		addSpawnBiomes(Biomes.rainforest);
		addSpawnBiomes(Biomes.redwoodForest);
		addSpawnBiomes(Biomes.savanna);
		addSpawnBiomes(Biomes.scrubland);
		addSpawnBiomes(Biomes.seasonalForest);
		addSpawnBiomes(Biomes.shrubland);
		addSpawnBiomes(Biomes.steppe);
		addSpawnBiomes(Biomes.temperateRainforest);
		addSpawnBiomes(Biomes.tropicalRainforest);
		addSpawnBiomes(Biomes.tropics);
		addSpawnBiomes(Biomes.tundra);
		addSpawnBiomes(Biomes.volcano);
		addSpawnBiomes(Biomes.wetland);
		addSpawnBiomes(Biomes.woodland);

		addSpawnBiomes(Biomes.forestNew);
		addSpawnBiomes(Biomes.plainsNew);
		addSpawnBiomes(Biomes.taigaNew);
		addSpawnBiomes(Biomes.jungleNew);
	}

	public WorldChunkManagerBOP(long par1, WorldType par3WorldType)
	{
		this();
//		GenLayer[] var4 = BiomeLayer.initializeAllBiomeGenerators(par1, par3WorldType, 0);
		
		GenLayer[] agenlayer = BiomeLayer.initializeAllBiomeGenerators(par1, par3WorldType, 0);
		agenlayer = getModdedBiomeGenerators(par3WorldType, par1, agenlayer);
		
//        genBiomes = var4[0];
//		biomeIndexLayer = var4[1];
		genBiomes = agenlayer[0];
        biomeIndexLayer = agenlayer[1];
	}

	public WorldChunkManagerBOP(World par1World)
	{
		this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
	}

	/**
	 * Gets the list of valid biomes for the player to spawn in.
	 */
	 @Override
	 public List<BiomeGenBase> getBiomesToSpawnIn()
	 {
		 return biomesToSpawnIn;
	 }

	/**
	 * Returns the BiomeGenBase related to the x, z position on the world.
	 */
	 @Override
	 public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		 return biomeCache.getBiomeGenAt(par1, par2);
	}

	 /**
	  * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
	  */
	 @Override
	 public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
	 {
		 IntCache.resetIntCache();

		 if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
		 {
			 par1ArrayOfFloat = new float[par4 * par5];
		 }

		 int[] var6 = biomeIndexLayer.getInts(par2, par3, par4, par5);

		 for (int var7 = 0; var7 < par4 * par5; ++var7)
		 {
			 float var8 = BiomeGenBase.biomeList[var6[var7]].getIntRainfall() / 65536.0F;

			 if (var8 > 1.0F)
			 {
				 var8 = 1.0F;
			 }

			 par1ArrayOfFloat[var7] = var8;
		 }

		 return par1ArrayOfFloat;
	 }

	 /**
	  * Return an adjusted version of a given temperature based on the y height
	  */
	 @Override
	 public float getTemperatureAtHeight(float par1, int par2)
	 {
		 return par1;
	 }

	 /**
	  * Returns a list of temperatures to use for the specified blocks.  Args: listToReuse, x, y, width, length
	  */
	 @Override
	 public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
	 {
		 IntCache.resetIntCache();

		 if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
		 {
			 par1ArrayOfFloat = new float[par4 * par5];
		 }

		 int[] var6 = biomeIndexLayer.getInts(par2, par3, par4, par5);

		 for (int var7 = 0; var7 < par4 * par5; ++var7)
		 {
			 float var8 = BiomeGenBase.biomeList[var6[var7]].getIntTemperature() / 65536.0F;

			 if (var8 > 1.0F)
			 {
				 var8 = 1.0F;
			 }

			 par1ArrayOfFloat[var7] = var8;
		 }

		 return par1ArrayOfFloat;
	 }

	 /**
	  * Returns an array of biomes for the location input.
	  */
	 @Override
	 public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	 {
		 IntCache.resetIntCache();

		 if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
		 {
			 par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		 }

		 int[] var6 = genBiomes.getInts(par2, par3, par4, par5);

		 for (int var7 = 0; var7 < par4 * par5; ++var7)
		 {
			 par1ArrayOfBiomeGenBase[var7] = BiomeGenBase.biomeList[var6[var7]];
		 }

		 return par1ArrayOfBiomeGenBase;
	 }

	 /**
	  * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the
	  * WorldChunkManager Args: oldBiomeList, x, z, width, depth
	  */
	 @Override
	 public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	 {
		 return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
	 }

	 /**
	  * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
	  * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
	  */
	 @Override
	 public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
	 {
		 IntCache.resetIntCache();

		 if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
		 {
			 par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		 }

		 if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
		 {
			 BiomeGenBase[] var9 = biomeCache.getCachedBiomes(par2, par3);
			 System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
			 return par1ArrayOfBiomeGenBase;
		 }
		 else
		 {
			 int[] var7 = biomeIndexLayer.getInts(par2, par3, par4, par5);

			 for (int var8 = 0; var8 < par4 * par5; ++var8)
			 {
				 par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.biomeList[var7[var8]];
			 }

			 return par1ArrayOfBiomeGenBase;
		 }
	 }

	 /**
	  * checks given Chunk's Biomes against List of allowed ones
	  */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
	 {
		 IntCache.resetIntCache();
		 int var5 = par1 - par3 >> 2;
			 int var6 = par2 - par3 >> 2;
		 int var7 = par1 + par3 >> 2;
		 int var8 = par2 + par3 >> 2;
		 int var9 = var7 - var5 + 1;
		 int var10 = var8 - var6 + 1;
		 int[] var11 = genBiomes.getInts(var5, var6, var9, var10);

		 for (int var12 = 0; var12 < var9 * var10; ++var12)
		 {
			 BiomeGenBase var13 = BiomeGenBase.biomeList[var11[var12]];

			 if (!par4List.contains(var13))
				 return false;
		 }

		 return true;
	 }

	 /**
	  * Finds a valid position within a range, that is in one of the listed biomes. Searches {par1,par2} +-par3 blocks.
	  * Strongly favors positive y positions.
	  */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
	 {
		 IntCache.resetIntCache();
		 int var6 = par1 - par3 >> 2;
		 int var7 = par2 - par3 >> 2;
		 int var8 = par1 + par3 >> 2;
		 int var9 = par2 + par3 >> 2;
		 int var10 = var8 - var6 + 1;
		 int var11 = var9 - var7 + 1;
		 int[] var12 = genBiomes.getInts(var6, var7, var10, var11);
		 ChunkPosition var13 = null;
		 int var14 = 0;

		 for (int var15 = 0; var15 < var12.length; ++var15)
		 {
			 int var16 = var6 + var15 % var10 << 2;
			 int var17 = var7 + var15 / var10 << 2;
			 BiomeGenBase var18 = BiomeGenBase.biomeList[var12[var15]];

			 if (par4List.contains(var18) && (var13 == null || par5Random.nextInt(var14 + 1) == 0))
			 {
				 var13 = new ChunkPosition(var16, 0, var17);
				 ++var14;
			 }
		 }

		 return var13;
	 }

	 /**
	  * Calls the WorldChunkManager's biomeCache.cleanupCache()
	  */
	 @Override
	 public void cleanupCache()
	 {
		 biomeCache.cleanupCache();
	 }

	 private void addSpawnBiomes(Optional<? extends BiomeGenBase> biome)
	 {
		 if (biome.isPresent()) {
			 biomesToSpawnIn.add(biome.get());
		 }
	 }
	 
	 @Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.newBiomeGens;
    }
}
