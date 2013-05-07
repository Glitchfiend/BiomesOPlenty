package biomesoplenty.world;

import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//import tdwp_ftw.biomesop.helpers.WorldChunkManagerPromised;

public class WorldProviderPromised extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		if (Biomes.promisedLand.isPresent())
			this.worldChunkMgr = new WorldChunkManagerHell(Biomes.promisedLand.get(), 0.8F, 0.1F);
		//this.worldChunkMgr = new WorldChunkManagerPromised(worldObj);
		this.dimensionId = BOPConfiguration.promisedLandDimID;
	}
	
    /**
     * A boolean that tells if a world does not have a sky. Used in calculating weather and skylight
     */
    public boolean hasNoSky = false;
	
	public String getDimensionName() 
	{
		return "Promised Land";
	}
	
	public boolean canRespawnHere()
	{
		return false;
	}
	
    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 1.0F;
    }
	
    /**
     * the y level at which clouds are rendered.
     */
    public float getCloudHeight()
    {
        return 0.0F;
    }
	
	public boolean darkenSkyDuringRain()
    {
        return false;
    }
	
    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return var3 == Blocks.holyGrass.get().blockID;
    }

    /**
     * Gets the hard-coded portal location to use when entering this dimension.
     */
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return new ChunkCoordinates(100, 50, 0);
    }

    public int getAverageGroundLevel()
    {
        return 50;
    }
	
    public double getHorizon(World world)
    {
        return 0.6D;
    }
	
	@SideOnly(Side.CLIENT)
	
    public boolean hasVoidParticles(boolean var1)
    {
        return false;
    }

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
	
    @SideOnly(Side.CLIENT)

    /**
     * Return Vec3D with biome specific fog color
     */
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
        float var5 = 0.91764705882F;
        float var6 = 0.4F;
        var4 *= var3 * 3.94F + 0.06F;
        var5 *= var3 * 0.94F + 0.06F;
        var6 *= var3 * 0.91F + 0.09F;
		return this.worldObj.getWorldVec3Pool().getVecFromPool((double)var4, (double)var5, (double)var6);
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
		return new ChunkProviderPromised(this.worldObj, this.worldObj.getSeed());
	}
}