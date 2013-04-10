package tdwp_ftw.biomesop;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import tdwp_ftw.biomesop.configuration.BOPItems;
import tdwp_ftw.biomesop.items.projectiles.EntityMudball;
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

		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(BOPItems.mudBall)); 
	}

	@Override
	public void spawnMud(World world, double x, double y, double z, double xVel, double yVel, double zVel) 
	{
        EntityFX entityfx = null;
        
        entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, BOPItems.mudBall, mc.renderEngine);
		mc.effectRenderer.addEffect(entityfx);
	}   

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}