package biomesoplenty.common.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.blocks.BlockAsh;
import biomesoplenty.common.blocks.BlockBOPAppleLeaves;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves.ColourizedLeafCategory;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockBOPCoral;
import biomesoplenty.common.blocks.BlockBOPFlower;
import biomesoplenty.common.blocks.BlockBOPFlower2;
import biomesoplenty.common.blocks.BlockBOPFoliage;
import biomesoplenty.common.blocks.BlockBOPGems;
import biomesoplenty.common.blocks.BlockBOPGeneric;
import biomesoplenty.common.blocks.BlockBOPGeneric.BlockType;
import biomesoplenty.common.blocks.BlockBOPGrass;
import biomesoplenty.common.blocks.BlockBOPLeaves;
import biomesoplenty.common.blocks.BlockBOPLeaves.LeafCategory;
import biomesoplenty.common.blocks.BlockBOPLog;
import biomesoplenty.common.blocks.BlockBOPLog.LogCategory;
import biomesoplenty.common.blocks.BlockBOPMushroom;
import biomesoplenty.common.blocks.BlockBOPPersimmonLeaves;
import biomesoplenty.common.blocks.BlockBOPPetals;
import biomesoplenty.common.blocks.BlockBOPPlank;
import biomesoplenty.common.blocks.BlockBOPPlant;
import biomesoplenty.common.blocks.BlockBOPRedRock;
import biomesoplenty.common.blocks.BlockBOPSapling;
import biomesoplenty.common.blocks.BlockBOPSkystone;
import biomesoplenty.common.blocks.BlockBOPSlab;
import biomesoplenty.common.blocks.BlockBOPSlab.SlabCategory;
import biomesoplenty.common.blocks.BlockBOPStairs;
import biomesoplenty.common.blocks.BlockBOPStairs.Category;
import biomesoplenty.common.blocks.BlockBamboo;
import biomesoplenty.common.blocks.BlockBones;
import biomesoplenty.common.blocks.BlockCandy;
import biomesoplenty.common.blocks.BlockCandy.BlockTypeCandy;
import biomesoplenty.common.blocks.BlockCloud;
import biomesoplenty.common.blocks.BlockFlesh;
import biomesoplenty.common.blocks.BlockFlowerVine;
import biomesoplenty.common.blocks.BlockFrostedCake;
import biomesoplenty.common.blocks.BlockGrave;
import biomesoplenty.common.blocks.BlockHive;
import biomesoplenty.common.blocks.BlockHoney;
import biomesoplenty.common.blocks.BlockIvy;
import biomesoplenty.common.blocks.BlockJelly;
import biomesoplenty.common.blocks.BlockLongGrass;
import biomesoplenty.common.blocks.BlockMoss;
import biomesoplenty.common.blocks.BlockMud;
import biomesoplenty.common.blocks.BlockOriginGrass;
import biomesoplenty.common.blocks.BlockOvergrownNetherrack;
import biomesoplenty.common.blocks.BlockStoneFormations;
import biomesoplenty.common.blocks.BlockTreeMoss;
import biomesoplenty.common.blocks.BlockTurnip;
import biomesoplenty.common.blocks.BlockWillow;
import biomesoplenty.common.itemblocks.ItemBlockAppleLeaves;
import biomesoplenty.common.itemblocks.ItemBlockBamboo;
import biomesoplenty.common.itemblocks.ItemBlockBones;
import biomesoplenty.common.itemblocks.ItemBlockColorizedLeaves;
import biomesoplenty.common.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.common.itemblocks.ItemBlockCoral;
import biomesoplenty.common.itemblocks.ItemBlockFlower;
import biomesoplenty.common.itemblocks.ItemBlockFlower2;
import biomesoplenty.common.itemblocks.ItemBlockFoliage;
import biomesoplenty.common.itemblocks.ItemBlockFrostedCake;
import biomesoplenty.common.itemblocks.ItemBlockGems;
import biomesoplenty.common.itemblocks.ItemBlockGrave;
import biomesoplenty.common.itemblocks.ItemBlockHive;
import biomesoplenty.common.itemblocks.ItemBlockIvy;
import biomesoplenty.common.itemblocks.ItemBlockJelly;
import biomesoplenty.common.itemblocks.ItemBlockLeaves;
import biomesoplenty.common.itemblocks.ItemBlockLog;
import biomesoplenty.common.itemblocks.ItemBlockMoss;
import biomesoplenty.common.itemblocks.ItemBlockMud;
import biomesoplenty.common.itemblocks.ItemBlockMushroom;
import biomesoplenty.common.itemblocks.ItemBlockPersimmonLeaves;
import biomesoplenty.common.itemblocks.ItemBlockPetals;
import biomesoplenty.common.itemblocks.ItemBlockPlank;
import biomesoplenty.common.itemblocks.ItemBlockPlant;
import biomesoplenty.common.itemblocks.ItemBlockRedRock;
import biomesoplenty.common.itemblocks.ItemBlockSapling;
import biomesoplenty.common.itemblocks.ItemBlockSkystone;
import biomesoplenty.common.itemblocks.ItemBlockSlab;
import biomesoplenty.common.itemblocks.ItemBlockStoneFormations;
import biomesoplenty.common.itemblocks.ItemBlockWillow;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlocks 
{
	public static void init()
	{
		registerBlocks();
		setFireInfo();
	}

	private static void registerBlocks()
	{
		registerBlock(new BlockMud().setBlockName("mud"), ItemBlockMud.class);
		//TODO:									   rock
        registerBlock(new BlockBOPGeneric(Material.rock, BlockType.DRIED_DIRT).setBlockName("driedDirt"));
        registerBlock(new BlockBOPRedRock().setBlockName("redRock"), ItemBlockRedRock.class);
		registerBlock(new BlockAsh().setBlockName("ash"));
        registerBlock(new BlockFlesh().setBlockName("flesh"));
        registerBlock(new BlockBOPPlant().setBlockName("plants"), ItemBlockPlant.class);
        registerBlock(new BlockBOPFlower().setBlockName("flowers"), ItemBlockFlower.class);
        registerBlock(new BlockBOPFlower2().setBlockName("flowers2"), ItemBlockFlower2.class);
        registerBlock(new BlockStoneFormations().setBlockName("stoneFormations"), ItemBlockStoneFormations.class);
        registerBlock(new BlockBOPMushroom().setBlockName("mushrooms"), ItemBlockMushroom.class);
        registerBlock(new BlockBOPCoral().setBlockName("coral"), ItemBlockCoral.class);
        registerBlock(new BlockWillow().setBlockName("willow"), ItemBlockWillow.class);
        registerBlock(new BlockIvy().setBlockName("ivy"), ItemBlockIvy.class);
        registerBlock(new BlockTreeMoss().setBlockName("treeMoss"));
        registerBlock(new BlockFlowerVine().setBlockName("flowerVine"));
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT1).setBlockName("leaves1"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT2).setBlockName("leaves2"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT3).setBlockName("leaves3"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT4).setBlockName("leaves4"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPFoliage().setBlockName("foliage"), ItemBlockFoliage.class);
        registerBlock(new BlockTurnip().setBlockName("turnip"));

        registerBlock(new BlockBOPGeneric(Material.rock, BlockType.ASH_STONE).setBlockName("ashStone"));
        registerBlock(new BlockBOPGeneric(Material.rock, BlockType.HARD_ICE).setBlockName("hardIce"));
        
        registerBlock(new BlockBOPAppleLeaves().setBlockName("appleLeaves"), ItemBlockAppleLeaves.class);
        registerBlock(new BlockBOPPersimmonLeaves().setBlockName("persimmonLeaves"), ItemBlockPersimmonLeaves.class);
        
        registerBlock(new BlockMoss().setBlockName("moss"), ItemBlockMoss.class);
        registerBlock(new BlockBamboo().setBlockName("bamboo"), ItemBlockBamboo.class);

        registerBlock(new BlockBOPGeneric(Material.rock, BlockType.MUD_BRICK).setBlockName("mudBricks"));
        
        registerBlock(new BlockOriginGrass().setBlockName("originGrass"));
        registerBlock(new BlockLongGrass().setBlockName("longGrass"));
        registerBlock(new BlockOvergrownNetherrack().setBlockName("overgrownNetherrack"));
        registerBlock(new BlockBOPGrass().setBlockName("grass"));
        
		registerBlock(new BlockBOPLog(LogCategory.CAT1).setBlockName("logs1"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT2).setBlockName("logs2"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT3).setBlockName("logs3"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT4).setBlockName("logs4"), ItemBlockLog.class);

		registerBlock(new BlockBOPPetals().setBlockName("petals"), ItemBlockPetals.class);
		
		registerBlock(new BlockBOPSapling().setBlockName("saplings"), ItemBlockSapling.class);
		registerBlock(new BlockBOPColorizedSapling().setBlockName("colorizedSaplings"), ItemBlockColorizedSapling.class);

        registerBlock(new BlockBOPGeneric(Material.sand, BlockType.HARD_SAND).setBlockName("hardSand"));
        registerBlock(new BlockBOPGeneric(Material.rock, BlockType.HARD_DIRT).setBlockName("hardDirt"));

        registerBlock(new BlockBOPGeneric(Material.sand, BlockType.HOLY_DIRT).setBlockName("holyDirt"));
        registerBlock(new BlockBOPSkystone().setBlockName("holyStone"), ItemBlockSkystone.class);

        registerBlock(new BlockBOPGeneric(Material.glass, BlockType.CRYSTAL).setBlockName("crystal"));

		registerBlock(new BlockBOPGems().setBlockName("gemOre"), ItemBlockGems.class);

        registerBlock(new BlockBOPGeneric(Material.rock, BlockType.CRAG_ROCK).setBlockName("cragRock"));

		registerBlock(new BlockCloud().setBlockName("cloud"));

		registerBlock(new BlockHive().setBlockName("hive"), ItemBlockHive.class);
		registerBlock(new BlockHoney().setBlockName("honeyBlock"));
		
		registerBlock(new BlockJelly().setBlockName("jellyBlock"), ItemBlockJelly.class);
		
		registerBlock(new BlockFrostedCake().setBlockName("frostedCake"), ItemBlockFrostedCake.class);
		registerBlock(new BlockCandy(Material.cake, BlockTypeCandy.CAKE).setBlockName("cakeBlock"));
		registerBlock(new BlockCandy(Material.cake, BlockTypeCandy.COOKIE).setBlockName("cookieBlock"));

		registerBlock(new BlockBones().setBlockName("bones"), ItemBlockBones.class);
		registerBlock(new BlockGrave().setBlockName("grave"), ItemBlockGrave.class);

		registerBlock(new BlockBOPPlank().setBlockName("planks"), ItemBlockPlank.class);

		BlockBOPSlab woodenSingleSlab1 = (BlockBOPSlab)new BlockBOPSlab(false, Material.wood, SlabCategory.WOOD1).setBlockName("woodenSingleSlab1");
		BlockBOPSlab woodenDoubleSlab1 = (BlockBOPSlab)new BlockBOPSlab(true, Material.wood, SlabCategory.WOOD1).setBlockName("woodenDoubleSlab1");
		
		registerBlock(woodenSingleSlab1, ItemBlockSlab.class, woodenSingleSlab1, woodenDoubleSlab1);
		registerBlock(woodenDoubleSlab1, ItemBlockSlab.class, woodenSingleSlab1, woodenDoubleSlab1);		

		BlockBOPSlab woodenSingleSlab2 = (BlockBOPSlab)new BlockBOPSlab(false, Material.wood, SlabCategory.WOOD2).setBlockName("woodenSingleSlab2");
		BlockBOPSlab woodenDoubleSlab2 = (BlockBOPSlab)new BlockBOPSlab(true, Material.wood, SlabCategory.WOOD2).setBlockName("woodenDoubleSlab2");
		
		registerBlock(woodenSingleSlab2, ItemBlockSlab.class, woodenSingleSlab2, woodenDoubleSlab2);
		registerBlock(woodenDoubleSlab2, ItemBlockSlab.class, woodenSingleSlab2, woodenDoubleSlab2);
		
		//TODO:															 stone
		BlockBOPSlab stoneSingleSlab = (BlockBOPSlab)new BlockBOPSlab(false, Material.rock, SlabCategory.STONE).setBlockName("stoneSingleSlab");
		//TODO:															 stone
		BlockBOPSlab stoneDoubleSlab = (BlockBOPSlab)new BlockBOPSlab(true, Material.rock, SlabCategory.STONE).setBlockName("stoneDoubleSlab");
		
		registerBlock(stoneSingleSlab, ItemBlockSlab.class, stoneSingleSlab, stoneDoubleSlab);
		registerBlock(stoneDoubleSlab, ItemBlockSlab.class, stoneSingleSlab, stoneDoubleSlab);
		
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.SACREDOAK).setBlockName("sacredoakStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.CHERRY).setBlockName("cherryStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.DARK).setBlockName("darkStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.FIR).setBlockName("firStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.HOLY).setBlockName("holyStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.MAGIC).setBlockName("magicStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.MANGROVE).setBlockName("mangroveStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.PALM).setBlockName("palmStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.REDWOOD).setBlockName("redwoodStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.WILLOW).setBlockName("willowStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.PINE).setBlockName("pineStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.HELL_BARK).setBlockName("hellBarkStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.JACARANDA).setBlockName("jacarandaStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.MAHOGANY).setBlockName("mahoganyStairs"));
		
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("redRock"), Category.RED_COBBLE).setBlockName("redCobbleStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("redRock"), Category.RED_BRICKS).setBlockName("redBricksStairs"));

		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("holyStone"), Category.HOLY_COBBLE).setBlockName("holyCobbleStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("holyStone"), Category.HOLY_BRICKS).setBlockName("holyBricksStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("redRock"), Category.MUD_BRICKS).setBlockName("mudBricksStairs"));

        registerBlock(new BlockBOPColorizedLeaves(ColourizedLeafCategory.CAT1).setBlockName("colorizedLeaves1"), ItemBlockColorizedLeaves.class);
        registerBlock(new BlockBOPColorizedLeaves(ColourizedLeafCategory.CAT2).setBlockName("colorizedLeaves2"), ItemBlockColorizedLeaves.class);
	}
	
	private static void setFireInfo()
	{
		Blocks.fire.setFireInfo(BOPBlockHelper.get("bamboo"), 5, 5);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("appleLeaves"), 30, 60);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("colorizedLeaves1"), 30, 60);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("colorizedLeaves2"), 30, 60);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("foliage"), 60, 100);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("persimmonLeaves"), 30, 60);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("petals"), 30, 60);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("planks"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("plants"), 60, 100);
		
		Blocks.fire.setFireInfo(BOPBlockHelper.get("woodenSingleSlab1"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("woodenDoubleSlab1"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("woodenSingleSlab2"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("woodenDoubleSlab2"), 5, 20);
		
		Blocks.fire.setFireInfo(BOPBlockHelper.get("sacredoakStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("cherryStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("darkStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("firStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("holyStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("magicStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("mangroveStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("palmStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("redwoodStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("willowStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("pineStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("hellBarkStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("jacarandaStairs"), 5, 20);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("mahoganyStairs"), 5, 20);
		
		Blocks.fire.setFireInfo(BOPBlockHelper.get("moss"), 15, 100);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("treeMoss"), 15, 100);
		Blocks.fire.setFireInfo(BOPBlockHelper.get("willow"), 15, 100);
	}
	
	public static void registerBlock(Block block)
	{
	    GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	}
	
    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
    {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
    }
    
    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs)
    {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""), null, constructorArgs);
    }
}
