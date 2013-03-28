package tdwp_ftw.biomesop;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
        	MinecraftForgeClient.preloadTexture(BLOCK_PNG);
            MinecraftForgeClient.preloadTexture(ITEMS_PNG);
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