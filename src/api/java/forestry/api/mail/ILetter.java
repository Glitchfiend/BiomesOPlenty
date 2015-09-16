/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.mail;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import forestry.api.core.INBTTagable;

public interface ILetter extends IInventory, INBTTagable {

	ItemStack[] getPostage();

	void setProcessed(boolean flag);

	boolean isProcessed();

	boolean isMailable();

	void setSender(IMailAddress address);

	IMailAddress getSender();

	boolean hasRecipient();

	void setRecipient(IMailAddress address);

	IMailAddress[] getRecipients();

	String getRecipientString();

	void setText(String text);

	String getText();

	void addTooltip(List<String> list);

	boolean isPostPaid();

	int requiredPostage();

	void invalidatePostage();

	ItemStack[] getAttachments();

	void addAttachment(ItemStack itemstack);

	void addAttachments(ItemStack[] itemstacks);

	int countAttachments();

	void addStamps(ItemStack stamps);

}
