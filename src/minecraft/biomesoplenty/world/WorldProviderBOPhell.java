package biomesoplenty.world;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import biomesoplenty.api.Biomes;
import cpw.mods.fml.common.Loader;

public class WorldProviderBOPhell extends WorldProviderHell
{
    @Override
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
		
		return new ChunkProviderBOPhell(this.worldObj, this.worldObj.getSeed());
	}
}