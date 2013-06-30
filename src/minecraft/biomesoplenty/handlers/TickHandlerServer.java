package biomesoplenty.handlers;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerServer implements ITickHandler 
{
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		WorldServer worldserver = (WorldServer)tickData[0];
		
        Iterator iterator = worldserver.activeChunkSet.iterator();
        
        int rand = new Random().nextInt();
		
        while (iterator.hasNext())
        {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)iterator.next();
            int k = chunkcoordintpair.chunkXPos * 16;
            int l = chunkcoordintpair.chunkZPos * 16;
            worldserver.theProfiler.startSection("getChunk");
            Chunk chunk = worldserver.getChunkFromChunkCoords(chunkcoordintpair.chunkXPos, chunkcoordintpair.chunkZPos);
            worldserver.theProfiler.endStartSection("tickChunk");
            
        	if (worldserver.provider.canDoRainSnowIce(chunk) && worldserver.rand.nextInt(1000) == 0)
        	{
        		rand = rand * 3 + 1013904223;
        		int i1 = rand >> 2;
        		int j1 = i1 & 15;
        		int k1 = i1 >> 8 & 15;
        		int l1 = worldserver.getPrecipitationHeight(j1 + k, k1 + l);

        		if (worldserver.isRaining() && canCreatePuddle(worldserver, j1 + k, l1, k1 + l))
        		{
        			worldserver.setBlock(j1 + k, l1 - 1, k1 + l, Blocks.puddle.get().blockID);
        		}
        	}
        }
	}
	
    public boolean canCreatePuddle(WorldServer worldserver, int par1, int par2, int par3)
    {
        BiomeGenBase biomegenbase = worldserver.getBiomeGenForCoords(par1, par3);
        float f = biomegenbase.getFloatTemperature();

        if (f > 0.15F)
        {
            if (par2 >= 0 && par2 < 256 && worldserver.getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10)
            {
                int l = worldserver.getBlockId(par1, par2 - 1, par3);

                if (l == Block.dirt.blockID || l == Block.grass.blockID)
                {
                	if (worldserver.isBlockSolidOnSide(par1 + 1, par2 - 1, par3, ForgeDirection.UP) && worldserver.isBlockSolidOnSide(par1 - 1, par2 - 1, par3, ForgeDirection.UP) && worldserver.isBlockSolidOnSide(par1, par2 - 1, par3 + 1, ForgeDirection.UP) && worldserver.isBlockSolidOnSide(par1, par2 - 1, par3 - 1, ForgeDirection.UP))
                	{
                		return true;
                	}
                }
            }

            return false;
        }
        else
        {
        	return false;
        }
    }

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() 
	{
		return "BiomesOPlenty - World tick";
	}

}
