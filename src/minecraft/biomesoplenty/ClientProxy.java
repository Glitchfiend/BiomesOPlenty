package biomesoplenty;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.RenderSnowball;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityMudball;
import biomesoplenty.items.projectiles.EntityPoisonDart;
import biomesoplenty.items.projectiles.RenderDart;
import biomesoplenty.items.projectiles.RenderPoisonDart;
import biomesoplenty.mobs.EntityGlob;
import biomesoplenty.mobs.RenderGlob;
import biomesoplenty.particles.EntityDandelionFX;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	  public static Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart()); 
		//RenderingRegistry.registerEntityRenderingHandler(EntityPoisonDart.class, new RenderPoisonDart()); 
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGlob.class, new RenderGlob(new ModelSlime(16), new ModelSlime(0), 0.25F)); 
		
		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
	}
	
	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
        EntityFX entityfx = null;
        
         if (string == "mud")
             entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get(), mc.renderEngine);
         else if (string == "dart")
             entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), mc.renderEngine);
         else if (string == "dandelion")
             entityfx = new EntityDandelionFX(mc.theWorld, x, y, z, 2.0F);
         
 		mc.effectRenderer.addEffect(entityfx);
	} 

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}