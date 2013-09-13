package biomesoplenty.integration;

import net.minecraft.block.Block;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.microblock.BlockMicroMaterial;
import biomesoplenty.api.Blocks;

public class MultipartIntegration
{
	protected static void init()
	{
		addMicroblock(Blocks.mud.get(), 0, 1);
		addMicroblock(Blocks.driedDirt.get());
		addMicroblock(Blocks.redRock.get(), 0, 2);
		addMicroblock(Blocks.ash.get());
		addMicroblock(Blocks.leaves1.get(), 0, 7);
		addMicroblock(Blocks.leaves2.get(), 0, 5);
		addMicroblock(Blocks.ashStone.get());
		addMicroblock(Blocks.hardIce.get());
		addMicroblock(Blocks.leavesFruit.get(), 0, 0);
		addMicroblock(Blocks.mudBrick.get());
		addMicroblock(Blocks.originGrass.get());
		addMicroblock(Blocks.longGrass.get());
		addMicroblock(Blocks.logs1.get(), 0, 3);
		addMicroblock(Blocks.logs2.get(), 0, 3);
		addMicroblock(Blocks.logs3.get(), 0, 3);
		addMicroblock(Blocks.logs4.get(), 0, 2);
		addMicroblock(Blocks.petals.get(), 0, 1);
		addMicroblock(Blocks.hardSand.get());
		addMicroblock(Blocks.hardDirt.get());
		addMicroblock(Blocks.crystal.get());
		addMicroblock(Blocks.holyGrass.get());
		addMicroblock(Blocks.holyDirt.get());
		addMicroblock(Blocks.holyStone.get(), 0, 2);
		addMicroblock(Blocks.amethystOre.get(), 0, 13);
		addMicroblock(Blocks.cragRock.get());
		addMicroblock(Blocks.cloud.get());
		addMicroblock(Blocks.planks.get(), 0, 13);
		addMicroblock(Blocks.leavesColorized.get(), 0, 5);
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
