package biomesoplenty.api;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


import com.google.common.base.Optional;

public class BlockReferences {

	public static enum EnumBlocks
	{
		acaciaLog (Blocks.logs1, 0),
		cherryLog (Blocks.logs1, 1),
		darkLog (Blocks.logs1, 2),
		firLog (Blocks.logs2, 3),
		holyLog (Blocks.logs1, 0),
		magicLog (Blocks.logs2, 1),
		mangroveLog (Blocks.logs2, 2),
		palmLog (Blocks.logs2, 3),
		redwoodLog (Blocks.logs3, 0),
		willowLog (Blocks.logs3, 1),
		deadLog (Blocks.logs3, 2),
		bigFlowerStem (Blocks.logs3, 3),
		
		acaciaPlank (Blocks.planks, 0),
		cherryPlank (Blocks.planks, 1),
		darkPlank (Blocks.planks, 2),
		firPlank (Blocks.planks, 3),
		holyPlank (Blocks.planks, 4),
		magicPlank (Blocks.planks, 5),
		mangrovePlank (Blocks.planks, 6),
		palmPlank (Blocks.planks, 7),
		redwoodPlank (Blocks.planks, 8),
		willowPlank (Blocks.planks, 9),
		bambooThatching (Blocks.planks, 10),
		
		acaciaLeaves (Blocks.leavesColorized, 0),
		mangroveLeaves (Blocks.leavesColorized, 1),
		palmLeaves (Blocks.leavesColorized, 2),
		redwoodLeaves (Blocks.leavesColorized, 3),
		willowLeaves (Blocks.leavesColorized, 4),
		
		yellowAutumnLeaves (Blocks.leaves1, 0),
		bambooLeaves (Blocks.leaves1, 1),
		magicLeaves (Blocks.leaves1, 2),
		darkLeaves (Blocks.leaves1, 3),
		deadLeaves (Blocks.leaves1, 4),
		firLeaves (Blocks.leaves1, 5),
		holyLeaves (Blocks.leaves1, 6),
		orangeAutumnLeaves (Blocks.leaves1, 7),
		originLeaves (Blocks.leaves2, 0),
		pinkCherryLeaves (Blocks.leaves2, 1),
		mapleLeaves (Blocks.leaves2, 2),
		whiteCherryLeaves (Blocks.leaves2, 3),
		
		appleLeaves (Blocks.leavesFruit, 3),
		appleLeavesFruitless (Blocks.leavesFruit, 0),
		
		bamboo (Blocks.bamboo, 0),
		
		barley (Blocks.plants, 6),
		cattail (Blocks.plants, 7),
		;

		public Optional<? extends Block> block;
		public int meta;

		private EnumBlocks(Optional<? extends Block> block, int meta) {
			this.block = block;
			this.meta = meta;
		}
		
		public Optional<? extends Block> getBlock() {
			return block;
		}
		 
		public int getMeta() {
			return meta;
		}
	}

	public static ItemStack getBlockItemStack(String string)
	{
		Optional<? extends Block> stackblock = EnumBlocks.valueOf(string).block;
		int stackmeta = EnumBlocks.valueOf(string).meta;
		
		if (stackmeta != 0)
		{
			return new ItemStack(stackblock.get(), 1, stackmeta);
		}
		else
		{
			return new ItemStack(stackblock.get(), 1);
		}
	}
	
	public static int getBlockID(String string)
	{
		Optional<? extends Block> stackblock = EnumBlocks.valueOf(string).block;

		return stackblock.get().blockID;
	}
	
	public static int getBlockMeta(String string)
	{
		int stackmeta = EnumBlocks.valueOf(string).meta;

		return stackmeta;
	}
}
