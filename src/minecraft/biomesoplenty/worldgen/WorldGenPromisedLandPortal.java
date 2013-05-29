package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenPromisedLandPortal extends WorldGenerator
{
    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
		var1.setBlock(0, 64, 0, Blocks.promisedPortal.get().blockID);
		var1.setBlock(0, 64, 1, Blocks.promisedPortal.get().blockID);
		var1.setBlock(1, 64, 0, Blocks.promisedPortal.get().blockID);
		var1.setBlock(0, 64, 1, Blocks.promisedPortal.get().blockID);
		var1.setBlock(0, 65, 0, 0);
		var1.setBlock(0, 65, 1, 0);
		var1.setBlock(1, 65, 0, 0);
		var1.setBlock(0, 65, 1, 0);
		var1.setBlock(0, 66, 0, 0);
		var1.setBlock(0, 66, 1, 0);
		var1.setBlock(1, 66, 0, 0);
		var1.setBlock(0, 66, 1, 0);
		var1.setBlock(0, 67, 0, 0);
		var1.setBlock(0, 67, 1, 0);
		var1.setBlock(1, 67, 0, 0);
		var1.setBlock(0, 67, 1, 0);
		var1.setBlock(0, 68, 0, 0);
		var1.setBlock(0, 68, 1, 0);
		var1.setBlock(1, 68, 0, 0);
		var1.setBlock(0, 68, 1, 0);
		var1.setBlock(0, 69, 0, 0);
		var1.setBlock(0, 69, 1, 0);
		var1.setBlock(1, 69, 0, 0);
		var1.setBlock(0, 69, 1, 0);
		var1.setBlock(0, 70, 0, 0);
		var1.setBlock(0, 70, 1, 0);
		var1.setBlock(1, 70, 0, 0);
		var1.setBlock(0, 70, 1, 0);
		var1.setBlock(0, 71, 0, 0);
		var1.setBlock(0, 71, 1, 0);
		var1.setBlock(1, 71, 0, 0);
		var1.setBlock(0, 71, 1, 0);
		var1.setBlock(0, 72, 0, 0);
		var1.setBlock(0, 72, 1, 0);
		var1.setBlock(1, 72, 0, 0);
		var1.setBlock(0, 72, 1, 0);
		var1.setBlock(0, 73, 0, 0);
		var1.setBlock(0, 73, 1, 0);
		var1.setBlock(1, 73, 0, 0);
		var1.setBlock(0, 73, 1, 0);
		var1.setBlock(0, 74, 0, 0);
		var1.setBlock(0, 74, 1, 0);
		var1.setBlock(1, 74, 0, 0);
		var1.setBlock(0, 74, 1, 0);
		
		return true;
    }
}
