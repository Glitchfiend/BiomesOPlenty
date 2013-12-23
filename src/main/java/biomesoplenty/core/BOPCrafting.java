package biomesoplenty.core;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.api.BOPItems;
import biomesoplenty.configuration.BOPConfigurationMisc;
import biomesoplenty.helpers.FurnaceFuel;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPCrafting
{
    private static List frontRecipes = new ArrayList();
    
	public static void init()
	{
		addOreRegistration();
		addCraftingRecipes();
		addSmeltingRecipes();
		
		CraftingManager.getInstance().getRecipeList().addAll(0, frontRecipes);
	}

	private static void addCraftingRecipes()
	{
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 13), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,5)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,6)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,15)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,8)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 10), new Object[] {new ItemStack(BOPBlocks.mushrooms.get(),1,3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {new ItemStack(BOPItems.miscItems.get(), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 6), new Object[] {new ItemStack(BOPBlocks.mushrooms.get(),1,4)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 8), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 11), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 5), new Object[] {new ItemStack(BOPBlocks.flowers2.get(),1,5)});

		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 5), new Object[] {new ItemStack(BOPBlocks.mushrooms.get(),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 6), new Object[] {new ItemStack(BOPBlocks.plants.get(),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 7), new Object[] {new ItemStack(BOPBlocks.moss.get(),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 8), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,9)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.miscItems.get(), 2, 9), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.food.get(), 4, 3), new Object[] {new ItemStack(BOPBlocks.flowers.get(),1,13)});

		//Brick stairs and slabs
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.stoneSingleSlab.get(), 6, 0), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.redCobbleStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.redCobbleStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.stoneSingleSlab.get(), 6, 1), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.redBricksStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.redBricksStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.stoneSingleSlab.get(), 6, 2), new Object[] {"RRR", 'R', BOPBlocks.mudBrick.get()});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.mudBricksStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', BOPBlocks.mudBrick.get()});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.mudBricksStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', BOPBlocks.mudBrick.get()});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.holyCobbleStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.holyCobbleStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.holyBricksStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.holyBricksStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.stoneSingleSlab.get(), 6, 3), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.stoneSingleSlab.get(), 6, 4), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,2)});

		//Redwood
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 8), new Object[] {new ItemStack(BOPBlocks.logs3.get(),1,0)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab2.get(), 6, 0), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 8)});
		addRecipeToFront(new ItemStack(BOPBlocks.redwoodStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 8)});
		addRecipeToFront(new ItemStack(BOPBlocks.redwoodStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 8)});

		//Willow
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 9), new Object[] {new ItemStack(BOPBlocks.logs3.get(),1,1)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab2.get(),6,1), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 9)});
		addRecipeToFront(new ItemStack(BOPBlocks.willowStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 9)});
		addRecipeToFront(new ItemStack(BOPBlocks.willowStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 9)});

		//Acacia
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 0), new Object[] {new ItemStack(BOPBlocks.logs1.get(),1,0)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,0), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 0)});
		addRecipeToFront(new ItemStack(BOPBlocks.acaciaStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 0)});
		addRecipeToFront(new ItemStack(BOPBlocks.acaciaStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 0)});

		//Fir
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 3), new Object[] {new ItemStack(BOPBlocks.logs1.get(),1,3)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,3), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 3)});
		addRecipeToFront(new ItemStack(BOPBlocks.firStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 3)});
		addRecipeToFront(new ItemStack(BOPBlocks.firStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 3)});

		//Cherry
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 1), new Object[] {new ItemStack(BOPBlocks.logs1.get(),1,1)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,1), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 1)});
		addRecipeToFront(new ItemStack(BOPBlocks.cherryStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 1)});
		addRecipeToFront(new ItemStack(BOPBlocks.cherryStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 1)});

		//Dark
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 2), new Object[] {new ItemStack(BOPBlocks.logs1.get(),1,2)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,2), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 2)});
		addRecipeToFront(new ItemStack(BOPBlocks.darkStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 2)});
		addRecipeToFront(new ItemStack(BOPBlocks.darkStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 2)});

		//Magic
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 5), new Object[] {new ItemStack(BOPBlocks.logs2.get(),1,1)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,5), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 5)});
		addRecipeToFront(new ItemStack(BOPBlocks.magicStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 5)});
		addRecipeToFront(new ItemStack(BOPBlocks.magicStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 5)});

		//Palm
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 7), new Object[] {new ItemStack(BOPBlocks.logs2.get(),1,3)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,7), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 7)});
		addRecipeToFront(new ItemStack(BOPBlocks.palmStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 7)});
		addRecipeToFront(new ItemStack(BOPBlocks.palmStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 7)});

		//Mangrove
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 6), new Object[] {new ItemStack(BOPBlocks.logs2.get(),1,2)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,6), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 6)});
		addRecipeToFront(new ItemStack(BOPBlocks.mangroveStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 6)});
		addRecipeToFront(new ItemStack(BOPBlocks.mangroveStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 6)});

		//Holy
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 4), new Object[] {new ItemStack(BOPBlocks.logs2.get(),1,0)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab1.get(),6,4), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(),1,4)});
		addRecipeToFront(new ItemStack(BOPBlocks.holyStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(),1,4)});
		addRecipeToFront(new ItemStack(BOPBlocks.holyStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(),1,4)});

		GameRegistry.addRecipe(new ItemStack(BOPBlocks.redRock.get(), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(BOPBlocks.redRock.get(),1,0)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.holyStone.get(), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(BOPBlocks.holyStone.get(),1,0)});

		//Pine
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 11), new Object[] {new ItemStack(BOPBlocks.logs4.get(), 1, 0)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab2.get(), 6, 2), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 11)});
		addRecipeToFront(new ItemStack(BOPBlocks.pineStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 11)});
		addRecipeToFront(new ItemStack(BOPBlocks.pineStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 11)});

		//Hellbark
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 12), new Object[] {new ItemStack(BOPBlocks.logs4.get(), 1, 1)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab2.get(), 6, 3), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 12)});
		addRecipeToFront(new ItemStack(BOPBlocks.hellBarkStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 12)});
		addRecipeToFront(new ItemStack(BOPBlocks.hellBarkStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 12)});

		//Jacaranda
		GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks.get(), 4, 13), new Object[] {new ItemStack(BOPBlocks.logs4.get(), 1, 2)});
		addRecipeToFront(new ItemStack(BOPBlocks.woodenSingleSlab2.get(), 6, 4), new Object[] {"RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 13)});
		addRecipeToFront(new ItemStack(BOPBlocks.jacarandaStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 13)});
		addRecipeToFront(new ItemStack(BOPBlocks.jacarandaStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(BOPBlocks.planks.get(), 1, 13)});

		GameRegistry.addRecipe(new ItemStack(BOPBlocks.overgrownNetherrack.get(), 1, 0), new Object[] {"SSS", "SNS", "SSS", 'S', Item.seeds, 'N', Block.netherrack});
		GameRegistry.addRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] {"CCC", "CCC", "CCC", 'C', new ItemStack(BOPBlocks.plants.get(), 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.miscItems.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.mud.get(), 1), new Object[] {"MM", "MM", 'M', BOPItems.mudball.get()});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 3), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 5), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 7), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 3)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 9), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 4)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 11), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 5)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.amethystOre.get(), 1, 13), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gems.get(), 1, 6)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.ash.get(), 1), new Object[] {"AA", "AA", 'A', new ItemStack(BOPItems.miscItems.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.mudBrick.get(), 1), new Object[] {"MM", "MM", 'M', new ItemStack(BOPItems.miscItems.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.crystal.get(), 1), new Object[] {"CC", "CC", 'C', new ItemStack(BOPItems.miscItems.get(), 1, 4)});
		//        GameRegistry.addRecipe(new ItemStack(Blocks.planks.get(), 1, 10), new Object[] {"###", "###", "###", '#', Blocks.bamboo.get()});
		GameRegistry.addRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', BOPBlocks.moss.get(), 'C', Block.cobblestone});
		GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', BOPBlocks.moss.get(), 'S', Block.stoneBrick});

		//Scythes
		if (BOPConfigurationMisc.scytheCrafting)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheWood.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheStone.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Block.cobblestone, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheIron.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Item.ingotIron, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheGold.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Item.ingotGold, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheDiamond.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Item.diamond, Character.valueOf('S'), "stickWood" }));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheWood.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheStone.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Block.cobblestone, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheIron.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Item.ingotIron, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheGold.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Item.ingotGold, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheDiamond.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Item.diamond, Character.valueOf('S'), "stickWood" }));	
		}
			
		//Mud Tools and Armor
		if (BOPConfigurationMisc.mudTools)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.pickaxeMud.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), BOPItems.mudball.get(), Character.valueOf('X'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.shovelMud.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), BOPItems.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.swordMud.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), BOPItems.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.axeMud.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), BOPItems.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.hoeMud.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), BOPItems.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ItemStack(BOPItems.helmetMud.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), BOPItems.mudball.get()});
			GameRegistry.addRecipe(new ItemStack(BOPItems.chestplateMud.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), BOPItems.mudball.get()});
			GameRegistry.addRecipe(new ItemStack(BOPItems.leggingsMud.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), BOPItems.mudball.get()});
			GameRegistry.addRecipe(new ItemStack(BOPItems.bootsMud.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), BOPItems.mudball.get()});
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheMud.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), BOPItems.mudball.get(), Character.valueOf('S'), "stickWood" }));	
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheMud.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), BOPItems.mudball.get(), Character.valueOf('S'), "stickWood" }));
		}
		
		//Amethyst Tools and Armor
		if (BOPConfigurationMisc.amethystTools)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItems.pickaxeAmethyst.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(BOPItems.shovelAmethyst.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(BOPItems.swordAmethyst.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(BOPItems.axeAmethyst.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(BOPItems.hoeAmethyst.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(BOPItems.helmetAmethyst.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.chestplateAmethyst.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.leggingsAmethyst.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.bootsAmethyst.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), new ItemStack(BOPItems.gems.get(), 1, 0)});
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheAmethyst.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('S'), Item.ingotIron}));	
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.scytheAmethyst.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), new ItemStack(BOPItems.gems.get(), 1, 0), Character.valueOf('S'), Item.ingotIron}));	
			GameRegistry.addRecipe(new ItemStack(Fluids.bopBucket.get(), 1, 0), new Object[] {"XXX", "AXA", "XAX", 'A', new ItemStack(BOPItems.gems.get(), 1, 0)});
		}

		//Flower Bands
		if (BOPConfigurationMisc.flowerbandCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItems.flowerBand.get(), 1, 0), new Object [] {"CCC", "C C", "CCC", Character.valueOf('C'), new ItemStack(BOPBlocks.flowers.get(), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.flowerBand.get(), 1, 1), new Object [] {"CDC", "D D", "CDC", Character.valueOf('C'), new ItemStack(BOPBlocks.flowers.get(), 1, 0), Character.valueOf('D'), new ItemStack(BOPBlocks.flowers.get(), 1, 5)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.flowerBand.get(), 1, 2), new Object [] {"CDC", "V V", "CDC", Character.valueOf('C'), new ItemStack(BOPBlocks.flowers.get(), 1, 0),Character.valueOf('D'), new ItemStack(BOPBlocks.flowers.get(), 1, 5), Character.valueOf('V'), new ItemStack(BOPBlocks.flowers.get(), 1, 8)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.flowerBand.get(), 1, 3), new Object [] {"CDT", "V V", "TDC", Character.valueOf('C'), new ItemStack(BOPBlocks.flowers.get(), 1, 0),Character.valueOf('D'), new ItemStack(BOPBlocks.flowers.get(), 1, 5), Character.valueOf('V'), new ItemStack(BOPBlocks.flowers.get(), 1, 8), Character.valueOf('T'), new ItemStack(BOPBlocks.flowers.get(), 1, 6)});
		}
		
		//Other
		GameRegistry.addRecipe(new ItemStack(Item.wheat, 1), new Object[] {"###", '#', new ItemStack(BOPBlocks.plants.get(),1,6)});

		if (BOPConfigurationMisc.staffCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItems.ancientStaff.get(), 1, 0), new Object[] {"T", "P", "H", 'T', new ItemStack(BOPItems.ancientStaff.get(), 1, 3), 'P', new ItemStack(BOPItems.ancientStaff.get(), 1, 2), 'H', new ItemStack(BOPItems.ancientStaff.get(), 1, 1)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.ancientStaff.get(), 1, 1), new Object[] {"ESE", "ETE", " E ", 'E', Block.whiteStone, 'T', new ItemStack(BOPItems.gems.get(), 1, 4), 'S', new ItemStack(BOPItems.gems.get(), 1, 6)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.ancientStaff.get(), 1, 2), new Object[] {"EPE", "EEE", "EAE", 'E', Block.whiteStone, 'P', new ItemStack(BOPItems.gems.get(), 1, 2), 'A', new ItemStack(BOPItems.gems.get(), 1, 5)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.ancientStaff.get(), 1, 3), new Object[] {" N ", "ERE", "ETE", 'E', Block.whiteStone, 'R', new ItemStack(BOPItems.gems.get(), 1, 1), 'T', new ItemStack(BOPItems.gems.get(), 1, 3), 'N', Item.netherStar});
			GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.ancientStaff.get(), 1, 0), new ItemStack(BOPItems.ancientStaff.get(), 1, 4), new ItemStack(Item.netherStar, 1));
		}
		
		if (BOPConfigurationMisc.enderporterCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItems.enderporter.get(), 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Item.eyeOfEnder, 'O', new ItemStack(BOPItems.miscItems.get(), 1, 10), 'A', new ItemStack(BOPItems.gems.get(), 1, 0)});
		}

		//Dart Blower
		if (BOPConfigurationMisc.dartCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(BOPItems.dartBlower.get(), 1), new Object[] {"R R", "R R", "R R", Character.valueOf('R'), new ItemStack(BOPBlocks.plants.get(), 1, 8)});
			GameRegistry.addRecipe(new ItemStack(BOPItems.dart.get(), 4, 0), new Object[] {"T", "R", "F", Character.valueOf('T'), new ItemStack(BOPBlocks.plants.get(), 1, 5), Character.valueOf('R'), new ItemStack(BOPBlocks.plants.get(), 1, 8), Character.valueOf('F'), Item.feather});
			GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.dart.get(), 1, 1), new Object[] {new ItemStack(BOPItems.jarFilled.get(), 1, 1), new ItemStack(BOPItems.dart.get(), 1, 0)});
		}
		
		
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.planks.get(), 1, 10), new Object[] {"##", "##", '#', BOPBlocks.bamboo.get()});
		GameRegistry.addRecipe(new ItemStack(BOPItems.jarEmpty.get(), 3, 0), new Object[] {"# #", "# #", "###", '#', Block.glass});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.food.get(), 1, 10), new Object[] {new ItemStack(BOPItems.miscItems.get(), 1, 11), new ItemStack(Item.potion, 1, 0), new ItemStack(BOPBlocks.flowers2.get(), 1, 6), new ItemStack(BOPBlocks.coral.get(), 1, 3), new ItemStack(BOPBlocks.plants.get(), 1, 15), new ItemStack(BOPItems.miscItems.get(), 1, 4), new ItemStack(BOPItems.jarFilled.get(), 1, 0), new ItemStack(BOPItems.food.get(), 1, 0), Item.sugar});
		
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.flesh.get(), 1, 0), new Object[] {"##", "##", '#', new ItemStack(BOPItems.miscItems.get(), 1, 3)});
		GameRegistry.addRecipe(new ItemStack(Item.rottenFlesh, 1, 0), new Object[] {"FFF", "FPF", "FFF", 'F', new ItemStack(BOPItems.miscItems.get(), 1, 3), 'P', new ItemStack(BOPItems.jarFilled.get(), 1, 1)});

		GameRegistry.addRecipe(new ItemStack(BOPBlocks.bamboo.get(), 8), new Object [] {" #", "# ", Character.valueOf('#'), new ItemStack(BOPBlocks.planks.get(), 1, 10)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.bamboo.get(), 8), new Object [] {"# ", " #", Character.valueOf('#'), new ItemStack(BOPBlocks.planks.get(), 1, 10)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 0), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 1), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 2), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 3), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 4), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 9)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 5), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 11)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gems.get(), 9, 6), new Object[] {new ItemStack(BOPBlocks.amethystOre.get(), 1, 13)});

		//Bone Segments > Bonemeal
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, 15), new Object[] {new ItemStack(BOPBlocks.bones.get(), 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 6, 15), new Object[] {new ItemStack(BOPBlocks.bones.get(), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 12, 15), new Object[] {new ItemStack(BOPBlocks.bones.get(), 1, 2)});
		
		//Honeycombs
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.hive.get(), 1, 0), new Object [] {"##", "##", Character.valueOf('#'), new ItemStack(BOPItems.miscItems.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(BOPBlocks.hive.get(), 1, 3), new Object [] {"##", "##", Character.valueOf('#'), new ItemStack(BOPItems.food.get(), 1, 9)});
		
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.food.get(), 2, 1), new Object[] {new ItemStack(BOPBlocks.mushrooms.get(),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.jarFilled.get(), 1, 1), new Object[] {new ItemStack(BOPBlocks.foliage.get(),1,7), new ItemStack(BOPItems.jarEmpty.get(),1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.food.get(), 1, 4), new Object[] {Item.bowlEmpty, new ItemStack(BOPItems.food.get(), 1, 0), Item.appleRed, Item.melon});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.food.get(), 1, 5), new Object[] {Item.bowlEmpty, new ItemStack(BOPItems.food.get(), 1, 2), Item.carrot, Item.potato});
		GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.food.get(), 1, 6), new Object[] {Item.bowlEmpty, new ItemStack(BOPBlocks.mushrooms.get(), 1, 0), new ItemStack(BOPBlocks.mushrooms.get(), 1, 1), new ItemStack(BOPBlocks.mushrooms.get(), 1, 2)});
	}

	private static void addSmeltingRecipes()
	{
		GameRegistry.addSmelting(Block.dirt.blockID, new ItemStack(BOPBlocks.driedDirt.get(), 1), 0F);
		FurnaceRecipes.smelting().addSmelting(BOPBlocks.redRock.get().blockID, 1, new ItemStack(BOPBlocks.redRock.get(), 1, 0), 0.1F);
		FurnaceRecipes.smelting().addSmelting(BOPBlocks.holyStone.get().blockID, 1, new ItemStack(BOPBlocks.holyStone.get(), 1, 0), 0.1F);
		FurnaceRecipes.smelting().addSmelting(BOPBlocks.plants.get().blockID, 12, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
		FurnaceRecipes.smelting().addSmelting(BOPItems.mudball.get().itemID, 0, new ItemStack(BOPItems.miscItems.get(), 1, 0), 0F);

		FurnaceRecipes.smelting().addSmelting(BOPBlocks.logs1.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
		FurnaceRecipes.smelting().addSmelting(BOPBlocks.logs2.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
		FurnaceRecipes.smelting().addSmelting(BOPBlocks.logs4.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
		for (int i = 0; i < 3; ++i) {
			FurnaceRecipes.smelting().addSmelting(BOPBlocks.logs3.get().blockID, i, new ItemStack(Item.coal, 1, 1), 15F);
		}

		GameRegistry.registerFuelHandler(checkNotNull(new FurnaceFuel()));
	}

	private static void addOreRegistration()
	{
		//Ore Registration
		OreDictionary.registerOre("plankWood", new ItemStack(BOPBlocks.planks.get(), 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stickWood", new ItemStack(BOPBlocks.bamboo.get()));
		OreDictionary.registerOre("stickWood", new ItemStack(BOPBlocks.plants.get(), 1, 8));

		OreDictionary.registerOre("dyeBlue", new ItemStack(BOPItems.miscItems.get(), 1, 5));
		OreDictionary.registerOre("dyeBrown", new ItemStack(BOPItems.miscItems.get(), 1, 6));
		OreDictionary.registerOre("dyeGreen", new ItemStack(BOPItems.miscItems.get(), 1, 7));
		OreDictionary.registerOre("dyeWhite", new ItemStack(BOPItems.miscItems.get(), 1, 8));
		OreDictionary.registerOre("dyeBlack", new ItemStack(BOPItems.miscItems.get(), 1, 9));
		
		OreDictionary.registerOre("gemRuby", new ItemStack(BOPItems.gems.get(), 1, 1));
		OreDictionary.registerOre("gemPeridot", new ItemStack(BOPItems.gems.get(), 1, 2));
		OreDictionary.registerOre("gemTopaz", new ItemStack(BOPItems.gems.get(), 1, 3));
		OreDictionary.registerOre("gemTanzanite", new ItemStack(BOPItems.gems.get(), 1, 4));
		OreDictionary.registerOre("gemMalachite", new ItemStack(BOPItems.gems.get(), 1, 5));
		OreDictionary.registerOre("gemSapphire", new ItemStack(BOPItems.gems.get(), 1, 6));
		
		OreDictionary.registerOre("oreRuby", new ItemStack(BOPBlocks.amethystOre.get(), 1, 2));
		OreDictionary.registerOre("oreTopaz", new ItemStack(BOPBlocks.amethystOre.get(), 1, 6));
		OreDictionary.registerOre("orePeridot", new ItemStack(BOPBlocks.amethystOre.get(), 1, 4));
		OreDictionary.registerOre("oreTanzanite", new ItemStack(BOPBlocks.amethystOre.get(), 1, 8));
		OreDictionary.registerOre("oreMalachite", new ItemStack(BOPBlocks.amethystOre.get(), 1, 10));
		OreDictionary.registerOre("oreSapphire", new ItemStack(BOPBlocks.amethystOre.get(), 1, 12));

		OreDictionary.registerOre("treeSapling", new ItemStack(BOPBlocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(BOPBlocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));

		for (int i = 0; i <= 3; i++)
		{
			OreDictionary.registerOre("logWood", new ItemStack(BOPBlocks.logs1.get(), 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(BOPBlocks.logs2.get(), 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(BOPBlocks.logs3.get(), 1, i));
			if (i < 3)
			{
				OreDictionary.registerOre("logWood", new ItemStack(BOPBlocks.logs4.get(), 1, i));
			}
		}

		OreDictionary.registerOre("slabWood", new ItemStack(BOPBlocks.woodenSingleSlab1.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("slabWood", new ItemStack(BOPBlocks.woodenSingleSlab2.get(), 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.redwoodStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.willowStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.acaciaStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.firStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.cherryStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.darkStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.magicStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.palmStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.mangroveStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.holyStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.pineStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.hellBarkStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(BOPBlocks.jacarandaStairs.get()));

		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leavesColorized1.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leavesColorized2.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leaves1.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leaves2.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leaves3.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leaves4.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leavesFruit.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(BOPBlocks.leavesFruit2.get(), 1, OreDictionary.WILDCARD_VALUE));
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
