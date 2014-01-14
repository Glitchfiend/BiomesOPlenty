package biomesoplenty.common.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.WorldProviderSurface;

public class WorldProviderSurfaceBOP extends WorldProviderSurface
{
    @Override
	public boolean canCoordinateBeSpawn(int x, int z)
    {
    	//TODO:							getTopBlock()
    	Block topBlock = this.worldObj.func_147474_b(x, z);
    	
        return topBlock == Blocks.sand && this.worldChunkMgr.getBiomesToSpawnIn().contains(this.worldObj.getBiomeGenForCoordsBody(x, z));
    }
}
