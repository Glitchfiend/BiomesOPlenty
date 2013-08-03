package biomesoplenty.configuration;

import static com.google.common.base.Preconditions.checkNotNull;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.api.Items;
import biomesoplenty.helpers.FurnaceFuel;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPCrafting
{
	public static void init()
	{
		addOreRegistration();
		addCraftingRecipes();
		addSmeltingRecipes();
	}

	private static void addCraftingRecipes()
	{
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] {new ItemStack(Blocks.flowers.get(),1,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 13), new Object[] {new ItemStack(Blocks.flowers.get(),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] {new ItemStack(Blocks.flowers.get(),1,5)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {new ItemStack(Blocks.flowers.get(),1,6)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {new ItemStack(Blocks.flowers.get(),1,15)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Blocks.flowers.get(),1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {new ItemStack(Blocks.flowers.get(),1,8)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 10), new Object[] {new ItemStack(Blocks.mushrooms.get(),1,3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {new ItemStack(Items.miscItems.get(), 1, 1)});

		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 2, 5), new Object[] {new ItemStack(Blocks.mushrooms.get(),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 2, 6), new Object[] {new ItemStack(Blocks.plants.get(),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 2, 7), new Object[] {new ItemStack(Blocks.moss.get(),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 2, 8), new Object[] {new ItemStack(Blocks.flowers.get(),1,9)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 2, 9), new Object[] {new ItemStack(Blocks.flowers.get(),1,2)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.food.get(), 4, 3), new Object[] {new ItemStack(Blocks.flowers.get(),1,13)});

		//Brick stairs and slabs
		GameRegistry.addRecipe(new ItemStack(Blocks.stoneSingleSlab.get(), 6, 0), new Object[] {"RRR", 'R', new ItemStack(Blocks.redRock.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.redCobbleStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.redRock.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.redCobbleStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.redRock.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.stoneSingleSlab.get(), 6, 1), new Object[] {"RRR", 'R', new ItemStack(Blocks.redRock.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.redBricksStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.redRock.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.redBricksStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.redRock.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.stoneSingleSlab.get(), 6, 2), new Object[] {"RRR", 'R', Blocks.mudBrick.get()});
		GameRegistry.addRecipe(new ItemStack(Blocks.mudBricksStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', Blocks.mudBrick.get()});
		GameRegistry.addRecipe(new ItemStack(Blocks.mudBricksStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', Blocks.mudBrick.get()});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyCobbleStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.holyStone.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyCobbleStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.holyStone.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyBricksStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.holyStone.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyBricksStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.holyStone.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.stoneSingleSlab.get(), 6, 3), new Object[] {"RRR", 'R', new ItemStack(Blocks.holyStone.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.stoneSingleSlab.get(), 6, 4), new Object[] {"RRR", 'R', new ItemStack(Blocks.holyStone.get(),1,2)});

		//Redwood
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 8), new Object[] {new ItemStack(Blocks.logs3.get(),1,0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab2.get(), 6, 0), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 8)});
		GameRegistry.addRecipe(new ItemStack(Blocks.redwoodStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 8)});
		GameRegistry.addRecipe(new ItemStack(Blocks.redwoodStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 8)});

		//Willow
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 9), new Object[] {new ItemStack(Blocks.logs3.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab2.get(),6,1), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 9)});
		GameRegistry.addRecipe(new ItemStack(Blocks.willowStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 9)});
		GameRegistry.addRecipe(new ItemStack(Blocks.willowStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 9)});

		//Acacia
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 0), new Object[] {new ItemStack(Blocks.logs1.get(),1,0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,0), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.acaciaStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.acaciaStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 0)});

		//Fir
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 3), new Object[] {new ItemStack(Blocks.logs1.get(),1,3)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,3), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 3)});
		GameRegistry.addRecipe(new ItemStack(Blocks.firStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 3)});
		GameRegistry.addRecipe(new ItemStack(Blocks.firStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 3)});

		//Cherry
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 1), new Object[] {new ItemStack(Blocks.logs1.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,1), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.cherryStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.cherryStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 1)});

		//Dark
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 2), new Object[] {new ItemStack(Blocks.logs1.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,2), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.darkStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.darkStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 2)});

		//Magic
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 5), new Object[] {new ItemStack(Blocks.logs2.get(),1,1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,5), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 5)});
		GameRegistry.addRecipe(new ItemStack(Blocks.magicStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 5)});
		GameRegistry.addRecipe(new ItemStack(Blocks.magicStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 5)});

		//Palm
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 7), new Object[] {new ItemStack(Blocks.logs2.get(),1,3)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,7), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Blocks.palmStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Blocks.palmStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 7)});

		//Mangrove
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 6), new Object[] {new ItemStack(Blocks.logs2.get(),1,2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,6), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 6)});
		GameRegistry.addRecipe(new ItemStack(Blocks.mangroveStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 6)});
		GameRegistry.addRecipe(new ItemStack(Blocks.mangroveStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 6)});

		//Holy
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 4), new Object[] {new ItemStack(Blocks.logs2.get(),1,0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,4), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(),1,4)});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(),1,4)});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(),1,4)});

		GameRegistry.addRecipe(new ItemStack(Blocks.redRock.get(), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(Blocks.redRock.get(),1,0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.holyStone.get(), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(Blocks.holyStone.get(),1,0)});

		//Pine
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 11), new Object[] {new ItemStack(Blocks.logs4.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab2.get(), 6, 2), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 11)});
		GameRegistry.addRecipe(new ItemStack(Blocks.pineStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 11)});
		GameRegistry.addRecipe(new ItemStack(Blocks.pineStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 11)});

		//Hellbark
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 12), new Object[] {new ItemStack(Blocks.logs4.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab2.get(), 6, 3), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 12)});
		GameRegistry.addRecipe(new ItemStack(Blocks.hellBarkStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 12)});
		GameRegistry.addRecipe(new ItemStack(Blocks.hellBarkStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 12)});

		//Jacaranda
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 4, 13), new Object[] {new ItemStack(Blocks.logs4.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab2.get(), 6, 4), new Object[] {"RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 13)});
		GameRegistry.addRecipe(new ItemStack(Blocks.jacarandaStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 13)});
		GameRegistry.addRecipe(new ItemStack(Blocks.jacarandaStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.planks.get(), 1, 13)});

		GameRegistry.addRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] {"CCC", "CCC", "CCC", 'C', new ItemStack(Blocks.plants.get(), 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.mud.get(), 1), new Object[] {"MM", "MM", 'M', Items.mudball.get()});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 3), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 10)});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 5), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 11)});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 7), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 12)});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 9), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 13)});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 11), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 14)});
		GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 13), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 15)});
		GameRegistry.addRecipe(new ItemStack(Blocks.ash.get(), 1), new Object[] {"AA", "AA", 'A', new ItemStack(Items.miscItems.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Blocks.mudBrick.get(), 1), new Object[] {"MM", "MM", 'M', new ItemStack(Items.miscItems.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Blocks.crystal.get(), 1), new Object[] {"CC", "CC", 'C', new ItemStack(Items.miscItems.get(), 1, 4)});
		//        GameRegistry.addRecipe(new ItemStack(Blocks.planks.get(), 1, 10), new Object[] {"###", "###", "###", '#', Blocks.bamboo.get()});
		GameRegistry.addRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', Blocks.moss.get(), 'C', Block.cobblestone});
		GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', Blocks.moss.get(), 'S', Block.stoneBrick});

		//Scythes
		if (BOPConfiguration.Misc.scytheCrafting)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheWood.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheStone.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Block.cobblestone, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheIron.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Item.ingotIron, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheGold.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Item.ingotGold, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheDiamond.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Item.diamond, Character.valueOf('S'), "stickWood" }));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheWood.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheStone.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Block.cobblestone, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheIron.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Item.ingotIron, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheGold.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Item.ingotGold, Character.valueOf('S'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheDiamond.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Item.diamond, Character.valueOf('S'), "stickWood" }));	
		}
			
		//Mud Tools and Armor
		if (BOPConfiguration.Misc.mudTools)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.pickaxeMud.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood" }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.shovelMud.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.swordMud.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.axeMud.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.hoeMud.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
			GameRegistry.addRecipe(new ItemStack(Items.helmetMud.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), Items.mudball.get()});
			GameRegistry.addRecipe(new ItemStack(Items.chestplateMud.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), Items.mudball.get()});
			GameRegistry.addRecipe(new ItemStack(Items.leggingsMud.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), Items.mudball.get()});
			GameRegistry.addRecipe(new ItemStack(Items.bootsMud.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), Items.mudball.get()});
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheMud.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.mudball.get(), Character.valueOf('S'), "stickWood" }));	
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheMud.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.mudball.get(), Character.valueOf('S'), "stickWood" }));
		}
		
		//Amethyst Tools and Armor
		if (BOPConfiguration.Misc.amethystTools)
		{
			GameRegistry.addRecipe(new ItemStack(Items.pickaxeAmethyst.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(Items.shovelAmethyst.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(Items.swordAmethyst.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(Items.axeAmethyst.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(Items.hoeAmethyst.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
			GameRegistry.addRecipe(new ItemStack(Items.helmetAmethyst.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
			GameRegistry.addRecipe(new ItemStack(Items.chestplateAmethyst.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
			GameRegistry.addRecipe(new ItemStack(Items.leggingsAmethyst.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
			GameRegistry.addRecipe(new ItemStack(Items.bootsAmethyst.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheAmethyst.get(), 1), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('S'), Item.ingotIron}));	
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.scytheAmethyst.get(), 1), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('S'), Item.ingotIron}));	
			GameRegistry.addRecipe(new ItemStack(Fluids.bopBucket.get(), 1, 0), new Object[] {"XXX", "AXA", "XAX", 'A', new ItemStack(Items.miscItems.get(), 1, 2)});
		}

		//Flower Bands
		if (BOPConfiguration.Misc.flowerbandCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(Items.flowerBand.get(), 1, 0), new Object [] {"CCC", "C C", "CCC", Character.valueOf('C'), new ItemStack(Blocks.flowers.get(), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(Items.flowerBand.get(), 1, 1), new Object [] {"CDC", "D D", "CDC", Character.valueOf('C'), new ItemStack(Blocks.flowers.get(), 1, 0), Character.valueOf('D'), new ItemStack(Blocks.flowers.get(), 1, 5)});
			GameRegistry.addRecipe(new ItemStack(Items.flowerBand.get(), 1, 2), new Object [] {"CDC", "V V", "CDC", Character.valueOf('C'), new ItemStack(Blocks.flowers.get(), 1, 0),Character.valueOf('D'), new ItemStack(Blocks.flowers.get(), 1, 5), Character.valueOf('V'), new ItemStack(Blocks.flowers.get(), 1, 8)});
			GameRegistry.addRecipe(new ItemStack(Items.flowerBand.get(), 1, 3), new Object [] {"CDT", "V V", "TDC", Character.valueOf('C'), new ItemStack(Blocks.flowers.get(), 1, 0),Character.valueOf('D'), new ItemStack(Blocks.flowers.get(), 1, 5), Character.valueOf('V'), new ItemStack(Blocks.flowers.get(), 1, 8), Character.valueOf('T'), new ItemStack(Blocks.flowers.get(), 1, 6)});
		}
		
		//Other
		GameRegistry.addRecipe(new ItemStack(Item.wheat, 1), new Object[] {"###", '#', new ItemStack(Blocks.plants.get(),1,6)});

		if (BOPConfiguration.Misc.staffCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 0), new Object[] {"T", "P", "H", 'T', new ItemStack(Items.ancientStaff.get(), 1, 3), 'P', new ItemStack(Items.ancientStaff.get(), 1, 2), 'H', new ItemStack(Items.ancientStaff.get(), 1, 1)});
			GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 1), new Object[] {"ESE", "ETE", " E ", 'E', Block.whiteStone, 'T', new ItemStack(Items.miscItems.get(), 1, 13), 'S', new ItemStack(Items.miscItems.get(), 1, 15)});
			GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 2), new Object[] {"EPE", "EEE", "EAE", 'E', Block.whiteStone, 'P', new ItemStack(Items.miscItems.get(), 1, 11), 'A', new ItemStack(Items.miscItems.get(), 1, 14)});
			GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 3), new Object[] {" N ", "ERE", "ETE", 'E', Block.whiteStone, 'R', new ItemStack(Items.miscItems.get(), 1, 10), 'T', new ItemStack(Items.miscItems.get(), 1, 12), 'N', Item.netherStar});
			GameRegistry.addShapelessRecipe(new ItemStack(Items.ancientStaff.get(), 1, 0), new ItemStack(Items.ancientStaff.get(), 1, 4), new ItemStack(Item.netherStar, 1));
		}
		
		if (BOPConfiguration.Misc.enderporterCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(Items.enderporter.get(), 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Item.eyeOfEnder, 'O', Block.obsidian, 'A', new ItemStack(Blocks.amethystOre.get(), 1, 1)});
			GameRegistry.addRecipe(new ItemStack(Items.bopDiscMud.get(), 1), new Object[] {" M ", "MDM", " M ", 'M', Items.mudball.get(), 'D', Items.bopDisc.get()});
		}
		
		if (BOPConfiguration.Misc.altarCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(Items.miscItems.get(), 1, 16), new Object[] {"SBS", "GKG", "SBS", 'S', Block.slowSand, 'B', Item.blazePowder, 'G', Item.ghastTear, 'K', new ItemStack(Item.skull, 1, 1)});
			GameRegistry.addRecipe(new ItemStack(Items.soulManipulator.get(), 1, 0), new Object[] {"G", "T", "B", 'G', Block.glass, 'T', Item.ghastTear, 'B', Item.blazeRod});
			GameRegistry.addRecipe(new ItemStack(Items.soulManipulator.get(), 1, 1), new Object[] {"TSA", "PMS", "APT", 'S', new ItemStack(Items.miscItems.get(), 1, 16), 'A', Block.slowSand, 'T', Item.ghastTear, 'P', Item.blazePowder, 'M', new ItemStack(Items.soulManipulator.get(), 1, 0)});
			GameRegistry.addRecipe(new ItemStack(Blocks.altar.get(), 1), new Object[] {"OBO", "BBB", "OBO", 'O', Block.obsidian, 'B', new ItemStack(Blocks.bones.get(), 1, 2)});
			GameRegistry.addRecipe(new ItemStack(Blocks.glass.get(), 1, 1), new Object[] {"PCP", "CSC", "PCP", 'P', Item.blazePowder, 'C', new ItemStack(Blocks.glass.get(), 1, 0), 'S', new ItemStack(Items.miscItems.get(), 1, 16)});
			GameRegistry.addRecipe(new ItemStack(Blocks.glass.get(), 1, 0), new Object [] {"CGC", "GGG", "CGC", Character.valueOf('G'), new ItemStack(Blocks.crystal.get(), 1, 0), Character.valueOf('C'), new ItemStack(Items.miscItems.get(), 1, 4)});
		}

		//Dart Blower
		if (BOPConfiguration.Misc.dartCrafting)
		{
			GameRegistry.addRecipe(new ItemStack(Items.dartBlower.get(), 1), new Object[] {"R R", "R R", "R R", Character.valueOf('R'), new ItemStack(Blocks.plants.get(), 1, 8)});
			GameRegistry.addRecipe(new ItemStack(Items.dart.get(), 4, 0), new Object[] {"T", "R", "F", Character.valueOf('T'), new ItemStack(Blocks.plants.get(), 1, 5), Character.valueOf('R'), new ItemStack(Blocks.plants.get(), 1, 8), Character.valueOf('F'), Item.feather});
			GameRegistry.addRecipe(new ItemStack(Items.dart.get(), 1, 1), new Object[] {"P", "D", Character.valueOf('P'), new ItemStack(Items.miscItems.get(), 1, 3), Character.valueOf('D'), new ItemStack(Items.dart.get(), 1, 0)});
		}
		
		GameRegistry.addRecipe(new ItemStack(Blocks.planks.get(), 1, 10), new Object[] {"##", "##", '#', Blocks.bamboo.get()});

		GameRegistry.addRecipe(new ItemStack(Blocks.bamboo.get(), 8), new Object [] {" #", "# ", Character.valueOf('#'), new ItemStack(Blocks.planks.get(), 1, 10)});
		GameRegistry.addRecipe(new ItemStack(Blocks.bamboo.get(), 8), new Object [] {"# ", " #", Character.valueOf('#'), new ItemStack(Blocks.planks.get(), 1, 10)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 2), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 10), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 11), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 12), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 13), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 9)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 14), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 11)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 15), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 13)});

		//Bone Segments > Bonemeal
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, 15), new Object[] {new ItemStack(Blocks.bones.get(), 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 6, 15), new Object[] {new ItemStack(Blocks.bones.get(), 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 12, 15), new Object[] {new ItemStack(Blocks.bones.get(), 1, 2)});

		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Items.food.get(), 2, 1), new Object[] {new ItemStack(Blocks.mushrooms.get(),1,0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 1, 3), new Object[] {new ItemStack(Blocks.foliage.get(),1,7)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.food.get(), 1, 4), new Object[] {Item.bowlEmpty, new ItemStack(Items.food.get(), 1, 0), Item.appleRed, Item.melon});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.food.get(), 1, 5), new Object[] {Item.bowlEmpty, new ItemStack(Items.food.get(), 1, 2), Item.carrot, Item.potato});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.food.get(), 1, 6), new Object[] {Item.bowlEmpty, new ItemStack(Blocks.mushrooms.get(), 1, 0), new ItemStack(Blocks.mushrooms.get(), 1, 1), new ItemStack(Blocks.mushrooms.get(), 1, 2)});

		//Glass
		//GameRegistry.addRecipe(new ItemStack(Blocks.glass.get(), 1, 0), new Object [] {"GGG", "GGG", "GGG", Character.valueOf('G'), Block.glass});
	}

	private static void addSmeltingRecipes()
	{
		GameRegistry.addSmelting(Block.dirt.blockID, new ItemStack(Blocks.driedDirt.get(), 1), 0F);
		FurnaceRecipes.smelting().addSmelting(Blocks.redRock.get().blockID, 1, new ItemStack(Blocks.redRock.get(), 1, 0), 0.1F);
		FurnaceRecipes.smelting().addSmelting(Blocks.holyStone.get().blockID, 1, new ItemStack(Blocks.holyStone.get(), 1, 0), 0.1F);
		FurnaceRecipes.smelting().addSmelting(Blocks.plants.get().blockID, 12, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
		FurnaceRecipes.smelting().addSmelting(Items.mudball.get().itemID, 0, new ItemStack(Items.miscItems.get(), 1, 0), 0F);

		FurnaceRecipes.smelting().addSmelting(Blocks.logs1.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
		FurnaceRecipes.smelting().addSmelting(Blocks.logs2.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
		FurnaceRecipes.smelting().addSmelting(Blocks.logs4.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
		for (int i = 0; i < 3; ++i) {
			FurnaceRecipes.smelting().addSmelting(Blocks.logs3.get().blockID, i, new ItemStack(Item.coal, 1, 1), 15F);
		}

		GameRegistry.registerFuelHandler(checkNotNull(new FurnaceFuel()));
	}

	private static void addOreRegistration()
	{
		//Ore Registration
		OreDictionary.registerOre("plankWood", new ItemStack(Blocks.planks.get(), 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stickWood", new ItemStack(Blocks.bamboo.get()));
		OreDictionary.registerOre("stickWood", new ItemStack(Blocks.plants.get(), 1, 8));

		OreDictionary.registerOre("dyeBlue", new ItemStack(Items.miscItems.get(), 1, 5));
		OreDictionary.registerOre("dyeBrown", new ItemStack(Items.miscItems.get(), 1, 6));
		OreDictionary.registerOre("dyeGreen", new ItemStack(Items.miscItems.get(), 1, 7));
		OreDictionary.registerOre("dyeWhite", new ItemStack(Items.miscItems.get(), 1, 8));
		OreDictionary.registerOre("dyeBlack", new ItemStack(Items.miscItems.get(), 1, 9));
		
		OreDictionary.registerOre("gemRuby", new ItemStack(Items.miscItems.get(), 1, 10));
		OreDictionary.registerOre("gemPeridot", new ItemStack(Items.miscItems.get(), 1, 11));
		OreDictionary.registerOre("gemTopaz", new ItemStack(Items.miscItems.get(), 1, 12));
		OreDictionary.registerOre("gemTanzanite", new ItemStack(Items.miscItems.get(), 1, 13));
		OreDictionary.registerOre("gemSapphire", new ItemStack(Items.miscItems.get(), 1, 15));

		OreDictionary.registerOre("treeSapling", new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));

		for (int i = 0; i <= 3; i++)
		{
			OreDictionary.registerOre("logWood", new ItemStack(Blocks.logs1.get(), 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(Blocks.logs2.get(), 1, i));
			OreDictionary.registerOre("logWood", new ItemStack(Blocks.logs3.get(), 1, i));
			if (i < 3)
			{
				OreDictionary.registerOre("logWood", new ItemStack(Blocks.logs4.get(), 1, i));
			}
		}

		OreDictionary.registerOre("slabWood", new ItemStack(Blocks.woodenSingleSlab1.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("slabWood", new ItemStack(Blocks.woodenSingleSlab2.get(), 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.redwoodStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.willowStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.acaciaStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.firStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.cherryStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.darkStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.magicStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.palmStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.mangroveStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.holyStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.pineStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.hellBarkStairs.get()));
		OreDictionary.registerOre("stairWood", new ItemStack(Blocks.jacarandaStairs.get()));

		OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leavesColorized.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leaves1.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leaves2.get(), 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leavesFruit.get(), 1, OreDictionary.WILDCARD_VALUE));
	}
}
