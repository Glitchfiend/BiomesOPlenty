package tdwp_ftw.biomesop;

import tdwp_ftw.biomesop.declarations.BOPItems;
import tdwp_ftw.biomesop.items.projectiles.EntityMudball;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

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
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}