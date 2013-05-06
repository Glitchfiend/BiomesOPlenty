package biomesoplenty.configuration;

import static com.google.common.base.Preconditions.checkNotNull;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import biomesoplenty.api.Blocks;
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
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {new ItemStack(Blocks.flowers.get(),1,9)});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Blocks.flowers.get(),1,4)});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {new ItemStack(Blocks.flowers.get(),1,8)});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {new ItemStack(Items.miscItems.get(), 1, 1)});

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
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks.get(), 1, 5), new Object[] {new ItemStack(Blocks.logs2.get(),1,1)});
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
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.woodenDoubleSlab1.get(),4,4), new Object[] {new ItemStack(Blocks.logs2.get(),1,0)});
        GameRegistry.addRecipe(new ItemStack(Blocks.woodenSingleSlab1.get(),6,4), new Object[] {"RRR", 'R', new ItemStack(Blocks.woodenDoubleSlab1.get(),1,4)});
        GameRegistry.addRecipe(new ItemStack(Blocks.holyStairs.get(), 4), new Object[] {"  R", " RR", "RRR", 'R', new ItemStack(Blocks.woodenDoubleSlab1.get(),1,4)});
        GameRegistry.addRecipe(new ItemStack(Blocks.holyStairs.get(), 4), new Object[] {"R  ", "RR ", "RRR", 'R', new ItemStack(Blocks.woodenDoubleSlab1.get(),1,4)});
        
        GameRegistry.addRecipe(new ItemStack(Blocks.redRock.get(), 4, 2), new Object[] {"RR", "RR", 'R', new ItemStack(Blocks.redRock.get(),1,0)});
        
        GameRegistry.addRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] {"CCC", "CCC", "CCC", 'C', new ItemStack(Blocks.plants.get(), 1, 7)});
        GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 1)});
        GameRegistry.addRecipe(new ItemStack(Blocks.mud.get(), 1), new Object[] {"MM", "MM", 'M', Items.mudball.get()});
        GameRegistry.addRecipe(new ItemStack(Blocks.amethystOre.get(), 1, 1), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(Items.miscItems.get(), 1, 2)});
        GameRegistry.addRecipe(new ItemStack(Blocks.ash.get(), 1), new Object[] {"AA", "AA", 'A', new ItemStack(Items.miscItems.get(), 1, 1)});
        GameRegistry.addRecipe(new ItemStack(Blocks.mudBrick.get(), 1), new Object[] {"MM", "MM", 'M', new ItemStack(Items.miscItems.get(), 1, 3)});
