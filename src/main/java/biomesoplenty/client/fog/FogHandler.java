package biomesoplenty.client.fog;

import biomesoplenty.common.configuration.BOPConfigurationMisc;
import com.sinthoras.hydroenergy.api.IHEHasCustomMaterialCalculation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
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

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FogHandler 
{
	private static final String hydroenergy = "hydroenergy";
	private static final boolean isHydroEnergyLoaded = Loader.isModLoaded(hydroenergy);

	@SubscribeEvent
	public void onGetFogColour(FogColors event)
	{
		if (!BOPConfigurationMisc.fogColors)
		{
			return;
		}

		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = player.worldObj;

			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);

			Block blockAtEyes = ActiveRenderInfo.getBlockAtEntityViewpoint(world, event.entity, (float)event.renderPartialTicks);
			if (blockAtEyes.getMaterial() == Material.lava)
			{
				return;
			}

			Vec3 mixedColor;
			if (getMaterialWrapper(event) == Material.water)
			{
				mixedColor = getFogBlendColorWater(world, player, x, y, z, event.renderPartialTicks);
			}
			else
			{
				mixedColor = getFogBlendColour(world, player, x, y, z, event.red, event.green, event.blue, event.renderPartialTicks);
			}

			event.red = (float)mixedColor.xCoord;
			event.green = (float)mixedColor.yCoord;
			event.blue = (float)mixedColor.zCoord;
		}
	}

	private Material getMaterialWrapper(EntityViewRenderEvent.FogColors event) {
		if(isHydroEnergyLoaded) {
			return getMaterialHEWrapper(event);
		}
		else {
			return event.block.getMaterial();
		}
	}

	@Optional.Method(modid = hydroenergy)
	// Only required for '== Material.water' checks
	private Material getMaterialHEWrapper(EntityViewRenderEvent.FogColors event) {
		if(event.block instanceof IHEHasCustomMaterialCalculation) {
			return ((IHEHasCustomMaterialCalculation)event.block).getMaterial(event.entity.posY + event.entity.getEyeHeight());
		}
		else {
			return event.block.getMaterial();
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
    		renderFog(event.fogMode, fogFarPlaneDistance, 0.75f);
        	return;
        }
        
        fogInit = true;
        
        int distance = 20;
        
        float fpDistanceBiomeFog = 0F;
		float weightBiomeFog = 0;
        
		for (int x = -distance; x <= distance; ++x)
		{
			for (int z = -distance; z <= distance; ++z)
			{
				BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
				if (biome instanceof IBiomeFog)
                {
					float distancePart = ((IBiomeFog)biome).getFogDensity(playerX + x, playerY, playerZ + z);
					float weightPart = 1;

					if (x == -distance)
					{
						double xDiff = 1 - (entity.posX - playerX);
						distancePart *= xDiff;
						weightPart *= xDiff;
					}
					else if (x == distance)
					{
						double xDiff = (entity.posX - playerX);
						distancePart *= xDiff;
						weightPart *= xDiff;
					}

					if (z == -distance)
					{
						double zDiff = 1 - (entity.posZ - playerZ);
						distancePart *= zDiff;
						weightPart *= zDiff;
					}
					else if (z == distance)
					{
						double zDiff = (entity.posZ - playerZ);
						distancePart *= zDiff;
						weightPart *= zDiff;
					}

					fpDistanceBiomeFog += distancePart;
					weightBiomeFog += weightPart;
                }
			}
		}

		float weightMixed = (distance * 2) * (distance * 2);
		float weightDefault = weightMixed - weightBiomeFog;

		float fpDistanceBiomeFogAvg = (weightBiomeFog == 0) ? 0 : fpDistanceBiomeFog / weightBiomeFog;

		float farPlaneDistance = (fpDistanceBiomeFog * 240 + event.farPlaneDistance * weightDefault) / weightMixed;
		float farPlaneDistanceScaleBiome = (0.1f * (1 - fpDistanceBiomeFogAvg) + 0.75f * fpDistanceBiomeFogAvg);
		float farPlaneDistanceScale = (farPlaneDistanceScaleBiome * weightBiomeFog + 0.75f * weightDefault) / weightMixed;

		fogX = entity.posX;
		fogZ = entity.posZ;
        fogFarPlaneDistance = Math.min(farPlaneDistance, event.farPlaneDistance);

		renderFog(event.fogMode, fogFarPlaneDistance, farPlaneDistanceScale);
	}
	
	private static void renderFog(int fogMode, float farPlaneDistance, float farPlaneDistanceScale)
	{
        if (fogMode < 0)
        {
            GL11.glFogf(GL11.GL_FOG_START, 0.0F);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance);
        }
        else
        {
            GL11.glFogf(GL11.GL_FOG_START, farPlaneDistance * farPlaneDistanceScale);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance);
        }
	}

	private static Vec3 postProcessColor (World world, EntityLivingBase player, float r, float g, float b, double renderPartialTicks)
	{
		double darkScale = (player.lastTickPosY + (player.posY - player.lastTickPosY) * renderPartialTicks) * world.provider.getVoidFogYFactor();

		if (player.isPotionActive(Potion.blindness))
		{
			int duration = player.getActivePotionEffect(Potion.blindness).getDuration();
			darkScale *= (duration < 20) ? (1 - duration / 20f) : 0;
		}

		if (darkScale < 1)
		{
			darkScale = (darkScale < 0) ? 0 : darkScale * darkScale;
			r *= darkScale;
			g *= darkScale;
			b *= darkScale;
		}

		if (player.isPotionActive(Potion.nightVision))
		{
			// Get night vision brightness, accounting for wavering at end of potion effect
			int duration = player.getActivePotionEffect(Potion.nightVision).getDuration();
			float brightness = (duration > 200) ? 1 : 0.7f + MathHelper.sin((float)((duration - renderPartialTicks) * Math.PI * 0.2f)) * 0.3f;

			// Find scale to bring r, g, or b to 1.0
			// Vanilla will actually set the colors to +Infinity if all components are 0, explaining the terrible
			// interaction between the blindness and night vision potion effects.
			float scale = 1 / r;
			scale = Math.min(scale, 1 / g);
			scale = Math.min(scale, 1 / b);

			r = r * (1 - brightness) + r * scale * brightness;
			g = g * (1 - brightness) + g * scale * brightness;
			b = b * (1 - brightness) + b * scale * brightness;
		}

		if (Minecraft.getMinecraft().gameSettings.anaglyph)
		{
			float aR = (r * 30 + g * 59 + b * 11) / 100;
			float aG = (r * 30 + g * 70) / 100;
			float aB = (r * 30 + b * 70) / 100;

			r = aR;
			g = aG;
			b = aB;
		}

		return Vec3.createVectorHelper(r, g, b);
	}

	private static Vec3 getFogBlendColorWater (World world, EntityLivingBase playerEntity, int playerX, int playerY, int playerZ, double renderPartialTicks)
	{
		int distance = 2;
		float rBiomeFog = 0;
		float gBiomeFog = 0;
		float bBiomeFog = 0;

		for (int x = -distance; x <= distance; ++x)
		{
			for (int z = -distance; z <= distance; ++z)
			{
				BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
				int waterColorMult = biome.waterColorMultiplier;

				float rPart = (waterColorMult & 0xFF0000) >> 16;
				float gPart = (waterColorMult & 0x00FF00) >> 8;
				float bPart = waterColorMult & 0x0000FF;

				if (x == -distance)
				{
					double xDiff = 1 - (playerEntity.posX - playerX);
					rPart *= xDiff;
					gPart *= xDiff;
					bPart *= xDiff;
				}
				else if (x == distance)
				{
					double xDiff = playerEntity.posX - playerX;
					rPart *= xDiff;
					gPart *= xDiff;
					bPart *= xDiff;
				}

				if (z == -distance)
				{
					double zDiff = 1 - (playerEntity.posZ - playerZ);
					rPart *= zDiff;
					gPart *= zDiff;
					bPart *= zDiff;
				}
				else if (z == distance)
				{
					double zDiff = playerEntity.posZ - playerZ;
					rPart *= zDiff;
					gPart *= zDiff;
					bPart *= zDiff;
				}

				rBiomeFog += rPart;
				gBiomeFog += gPart;
				bBiomeFog += bPart;
			}
		}

		rBiomeFog /= 255f;
		gBiomeFog /= 255f;
		bBiomeFog /= 255f;

		float weight = (distance * 2) * (distance * 2);
		float respirationLevel = (float)EnchantmentHelper.getRespiration(playerEntity) * 0.2F;

		float rMixed = (rBiomeFog * 0.02f + respirationLevel) / weight;
		float gMixed = (gBiomeFog * 0.02f + respirationLevel) / weight;
		float bMixed = (bBiomeFog * 0.2f + respirationLevel) / weight;

		return postProcessColor(world, playerEntity, rMixed, gMixed, bMixed, renderPartialTicks);
	}

	private static Vec3 getFogBlendColour(World world, EntityLivingBase playerEntity, int playerX, int playerY, int playerZ, float defR, float defG, float defB, double renderPartialTicks)
	{
		GameSettings settings = Minecraft.getMinecraft().gameSettings;
		int[] ranges = ForgeModContainer.blendRanges;
		int distance = 6;
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

		if (weightBiomeFog == 0 || distance == 0)
		{
			return Vec3.createVectorHelper(defR, defG, defB);
		}

		rBiomeFog /= 255f;
		gBiomeFog /= 255f;
		bBiomeFog /= 255f;

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

		// Apply post-processing to BiomeFog component.  Default color was already processed by Vanilla.
		rBiomeFog *= rScale / weightBiomeFog;
		gBiomeFog *= gScale / weightBiomeFog;
		bBiomeFog *= bScale / weightBiomeFog;

		Vec3 processedColor = postProcessColor(world, playerEntity, rBiomeFog, gBiomeFog, bBiomeFog, renderPartialTicks);
		rBiomeFog = (float)processedColor.xCoord;
		gBiomeFog = (float)processedColor.yCoord;
		bBiomeFog = (float)processedColor.zCoord;

		// Mix default fog component with BiomeFog component
		float weightMixed = (distance * 2) * (distance * 2);
		float weightDefault = weightMixed - weightBiomeFog;

		processedColor.xCoord = (rBiomeFog * weightBiomeFog + defR * weightDefault) / weightMixed;
		processedColor.yCoord = (gBiomeFog * weightBiomeFog + defG * weightDefault) / weightMixed;
		processedColor.zCoord = (bBiomeFog * weightBiomeFog + defB * weightDefault) / weightMixed;

		return processedColor;
	}
}
