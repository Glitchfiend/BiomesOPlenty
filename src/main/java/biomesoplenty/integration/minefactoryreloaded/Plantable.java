package biomesoplenty.integration.minefactoryreloaded;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class Plantable implements IFactoryPlantable 
{
    protected int blockId;
    
    public Plantable(int blockId)
    {
        this.blockId = blockId;
    }

	@Override
	public int getSeedId() 
	{
		return this.blockId;
	}

	@Override
	public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack) 
	{
		return this.blockId;
	}

	@Override
	public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack) 
	{
		return stack.getItemDamage();
	}

	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) 
	{
		int groundId = world.getBlockId(x, y - 1, z);
        if(!world.isAirBlock(x, y, z))
        {
            return false;
        }
        return 
        		(Block.blocksList[blockId].canPlaceBlockAt(world, x, y, z) && Block.blocksList[blockId].canBlockStay(world, x, y, z)) ||
                (Block.blocksList[blockId] instanceof IPlantable && Block.blocksList[groundId] != null &&
                Block.blocksList[groundId].canSustainPlant(world, x, y, z, ForgeDirection.UP, ((IPlantable)Block.blocksList[blockId])));
        
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack) 
	{
		return;		
	}

	@Override
	public void postPlant(World world, int x, int y, int z, ItemStack stack) 
	{
		return;
	}
}
