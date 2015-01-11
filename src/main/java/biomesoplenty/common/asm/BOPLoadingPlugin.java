/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.asm;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions("biomesoplenty.common.asm")
public class BOPLoadingPlugin implements IFMLLoadingPlugin
{
	public BOPLoadingPlugin()
	{
		MixinBootstrap.init();
		MixinEnvironment.getCurrentEnvironment().addConfiguration("mixins.biomesoplenty.json");
	}
	
    @Override
    public String[] getASMTransformerClass()
    {
	    return new String[] { MixinBootstrap.TRANSFORMER_CLASS };
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
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass()
    {
	    return null;
    }
}
