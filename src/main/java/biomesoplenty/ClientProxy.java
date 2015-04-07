package biomesoplenty;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.client.particles.EntityDandelionFX;
import biomesoplenty.client.particles.EntityFlowerScatterFX;
import biomesoplenty.client.particles.EntityMagicTreeFX;
import biomesoplenty.client.particles.EntityPixieTrailFX;
import biomesoplenty.client.particles.EntitySteamFX;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.client.render.blocks.BambooRenderer;
import biomesoplenty.client.render.blocks.ColorizedLeavesRenderer;
import biomesoplenty.client.render.blocks.FoliageRenderer;
import biomesoplenty.client.render.blocks.GraveRenderer;
import biomesoplenty.client.render.blocks.PlantsRenderer;
import biomesoplenty.client.render.blocks.RenderNewGrass;
import biomesoplenty.client.render.blocks.SmallBlockRenderer;
import biomesoplenty.client.render.entities.RenderBird;
import biomesoplenty.client.render.entities.RenderGlob;
import biomesoplenty.client.render.entities.RenderJungleSpider;
import biomesoplenty.client.render.entities.RenderPhantom;
import biomesoplenty.client.render.entities.RenderPixie;
import biomesoplenty.client.render.entities.RenderRosester;
import biomesoplenty.client.render.entities.RenderWasp;
import biomesoplenty.client.render.entities.projectiles.RenderDart;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.entities.EntityBird;
import biomesoplenty.common.entities.EntityGlob;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.entities.EntityPhantom;
import biomesoplenty.common.entities.EntityPixie;
import biomesoplenty.common.entities.EntityRosester;
import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.common.entities.projectiles.EntityMudball;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	public static Minecraft minecraft = Minecraft.getMinecraft();
	
	//Client Only
	@Override
	public void registerRenderers()
	{
        RenderUtils.foliageModel = RenderingRegistry.getNextAvailableRenderId();
        RenderUtils.plantsModel = RenderingRegistry.getNextAvailableRenderId();
        RenderUtils.bonesModel = RenderingRegistry.getNextAvailableRenderId();
        RenderUtils.graveModel = RenderingRegistry.getNextAvailableRenderId();
        RenderUtils.bambooModel = RenderingRegistry.getNextAvailableRenderId();
        RenderUtils.newGrassModel = RenderingRegistry.getNextAvailableRenderId();
        RenderUtils.leavesModel = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(BOPCItems.mudball, 0));
		
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
        RenderingRegistry.registerBlockHandler(new GraveRenderer());
        RenderingRegistry.registerBlockHandler(new BambooRenderer());
        RenderingRegistry.registerBlockHandler(new RenderNewGrass());
        RenderingRegistry.registerBlockHandler(new ColorizedLeavesRenderer());
	}
	
	@Override
	public void spawnParticle(String string, double x, double y, double z, Object... args)
	{
		EntityFX entityfx = null;
		
		Random rand = new Random();

		if (string == "mud") 
		{
			entityfx = new EntityBreakingFX(minecraft.theWorld, x, y, z, BOPCItems.mudball);
		} 
		else if (string == "dart") 
		{
			entityfx = new EntityBreakingFX(minecraft.theWorld, x, y, z, BOPCItems.dart, 0);
		} 
		else if (string == "poisondart") 
		{
			entityfx = new EntityBreakingFX(minecraft.theWorld, x, y, z, BOPCItems.dart, 1);
		} 
		else if (string == "dandelion") 
		{
			entityfx = new EntityDandelionFX(minecraft.theWorld, x, y, z, 2.0F);
		} 
		else if (string == "steam") 
		{
			entityfx = new EntitySteamFX(minecraft.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		else if (string == "magictree") 
		{
			entityfx = new EntityMagicTreeFX(minecraft.theWorld, x, y, z, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03), -0.02D, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03));
		}
		else if (string == "pixietrail") 
		{
			entityfx = new EntityPixieTrailFX(minecraft.theWorld, x, y, z, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03), -0.02D, MathHelper.getRandomDoubleInRange(rand, -0.03, 0.03));
		}
		else if (string == "flowerscatter")
		{
			entityfx = new EntityFlowerScatterFX(minecraft.theWorld, x, y, z, (IIcon)args[0]);
		}

		minecraft.effectRenderer.addEffect(entityfx);
	}
	
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}