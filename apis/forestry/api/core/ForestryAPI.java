package forestry.api.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForestryAPI {

	/**
	 * The main mod instance for Forestry.
	 */
	public static Object instance;

	/**
	 * A {@link ITextureManager} needed for some things in the API.
	 */
	@SideOnly(Side.CLIENT)
	public static ITextureManager textureManager;

	/**
	 * The currently active {@link IGameMode}.
	 */
	public static IGameMode activeMode;
	
}
