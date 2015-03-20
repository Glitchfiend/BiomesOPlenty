package biomesoplenty.client.fog;

import cpw.mods.fml.common.eventhandler.Event;
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
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.common.ForgeModContainer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FogHandler 
{
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
			int colour = getFogBlendColour(world, player, x, y, z, oldColour, event.renderPartialTicks);

			event.red = (colour >> 16 & 255) / 255.0F; event.green = (colour >> 8 & 255) / 255.0F; event.blue = (colour & 255) / 255.0F;
		}
	}

    private static double fogX, fogZ;

    private static boolean fogInit;
    private static float fogFarPlaneDistance;
	
	@SubscribeEvent
	public void onRenderFog(EntityViewRenderEvent.RenderFogEvent event)
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
				float distancePart = event.farPlaneDistance;
	            
				if (biome instanceof IBiomeFog)
                {
					distancePart = ((IBiomeFog)biome).getFogDensity(playerX + x, playerY, playerZ + z);
                }

				if (x == -distance)
				{
					distancePart *= 1 - (entity.posX - playerX);
				}
				else if (x == distance)
				{
					distancePart *= (entity.posX - playerX);
				}

				if (z == -distance)
				{
					distancePart *= 1 - (entity.posZ - playerZ);
				}
				else if (z == distance)
				{
					distancePart *= (entity.posZ - playerZ);
				}

				farPlaneDistance += distancePart;
				
                divider++;
			}
		}

		// Total area calculated is actually (distance - 1)^2
		divider -= distance * 2 - 1;
		
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

	public static int getFogBlendColour(World world, Entity playerEntity, int playerX, int playerY, int playerZ, int defaultColour, double renderPartialTicks)
	{
		GameSettings settings = Minecraft.getMinecraft().gameSettings;
		int[] ranges = ForgeModContainer.blendRanges;
		int distance = 0;
		if (settings.fancyGraphics && settings.renderDistanceChunks >= 0 && settings.renderDistanceChunks < ranges.length)
		{
			distance = ranges[settings.renderDistanceChunks];
		}

		float rBiomeFog = 0;
		float gBiomeFog = 0;
		float bBiomeFog = 0;
		float weightBiomeFog = 0;

		for (int x = -distance; x <= distance; ++x)
		{
			for (int z = -distance; z <= distance; ++z)
			{
				BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);

				if (biome instanceof IBiomeFog)
				{
					IBiomeFog biomeFog = (IBiomeFog)biome;
					int fogColour = biomeFog.getFogColour(playerX + x, playerY, playerZ + z);

					float rPart = (fogColour & 0xFF0000) >> 16;
					float gPart = (fogColour & 0x00FF00) >> 8;
					float bPart = fogColour & 0x0000FF;
					float weightPart = 1;

					if (x == -distance)
					{
						double xDiff = 1 - (playerEntity.posX - playerX);
						rPart *= xDiff;
						gPart *= xDiff;
						bPart *= xDiff;
						weightPart *= xDiff;
					}
					else if (x == distance)
					{
						double xDiff = playerEntity.posX - playerX;
						rPart *= xDiff;
						gPart *= xDiff;
						bPart *= xDiff;
						weightPart *= xDiff;
					}

					if (z == -distance)
					{
						double zDiff = 1 - (playerEntity.posZ - playerZ);
						rPart *= zDiff;
						gPart *= zDiff;
						bPart *= zDiff;
						weightPart *= zDiff;
					}
					else if (z == distance)
					{
						double zDiff = playerEntity.posZ - playerZ;
						rPart *= zDiff;
						gPart *= zDiff;
						bPart *= zDiff;
						weightPart *= zDiff;
					}

					rBiomeFog += rPart;
					gBiomeFog += gPart;
					bBiomeFog += bPart;
					weightBiomeFog += weightPart;
				}
			}
		}

		// Calculate day / night / weather scale for BiomeFog component
		float celestialAngle = world.getCelestialAngle((float)renderPartialTicks);
		float baseScale = MathHelper.clamp_float(MathHelper.cos(celestialAngle * (float)Math.PI * 2.0F) * 2.0F + 0.5F, 0, 1);

		float rScale = baseScale * 0.94F + 0.06F;
		float gScale = baseScale * 0.94F + 0.06F;
		float bScale = baseScale * 0.91F + 0.09F;

		float rainStrength = world.getRainStrength((float)renderPartialTicks);
		if (rainStrength > 0) {
			rScale *= 1 - rainStrength * 0.5f;
			gScale *= 1 - rainStrength * 0.5f;
			bScale *= 1 - rainStrength * 0.4f;
		}

		float thunderStrength = world.getWeightedThunderStrength((float) renderPartialTicks);
		if (thunderStrength > 0) {
			rScale *= 1 - thunderStrength * 0.5f;
			gScale *= 1 - thunderStrength * 0.5f;
			bScale *= 1 - thunderStrength * 0.5f;
		}

		// Mix default fog component with BiomeFog component
		float rDefault = (defaultColour & 0xFF0000) >> 16;
		float gDefault = (defaultColour & 0x00FF00) >> 8;
		float bDefault = defaultColour & 0x0000FF;

		float weightMixed = (distance * 2) * (distance * 2);
		float weightDefault = weightMixed - weightBiomeFog;

		float rMixed = (rBiomeFog * rScale + rDefault * weightDefault) / weightMixed;
		float gMixed = (gBiomeFog * gScale + gDefault * weightDefault) / weightMixed;
		float bMixed = (bBiomeFog * bScale + bDefault * weightDefault) / weightMixed;

		int multiplier = ((int)rMixed & 255) << 16 | ((int)gMixed & 255) << 8 | (int)bMixed & 255;

		return multiplier;
	}
}
