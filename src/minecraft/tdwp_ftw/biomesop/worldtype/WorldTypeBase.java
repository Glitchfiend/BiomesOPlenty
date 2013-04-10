package tdwp_ftw.biomesop.worldtype;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class WorldTypeBase extends WorldType
{
	
    public WorldTypeBase(int par1, String par2Str) {
		super(par1, par2Str);
	}

	public WorldChunkManager getChunkManager(World var1)
    {
        return new WorldChunkManagerBOP(var1);
    }
	
   /* public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderBOP(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }*/
	
	public void removeAllBiomes()
	{
		this.removeBiome(BiomeGenBase.plains);
		this.removeBiome(BiomeGenBase.desert);
		this.removeBiome(BiomeGenBase.forest);
		this.removeBiome(BiomeGenBase.extremeHills);
		this.removeBiome(BiomeGenBase.taiga);
		this.removeBiome(BiomeGenBase.swampland);
		this.removeBiome(BiomeGenBase.jungle);
	}
}
