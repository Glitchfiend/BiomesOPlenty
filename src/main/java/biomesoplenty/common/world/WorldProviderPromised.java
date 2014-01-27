package biomesoplenty.common.world;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderPromised extends WorldProvider {
	
	@Override
	public void setDimension (int dim) {
		this.dimensionId = dim;
		super.setDimension(dim);
	}
	
	@Override
	public long getSeed () {
		Long seed = super.getSeed();
		return seed;
	}
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderPromised(worldObj, worldObj.getSeed());
	}
	
	@Override
	public void registerWorldChunkManager()
	{
		if (BOPBiomeHelper.getBOPBiome("wonderousWoods") != null || BOPBiomeHelper.getBOPBiome("majesticMeadow") != null || BOPBiomeHelper.getBOPBiome("blessedBog") != null)
		{
			worldChunkMgr = new WorldChunkManagerPromised(worldObj);
		}
		dimensionId = BOPConfigurationIDs.promisedLandDimID;
	}
	
	/**
     * A message to display to the user when they transfer to this dimension.
     *
     * @return The message to be displayed
     */
    public String getWelcomeMessage()
    {
        if (this instanceof WorldProviderPromised)
        {
            return "Entering the "+getDimensionName();
        }
        return null;
    }

    /**
     * A Message to display to the user when they transfer out of this dismension.
     *
     * @return The message to be displayed
     */
    public String getDepartMessage()
    {
        if (this instanceof WorldProviderPromised)
        {
            return "Leaving the "+getDimensionName();
        }
        return null;
    }

	@Override
	public String getDimensionName() {
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
		Block var3 = worldObj.func_147474_b(par1, par2);
		return var3 == BOPBlockHelper.get("grass");
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

	//@Override
	//public IChunkProvider createChunkGenerator()
	//{
	//	return new ChunkProviderPromised(worldObj, worldObj.getSeed());
	//}
	
	//@Override
	public boolean isLightingDisabled()
	{
	    return true;
	}

    //@Override
    public Float[] getLightingMultipliers(WorldClient worldclient)
    {
        return new Float[] { 0.92F, 0.98F, 0.95F };
    }

}
