package biomesoplenty.integration.minefactoryreloaded;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryFruit;

public class FruitLeaves implements IFactoryFruit
{
    private int sourceId;
    
    public FruitLeaves(int sourceId)
    {
        if(sourceId > Block.blocksList.length)
        {
            throw new IllegalArgumentException("Passed an Item ID to FactoryHarvestableStandard's source block argument");
        }
        this.sourceId = sourceId;
    }
    
    @Override
    public int getSourceBlockId()
    {
        return sourceId;
    }
    
    @Override
    public boolean canBePicked(World world, int x, int y, int z)
    {
        return (world.getBlockMetadata(x, y, z) & 3) == 3;
    }
    
    @Override
    public ItemStack getReplacementBlock(World world, int x, int y, int z)
    {
        return new ItemStack(world.getBlockId(x, y, z), 1, world.getBlockMetadata(x,y,z));
    }
    
    @Override
    public void prePick(World world, int x, int y, int z)
    {
    }
    
    @Override
    public List<ItemStack> getDrops(World world, Random rand, int x, int y, int z)
    {
        ArrayList<ItemStack> result = new ArrayList<ItemStack>();
        result.add(new ItemStack(Item.appleRed));
        return result;
    }
    
    @Override
    public void postPick(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x,y,z) - 3, 2);
    }
}
