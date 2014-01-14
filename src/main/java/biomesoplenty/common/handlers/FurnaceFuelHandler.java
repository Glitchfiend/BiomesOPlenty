package biomesoplenty.common.handlers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuelHandler implements IFuelHandler 
{
	private static ArrayList<FuelValue> fuelList = new ArrayList<FuelValue>();
	
	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		return getFuelValue(fuel);
	}

	// Add Fuel rates
	public static void setFuelValues()
	{
		addFuel(new ItemStack(BOPBlockHelper.get("saplings")), 100);
		addFuel(new ItemStack(BOPBlockHelper.get("colorizedSaplings")), 100);
		
		addFuel(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1")), 150);
		addFuel(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2")), 150);
		
		addFuel(new ItemStack(BOPBlockHelper.get("redwoodStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("willowStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("firStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("sacredoakStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("cherryStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("darkStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("magicStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("palmStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("mangroveStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("holyStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("pineStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("jacarandaStairs")), 300);
		addFuel(new ItemStack(BOPBlockHelper.get("hellBarkStairs")), 300);
		
		addFuel(new ItemStack(BOPItemHelper.get("misc"), 1, 1), 400);
	}
	
	private static void addFuel(ItemStack stack, int value)
	{
		fuelList.add(new FuelValue(stack, value));
	}
	
	private static int getFuelValue(ItemStack stack)
	{
		for (FuelValue fuelValue : fuelList)
		{
			ItemStack stackToCompareTo = fuelValue.getStack();

			if (stackToCompareTo.getItem() == stack.getItem() && (stack.getItemDamage() == 32767 || stackToCompareTo.getItemDamage() == stack.getItemDamage())) return fuelValue.getValue();

			return fuelValue.getValue();
		}

		return 0;
	}

	public static class FuelValue
	{
		private ItemStack stack;
		private int value;
		
		public FuelValue(ItemStack stack, int value)
		{
			this.setStack(stack);
			this.setValue(value);
		}

		public ItemStack getStack()
		{
			return stack;
		}

		public void setStack(ItemStack stack) 
		{
			this.stack = stack;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value) 
		{
			this.value = value;
		}
	}
}
