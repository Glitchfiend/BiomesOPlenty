package forestry.api.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForestryAPI {

	public static Object instance;

	@SideOnly(Side.CLIENT)
	public static ITextureManager textureManager;
}