//        GameRegistry.addRecipe(new ItemStack(Blocks.planks.get(), 1, 10), new Object[] {"###", "###", "###", '#', Blocks.bamboo.get()});
        GameRegistry.addRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', Blocks.moss.get(), 'C', Block.cobblestone});
        GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', Blocks.moss.get(), 'S', Block.stoneBrick});
        
        //Mud Tools and Armor        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.pickaxeMud.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.shovelMud.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.swordMud.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.axeMud.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.hoeMud.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), Items.mudball.get(), Character.valueOf('X'), "stickWood"}));
        GameRegistry.addRecipe(new ItemStack(Items.helmetMud.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), Items.mudball.get()});
        GameRegistry.addRecipe(new ItemStack(Items.chestplateMud.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), Items.mudball.get()});
        GameRegistry.addRecipe(new ItemStack(Items.leggingsMud.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), Items.mudball.get()});
        GameRegistry.addRecipe(new ItemStack(Items.bootsMud.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), Items.mudball.get()});

        //Amethyst Tools and Armor
        GameRegistry.addRecipe(new ItemStack(Items.pickaxeAmethyst.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(Items.shovelAmethyst.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(Items.swordAmethyst.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(Items.axeAmethyst.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(Items.hoeAmethyst.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(Items.helmetAmethyst.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
        GameRegistry.addRecipe(new ItemStack(Items.chestplateAmethyst.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
        GameRegistry.addRecipe(new ItemStack(Items.leggingsAmethyst.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
        GameRegistry.addRecipe(new ItemStack(Items.bootsAmethyst.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
        
        //Other
        GameRegistry.addRecipe(new ItemStack(Item.wheat, 1), new Object[] {"###", '#', new ItemStack(Blocks.plants.get(),1,6)});
        GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 0), new Object[] {"T", "P", "H", 'T', new ItemStack(Items.ancientStaff.get(), 1, 3), 'P', new ItemStack(Items.ancientStaff.get(), 1, 2), 'H', new ItemStack(Items.ancientStaff.get(), 1, 1)});
        GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 1), new Object[] {"ISI", "ISI", " E ", 'I', Item.ingotIron, 'S', Block.whiteStone, 'E', Item.emerald});
        GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 2), new Object[] {"ISI", "IRI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'R', Item.redstone});
        GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 3), new Object[] {" N ", "IDI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'D', Item.diamond, 'N', Item.netherStar});
        GameRegistry.addRecipe(new ItemStack(Items.enderporter.get(), 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Item.eyeOfEnder, 'O', Block.obsidian, 'A', new ItemStack(Blocks.amethystOre.get(), 1, 1)});
        GameRegistry.addRecipe(new ItemStack(Items.bopDiscMud.get(), 1), new Object[] {" M ", "MDM", " M ", 'M', Items.mudball.get(), 'D', Items.bopDisc.get()});
        
        GameRegistry.addRecipe(new ItemStack(Blocks.planks.get(), 1, 10), new Object[] {"##", "##", '#', Blocks.bamboo.get()});
        
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.bamboo.get(), 4), new Object[] {new ItemStack(Blocks.planks.get(), 1, 10)});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 2), new Object[] {new ItemStack(Blocks.amethystOre.get(), 1, 1)});
        
        //Plants
        GameRegistry.addShapelessRecipe(new ItemStack(Items.shroomPowder.get(), 2), new Object[] {new ItemStack(Blocks.flowers.get(),1,10)});
    }
    
    private static void addSmeltingRecipes()
    {
        GameRegistry.addSmelting(Block.dirt.blockID, new ItemStack(Blocks.driedDirt.get(), 1), 0F);
        FurnaceRecipes.smelting().addSmelting(Blocks.redRock.get().blockID, 1, new ItemStack(Blocks.redRock.get(), 1, 0), 0.1F);
        FurnaceRecipes.smelting().addSmelting(Blocks.flowers.get().blockID, 11, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
        FurnaceRecipes.smelting().addSmelting(Items.mudball.get().itemID, 0, new ItemStack(Items.miscItems.get(), 1, 0), 0F);
        
        FurnaceRecipes.smelting().addSmelting(Blocks.logs1.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
        FurnaceRecipes.smelting().addSmelting(Blocks.logs2.get().blockID, new ItemStack(Item.coal, 1, 1), 15F);
        for (int i = 0; i < 3; ++i)
            FurnaceRecipes.smelting().addSmelting(Blocks.logs3.get().blockID, i, new ItemStack(Item.coal, 1, 1), 15F);
        
        GameRegistry.registerFuelHandler(checkNotNull(new FurnaceFuel()));
    }
    
    private static void addOreRegistration()
    {
      //Ore Registration
        for (int i = 0; i < 10; ++i)
            OreDictionary.registerOre("plankWood", new ItemStack(Blocks.planks.get(), 1, i));
        
        OreDictionary.registerOre("stickWood", new ItemStack(Blocks.bamboo.get()));
		OreDictionary.registerOre("stickWood", new ItemStack(Blocks.plants.get(), 1, 8));
        
        OreDictionary.registerOre("treeSapling", new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeSapling", new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));
        
        OreDictionary.registerOre("woodLog", new ItemStack(Blocks.logs1.get(), 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("woodLog", new ItemStack(Blocks.logs2.get(), 1, OreDictionary.WILDCARD_VALUE));
        for (int i = 0; i < 3; ++i)
            OreDictionary.registerOre("woodLog", new ItemStack(Blocks.logs3.get(), 1, i));
        
        OreDictionary.registerOre("slabWood", new ItemStack(Blocks.woodenSingleSlab1.get()));
        OreDictionary.registerOre("slabWood", new ItemStack(Blocks.woodenSingleSlab2.get()));
        
        OreDictionary.registerOre("slabWood", new ItemStack(Blocks.woodenDoubleSlab1.get()));
        OreDictionary.registerOre("slabWood", new ItemStack(Blocks.woodenDoubleSlab2.get()));
        
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

        OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leavesColorized.get(), 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leaves1.get(), 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leaves2.get(), 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(Blocks.leavesFruit.get(), 1, OreDictionary.WILDCARD_VALUE));
    }
}
