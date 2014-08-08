package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCBlocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.BlockAsh;
import biomesoplenty.common.blocks.BlockBOPAppleLeaves;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves.ColourizedLeafCategory;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockBOPCoral;
import biomesoplenty.common.blocks.BlockBOPCoral.CoralCategory;
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
import biomesoplenty.common.blocks.BlockBOPNewGrass;
import biomesoplenty.common.blocks.BlockBOPPersimmonLeaves;
import biomesoplenty.common.blocks.BlockBOPPetals;
import biomesoplenty.common.blocks.BlockBOPPlank;
import biomesoplenty.common.blocks.BlockBOPPlant;
import biomesoplenty.common.blocks.BlockBOPRocks;
import biomesoplenty.common.blocks.BlockBOPSapling;
import biomesoplenty.common.blocks.BlockBOPSlab;
import biomesoplenty.common.blocks.BlockBOPSlab.SlabCategory;
import biomesoplenty.common.blocks.BlockBOPStairs;
import biomesoplenty.common.blocks.BlockBOPStairs.Category;
import biomesoplenty.common.blocks.BlockBamboo;
import biomesoplenty.common.blocks.BlockBones;
import biomesoplenty.common.blocks.BlockFlesh;
import biomesoplenty.common.blocks.BlockFlowerVine;
import biomesoplenty.common.blocks.BlockGrave;
import biomesoplenty.common.blocks.BlockHive;
import biomesoplenty.common.blocks.BlockHoney;
import biomesoplenty.common.blocks.BlockIvy;
import biomesoplenty.common.blocks.BlockLongGrass;
import biomesoplenty.common.blocks.BlockMoss;
import biomesoplenty.common.blocks.BlockMud;
import biomesoplenty.common.blocks.BlockOriginGrass;
import biomesoplenty.common.blocks.BlockOvergrownNetherrack;
import biomesoplenty.common.blocks.BlockStoneFormations;
import biomesoplenty.common.blocks.BlockTreeMoss;
import biomesoplenty.common.blocks.BlockTurnip;
import biomesoplenty.common.blocks.BlockWillow;
import biomesoplenty.common.fluids.blocks.BlockBloodFluid;
import biomesoplenty.common.fluids.blocks.BlockHoneyFluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;
import biomesoplenty.common.itemblocks.ItemBlockAppleLeaves;
import biomesoplenty.common.itemblocks.ItemBlockBOPMetadata;
import biomesoplenty.common.itemblocks.ItemBlockBamboo;
import biomesoplenty.common.itemblocks.ItemBlockBones;
import biomesoplenty.common.itemblocks.ItemBlockColorizedLeaves;
import biomesoplenty.common.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.common.itemblocks.ItemBlockCoral;
import biomesoplenty.common.itemblocks.ItemBlockFlower;
import biomesoplenty.common.itemblocks.ItemBlockFlower2;
import biomesoplenty.common.itemblocks.ItemBlockFoliage;
import biomesoplenty.common.itemblocks.ItemBlockGems;
import biomesoplenty.common.itemblocks.ItemBlockGrass;
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
import biomesoplenty.common.itemblocks.ItemBlockRocks;
import biomesoplenty.common.itemblocks.ItemBlockSapling;
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
		mud = registerBlock(new BlockMud().setBlockName("mud"), ItemBlockMud.class);
        driedDirt = registerBlock(new BlockBOPGeneric(Material.rock, BlockType.DRIED_DIRT).setBlockName("driedDirt"));
        rocks = registerBlock(new BlockBOPRocks().setBlockName("rocks"), ItemBlockRocks.class);
		ash = registerBlock(new BlockAsh().setBlockName("ash"));
        flesh = registerBlock(new BlockFlesh().setBlockName("flesh"));
        plants = registerBlock(new BlockBOPPlant().setBlockName("plants"), ItemBlockPlant.class);
        flowers = registerBlock(new BlockBOPFlower().setBlockName("flowers"), ItemBlockFlower.class);
        flowers2 = registerBlock(new BlockBOPFlower2().setBlockName("flowers2"), ItemBlockFlower2.class);
        stoneFormations = registerBlock(new BlockStoneFormations().setBlockName("stoneFormations"), ItemBlockStoneFormations.class);
        mushrooms = registerBlock(new BlockBOPMushroom().setBlockName("mushrooms"), ItemBlockMushroom.class);
        willow = registerBlock(new BlockWillow().setBlockName("willow"), ItemBlockWillow.class);
        ivy = registerBlock(new BlockIvy().setBlockName("ivy"), ItemBlockIvy.class);
        treeMoss = registerBlock(new BlockTreeMoss().setBlockName("treeMoss"));
        flowerVine = registerBlock(new BlockFlowerVine().setBlockName("flowerVine"));
        foliage = registerBlock(new BlockBOPFoliage().setBlockName("foliage"), ItemBlockFoliage.class);
        turnip = registerBlock(new BlockTurnip().setBlockName("turnip"));
        
        coral1 = registerBlock(new BlockBOPCoral(CoralCategory.CAT1).setBlockName("coral1"), ItemBlockCoral.class);
        coral2 = registerBlock(new BlockBOPCoral(CoralCategory.CAT2).setBlockName("coral2"), ItemBlockCoral.class);

        ashStone = registerBlock(new BlockBOPGeneric(Material.rock, BlockType.ASH_STONE).setBlockName("ashStone"));
        hardIce = registerBlock(new BlockBOPGeneric(Material.rock, BlockType.HARD_ICE).setBlockName("hardIce"));
        
        appleLeaves = registerBlock(new BlockBOPAppleLeaves().setBlockName("appleLeaves"), ItemBlockAppleLeaves.class);
        persimmonLeaves = registerBlock(new BlockBOPPersimmonLeaves().setBlockName("persimmonLeaves"), ItemBlockPersimmonLeaves.class);
        
        moss = registerBlock(new BlockMoss().setBlockName("moss"), ItemBlockMoss.class);
        bamboo = registerBlock(new BlockBamboo().setBlockName("bamboo"), ItemBlockBamboo.class);

        mudBricks = registerBlock(new BlockBOPGeneric(Material.rock, BlockType.MUD_BRICK).setBlockName("mudBricks"));
        
        originGrass = registerBlock(new BlockOriginGrass().setBlockName("originGrass"));
        longGrass = registerBlock(new BlockLongGrass().setBlockName("longGrass"));
        overgrownNetherrack = registerBlock(new BlockOvergrownNetherrack().setBlockName("overgrownNetherrack"));
        
        bopGrass = registerBlock(new BlockBOPGrass().setBlockName("bopGrass"), ItemBlockGrass.class);
        newBopGrass = registerMetadataBlock(new BlockBOPNewGrass().setBlockName("newBopGrass"));
        
		logs1 = registerBlock(new BlockBOPLog(LogCategory.CAT1).setBlockName("logs1"), ItemBlockLog.class);
		logs2 = registerBlock(new BlockBOPLog(LogCategory.CAT2).setBlockName("logs2"), ItemBlockLog.class);
		logs3 = registerBlock(new BlockBOPLog(LogCategory.CAT3).setBlockName("logs3"), ItemBlockLog.class);
		logs4 = registerBlock(new BlockBOPLog(LogCategory.CAT4).setBlockName("logs4"), ItemBlockLog.class);

        leaves1 = registerBlock(new BlockBOPLeaves(LeafCategory.CAT1).setBlockName("leaves1"), ItemBlockLeaves.class);
        leaves2 = registerBlock(new BlockBOPLeaves(LeafCategory.CAT2).setBlockName("leaves2"), ItemBlockLeaves.class);
        leaves3 = registerBlock(new BlockBOPLeaves(LeafCategory.CAT3).setBlockName("leaves3"), ItemBlockLeaves.class);
        leaves4 = registerBlock(new BlockBOPLeaves(LeafCategory.CAT4).setBlockName("leaves4"), ItemBlockLeaves.class);
		
		petals = registerBlock(new BlockBOPPetals().setBlockName("petals"), ItemBlockPetals.class);
		
		saplings = registerBlock(new BlockBOPSapling().setBlockName("saplings"), ItemBlockSapling.class);
		colorizedSaplings = registerBlock(new BlockBOPColorizedSapling().setBlockName("colorizedSaplings"), ItemBlockColorizedSapling.class);

        hardSand = registerBlock(new BlockBOPGeneric(Material.sand, BlockType.HARD_SAND).setBlockName("hardSand"));
        hardDirt = registerBlock(new BlockBOPGeneric(Material.rock, BlockType.HARD_DIRT).setBlockName("hardDirt"));

        biomeBlock = registerBlock(new BlockBOPGeneric(Material.glass, BlockType.BIOME_BLOCK).setBlockName("biomeBlock"));

        crystal = registerBlock(new BlockBOPGeneric(Material.glass, BlockType.CRYSTAL).setBlockName("crystal"));

		gemOre = registerBlock(new BlockBOPGems().setBlockName("gemOre"), ItemBlockGems.class);

        cragRock = registerBlock(new BlockBOPGeneric(Material.rock, BlockType.CRAG_ROCK).setBlockName("cragRock"));

		hive = registerBlock(new BlockHive().setBlockName("hive"), ItemBlockHive.class);
		honeyBlock = registerBlock(new BlockHoney().setBlockName("honeyBlock"));

		bones = registerBlock(new BlockBones().setBlockName("bones"), ItemBlockBones.class);
		grave = registerBlock(new BlockGrave().setBlockName("grave"), ItemBlockGrave.class);

		planks = registerBlock(new BlockBOPPlank().setBlockName("planks"), ItemBlockPlank.class);

		BlockBOPSlab singleSlab = (BlockBOPSlab)new BlockBOPSlab(false, Material.wood, SlabCategory.WOOD1).setBlockName("woodenSingleSlab1");
		BlockBOPSlab doubleSlab = (BlockBOPSlab)new BlockBOPSlab(true, Material.wood, SlabCategory.WOOD1).setBlockName("woodenDoubleSlab1");
		
		woodenSingleSlab1 = registerBlock(singleSlab, ItemBlockSlab.class, singleSlab, doubleSlab);
		woodenDoubleSlab1 = registerBlock(doubleSlab, ItemBlockSlab.class, singleSlab, doubleSlab);		

		singleSlab = (BlockBOPSlab)new BlockBOPSlab(false, Material.wood, SlabCategory.WOOD2).setBlockName("woodenSingleSlab2");
		doubleSlab = (BlockBOPSlab)new BlockBOPSlab(true, Material.wood, SlabCategory.WOOD2).setBlockName("woodenDoubleSlab2");
		
		woodenSingleSlab2 = registerBlock(singleSlab, ItemBlockSlab.class, singleSlab, doubleSlab);
		woodenDoubleSlab2 = registerBlock(doubleSlab, ItemBlockSlab.class, singleSlab, doubleSlab);
		
		singleSlab = (BlockBOPSlab)new BlockBOPSlab(false, Material.rock, SlabCategory.STONE).setBlockName("stoneSingleSlab");
		doubleSlab = (BlockBOPSlab)new BlockBOPSlab(true, Material.rock, SlabCategory.STONE).setBlockName("stoneDoubleSlab");
		
		stoneSingleSlab = registerBlock(singleSlab, ItemBlockSlab.class, singleSlab, doubleSlab);
		stoneDoubleSlab = registerBlock(doubleSlab, ItemBlockSlab.class, singleSlab, doubleSlab);
		
		sacredoakStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.SACREDOAK).setBlockName("sacredoakStairs"));
		cherryStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.CHERRY).setBlockName("cherryStairs"));
		darkStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.DARK).setBlockName("darkStairs"));
		firStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.FIR).setBlockName("firStairs"));
		etherealStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.ETHEREAL).setBlockName("etherealStairs"));
		magicStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.MAGIC).setBlockName("magicStairs"));
		mangroveStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.MANGROVE).setBlockName("mangroveStairs"));
		palmStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.PALM).setBlockName("palmStairs"));
		redwoodStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.REDWOOD).setBlockName("redwoodStairs"));
		willowStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.WILLOW).setBlockName("willowStairs"));
		pineStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.PINE).setBlockName("pineStairs"));
		hellBarkStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.HELL_BARK).setBlockName("hellBarkStairs"));
		jacarandaStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.JACARANDA).setBlockName("jacarandaStairs"));
		mahoganyStairs = registerBlock(new BlockBOPStairs(BOPCBlocks.planks, Category.MAHOGANY).setBlockName("mahoganyStairs"));

		mudBricksStairs = registerBlock(new BlockBOPStairs(mudBricks, Category.MUD_BRICKS).setBlockName("mudBricksStairs"));

        colorizedLeaves1 = registerBlock(new BlockBOPColorizedLeaves(ColourizedLeafCategory.CAT1).setBlockName("colorizedLeaves1"), ItemBlockColorizedLeaves.class);
        colorizedLeaves2 = registerBlock(new BlockBOPColorizedLeaves(ColourizedLeafCategory.CAT2).setBlockName("colorizedLeaves2"), ItemBlockColorizedLeaves.class);
        
        //Fluid Blocks
		poison = registerBlock(new BlockPoisonFluid().setBlockName("poison"));
		blood = registerBlock(new BlockBloodFluid().setBlockName("hell_blood"));
		honey = registerBlock(new BlockHoneyFluid().setBlockName("honey"));
	}
	
	private static void setFireInfo()
	{
		Blocks.fire.setFireInfo(BOPCBlocks.bamboo, 5, 5);
		Blocks.fire.setFireInfo(BOPCBlocks.appleLeaves, 30, 60);
		Blocks.fire.setFireInfo(BOPCBlocks.colorizedLeaves1, 30, 60);
		Blocks.fire.setFireInfo(BOPCBlocks.colorizedLeaves2, 30, 60);
		Blocks.fire.setFireInfo(BOPCBlocks.foliage, 60, 100);
		Blocks.fire.setFireInfo(BOPCBlocks.persimmonLeaves, 30, 60);
		Blocks.fire.setFireInfo(BOPCBlocks.petals, 30, 60);
		Blocks.fire.setFireInfo(BOPCBlocks.planks, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.plants, 60, 100);
		
		Blocks.fire.setFireInfo(BOPCBlocks.woodenSingleSlab1, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.woodenDoubleSlab1, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.woodenSingleSlab2, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.woodenDoubleSlab2, 5, 20);
		
		Blocks.fire.setFireInfo(BOPCBlocks.sacredoakStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.cherryStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.darkStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.firStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.etherealStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.magicStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.mangroveStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.palmStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.redwoodStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.willowStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.pineStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.hellBarkStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.jacarandaStairs, 5, 20);
		Blocks.fire.setFireInfo(BOPCBlocks.mahoganyStairs, 5, 20);
		
		Blocks.fire.setFireInfo(BOPCBlocks.moss, 15, 100);
		Blocks.fire.setFireInfo(BOPCBlocks.treeMoss, 15, 100);
		Blocks.fire.setFireInfo(BOPCBlocks.willow, 15, 100);
	}
	
	public static Block registerBlock(Block block)
	{
	    GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	    
	    return block;
	}
	
	public static Block registerMetadataBlock(Block block)
	{
		registerBlock(block, ItemBlockBOPMetadata.class);
		
		return block;
	}
	
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
    {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
        
        return block;
    }
    
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs)
    {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""), constructorArgs);
        
        return block;
    }
}
