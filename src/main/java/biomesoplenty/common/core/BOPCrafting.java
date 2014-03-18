package biomesoplenty.common.core;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.handlers.FurnaceFuelHandler;
import biomesoplenty.common.recipes.BiomeEssenceRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BOPCrafting
{
    private static List frontRecipes = new ArrayList();
    
	public static void init()
	{
		FurnaceFuelHandler.setFuelValues();
		addOreRegistration();
		addCraftingRecipes();
		addSmeltingRecipes();
		
		CraftingManager.getInstance().getRecipeList().addAll(0, frontRecipes);
	}

	private static void addCraftingRecipes()
	{
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 6), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 13), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,5)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,6)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,15)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,8)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 10), new Object[] {new ItemStack(BOPBlockHelper.get("mushrooms"),1,3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 8), new Object[] {new ItemStack(BOPItemHelper.get("misc"), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 6), new Object[] {new ItemStack(BOPBlockHelper.get("mushrooms"),1,4)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 8), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 11), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 5), new Object[] {new ItemStack(BOPBlockHelper.get("flowers2"),1,5)});

		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 5), new Object[] {new ItemStack(BOPBlockHelper.get("mushrooms"),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 6), new Object[] {new ItemStack(BOPBlockHelper.get("plants"),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 7), new Object[] {new ItemStack(BOPBlockHelper.get("moss"),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 8), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,9)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("misc"), 1, 9), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("food"), 4, 3), new Object[] {new ItemStack(BOPBlockHelper.get("flowers"),1,13)});

		//Brick stairs and slabs
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("stoneSingleSlab"), 6, 0), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("redCobbleStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("redCobbleStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("redBricksStairs"), 6, 1), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("redBricksStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("redBricksStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("stoneSingleSlab"), 6, 2), new Object[] {"RRR", 'R', BOPBlockHelper.get("mudBricks")});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("mudBricksStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', BOPBlockHelper.get("mudBricks")});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("mudBricksStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', BOPBlockHelper.get("mudBricks")});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("holyCobbleStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("holyCobbleStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("holyBricksStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("holyBricksStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("stoneSingleSlab"), 6, 3), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("stoneSingleSlab"), 6, 4), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,2)});

		//Redwood
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 8), new Object[] {new ItemStack(BOPBlockHelper.get("logs3"),1,0)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"), 6, 0), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 8)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("redwoodStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 8)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("redwoodStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 8)});

		//Willow
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 9), new Object[] {new ItemStack(BOPBlockHelper.get("logs3"),1,1)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"),6,1), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 9)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("willowStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 9)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("willowStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 9)});

		//Sacred Oak
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 0), new Object[] {new ItemStack(BOPBlockHelper.get("logs1"),1,0)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,0), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 0)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("sacredoakStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 0)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("sacredoakStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 0)});

		//Fir
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 3), new Object[] {new ItemStack(BOPBlockHelper.get("logs1"),1,3)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,3), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 3)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("firStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 3)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("firStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 3)});

		//Cherry
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 1), new Object[] {new ItemStack(BOPBlockHelper.get("logs1"),1,1)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,1), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 1)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("cherryStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 1)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("cherryStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 1)});

		//Dark
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 2), new Object[] {new ItemStack(BOPBlockHelper.get("logs1"),1,2)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,2), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 2)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("darkStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 2)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("darkStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 2)});

		//Magic
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 5), new Object[] {new ItemStack(BOPBlockHelper.get("logs2"),1,1)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,5), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 5)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("magicStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 5)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("magicStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 5)});

		//Palm
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 7), new Object[] {new ItemStack(BOPBlockHelper.get("logs2"),1,3)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,7), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 7)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("palmStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 7)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("palmStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 7)});

		//Mangrove
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 6), new Object[] {new ItemStack(BOPBlockHelper.get("logs2"),1,2)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,6), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 6)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("mangroveStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 6)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("mangroveStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 6)});

		//Holy
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 4), new Object[] {new ItemStack(BOPBlockHelper.get("logs2"),1,0)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"),6,4), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"),1,4)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("holyStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"),1,4)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("holyStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"),1,4)});

		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("redRock"), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(BOPBlockHelper.get("redRock"),1,0)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("holyStone"), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(BOPBlockHelper.get("holyStone"),1,0)});

		//Pine
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 11), new Object[] {new ItemStack(BOPBlockHelper.get("logs4"), 1, 0)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"), 6, 2), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 11)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("pineStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 11)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("pineStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 11)});

		//Hellbark
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 12), new Object[] {new ItemStack(BOPBlockHelper.get("logs4"), 1, 1)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"), 6, 3), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 12)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("hellBarkStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 12)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("hellBarkStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 12)});

		//Jacaranda
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 13), new Object[] {new ItemStack(BOPBlockHelper.get("logs4"), 1, 2)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"), 6, 4), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 13)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("jacarandaStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 13)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("jacarandaStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 13)});
		
		//Mahogany
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlockHelper.get("planks"), 4, 14), new Object[] {new ItemStack(BOPBlockHelper.get("logs4"), 1, 3)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"), 6, 5), new Object[] {"RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 14)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("mahoganyStairs"), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 14)});
		addRecipeToFront(new ItemStack(BOPBlockHelper.get("mahoganyStairs"), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlockHelper.get("planks"), 1, 14)});

		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("overgrownNetherrack"), 1, 0), new Object[] {"SSS", "SNS", "SSS", 'S', Items.wheat_seeds, 'N', Blocks.netherrack});
		GameRegistry.addRecipe(new ItemStack(Blocks.wool, 1, 0), new Object[] {"CCC", "CCC", "CCC", 'C', new ItemStack(BOPBlockHelper.get("plants"), 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Items.coal, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("misc"), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("mud"), 1), new Object[] {"MM", "MM", 'M', BOPItemHelper.get("mudball")});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 3), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 5), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 7), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 3)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 9), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 4)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 11), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 5)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("gemOre"), 1, 13), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 6)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("ash"), 1), new Object[] {"AA", "AA", 'A', new ItemStack(BOPItemHelper.get("misc"), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("mudBricks"), 1), new Object[] {"MM", "MM", 'M', new ItemStack(BOPItemHelper.get("misc"), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("crystal"), 1), new Object[] {"CC", "CC", 'C', new ItemStack(BOPItemHelper.get("misc"), 1, 4)});
		//        GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("planks"), 1, 10), new Object[] {"###", "###", "###", '#', BOPBlockHelper.get("bamboo")});
		GameRegistry.addRecipe(new ItemStack(Blocks.mossy_cobblestone, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', BOPBlockHelper.get("moss"), 'C', Blocks.cobblestone});
		GameRegistry.addRecipe(new ItemStack(Blocks.stonebrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', BOPBlockHelper.get("moss"), 'S', Blocks.stonebrick});

		//Scythes
		if (BOPConfigurationMisc.scytheCrafting)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheWood"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheStone"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Blocks.cobblestone, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheIron"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.iron_ingot, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheGold"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.gold_ingot, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheDiamond"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.diamond, Character.valueOf('S'), "stickWood" }));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheWood"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheStone"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Blocks.cobblestone, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheIron"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.iron_ingot, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheGold"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.gold_ingot, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheDiamond"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.diamond, Character.valueOf('S'), "stickWood" }));	
		}
			
		//Mud Tools and Armor
		if (BOPConfigurationMisc.mudTools)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("pickaxeMud"), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), BOPItemHelper.get("mudball"), Character.valueOf('X'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("shovelMud"), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), BOPItemHelper.get("mudball"), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("swordMud"), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), BOPItemHelper.get("mudball"), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("axeMud"), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), BOPItemHelper.get("mudball"), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("hoeMud"), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), BOPItemHelper.get("mudball"), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("helmetMud"), 1), new Object [] {"###", "# #", Character.valueOf('#'), BOPItemHelper.get("mudball")});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("chestplateMud"), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), BOPItemHelper.get("mudball")});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("leggingsMud"), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), BOPItemHelper.get("mudball")});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("bootsMud"), 1), new Object [] {"# #", "# #", Character.valueOf('#'), BOPItemHelper.get("mudball")});
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheMud"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), BOPItemHelper.get("mudball"), Character.valueOf('S'), "stickWood" }));	
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheMud"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), BOPItemHelper.get("mudball"), Character.valueOf('S'), "stickWood" }));
		}
		
		//Amethyst Tools and Armor
		if (BOPConfigurationMisc.amethystTools)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("pickaxeAmethyst"), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('X'), Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("shovelAmethyst"), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('X'), Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("swordAmethyst"), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('X'), Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("axeAmethyst"), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('X'), Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("hoeAmethyst"), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('X'), Items.iron_ingot});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("helmetAmethyst"), 1), new Object [] {"###", "# #", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("chestplateAmethyst"), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("leggingsAmethyst"), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("bootsAmethyst"), 1), new Object [] {"# #", "# #", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheAmethyst"), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('S'), Items.iron_ingot}));	
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItemHelper.get("scytheAmethyst"), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), new ItemStack(BOPItemHelper.get("gems"), 1, 0), Character.valueOf('S'), Items.iron_ingot}));	
			//TODO: FEATURE <Once Fluids Are Updated> GameRegistry.addRecipe(new ItemStack(Fluids.bopBucket.get(), 1, 0), new Object[] {"XXX", "AXA", "XAX", 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
		}

		//Flower Bands
		if (BOPConfigurationMisc.flowerbandCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("flowerBand"), 1, 0), new Object [] {"CCC", "C C", "CCC", Character.valueOf('C'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("flowerBand"), 1, 1), new Object [] {"CDC", "D D", "CDC", Character.valueOf('C'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 0), Character.valueOf('D'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 5)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("flowerBand"), 1, 2), new Object [] {"CDC", "V V", "CDC", Character.valueOf('C'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 0),Character.valueOf('D'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 5), Character.valueOf('V'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 8)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("flowerBand"), 1, 3), new Object [] {"CDT", "V V", "TDC", Character.valueOf('C'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 0),Character.valueOf('D'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 5), Character.valueOf('V'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 8), Character.valueOf('T'), new ItemStack(BOPBlockHelper.get("flowers"), 1, 6)});
		}
		
		//Other
		GameRegistry.addRecipe(new ItemStack(Items.wheat, 1), new Object[] {"###", '#', new ItemStack(BOPBlockHelper.get("plants"),1,6)});

		if (BOPConfigurationMisc.staffCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 0), new Object[] {"T", "P", "H", 'T', new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 3), 'P', new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 2), 'H', new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 1)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 1), new Object[] {"ESE", "ETE", " E ", 'E', Blocks.end_stone, 'T', new ItemStack(BOPItemHelper.get("gems"), 1, 4), 'S', new ItemStack(BOPItemHelper.get("gems"), 1, 6)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 2), new Object[] {"EPE", "EEE", "EAE", 'E', Blocks.end_stone, 'P', new ItemStack(BOPItemHelper.get("gems"), 1, 2), 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 5)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 3), new Object[] {" N ", "ERE", "ETE", 'E', Blocks.end_stone, 'R', new ItemStack(BOPItemHelper.get("gems"), 1, 1), 'T', new ItemStack(BOPItemHelper.get("gems"), 1, 3), 'N', Items.nether_star});
			GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 0), new ItemStack(BOPItemHelper.get("ancientStaff"), 1, 4), new ItemStack(Items.nether_star, 1));
		}
		
		if (BOPConfigurationMisc.enderporterCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("enderporter"), 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Items.ender_eye, 'O', new ItemStack(BOPItemHelper.get("misc"), 1, 10), 'A', new ItemStack(BOPItemHelper.get("gems"), 1, 0)});
		}

		//Dart Blower
		if (BOPConfigurationMisc.dartCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("dartBlower"), 1), new Object[] {"R R", "R R", "R R", Character.valueOf('R'), new ItemStack(BOPBlockHelper.get("plants"), 1, 8)});
			GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("dart"), 4, 0), new Object[] {"T", "R", "F", Character.valueOf('T'), new ItemStack(BOPBlockHelper.get("plants"), 1, 5), Character.valueOf('R'), new ItemStack(BOPBlockHelper.get("plants"), 1, 8), Character.valueOf('F'), Items.feather});
			GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("dart"), 1, 1), new Object[] {new ItemStack(BOPItemHelper.get("jarFilled"), 1, 1), new ItemStack(BOPItemHelper.get("dart"), 1, 0)});
		}
		
		
		GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("biomeFinder"), 1, 0), new Object[] {" E ", "ERE", " E ", 'E', new ItemStack(Items.emerald, 1, 0), 'R', new ItemStack(Items.redstone, 1, 0)});
		GameRegistry.addRecipe(new BiomeEssenceRecipe());
		
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("planks"), 1, 10), new Object[] {"##", "##", '#', BOPBlockHelper.get("bamboo")});
		GameRegistry.addRecipe(new ItemStack(BOPItemHelper.get("jarEmpty"), 3, 0), new Object[] {"# #", "# #", "###", '#', Blocks.glass});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("food"), 1, 10), new Object[] {new ItemStack(BOPItemHelper.get("misc"), 1, 11), new ItemStack(Items.potionitem, 1, 0), new ItemStack(BOPBlockHelper.get("flowers2"), 1, 6), new ItemStack(BOPBlockHelper.get("coral"), 1, 3), new ItemStack(BOPBlockHelper.get("plants"), 1, 15), new ItemStack(BOPItemHelper.get("misc"), 1, 4), new ItemStack(BOPItemHelper.get("jarFilled"), 1, 0), new ItemStack(BOPItemHelper.get("food"), 1, 0), Items.sugar});
		
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("flesh"), 1, 0), new Object[] {"##", "##", '#', new ItemStack(BOPItemHelper.get("misc"), 1, 3)});
		GameRegistry.addRecipe(new ItemStack(Items.rotten_flesh, 1, 0), new Object[] {"FFF", "FPF", "FFF", 'F', new ItemStack(BOPItemHelper.get("misc"), 1, 3), 'P', new ItemStack(BOPItemHelper.get("jarFilled"), 1, 1)});

		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("bamboo"), 8), new Object [] {" #", "# ", Character.valueOf('#'), new ItemStack(BOPBlockHelper.get("planks"), 1, 10)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("bamboo"), 8), new Object [] {"# ", " #", Character.valueOf('#'), new ItemStack(BOPBlockHelper.get("planks"), 1, 10)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 0), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 1), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 2), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 3), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 4), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 9)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 5), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 11)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("gems"), 9, 6), new Object[] {new ItemStack(BOPBlockHelper.get("gemOre"), 1, 13)});

		//Bone Segments > Bonemeal
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), new Object[] {new ItemStack(BOPBlockHelper.get("bones"), 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 6, 15), new Object[] {new ItemStack(BOPBlockHelper.get("bones"), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 12, 15), new Object[] {new ItemStack(BOPBlockHelper.get("bones"), 1, 2)});
		
		//Honeycombs
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("hive"), 1, 0), new Object [] {"##", "##", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("misc"), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlockHelper.get("hive"), 1, 3), new Object [] {"##", "##", Character.valueOf('#'), new ItemStack(BOPItemHelper.get("food"), 1, 9)});
		
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("food"), 2, 1), new Object[] {new ItemStack(BOPBlockHelper.get("mushrooms"),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("jarFilled"), 1, 1), new Object[] {new ItemStack(BOPBlockHelper.get("foliage"),1,7), new ItemStack(BOPItemHelper.get("jarEmpty"),1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("food"), 1, 4), new Object[] {Items.bowl, new ItemStack(BOPItemHelper.get("food"), 1, 0), Items.apple, Items.melon});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("food"), 1, 5), new Object[] {Items.bowl, new ItemStack(BOPItemHelper.get("food"), 1, 2), Items.carrot, Items.potato});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItemHelper.get("food"), 1, 6), new Object[] {Items.bowl, new ItemStack(BOPBlockHelper.get("mushrooms"), 1, 0), new ItemStack(BOPBlockHelper.get("mushrooms"), 1, 1), new ItemStack(BOPBlockHelper.get("mushrooms"), 1, 2)});
	}

	private static void addSmeltingRecipes()
	{
		GameRegistry.addSmelting(Blocks.dirt, new ItemStack(BOPBlockHelper.get("driedDirt"), 1), 0F);
		
		GameRegistry.addSmelting(new ItemStack(BOPBlockHelper.get("redRock"), 1, 1), new ItemStack(BOPBlockHelper.get("redRock"), 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BOPBlockHelper.get("holyStone"), 1, 1), new ItemStack(BOPBlockHelper.get("holyStone"), 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BOPBlockHelper.get("plants"), 1, 12), new ItemStack(Items.dye, 1, 2), 0.2F);
		GameRegistry.addSmelting(BOPItemHelper.get("mudball"), new ItemStack(BOPItemHelper.get("misc"), 1, 0), 0F);

		GameRegistry.addSmelting(BOPBlockHelper.get("logs1"), new ItemStack(Items.coal, 1, 1), 15F);
		GameRegistry.addSmelting(BOPBlockHelper.get("logs2"), new ItemStack(Items.coal, 1, 1), 15F);
		GameRegistry.addSmelting(BOPBlockHelper.get("logs4"), new ItemStack(Items.coal, 1, 1), 15F);
		
		for (int i = 0; i < 3; ++i) 
		{
			GameRegistry.addSmelting(new ItemStack(BOPBlockHelper.get("logs3"), 1, i), new ItemStack(Items.coal, 1, 1), 15F);
		}

		GameRegistry.registerFuelHandler(new FurnaceFuelHandler());
	}

	private static void addOreRegistration()
	{
		//Ore Registration
		OreDictionary.registerOre("plankWood", new ItemStack(BOPBlockHelper.get("planks"), 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stickWood", new ItemStack(BOPBlockHelper.get("bamboo")));
		OreDictionary.registerOre("stickWood", new ItemStack(BOPBlockHelper.get("plants"), 1, 8));
		
		OreDictionary.registerOre("blockMeatRaw", new ItemStack(BOPBlockHelper.get("flesh"), 1, 0));

		OreDictionary.registerOre("dyeBlue", new ItemStack(BOPItemHelper.get("misc"), 1, 5));
		OreDictionary.registerOre("dyeBrown", new ItemStack(BOPItemHelper.get("misc"), 1, 6));
		OreDictionary.registerOre("dyeGreen", new ItemStack(BOPItemHelper.get("misc"), 1, 7));
		OreDictionary.registerOre("dyeWhite", new ItemStack(BOPItemHelper.get("misc"), 1, 8));
		OreDictionary.registerOre("dyeBlack", new ItemStack(BOPItemHelper.get("misc"), 1, 9));
		
		OreDictionary.registerOre("gemRuby", new ItemStack(BOPItemHelper.get("gems"), 1, 1));
		OreDictionary.registerOre("gemPeridot", new ItemStack(BOPItemHelper.get("gems"), 1, 2));
		OreDictionary.registerOre("gemTopaz", new ItemStack(BOPItemHelper.get("gems"), 1, 3));
		OreDictionary.registerOre("gemTanzanite", new ItemStack(BOPItemHelper.get("gems"), 1, 4));
		OreDictionary.registerOre("gemMalachite", new ItemStack(BOPItemHelper.get("gems"), 1, 5));
		OreDictionary.registerOre("gemSapphire", new ItemStack(BOPItemHelper.get("gems"), 1, 6));
		
		OreDictionary.registerOre("oreRuby", new ItemStack(BOPBlockHelper.get("gemOre"), 1, 2));
		OreDictionary.registerOre("oreTopaz", new ItemStack(BOPBlockHelper.get("gemOre"), 1, 6));
		OreDictionary.registerOre("orePeridot", new ItemStack(BOPBlockHelper.get("gemOre"), 1, 4));
		OreDictionary.registerOre("oreTanzanite", new ItemStack(BOPBlockHelper.get("gemOre"), 1, 8));
		OreDictionary.registerOre("oreMalachite", new ItemStack(BOPBlockHelper.get("gemOre"), 1, 10));
		OreDictionary.registerOre("oreSapphire", new ItemStack(BOPBlockHelper.get("gemOre"), 1, 12));

		OreDictionary.registerOre("treeSapling", new ItemStack(BOPBlockHelper.get("saplings"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(BOPBlockHelper.get("colorizedSaplings"), 1, OreDictionary.WILDCARD_VALUE));

		for (int i = 0; i <= 3; i++)
		{
			OreDictionary.registerOre("logWood", new ItemStack(BOPBlockHelper.get("logs1"), 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(BOPBlockHelper.get("logs2"), 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(BOPBlockHelper.get("logs3"), 1, i));
			if (i < 3)
			{
				OreDictionary.registerOre("logWood", new ItemStack(BOPBlockHelper.get("logs4"), 1, i));
			}
		}

		OreDictionary.registerOre("slabWood", new ItemStack(BOPBlockHelper.get("woodenSingleSlab1"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("slabWood", new ItemStack(BOPBlockHelper.get("woodenSingleSlab2"), 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("redwoodStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("willowStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("sacredoakStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("firStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("cherryStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("darkStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("magicStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("palmStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("mangroveStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("holyStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("pineStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("hellBarkStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("jacarandaStairs")));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlockHelper.get("mahoganyStairs")));

		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("colorizedLeaves1"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("colorizedLeaves2"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("leaves1"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("leaves2"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("leaves3"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("leaves4"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("appleLeaves"), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlockHelper.get("persimmonLeaves"), 1, OreDictionary.WILDCARD_VALUE));
	}
	
    public static ShapedRecipes addRecipeToFront(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (par2ArrayOfObj[i] instanceof String[])
        {
            String[] astring = (String[])((String[])par2ArrayOfObj[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (par2ArrayOfObj[i] instanceof String)
            {
                String s2 = (String)par2ArrayOfObj[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < par2ArrayOfObj.length; i += 2)
        {
            Character character = (Character)par2ArrayOfObj[i];
            ItemStack itemstack1 = null;

            if (par2ArrayOfObj[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)par2ArrayOfObj[i + 1]);
            }
            else if (par2ArrayOfObj[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)par2ArrayOfObj[i + 1], 1, 32767);
            }
            else if (par2ArrayOfObj[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)par2ArrayOfObj[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, par1ItemStack);
        
        frontRecipes.add(shapedrecipes);
        
        return shapedrecipes;
    }
}
