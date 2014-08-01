package biomesoplenty.client.fog;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.common.ForgeModContainer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FogHandler 
{
	/*@SubscribeEvent
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
	}*/
	
    private static double fogX, fogZ;

    private static boolean fogInit;
    private static float fogFarPlaneDistance;
	
	@SubscribeEvent
	public void onRenderFog(RenderFogEvent event)
	{
		Entity entity = event.entity;
        World world = entity.worldObj;
        
        int playerX = MathHelper.floor_double(entity.posX);
        int playerY = MathHelper.floor_double(entity.posY);
        int playerZ = MathHelper.floor_double(entity.posZ);
        
        if (playerX == fogX && playerZ == fogZ && fogInit)
        {
    		renderFog(event.fogMode, fogFarPlaneDistance);
        	
        	return;
        }
        
        fogInit = true;
        
        int distance = 20;
        int divider = 0;
        
        float farPlaneDistance = 0F;
        
		for (int x = -distance; x <= distance; ++x)
		{
			for (int z = -distance; z <= distance; ++z)
			{
				BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
	            
				if (biome instanceof IBiomeFog)
                {
					farPlaneDistance += ((IBiomeFog)biome).getFogDensity(playerX + x, playerY, playerZ + z);
                }
                else
                {
                	farPlaneDistance += event.farPlaneDistance;
                }
				
                divider++;
			}
		}
		
		fogX = entity.posX;
		fogZ = entity.posZ;
        fogFarPlaneDistance = Math.min(farPlaneDistance / divider, event.farPlaneDistance);
		
		renderFog(event.fogMode, fogFarPlaneDistance);
	}
	
	private static void renderFog(int fogMode, float farPlaneDistance)
	{
        if (fogMode < 0)
        {
            GL11.glFogf(GL11.GL_FOG_START, 0.0F);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance);
        }
        else
        {
            GL11.glFogf(GL11.GL_FOG_START, farPlaneDistance * 0.75F);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance);
        }
	}

	/*public static int getFogBlendColour(World world, int playerX, int playerY, int playerZ, int defaultColour)
	{
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

		return multiplier;
	}*/
}
