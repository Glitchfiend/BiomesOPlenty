package biomesoplenty;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.MathHelper;
import biomesoplenty.api.BOPItems;
import biomesoplenty.blocks.renderers.BambooRenderer;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.GraveRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.blocks.renderers.PuddleRender;
import biomesoplenty.blocks.renderers.RenderUtils;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import biomesoplenty.configuration.BOPConfigurationIDs;
import biomesoplenty.entities.EntityBird;
import biomesoplenty.entities.EntityGlob;
import biomesoplenty.entities.EntityJungleSpider;
import biomesoplenty.entities.EntityPhantom;
import biomesoplenty.entities.EntityPixie;
import biomesoplenty.entities.EntityRosester;
import biomesoplenty.entities.EntityWasp;
import biomesoplenty.entities.projectiles.EntityDart;
import biomesoplenty.entities.projectiles.EntityMudball;
import biomesoplenty.entities.render.RenderBird;
import biomesoplenty.entities.render.RenderDart;
import biomesoplenty.entities.render.RenderGlob;
import biomesoplenty.entities.render.RenderJungleSpider;
import biomesoplenty.entities.render.RenderPhantom;
import biomesoplenty.entities.render.RenderPixie;
import biomesoplenty.entities.render.RenderRosester;
import biomesoplenty.entities.render.RenderWasp;
import biomesoplenty.particles.EntityDandelionFX;
import biomesoplenty.particles.EntityMagicTreeFX;
import biomesoplenty.particles.EntityPixieTrailFX;
import biomesoplenty.particles.EntitySteamFX;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	/*public static Minecraft mc = Minecraft.getMinecraft();
	public static int puddleRenderPass;

	@Override
	public void registerRenderers()
	{
		RenderUtils.foliageModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.plantsModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.puddleModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.bonesModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.graveModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.bambooModel = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());

		if (BOPConfigurationIDs.globID > 0)
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityGlob.class, new RenderGlob(new ModelSlime(16), new ModelSlime(0), 0.25F));
		}
		
		if (BOPConfigurationIDs.jungleSpiderID > 0)
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityJungleSpider.class, new RenderJungleSpider());
		}
		
		if (BOPConfigurationIDs.rosesterID > 0)
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityRosester.class, new RenderRosester(new ModelChicken(), 0.3F));
		}
		
		if (BOPConfigurationIDs.phantomID > 0)
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, new RenderPhantom());
		}

		if (BOPConfigurationIDs.waspID > 0)
		{
		    RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp());
		}
		
		if (BOPConfigurationIDs.birdID > 0)
		{
		    RenderingRegistry.registerEntityRenderingHandler(EntityBird.class, new RenderBird());
		}
		
		if (BOPConfigurationIDs.pixieID > 0)
		{
		    RenderingRegistry.registerEntityRenderingHandler(EntityPixie.class, new RenderPixie());
		}

		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
		RenderingRegistry.registerBlockHandler(new SmallBlockRenderer());
		RenderingRegistry.registerBlockHandler(new PuddleRender());
		RenderingRegistry.registerBlockHandler(new GraveRenderer());
		RenderingRegistry.registerBlockHandler(new BambooRenderer());
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;
		
		Random rand = new Random();

		if (string == "mud") 
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get());
		} 
		else if (string == "dart") 
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), 0);
		} 
		else if (string == "poisondart") 
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), 1);
		} 
		else if (string == "dandelion") 
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
		}

		mc.effectRenderer.addEffect(entityfx);
	}
	
	public static double generatRandomPositiveNegitiveValue(double max, double min) 
	{
	    Random rand = new Random();
	    double d = -min + (Math.random() * ((max - (-min)) + 1));
	    return d;
	}

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}*/
}