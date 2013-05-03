package forestry.api.core;

/**
 * Plugins get loaded at the beginning of Forestry's ModsLoaded() if isAvailable() returns true.
 * 
 * @author SirSengir
 */
public interface IPlugin {
	public boolean isAvailable();

	public void preInit();

	public void doInit();

	public void postInit();

}
