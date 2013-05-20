package forestry.api.core;

import net.minecraft.item.ItemStack;

public interface IGameMode {

	/**
	 * @return Human-readable identifier for the game mode. (i.e. 'EASY', 'NORMAL', 'HARD')
	 */
	String getIdentifier();

	/**
	 * @param ident Identifier for the setting. (See the gamemode config.)
	 * @return
	 */
	boolean getBooleanSetting(String ident);
	
	/**
	 * @param ident Identifier for the setting. (See the gamemode config.)
	 * @return
	 */
	int getIntegerSetting(String ident);

	/**
	 * @param ident Identifier for the setting. (See the gamemode config.)
	 * @return
	 */
	float getFloatSetting(String ident);

	/**
	 * @param ident Identifier for the setting. (See the gamemode config.)
	 * @return
	 */
	ItemStack getStackSetting(String ident);

}
