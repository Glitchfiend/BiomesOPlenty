/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.mail;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

public interface IPostRegistry {

	/* POST OFFICE */
	IPostOffice getPostOffice(World world);

	/* MAIL ADDRESSES */
	IMailAddress getMailAddress(GameProfile gameProfile);
	IMailAddress getMailAddress(String traderName);

	/* LETTERS */
	boolean isLetter(ItemStack itemstack);

	ILetter createLetter(IMailAddress sender, IMailAddress recipient);

	ILetter getLetter(ItemStack itemstack);

	ItemStack createLetterStack(ILetter letter);

	/* CARRIERS */
	/**
	 * Registers a new {@link IPostalCarrier}. See {@link IPostalCarrier} for details.
	 * @param carrier {@link IPostalCarrier} to register.
	 */
	void registerCarrier(IPostalCarrier carrier);

	IPostalCarrier getCarrier(EnumAddressee uid);

	Map<EnumAddressee, IPostalCarrier> getRegisteredCarriers();

	/* TRADE STATIONS */
	void deleteTradeStation(World world, IMailAddress address);

	ITradeStation getOrCreateTradeStation(World world, GameProfile owner, IMailAddress address);

	ITradeStation getTradeStation(World world, IMailAddress address);

	boolean isAvailableTradeAddress(World world, IMailAddress address);

	boolean isValidTradeAddress(World world, IMailAddress address);

	/* PO BOXES */
	boolean isValidPOBox(World world, IMailAddress address);

}
