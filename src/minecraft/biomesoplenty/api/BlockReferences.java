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
