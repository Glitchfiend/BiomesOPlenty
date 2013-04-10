package forestry.api.fuels;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class ItemStackMap<T> extends HashMap<ItemStack, T> {

	private static final long serialVersionUID = 5383477742290646466L;

	@Override
	public boolean containsKey(Object key) {
		for (Map.Entry<ItemStack, T> entry : this.entrySet())
			if (areItemStacksEqual(entry.getKey(), key))
				return true;
		return super.containsKey(key);
	}

	@Override
	public T remove(Object key) {
		Iterator<Map.Entry<ItemStack, T>> iterator = this.entrySet().iterator();
		;
		while (iterator.hasNext()) {
			Map.Entry<ItemStack, T> entry = iterator.next();
			if (areItemStacksEqual(entry.getKey(), key))
				iterator.remove();
		}
		return super.remove(key);
	}

	@Override
	public T get(Object key) {
		for (Map.Entry<ItemStack, T> entry : this.entrySet())
			if (areItemStacksEqual(entry.getKey(), key))
				return entry.getValue();
		return super.get(key);
	}

	private boolean areItemStacksEqual(ItemStack a, Object b) {
		if (a == null || b == null)
			return false;
		if (b instanceof ItemStack)
			return ItemStack.areItemStackTagsEqual(a, (ItemStack) b) && a.isItemEqual((ItemStack) b);
		else if (b instanceof LiquidStack)
			return ItemStack.areItemStackTagsEqual(a, ((LiquidStack) b).asItemStack()) && a.isItemEqual(((LiquidStack) b).asItemStack());
		else if (b instanceof Integer)
			return ((Integer) b).equals(a.itemID);
		else if (b instanceof Item)
			return ((Item) b).itemID == a.itemID;
		return false;
	}

}
