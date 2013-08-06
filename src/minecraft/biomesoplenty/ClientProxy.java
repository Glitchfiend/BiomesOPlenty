package biomesoplenty;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.RenderBlockFluid;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.AltarRenderer;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.GraveRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.blocks.renderers.PuddleRender;
import biomesoplenty.blocks.renderers.RenderUtils;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import biomesoplenty.entities.EntityGlob;
import biomesoplenty.entities.projectiles.EntityDart;
import biomesoplenty.entities.projectiles.EntityMudball;
import biomesoplenty.entities.render.RenderDart;
import biomesoplenty.entities.render.RenderGlob;
import biomesoplenty.particles.EntityDandelionFX;
import biomesoplenty.particles.EntitySteamFX;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static Minecraft mc = Minecraft.getMinecraft();
	public static int puddleRenderPass;

	@Override
	public void registerRenderers()
	{
		RenderUtils.altarModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.foliageModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.plantsModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.puddleModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.bonesModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.graveModel = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());

		RenderingRegistry.registerEntityRenderingHandler(EntityGlob.class, new RenderGlob(new ModelSlime(16), new ModelSlime(0), 0.25F));

		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
		RenderingRegistry.registerBlockHandler(new SmallBlockRenderer());
		RenderingRegistry.registerBlockHandler(new AltarRenderer());
		RenderingRegistry.registerBlockHandler(new PuddleRender());
		RenderingRegistry.registerBlockHandler(new GraveRenderer());
		
		FluidRegistry.renderIdFluid = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(RenderBlockFluid.instance);
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;

		if (string == "mud") 
		{
		    entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get(), mc.renderEngine);
		} 
		else if (string == "dart") 
		{
		    entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), mc.renderEngine);
		} 
		else if (string == "poisondart") 
		{
		    entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), mc.renderEngine);
		} 
		else if (string == "dandelion") 
		{
			entityfx = new EntityDandelionFX(mc.theWorld, x, y, z, 2.0F);
		} 
		else if (string == "steam") 
		{
			entityfx = new EntitySteamFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
		}

		mc.effectRenderer.addEffect(entityfx);
	}

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}