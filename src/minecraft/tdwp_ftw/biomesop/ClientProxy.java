package tdwp_ftw.biomesop;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import tdwp_ftw.biomesop.items.projectiles.EntityMudball;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
            MinecraftForgeClient.preloadTexture(ARMOR_MUD1_PNG);
            MinecraftForgeClient.preloadTexture(ARMOR_MUD2_PNG);
			MinecraftForgeClient.preloadTexture(ARMOR_AMETHYST1_PNG);
            MinecraftForgeClient.preloadTexture(ARMOR_AMETHYST2_PNG);
            
            RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(mod_BiomesOPlenty.mudBall));
    }
    
    @Override
    public int addArmor(String armor)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }
}