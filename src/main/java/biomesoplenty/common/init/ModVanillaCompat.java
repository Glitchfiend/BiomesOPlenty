package biomesoplenty.common.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviorDart;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviorMudball;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPTrees;
import net.minecraft.block.BlockDispenser;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

public class ModVanillaCompat
{
    public static void init()
    {
    	registerDispenserBehaviors();
    	addDungeonLoot();
    }
    
    private static void registerDispenserBehaviors()
    {
    	BlockDispenser.dispenseBehaviorRegistry.putObject(BOPItems.mudball, new DispenserBehaviorMudball());
    	BlockDispenser.dispenseBehaviorRegistry.putObject(BOPItems.dart, new DispenserBehaviorDart());
    }
    
	private static void addDungeonLoot()
	{
		//TODO: Requires Forge update
		/*ChestGenHooks desertTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
		ChestGenHooks dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		ChestGenHooks jungleTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
		ChestGenHooks mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		ChestGenHooks strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		ChestGenHooks strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		ChestGenHooks strongholdLibrary = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
		ChestGenHooks village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
		ChestGenHooks bonusChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
		ChestGenHooks netherFortress = ChestGenHooks.getInfo(ChestGenHooks.NETHER_FORTRESS);

		bonusChest.addItem(new WeightedRandomChestContent(BOPItems.peach, 0, 1, 3, 10));
		bonusChest.addItem(new WeightedRandomChestContent(BOPItems.pear, 0, 1, 3, 10));
		bonusChest.addItem(new WeightedRandomChestContent(BOPItems.persimmon, 0, 1, 3, 10));
		
		netherFortress.addItem(new WeightedRandomChestContent(BOPItems.fleshchunk, 0, 2, 6, 5));
		netherFortress.addItem(new WeightedRandomChestContent(BOPItems.honeycomb, 0, 2, 6, 5));
		netherFortress.addItem(new WeightedRandomChestContent(BOPItems.filled_honeycomb, 0, 2, 6, 3));
		
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.turnip_seeds, 0, 3, 6, 25));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.RUBY.ordinal(), 1, 4, 3));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.AMBER.ordinal(), 1, 4, 3));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.MALACHITE.ordinal(), 1, 4, 3));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.PERIDOT.ordinal(), 1, 4, 3));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.SAPPHIRE.ordinal(), 1, 4, 3));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.TANZANITE.ordinal(), 1, 4, 3));
		mineshaft.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.TOPAZ.ordinal(), 1, 4, 3));
		
		strongholdCorridor.addItem(new WeightedRandomChestContent(Item.getItemFromBlock(BOPBlocks.sapling_1), BOPTrees.SACRED_OAK.ordinal(), 1, 1, 1));
		strongholdCrossing.addItem(new WeightedRandomChestContent(Item.getItemFromBlock(BOPBlocks.sapling_1), BOPTrees.SACRED_OAK.ordinal(), 1, 1, 1));
		strongholdLibrary.addItem(new WeightedRandomChestContent(Item.getItemFromBlock(BOPBlocks.sapling_1), BOPTrees.SACRED_OAK.ordinal(), 1, 1, 1));
		
		village.addItem(new WeightedRandomChestContent(BOPItems.wading_boots, 0, 1, 1, 15));
		village.addItem(new WeightedRandomChestContent(BOPItems.flippers, 0, 1, 1, 15));
		
		desertTemple.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.RUBY.ordinal(), 2, 8, 10));
		jungleTemple.addItem(new WeightedRandomChestContent(BOPItems.gem, BOPGems.TOPAZ.ordinal(), 2, 8, 10));*/
	}
}
