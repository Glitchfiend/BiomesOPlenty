package biomesoplenty.configuration;

import biomesoplenty.api.Blocks;
import biomesoplenty.items.projectiles.DispenserBehaviorMudball;
import net.minecraft.block.BlockDispenser;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class BOPVanillaCompat {
	
	public static void init()
	{
		// Dispenser behavior for mud balls
		BlockDispenser.dispenseBehaviorRegistry.putObject(BOPItems.mudBall, new DispenserBehaviorMudball());
		
		ChestGenHooks dungeon;
		ChestGenHooks mineshaft;
		ChestGenHooks strongholdCorridor;
		ChestGenHooks strongholdCrossing;
		ChestGenHooks village;
		
		dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);

		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.bopDisc), 1, 1, 2));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.moss.get()), 2, 8, 50));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));

		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.ashes), 2, 8, 25));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.plants.get(),1,5), 4, 6, 15));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mudBall), 2, 8, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));

		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.moss.get()), 2, 8, 50));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,3), 1, 4, 25));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,2), 1, 4, 25));

		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.moss.get()), 2, 8, 50));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,3), 1, 4, 25));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,2), 1, 4, 25));

//		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.barleyItem), 4, 10, 75));
		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.shroomPowder), 1, 5, 50));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.plants.get(),1,5), 2, 6, 25));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));
	}
}
