package biomesoplenty.common.world;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.Loader;

public class WorldProviderBOPEnd extends WorldProviderEnd
{
    @Override
	public void registerWorldChunkManager()
    {
		this.worldChunkMgr = new WorldChunkManagerBOPEnd();
        this.dimensionId = 1;
        this.hasNoSky = true;
    }

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderBOPEnd(this.worldObj, this.worldObj.getSeed());
	}
}