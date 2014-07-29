package biomesoplenty.common.handlers;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import cpw.mods.fml.common.IFuelHandler;

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
		addFuel(BOPCBlocks.saplings, 100);
		addFuel(BOPCBlocks.colorizedSaplings, 100);
		
		addFuel(BOPCBlocks.woodenSingleSlab1, 150);
		addFuel(BOPCBlocks.woodenSingleSlab2, 150);
		
		addFuel(BOPCBlocks.redwoodStairs, 300);
		addFuel(BOPCBlocks.willowStairs, 300);
		addFuel(BOPCBlocks.firStairs, 300);
		addFuel(BOPCBlocks.sacredoakStairs, 300);
		addFuel(BOPCBlocks.cherryStairs, 300);
		addFuel(BOPCBlocks.darkStairs, 300);
		addFuel(BOPCBlocks.magicStairs, 300);
		addFuel(BOPCBlocks.palmStairs, 300);
		addFuel(BOPCBlocks.mangroveStairs, 300);
		addFuel(BOPCBlocks.etherealStairs, 300);
		addFuel(BOPCBlocks.pineStairs, 300);
		addFuel(BOPCBlocks.jacarandaStairs, 300);
		addFuel(BOPCBlocks.hellBarkStairs, 300);
		addFuel(BOPCBlocks.mahoganyStairs, 300);
		
		addFuel(BOPCItems.misc, 1, 400);
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
