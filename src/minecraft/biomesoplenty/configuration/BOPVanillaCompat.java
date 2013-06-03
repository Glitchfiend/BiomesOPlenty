package biomesoplenty.configuration;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.items.projectiles.DispenserBehaviorDart;
import biomesoplenty.items.projectiles.DispenserBehaviorMudball;

public class BOPVanillaCompat {

	public static void init()
	{
		// Dispenser behavior for mud balls
		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.mudball.get(), new DispenserBehaviorMudball());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.dart.get(), new DispenserBehaviorDart());

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

		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.bopDisc.get()), 1, 1, 2));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 10), 1, 2, 5));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 11), 1, 2, 5));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 12), 1, 2, 5));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 13), 1, 2, 5));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 14), 1, 2, 5));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 15), 1, 2, 5));

		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 1), 2, 8, 25));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 10), 1, 3, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 11), 1, 3, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 12), 1, 3, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 13), 1, 3, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 14), 1, 3, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 15), 1, 3, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.plants.get(),1,5), 4, 6, 15));

		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,3), 1, 4, 25));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,2), 1, 4, 25));

		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,3), 1, 4, 25));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,2), 1, 4, 25));

		village.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.plants.get(),1,5), 2, 6, 25));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 1), 2, 8, 25));
	}
}
