package biomesoplenty.common.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import biomesoplenty.common.blocks.BlockCloud;
import biomesoplenty.common.blocks.BlockFlesh;
import biomesoplenty.common.blocks.BlockGrave;
import biomesoplenty.common.blocks.BlockHive;
import biomesoplenty.common.blocks.BlockHoney;
import biomesoplenty.common.blocks.BlockIvy;
import biomesoplenty.common.blocks.BlockLongGrass;
import biomesoplenty.common.blocks.BlockMoss;
import biomesoplenty.common.blocks.BlockMud;
import biomesoplenty.common.blocks.BlockOriginGrass;
import biomesoplenty.common.blocks.BlockOvergrownNetherrack;
import biomesoplenty.common.blocks.BlockPromisedPortal;
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
import biomesoplenty.common.itemblocks.ItemBlockGems;
import biomesoplenty.common.itemblocks.ItemBlockGrave;
import biomesoplenty.common.itemblocks.ItemBlockHive;
import biomesoplenty.common.itemblocks.ItemBlockIvy;
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
import biomesoplenty.common.itemblocks.ItemBlockTurnip;
import biomesoplenty.common.itemblocks.ItemBlockWillow;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlocks 
{
	public static void init()
	{
		initializeBlocks();
	}

	private static void initializeBlocks()
	{
		// Block declaration

		//TODO:						setBlockName
		registerBlock(new BlockMud().func_149663_c("mud"), ItemBlockMud.class);
		//TODO:									   rock
        registerBlock(new BlockBOPGeneric(Material.field_151576_e, BlockType.DRIED_DIRT).func_149663_c("driedDirt"));
        registerBlock(new BlockBOPRedRock().func_149663_c("redRock"), ItemBlockRedRock.class);
		registerBlock(new BlockAsh().func_149663_c("ash"));
        registerBlock(new BlockFlesh().func_149663_c("flesh"));
        registerBlock(new BlockBOPPlant().func_149663_c("plants"), ItemBlockPlant.class);
        registerBlock(new BlockBOPFlower().func_149663_c("flowers"), ItemBlockFlower.class);
        registerBlock(new BlockBOPFlower2().func_149663_c("flowers2"), ItemBlockFlower2.class);
        registerBlock(new BlockStoneFormations().func_149663_c("stoneFormations"), ItemBlockStoneFormations.class);
        registerBlock(new BlockBOPMushroom().func_149663_c("mushrooms"), ItemBlockMushroom.class);
        registerBlock(new BlockBOPCoral().func_149663_c("coral"), ItemBlockCoral.class);
        registerBlock(new BlockWillow().func_149663_c("willow"), ItemBlockWillow.class);
        registerBlock(new BlockIvy().func_149663_c("ivy"), ItemBlockIvy.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT1).func_149663_c("leaves1"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT2).func_149663_c("leaves2"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT3).func_149663_c("leaves3"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPLeaves(LeafCategory.CAT4).func_149663_c("leaves4"), ItemBlockLeaves.class);
        registerBlock(new BlockBOPFoliage().func_149663_c("foliage"), ItemBlockFoliage.class);
        registerBlock(new BlockTurnip().func_149663_c("turnip"), ItemBlockTurnip.class);
		//TODO:									   rock
        registerBlock(new BlockBOPGeneric(Material.field_151576_e, BlockType.ASH_STONE).func_149663_c("ashStone"));
		//TODO:									   rock
        registerBlock(new BlockBOPGeneric(Material.field_151576_e, BlockType.HARD_ICE).func_149663_c("hardIce"));
        
        registerBlock(new BlockBOPAppleLeaves().func_149663_c("appleLeaves"), ItemBlockAppleLeaves.class);
        registerBlock(new BlockBOPPersimmonLeaves().func_149663_c("persimmonLeaves"), ItemBlockPersimmonLeaves.class);
        
        registerBlock(new BlockBamboo().func_149663_c("bamboo"), ItemBlockBamboo.class);
        //TODO:							  		   rock
        registerBlock(new BlockBOPGeneric(Material.field_151576_e, BlockType.MUD_BRICK).func_149663_c("mudBricks"));
        
        registerBlock(new BlockOriginGrass().func_149663_c("originGrass"));
        registerBlock(new BlockLongGrass().func_149663_c("longGrass"));
        registerBlock(new BlockOvergrownNetherrack().func_149663_c("overgrownNetherrack"));
        registerBlock(new BlockBOPGrass().func_149663_c("grass"));
        
        registerBlock(new BlockTreeMoss().func_149663_c("treeMoss"));
        
		registerBlock(new BlockBOPLog(LogCategory.CAT1).func_149663_c("logs1"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT2).func_149663_c("logs2"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT3).func_149663_c("logs3"), ItemBlockLog.class);
		registerBlock(new BlockBOPLog(LogCategory.CAT4).func_149663_c("logs4"), ItemBlockLog.class);

		registerBlock(new BlockBOPPetals().func_149663_c("petals"), ItemBlockPetals.class);
		
		registerBlock(new BlockBOPSapling().func_149663_c("saplings"), ItemBlockSapling.class);
		registerBlock(new BlockBOPColorizedSapling().func_149663_c("colorizedSaplings"), ItemBlockColorizedSapling.class);
		
        //TODO:							  		   sand
        registerBlock(new BlockBOPGeneric(Material.field_151595_p, BlockType.HARD_SAND).func_149663_c("hardSand"));
        //TODO:							  		   rock
        registerBlock(new BlockBOPGeneric(Material.field_151576_e, BlockType.HARD_DIRT).func_149663_c("hardDirt"));
		
		//TODO:									   sand
        registerBlock(new BlockBOPGeneric(Material.field_151595_p, BlockType.HOLY_DIRT).func_149663_c("holyDirt"));
        registerBlock(new BlockBOPSkystone().func_149663_c("holyStone"), ItemBlockSkystone.class);
  
		//TODO:									   glass
        registerBlock(new BlockBOPGeneric(Material.field_151592_s, BlockType.CRYSTAL).func_149663_c("crystal"));
        registerBlock(new BlockPromisedPortal().func_149663_c("promisedPortal"));

		registerBlock(new BlockBOPGems().func_149663_c("gemOre"), ItemBlockGems.class);
       
		registerBlock(new BlockMoss().func_149663_c("moss"), ItemBlockMoss.class);

		//TODO:									   rock
        registerBlock(new BlockBOPGeneric(Material.field_151576_e, BlockType.CRAG_ROCK).func_149663_c("cragRock"));

		registerBlock(new BlockCloud().func_149663_c("cloud"));

		registerBlock(new BlockHive().func_149663_c("hive"), ItemBlockHive.class);
		registerBlock(new BlockHoney().func_149663_c("honeyBlock"));

		registerBlock(new BlockBones().func_149663_c("bones"), ItemBlockBones.class);
		registerBlock(new BlockGrave().func_149663_c("grave"), ItemBlockGrave.class);

		registerBlock(new BlockBOPPlank().func_149663_c("planks"), ItemBlockPlank.class);

		//TODO:										  wood
		registerBlock(new BlockBOPSlab(true, Material.field_151575_d, SlabCategory.WOOD1).func_149663_c("woodenDoubleSlab1"), ItemBlockSlab.class);
		//TODO:										  wood
		registerBlock(new BlockBOPSlab(false, Material.field_151575_d, SlabCategory.WOOD1).func_149663_c("woodenSingleSlab1"), ItemBlockSlab.class);
		//TODO:										  wood
		registerBlock(new BlockBOPSlab(true, Material.field_151575_d, SlabCategory.WOOD2).func_149663_c("woodenDoubleSlab2"), ItemBlockSlab.class);
		//TODO:										  wood
		registerBlock(new BlockBOPSlab(false, Material.field_151575_d, SlabCategory.WOOD2).func_149663_c("woodenSingleSlab2"), ItemBlockSlab.class);

		//TODO:										  stone
		registerBlock(new BlockBOPSlab(true, Material.field_151576_e, SlabCategory.STONE).func_149663_c("stoneDoubleSlab"), ItemBlockSlab.class);
		//TODO:										  stone
		registerBlock(new BlockBOPSlab(false, Material.field_151576_e, SlabCategory.STONE).func_149663_c("stoneSingleSlab"), ItemBlockSlab.class);
		
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.ACACIA).func_149663_c("acaciaStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.CHERRY).func_149663_c("cherryStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.DARK).func_149663_c("darkStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.FIR).func_149663_c("firStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.HOLY).func_149663_c("holyStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.MAGIC).func_149663_c("magicStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.MANGROVE).func_149663_c("mangroveStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.PALM).func_149663_c("palmStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.REDWOOD).func_149663_c("redwoodStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.WILLOW).func_149663_c("willowStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.PINE).func_149663_c("pineStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.HELL_BARK).func_149663_c("hellBarkStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("planks"), Category.JACARANDA).func_149663_c("jacarandaStairs"));
		
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("redRock"), Category.RED_COBBLE).func_149663_c("redCobbleStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("redRock"), Category.RED_BRICKS).func_149663_c("redBricksStairs"));

		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("holyStone"), Category.HOLY_COBBLE).func_149663_c("holyCobbleStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("holyStone"), Category.HOLY_BRICKS).func_149663_c("holyBricksStairs"));
		registerBlock(new BlockBOPStairs(BOPBlockHelper.get("redRock"), Category.MUD_BRICKS).func_149663_c("mudBricksStairs"));

        registerBlock(new BlockBOPColorizedLeaves(ColourizedLeafCategory.CAT1).func_149663_c("colorizedLeaves1"), ItemBlockColorizedLeaves.class);
        registerBlock(new BlockBOPColorizedLeaves(ColourizedLeafCategory.CAT2).func_149663_c("colorizedLeaves2"), ItemBlockColorizedLeaves.class);
	}
	
	public static void registerBlock(Block block)
	{
		//TODO: 								getUnlocalizedName()
	    GameRegistry.registerBlock(block, block.func_149739_a().replace("tile.", ""));
	}
	
    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
    {
		//TODO: 												getUnlocalizedName()
        GameRegistry.registerBlock(block, itemBlockClass, block.func_149739_a().replace("tile.", ""));
    }
}
