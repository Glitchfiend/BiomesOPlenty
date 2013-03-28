package tdwp_ftw.biomesop.worldtype;

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
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

public class WorldChunkManagerBOP extends WorldChunkManager
{
    private GenLayer genBiomes;

    /** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
    private GenLayer biomeIndexLayer;

    /** The BiomeCache object for this world. */
    private BiomeCache biomeCache;

    /** A list of biomes that the player can spawn in. */
    private List biomesToSpawnIn;

    protected WorldChunkManagerBOP()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        this.biomesToSpawnIn.add(BiomeGenBase.forest);
        this.biomesToSpawnIn.add(BiomeGenBase.plains);
        this.biomesToSpawnIn.add(BiomeGenBase.taiga);
        this.biomesToSpawnIn.add(BiomeGenBase.taigaHills);
        this.biomesToSpawnIn.add(BiomeGenBase.forestHills);
        this.biomesToSpawnIn.add(BiomeGenBase.jungle);
        this.biomesToSpawnIn.add(BiomeGenBase.jungleHills);
		
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.alps);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.arctic);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.badlands);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.bambooForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.bayou);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.birchForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.bog);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.borealForest);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.chaparral);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.cherryBlossomGrove);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.coniferousForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.crag);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.deadForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.deciduousForest);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.drylands);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.dunes);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.frostForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.glacier);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.grassland);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.grove);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.heathland);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.highland);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.lushDesert);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.lushSwamp);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.mangrove);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.mapleWoods);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.marsh);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.meadow);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.mesa);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.mountain);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.oasis);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.orchard);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.pasture);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.prairie);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.quagmire);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.rainforest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.redwoodForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.savanna);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.scrubland);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.seasonalForest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.shrubland);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.steppe);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.temperateRainforest);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.tropicalRainforest);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.tropics);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.tundra);
		this.biomesToSpawnIn.add(mod_BiomesOPlenty.volcano);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.wetland);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.woodland);
		
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.forestNew);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.plainsNew);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.taigaNew);
        this.biomesToSpawnIn.add(mod_BiomesOPlenty.jungleNew);
    }

    public WorldChunkManagerBOP(long par1, WorldType par3WorldType)
    {
        this();
        GenLayer[] var4 = GenLayer.initializeAllBiomeGenerators(par1, par3WorldType);
        this.genBiomes = (GenLayer) var4[0];
        this.biomeIndexLayer = (GenLayer) var4[1];
    }

    public WorldChunkManagerBOP(World par1World)
    {
        this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
    }

    /**
     * Gets the list of valid biomes for the player to spawn in.
     */
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    /**
     * Returns the BiomeGenBase related to the x, z position on the world.
     */
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomeCache.getBiomeGenAt(par1, par2);
    }

    /**
     * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
     */
    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] var6 = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.biomeList[var6[var7]].getIntRainfall() / 65536.0F;

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
    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    /**
     * Returns a list of temperatures to use for the specified blocks.  Args: listToReuse, x, y, width, length
     */
    public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] var6 = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.biomeList[var6[var7]].getIntTemperature() / 65536.0F;

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
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        int[] var6 = this.genBiomes.getInts(par2, par3, par4, par5);

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
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
     * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
     */
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] var9 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
            int[] var7 = this.biomeIndexLayer.getInts(par2, par3, par4, par5);

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
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
        IntCache.resetIntCache();
        int var5 = par1 - par3 >> 2;
        int var6 = par2 - par3 >> 2;
        int var7 = par1 + par3 >> 2;
        int var8 = par2 + par3 >> 2;
        int var9 = var7 - var5 + 1;
        int var10 = var8 - var6 + 1;
        int[] var11 = this.genBiomes.getInts(var5, var6, var9, var10);

        for (int var12 = 0; var12 < var9 * var10; ++var12)
        {
            BiomeGenBase var13 = BiomeGenBase.biomeList[var11[var12]];

            if (!par4List.contains(var13))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Finds a valid position within a range, that is in one of the listed biomes. Searches {par1,par2} +-par3 blocks.
     * Strongly favors positive y positions.
     */
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
    {
        IntCache.resetIntCache();
        int var6 = par1 - par3 >> 2;
        int var7 = par2 - par3 >> 2;
        int var8 = par1 + par3 >> 2;
        int var9 = par2 + par3 >> 2;
        int var10 = var8 - var6 + 1;
        int var11 = var9 - var7 + 1;
        int[] var12 = this.genBiomes.getInts(var6, var7, var10, var11);
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
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}
