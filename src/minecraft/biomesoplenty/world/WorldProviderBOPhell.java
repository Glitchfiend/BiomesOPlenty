package biomesoplenty.world;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import biomesoplenty.api.Biomes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderBOPhell extends WorldProvider
{
	@Override
	public void registerWorldChunkManager()
	{
		if (Biomes.netherGarden.isPresent() || Biomes.netherDesert.isPresent() || Biomes.netherLava.isPresent() || Biomes.netherBone.isPresent())
		{
			worldChunkMgr = new WorldChunkManagerBOPhell(worldObj);
		}
		isHellWorld = true;
		hasNoSky = true;
		dimensionId = -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2)
	{
		return worldObj.getWorldVec3Pool().getVecFromPool(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
	}

	@Override
	protected void generateLightBrightnessTable()
	{
		float f = 0.1F;

		for (int i = 0; i <= 15; ++i)
		{
			float f1 = 1.0F - i / 15.0F;
			lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		return false;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3)
	{
		return 0.5F;
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2)
	{
		return true;
	}

	@Override
	public String getDimensionName()
	{
		return "Nether";
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderBOPhell(worldObj, worldObj.getSeed());
	}
}