package biomesoplenty.common.world;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.Loader;

public class WorldProviderBopHell extends WorldProviderHell
{
    @Override
	public void registerWorldChunkManager()
    {
		this.worldChunkMgr = new WorldChunkManagerBOPHell(worldObj);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

	@Override
	public IChunkProvider createChunkGenerator()
	{
		/*
		if (Loader.isModLoaded("Natura"))
		{
			try 
			{
				return new ChunkProviderBOPNaturaHell(this.worldObj, this.worldObj.getSeed());
			}
			catch (Exception e) 
			{
				System.out.println("[BiomesOPlenty] There was an error while integrating Natura with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
		*/
		
		return new ChunkProviderBOPHell(this.worldObj, this.worldObj.getSeed());
	}
}