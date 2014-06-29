package biomesoplenty.client.utils;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;

import org.apache.commons.io.FilenameUtils;

import biomesoplenty.api.BOPObfuscationHelper;
import biomesoplenty.common.utils.remote.IVersionChecker;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ExternalTextureManager 
{
	private static ExternalTextureManager instance;
	
	private final TextureManager textureManager;
	private final File bopExternalFiles;
	
	private ExternalTextureManager()
	{
		this.textureManager = Minecraft.getMinecraft().renderEngine;
		this.bopExternalFiles = new File((File)ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), BOPObfuscationHelper.fileAssets), "bopexternal");
	}
	
	public ExternalTexture retrieveExternalTexture(String url, String type, IVersionChecker versionChecker)
	{
        File file1 = new File(this.bopExternalFiles, type);
        File file2 = new File(file1, getBaseName(url));
        
        return new ExternalTexture(file2, url, versionChecker);
	}
	
	public ExternalTexture retrieveExternalTexture(String url, String type)
	{
		return this.retrieveExternalTexture(url, type, null);
	}
	
    private String getBaseName(String url)
    {
    	return FilenameUtils.getBaseName(url);
    }
	
	public static ExternalTextureManager getInstance()
	{
		return instance == null ? instance = new ExternalTextureManager() : instance;
	}
}
