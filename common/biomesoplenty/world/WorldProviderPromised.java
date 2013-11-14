package biomesoplenty.world;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderPromised extends WorldProvider
{
	public boolean hasNoSky = false;

	@Override
	public void registerWorldChunkManager()
	{
		if (Biomes.promisedLandForest.isPresent() || Biomes.promisedLandPlains.isPresent() || Biomes.promisedLandShrub.isPresent() || Biomes.promisedLandSwamp.isPresent())
		{
			worldChunkMgr = new WorldChunkManagerPromised(worldObj);
		}
		dimensionId = BOPConfigurationIDs.promisedLandDimID;
	}

	@Override
	public String getDimensionName()
	{
		return "Promised Land";
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
    public double getMovementFactor()
    {
        return 16.0;
    }

	@Override
	public float calculateCelestialAngle(long par1, float par3)
	{
		return 1.0F;
	}

	@Override
	public float getCloudHeight()
	{
		return 0.0F;
	}

	public boolean darkenSkyDuringRain()
	{
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		int var3 = worldObj.getFirstUncoveredBlock(par1, par2);
		return var3 == Blocks.holyGrass.get().blockID;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(100, 50, 0);
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 64;
	}

	@Override
	public double getHorizon()
	{
		return 0.0D;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasVoidParticles(boolean var1)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return true;
	}

	@Override
	public double getVoidFogYFactor()
	{
		return 1.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2)
	{
		float var3 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}

		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}

		float var4 = 1.0F;
		float var5 = 0.73725490196F;
		float var6 = 0.25882352941F;
		var4 *= var3 * 3.94F + 0.06F;
		var5 *= var3 * 0.94F + 0.06F;
		var6 *= var3 * 0.91F + 0.09F;
		return worldObj.getWorldVec3Pool().getVecFromPool(var4, var5, var6);
	}

	@Override
	public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
	{
		allowPeaceful = true;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Entering the Promised Land";
	}

	@Override
	public String getDepartMessage()
	{
		return "Leaving the Promised Land";
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderPromised(worldObj, worldObj.getSeed());
	}
}