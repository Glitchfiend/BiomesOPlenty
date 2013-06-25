package biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.RenderBlockFluid;

import org.lwjgl.opengl.GL11;

import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.AltarRenderer;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.blocks.renderers.PuddleRender;
import biomesoplenty.blocks.renderers.RenderUtils;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import biomesoplenty.entities.EntityGlob;
import biomesoplenty.entities.RenderGlob;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityMudball;
import biomesoplenty.items.projectiles.RenderDart;
import biomesoplenty.particles.EntityDandelionFX;
import biomesoplenty.particles.EntitySteamFX;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerRenderers()
	{
		RenderUtils.altarModel = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
		//RenderingRegistry.registerEntityRenderingHandler(EntityPoisonDart.class, new RenderPoisonDart());

		RenderingRegistry.registerEntityRenderingHandler(EntityGlob.class, new RenderGlob(new ModelSlime(16), new ModelSlime(0), 0.25F));

		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
		RenderingRegistry.registerBlockHandler(new SmallBlockRenderer());
		RenderingRegistry.registerBlockHandler(new AltarRenderer());
		RenderingRegistry.registerBlockHandler(new PuddleRender());

		//TODO: Remove upon Fluid API being integrated into Forge
		FluidRegistry.renderIdFluid = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderBlockFluid.instance);
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;

		if (string == "mud") {
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get(), mc.renderEngine);
		} else if (string == "dart") {
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), mc.renderEngine);
		} else if (string == "dandelion") {
			entityfx = new EntityDandelionFX(mc.theWorld, x, y, z, 2.0F);
		} else if (string == "steam") {
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