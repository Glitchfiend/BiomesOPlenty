package biomesoplenty.client.texture;

import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.FMLFileResourcePack;
import net.minecraftforge.fml.common.ModContainer;

//TODO: Switch title panorama replacement over to using this, allows resource packs to be used normally
public class ForgeRedirectedResourcePack extends FMLFileResourcePack
{
    private IResourcePack bopResourcePack;
    
    public ForgeRedirectedResourcePack(ModContainer container) 
    {
        super(container);
        
        this.bopResourcePack = (IResourcePack)FMLClientHandler.instance().getResourcePackFor("BiomesOPlenty");
    }

    @Override
    protected InputStream getInputStreamByName(String resourceName) throws IOException
    {
        //Replace the bucket textures with our new ones
        if (resourceName.equals("assets/forge/textures/items/bucket_cover.png") || resourceName.equals("assets/forge/textures/items/bucket_base.png"))
        {
            return bopResourcePack.getInputStream(new ResourceLocation("biomesoplenty:textures/replacements/" + resourceName.substring(resourceName.indexOf("bucket"))));
        }
        
        return super.getInputStreamByName(resourceName);
    }
}
