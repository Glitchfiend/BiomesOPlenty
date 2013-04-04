package tdwp_ftw.biomesop;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
            MinecraftForgeClient.preloadTexture(ARMOR_MUD1_PNG);
            MinecraftForgeClient.preloadTexture(ARMOR_MUD2_PNG);
			MinecraftForgeClient.preloadTexture(ARMOR_AMETHYST1_PNG);
            MinecraftForgeClient.preloadTexture(ARMOR_AMETHYST2_PNG);

    }
    
    @Override
    public int addArmor(String armor)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }
}