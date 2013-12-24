package biomesoplenty;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.client.render.entities.RenderDart;
import biomesoplenty.common.entities.projectiles.EntityDart;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	public static Minecraft minecraft = Minecraft.getMinecraft();
	
	@Override
	public void registerEventHandlers()
	{
		
	}
	
	//Client Only
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
	}
	
	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;
		
		Random rand = new Random();

		/*if (string == "mud") 
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get());
		} 
		else*/ if (string == "dart") 
		{
			entityfx = new EntityBreakingFX(minecraft.theWorld, x, y, z, BOPItemHelper.get("dart"), 0);
		} 
		else if (string == "poisondart") 
		{
			entityfx = new EntityBreakingFX(minecraft.theWorld, x, y, z, BOPItemHelper.get("dart"), 1);
		} 
		/*else if (string == "dandelion") 
		{
			entityfx = new EntityDandelionFX(mc.theWorld, x, y, z, 2.0F);
		} 
		else if (string == "steam") 
		{
			entityfx = new EntitySteamFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "magictree") 
		{
			entityfx = new EntityMagicTreeFX(mc.theWorld, x, y, z, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03), -0.02D, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03));
		}
		else if (string == "pixietrail") 
		{
			entityfx = new EntityPixieTrailFX(mc.theWorld, x, y, z, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03), -0.02D, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03));
		}*/

		minecraft.effectRenderer.addEffect(entityfx);
	}
}