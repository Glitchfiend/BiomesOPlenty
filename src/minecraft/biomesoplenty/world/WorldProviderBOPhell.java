package biomesoplenty.world;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import biomesoplenty.api.Biomes;

public class WorldProviderBOPhell extends WorldProviderHell
{
    public void registerWorldChunkManager()
    {
		if (Biomes.netherGarden.isPresent() || Biomes.netherDesert.isPresent() || Biomes.netherLava.isPresent() || Biomes.netherBone.isPresent())
		{
			this.worldChunkMgr = new WorldChunkManagerBOPhell(worldObj);
		}
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderBOPhell(this.worldObj, this.worldObj.getSeed());
	}
}