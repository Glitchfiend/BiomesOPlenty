package biomesoplenty.api.content;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.blocks.BlockAsh;
import biomesoplenty.common.blocks.BlockBOPAppleLeaves;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockBOPCoral;
import biomesoplenty.common.blocks.BlockBOPFlower;
import biomesoplenty.common.blocks.BlockBOPFlower2;
import biomesoplenty.common.blocks.BlockBOPFoliage;
import biomesoplenty.common.blocks.BlockBOPGems;
import biomesoplenty.common.blocks.BlockBOPGeneric;
import biomesoplenty.common.blocks.BlockBOPGrass;
import biomesoplenty.common.blocks.BlockBOPLeaves;
import biomesoplenty.common.blocks.BlockBOPLog;
import biomesoplenty.common.blocks.BlockBOPMushroom;
import biomesoplenty.common.blocks.BlockBOPPersimmonLeaves;
import biomesoplenty.common.blocks.BlockBOPPetals;
import biomesoplenty.common.blocks.BlockBOPPlank;
import biomesoplenty.common.blocks.BlockBOPPlant;
import biomesoplenty.common.blocks.BlockBOPRocks;
import biomesoplenty.common.blocks.BlockBOPSapling;
import biomesoplenty.common.blocks.BlockBOPSlab;
import biomesoplenty.common.blocks.BlockBOPStairs;
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
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves.ColourizedLeafCategory;
import biomesoplenty.common.blocks.BlockBOPCoral.CoralCategory;
import biomesoplenty.common.blocks.BlockBOPGeneric.BlockType;
import biomesoplenty.common.blocks.BlockBOPLeaves.LeafCategory;
import biomesoplenty.common.blocks.BlockBOPLog.LogCategory;
import biomesoplenty.common.blocks.BlockBOPSlab.SlabCategory;
import biomesoplenty.common.blocks.BlockBOPStairs.Category;
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

public class BOPCBlocks 
{
	public static Block mud;
	public static Block driedDirt;
	public static Block rocks;
	public static Block ash;
	public static Block flesh;
	public static Block plants;
	public static Block flowers;
	public static Block flowers2;
	public static Block stoneFormations;
	public static Block mushrooms;
	public static Block willow;
	public static Block ivy;
	public static Block treeMoss;
	public static Block flowerVine;
	public static Block foliage;
	public static Block turnip;
	
	public static Block coral1;
	public static Block coral2;

	public static Block ashStone;
	public static Block hardIce;
    
	public static Block appleLeaves;
	public static Block persimmonLeaves;

	public static Block moss;
	public static Block bamboo;

	public static Block mudBricks;
	
	public static Block originGrass;
	public static Block longGrass;
	public static Block overgrownNetherrack;
	public static Block bopGrass;
    
	public static Block logs1;
	public static Block logs2;
	public static Block logs3;
	public static Block logs4;
	
	public static Block leaves1;
	public static Block leaves2;
	public static Block leaves3;
	public static Block leaves4;
	
	public static Block petals;
	
	public static Block saplings;
	public static Block colorizedSaplings;

	public static Block hardSand;
	public static Block hardDirt;
	
	public static Block biomeBlock;
	
	public static Block crystal;
	
	public static Block gemOre;
	
	public static Block cragRock;
	
	public static Block hive;
	public static Block honeyBlock;
	
	public static Block bones;
	public static Block grave;
	
	public static Block planks;

	public static Block woodenSingleSlab1;
	public static Block woodenDoubleSlab1;
	
	public static Block woodenSingleSlab2;
	public static Block woodenDoubleSlab2;
	
	public static Block stoneSingleSlab;
	public static Block stoneDoubleSlab;

	public static Block sacredoakStairs;
	public static Block cherryStairs;
	public static Block darkStairs;
	public static Block firStairs;
	public static Block holyStairs;
	public static Block magicStairs;
	public static Block mangroveStairs;
	public static Block palmStairs;
	public static Block redwoodStairs;
	public static Block willowStairs;
	public static Block pineStairs;
	public static Block hellBarkStairs;
	public static Block jacarandaStairs;
	public static Block mahoganyStairs;

	public static Block mudBricksStairs;
	
	public static Block colorizedLeaves1;
	public static Block colorizedLeaves2;
	
	//Fluid blocks
	public static Block poison;
	public static Block springWater;
	public static Block honey;
}
