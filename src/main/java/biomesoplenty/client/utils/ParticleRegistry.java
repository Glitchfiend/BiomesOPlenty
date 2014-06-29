package biomesoplenty.client.utils;

import java.util.HashMap;

import biomesoplenty.common.utils.remote.TrailsVersionChecker;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleRegistry 
{
	private ExternalTextureManager externalTextureManager;
	
	public static HashMap<String, ExternalTexture> trailMap = new HashMap<String, ExternalTexture>();
    
    public ParticleRegistry()
    {
    	this.externalTextureManager = ExternalTextureManager.getInstance();
    	
    	TrailsVersionChecker trailsVersionChecker = TrailsVersionChecker.getInstance();
    	
    	trailMap.put("dev_trail", this.externalTextureManager.retrieveExternalTexture("https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/trails/dev_trail.png", "trails", trailsVersionChecker));
    	trailMap.put("donator_trail", this.externalTextureManager.retrieveExternalTexture("https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/trails/donator_trail.png", "trails", trailsVersionChecker));
    	trailMap.put("helper_trail", this.externalTextureManager.retrieveExternalTexture("https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/trails/helper_trail.png", "trails", trailsVersionChecker));
    }
    
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event)
	{
		if (event.map.getTextureType() == 1) 
		{
			event.map.setTextureEntry("biomesoplenty:dev_trail", trailMap.get("dev_trail"));
			event.map.setTextureEntry("biomesoplenty:donator_trail", trailMap.get("donator_trail"));
			event.map.setTextureEntry("biomesoplenty:helper_trail", trailMap.get("helper_trail"));
		}
	}
}
