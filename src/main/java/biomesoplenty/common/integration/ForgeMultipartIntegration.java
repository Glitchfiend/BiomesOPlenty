package biomesoplenty.common.integration;

import biomesoplenty.api.content.BOPCBlocks;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import net.minecraft.block.Block;

public class ForgeMultipartIntegration
{
	protected static void init()
	{
		addMicroblock(BOPCBlocks.mud);
		addMicroblock(BOPCBlocks.driedDirt);
		addMicroblock(BOPCBlocks.ash);
		addMicroblock(BOPCBlocks.ashStone);
		addMicroblock(BOPCBlocks.appleLeaves);
		addMicroblock(BOPCBlocks.bopGrass, 0, 1);
		addMicroblock(BOPCBlocks.colorizedLeaves1, 0, 3);
		addMicroblock(BOPCBlocks.colorizedLeaves2, 0, 3);
		addMicroblock(BOPCBlocks.persimmonLeaves);
		addMicroblock(BOPCBlocks.newBopGrass, 0, 2);
		addMicroblock(BOPCBlocks.newBopDirt, 0, 5);
		addMicroblock(BOPCBlocks.leaves1, 0, 3);
		addMicroblock(BOPCBlocks.leaves2, 0, 3);
		addMicroblock(BOPCBlocks.leaves3, 0, 3);
		addMicroblock(BOPCBlocks.leaves4, 0, 1);
		addMicroblock(BOPCBlocks.mudBricks);
		addMicroblock(BOPCBlocks.originGrass);
		addMicroblock(BOPCBlocks.longGrass);
		addMicroblock(BOPCBlocks.logs1, 0, 3);
		addMicroblock(BOPCBlocks.logs2, 0, 3);
		addMicroblock(BOPCBlocks.logs3, 0, 3);
		addMicroblock(BOPCBlocks.logs4, 0, 3);
		addMicroblock(BOPCBlocks.petals, 0, 1);
		addMicroblock(BOPCBlocks.hardDirt);
		addMicroblock(BOPCBlocks.hardIce);
		addMicroblock(BOPCBlocks.hardSand);
		addMicroblock(BOPCBlocks.rocks, 0, 5);
		addMicroblock(BOPCBlocks.crystal);
		addMicroblock(BOPCBlocks.flesh);
		addMicroblock(BOPCBlocks.hive, 0, 3);
		addMicroblock(BOPCBlocks.honeyBlock);
		addMicroblock(BOPCBlocks.gemOre, 0, 15);
		addMicroblock(BOPCBlocks.cragRock);
		addMicroblock(BOPCBlocks.planks, 0, 14);
	}

	// Register a microblock.
	private static void addMicroblock(Block b)
	{
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(b, 0), b.getUnlocalizedName());
	}

	// Register multiple microblocks. Meta range is inclusive.
	private static void addMicroblock(Block b, int metaFrom, int metaTo)
	{
		for(int i = metaFrom; i <= metaTo; i++)
		{
			MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(b, i), b.getUnlocalizedName() + "." + i);
		}
	}
}
