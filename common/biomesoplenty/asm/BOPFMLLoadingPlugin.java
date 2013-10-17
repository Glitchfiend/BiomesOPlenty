package biomesoplenty.asm;

import java.io.File;
import java.util.Map;

import biomesoplenty.asm.smoothing.BOPBiomeTransitionSmoothing;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class BOPFMLLoadingPlugin implements IFMLLoadingPlugin
{
    public static File location;
    
    @Override
    public String[] getLibraryRequestClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {BOPBiomeTransitionSmoothing.class.getName()};
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
        location = (File)data.get("coremodLocation");
    }
}
