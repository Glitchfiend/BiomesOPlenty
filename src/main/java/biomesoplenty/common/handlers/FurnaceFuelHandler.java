package biomesoplenty.common.handlers;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;

public class FurnaceFuelHandler implements IFuelHandler 
{
	private static HashMap<Pair<Item, Integer>, Integer> fuelList = new HashMap<Pair<Item, Integer>, Integer>();
	
	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		return getFuelValue(fuel);
	}

	public static void setFuelValues()
	{
		addFuel(BOPBlockHelper.get("saplings"), 100);
		addFuel(BOPBlockHelper.get("colorizedSaplings"), 100);
		
		addFuel(BOPBlockHelper.get("woodenSingleSlab1"), 150);
		addFuel(BOPBlockHelper.get("woodenSingleSlab2"), 150);
		
		addFuel(BOPBlockHelper.get("redwoodStairs"), 300);
		addFuel(BOPBlockHelper.get("willowStairs"), 300);
		addFuel(BOPBlockHelper.get("firStairs"), 300);
		addFuel(BOPBlockHelper.get("sacredoakStairs"), 300);
		addFuel(BOPBlockHelper.get("cherryStairs"), 300);
		addFuel(BOPBlockHelper.get("darkStairs"), 300);
		addFuel(BOPBlockHelper.get("magicStairs"), 300);
		addFuel(BOPBlockHelper.get("palmStairs"), 300);
		addFuel(BOPBlockHelper.get("mangroveStairs"), 300);
		addFuel(BOPBlockHelper.get("holyStairs"), 300);
		addFuel(BOPBlockHelper.get("pineStairs"), 300);
		addFuel(BOPBlockHelper.get("jacarandaStairs"), 300);
		addFuel(BOPBlockHelper.get("hellBarkStairs"), 300);
		addFuel(BOPBlockHelper.get("mahoganyStairs"), 300);
		
		addFuel(BOPItemHelper.get("misc"), 1, 400);
	}
	
	private static void addFuel(Item item, int metadata, int value)
	{
        fuelList.put(Pair.of(item, metadata), value);
	}

    private static void addFuel(Item item, int value)
    {
        addFuel(item, 0, value);
    }

    private static void addFuel(Block block, int metadata, int value)
    {
        addFuel(Item.getItemFromBlock(block), metadata, value);
    }

    private static void addFuel(Block block, int value)
    {
        addFuel(Item.getItemFromBlock(block), 0, value);
    }
	
	private static int getFuelValue(ItemStack stack)
	{
        Pair<Item, Integer> pair = Pair.of(stack.getItem(), stack.getItemDamage());

        if (fuelList.containsKey(pair))
        {
            return fuelList.get(pair);
        }

        return 0;
	}
}
