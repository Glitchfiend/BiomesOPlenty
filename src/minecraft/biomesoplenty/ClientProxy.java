package biomesoplenty;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.items.projectiles.EntityMudball;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	  public static Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerRenderers() 
	{
		MinecraftForgeClient.preloadTexture(ARMOR_MUD1_PNG);
		MinecraftForgeClient.preloadTexture(ARMOR_MUD2_PNG);
		MinecraftForgeClient.preloadTexture(ARMOR_AMETHYST1_PNG);
		MinecraftForgeClient.preloadTexture(ARMOR_AMETHYST2_PNG);

		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.miscItems.get(), 0)); 
		
		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
	}

	@Override
	public void spawnMud(World world, double x, double y, double z, double xVel, double yVel, double zVel) 
	{
        EntityFX entityfx = null;
        
        entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.miscItems.get(), mc.renderEngine);
		mc.effectRenderer.addEffect(entityfx);
	}   

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}