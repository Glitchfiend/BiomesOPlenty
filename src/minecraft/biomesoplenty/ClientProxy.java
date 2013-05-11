package biomesoplenty;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.world.World;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityMudball;
import biomesoplenty.items.projectiles.RenderDart;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	  public static Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart()); 
		
		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
	}

	@Override
	public void spawnMud(World world, double x, double y, double z, double xVel, double yVel, double zVel) 
	{
        EntityFX entityfx = null;
        
        entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get(), mc.renderEngine);
		mc.effectRenderer.addEffect(entityfx);
	} 
	
	@Override
	public void spawnDart(World world, double x, double y, double z, double xVel, double yVel, double zVel) 
	{
        EntityFX entityfx = null;
        
        entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), mc.renderEngine);
		mc.effectRenderer.addEffect(entityfx);
	}   

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}