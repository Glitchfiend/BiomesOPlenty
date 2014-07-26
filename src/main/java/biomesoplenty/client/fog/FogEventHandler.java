package biomesoplenty.client.fog;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.common.ForgeModContainer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FogEventHandler 
{
	@SubscribeEvent
	public void onGetFogDensity(FogDensity event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = player.worldObj;
			
			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);
			
			event.density = getFogDensityBlend(world, x, y, z);
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onGetFogColour(FogColors event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = player.worldObj;
			
			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);
			
			int oldColour = ((int)(event.red * 255) & 255) << 16 | ((int)(event.green * 255) & 255) << 8 | (int)(event.blue * 255) & 255;
			int colour = getFogBlendColour(world, x, y, z, oldColour);

			event.red = (colour >> 16 & 255) / 255.0F; event.green = (colour >> 8 & 255) / 255.0F; event.blue = (colour & 255) / 255.0F;
		}
	}
	
    private static int prevDensityX, prevDensityZ;

    private static boolean fogDensityInit;
    private static float fogDensityMultiplier;
	
    public static float getFogDensityBlend(World world, int playerX, int playerY, int playerZ)
    {
        if (playerX == prevDensityX && playerZ == prevDensityZ && fogDensityInit)
        {
            return fogDensityMultiplier;
        }
        fogDensityInit = true;

        int distance = 26;

        float fogDensity = 0;

        int divider = 0;
        for (int x = -distance; x <= distance; ++x)
        {
            for (int z = -distance; z <= distance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
                
                if (biome instanceof IBiomeFog) fogDensity += ((IBiomeFog)biome).getFogDensity(playerX + x, playerY, playerZ + z);
                
                divider++;
            }
        }

        float multiplier = fogDensity / divider;

        prevDensityX = playerX;
        prevDensityZ = playerZ;
        fogDensityMultiplier = multiplier;
        return fogDensityMultiplier;
    }
    
    private static int prevFogColourX, prevFogColourZ;

    private static boolean fogColourInit;
    private static int fogColourMultiplier;

    public static int getFogBlendColour(World world, int playerX, int playerY, int playerZ, int defaultColour)
    {
        if (playerX == prevFogColourX && playerZ == prevFogColourZ && fogColourInit)
        {
            return fogColourMultiplier;
        }
        fogColourInit = true;

        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        int[] ranges = ForgeModContainer.blendRanges;
        int distance = 0;
        if (settings.fancyGraphics && settings.renderDistanceChunks >= 0 && settings.renderDistanceChunks < ranges.length)
        {
            distance = ranges[settings.renderDistanceChunks];
        }

        int r = 0;
        int g = 0;
        int b = 0;

        int divider = 0;
        for (int x = -distance; x <= distance; ++x)
        {
            for (int z = -distance; z <= distance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
                
                if (biome instanceof IBiomeFog)
                {
                	IBiomeFog biomeFog = (IBiomeFog)biome;
                	int fogColour = biomeFog.getFogColour(playerX + x, playerY, playerZ + z);
                	
                    r += (fogColour & 0xFF0000) >> 16;
                    g += (fogColour & 0x00FF00) >> 8;
                    b += fogColour & 0x0000FF;
                }
                else
                {
                    r += (defaultColour & 0xFF0000) >> 16;
                    g += (defaultColour & 0x00FF00) >> 8;
                    b += defaultColour & 0x0000FF;
                }
                
                divider++;
            }
        }
        
        int multiplier = (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;

        prevFogColourX = playerX;
        prevFogColourZ = playerZ;
        fogColourMultiplier = multiplier;
        return fogColourMultiplier;
    }
}
