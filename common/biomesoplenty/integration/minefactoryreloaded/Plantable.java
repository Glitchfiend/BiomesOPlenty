package biomesoplenty.integration.minefactoryreloaded;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class Plantable implements IFactoryPlantable
{
    protected int sourceId;
    protected int plantedBlockId;
    
    public Plantable(int sourceId, int plantedBlockId)
    {
        if(plantedBlockId >= Block.blocksList.length)
        {
            throw new IllegalArgumentException("Passed an Item ID to FactoryPlantableStandard's planted block argument");
        }
        this.sourceId = sourceId;
        this.plantedBlockId = plantedBlockId;
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
                (Block.blocksList[plantedBlockId].canPlaceBlockAt(world, x, y, z) && Block.blocksList[plantedBlockId].canBlockStay(world, x, y, z)) ||
                (Block.blocksList[plantedBlockId] instanceof IPlantable && Block.blocksList[groundId] != null &&
                Block.blocksList[groundId].canSustainPlant(world, x, y, z, ForgeDirection.UP, ((IPlantable)Block.blocksList[plantedBlockId])));
    }
    
    @Override
    public void prePlant(World world, int x, int y, int z, ItemStack stack)
    {
    }
    
    @Override
    public void postPlant(World world, int x, int y, int z, ItemStack stack)
    {
    }
    
    @Override
    public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack)
    {
        if(stack.itemID != sourceId)
        {
            return -1;
        }
        return plantedBlockId;
    }
    
    @Override
    public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack)
    {
        return stack.getItemDamage();
    }
    
    @Override
    public int getSeedId()
    {
        return sourceId;
    }
}
