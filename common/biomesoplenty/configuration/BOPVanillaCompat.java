package biomesoplenty.configuration;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.entities.projectiles.DispenserBehaviorDart;
import biomesoplenty.entities.projectiles.DispenserBehaviorMudball;

public class BOPVanillaCompat {

	public static void init()
	{
		// Dispenser behavior for mud balls
		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.mudball.get(), new DispenserBehaviorMudball());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.dart.get(), new DispenserBehaviorDart());

		ChestGenHooks desertTemple;
		ChestGenHooks dungeon;
		ChestGenHooks jungleTemple;
		ChestGenHooks mineshaft;		
		ChestGenHooks strongholdCorridor;
		ChestGenHooks strongholdCrossing;
		ChestGenHooks strongholdLibrary;
		ChestGenHooks village;

		desertTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
		dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		jungleTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
		mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		strongholdLibrary = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
		village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);

		if (BOPConfigurationMisc.dungeonLoot == true)
		{
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.colorizedSaplings.get(),1,6), 1, 1, 1));

			mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 1), 2, 8, 25));
			mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.plants.get(),1,5), 4, 6, 15));

			strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,3), 1, 4, 25));
			strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,2), 1, 4, 25));

			strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,3), 1, 4, 25));
			strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.flowers.get(),1,2), 1, 4, 25));
			
			desertTemple.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.colorizedSaplings.get(),1,6), 1, 1, 1));
			
			jungleTemple.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.colorizedSaplings.get(),1,6), 1, 1, 1));

			village.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.plants.get(),1,5), 2, 6, 25));
			village.addItem(new WeightedRandomChestContent(new ItemStack(Items.miscItems.get(), 1, 1), 2, 8, 25));
			village.addItem(new WeightedRandomChestContent(new ItemStack(Items.wadingBoots.get(), 1, 0), 1, 1, 5));
			village.addItem(new WeightedRandomChestContent(new ItemStack(Items.flippers.get(), 1, 0), 1, 1, 5));
		}
	}
}
