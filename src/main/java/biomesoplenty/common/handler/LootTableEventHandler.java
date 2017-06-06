package biomesoplenty.common.handler;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableEventHandler
{
	@SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event)
    {
		if (event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE))
		{
            LootPool main = event.getTable().getPool("main");
            if (main != null)
            {
                main.addEntry(new LootEntryItem(BOPItems.ash, 3, 6, new LootFunction[0], new LootCondition[0], "biomesoplenty:ash"));
                main.addEntry(new LootEntryItem(BOPItems.fleshchunk, 5, 5, new LootFunction[0], new LootCondition[0], "biomesoplenty:fleshchunk"));
            }
		}
		if (event.getName().equals(LootTableList.CHESTS_SPAWN_BONUS_CHEST))
		{
            LootPool main = event.getTable().getPool("main");
            if (main != null)
            {
            	main.addEntry(new LootEntryItem(BOPItems.pear, 4, 2, new LootFunction[0], new LootCondition[0], "biomesoplenty:pear"));
            	main.addEntry(new LootEntryItem(BOPItems.peach, 4, 2, new LootFunction[0], new LootCondition[0], "biomesoplenty:peach"));
            	main.addEntry(new LootEntryItem(BOPItems.persimmon, 4, 2, new LootFunction[0], new LootCondition[0], "biomesoplenty:persimmon"));
            }
		}
		if (event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH))
		{
            LootPool main = event.getTable().getPool("main");
            if (main != null)
            {
            	main.addEntry(new LootEntryItem(BOPItems.flippers, 3, 1, new LootFunction[0], new LootCondition[0], "biomesoplenty:flippers"));
            	main.addEntry(new LootEntryItem(BOPItems.wading_boots, 3, 1, new LootFunction[0], new LootCondition[0], "biomesoplenty:wading_boots"));
            }
		}
		if (event.getName().equals(LootTableList.GAMEPLAY_FISHING_JUNK))
        {
            LootPool main = event.getTable().getPool("main");
            if (main != null)
            {
                main.addEntry(new LootEntryItem(new ItemStack(BOPBlocks.seaweed).getItem(), 7, 1, new LootFunction[0], new LootCondition[0], "biomesoplenty:seaweed"));
            }
        }
    }
}