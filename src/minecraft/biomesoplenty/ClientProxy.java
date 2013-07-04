package biomesoplenty;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.AltarRenderer;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.ItemGraveRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.blocks.renderers.PuddleRender;
import biomesoplenty.blocks.renderers.RenderUtils;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import biomesoplenty.blocks.renderers.TileEntityGraveRenderer;
import biomesoplenty.entities.EntityGlob;
import biomesoplenty.entities.EntityJungleSpider;
import biomesoplenty.entities.EntityRosester;
import biomesoplenty.entities.projectiles.EntityDart;
import biomesoplenty.entities.projectiles.EntityMudball;
import biomesoplenty.entities.render.RenderDart;
import biomesoplenty.entities.render.RenderGlob;
import biomesoplenty.entities.render.RenderJungleSpider;
import biomesoplenty.entities.render.RenderRosester;
import biomesoplenty.particles.EntityDandelionFX;
import biomesoplenty.particles.EntitySteamFX;
import biomesoplenty.tileentities.TileEntityGrave;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static Minecraft mc = Minecraft.getMinecraft();
	public static int puddleRenderPass;

	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrave.class, new TileEntityGraveRenderer());
		MinecraftForgeClient.registerItemRenderer(Blocks.grave.get().blockID, new ItemGraveRenderer());
		
		RenderUtils.altarModel = RenderingRegistry.getNextAvailableRenderId();
		RenderUtils.foliageModel = RenderingRegistry.getNextAvailableRenderId();;
		RenderUtils.plantsModel = RenderingRegistry.getNextAvailableRenderId();;
		RenderUtils.puddleModel = RenderingRegistry.getNextAvailableRenderId();;
		RenderUtils.bonesModel = RenderingRegistry.getNextAvailableRenderId();;
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());

		RenderingRegistry.registerEntityRenderingHandler(EntityGlob.class, new RenderGlob(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJungleSpider.class, new RenderJungleSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityRosester.class, new RenderRosester(new ModelChicken(), 0.3F));

		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
		RenderingRegistry.registerBlockHandler(new SmallBlockRenderer());
		RenderingRegistry.registerBlockHandler(new AltarRenderer());
		RenderingRegistry.registerBlockHandler(new PuddleRender());
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;

		if (string == "mud") 
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get());
		} 
		else if (string == "dart") 
		{
			entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get());
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