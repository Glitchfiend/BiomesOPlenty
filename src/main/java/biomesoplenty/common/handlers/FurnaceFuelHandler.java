package biomesoplenty.common.handlers;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.utils.ListUtils;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuelHandler implements IFuelHandler 
{
	private static HashMap<ItemStack, Integer> fuelMap = new HashMap();
	
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
		addFuel(new ItemStack(BOPBlockHelper.get("acaciaStairs")), 300);
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
		fuelMap.put(stack, value);
	}
	
	private static int getFuelValue(ItemStack stack)
	{
		return ListUtils.getItemStackMapValue(fuelMap, stack);
	}
}
