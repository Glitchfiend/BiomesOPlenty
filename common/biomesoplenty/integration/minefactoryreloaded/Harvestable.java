package biomesoplenty.integration.minefactoryreloaded;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class Harvestable implements IFactoryHarvestable
{
    private int sourceId;
    private HarvestType harvestType;
    
    public Harvestable(int sourceId, HarvestType harvestType)
    {
        if(sourceId > Block.blocksList.length)
        {
            throw new IllegalArgumentException("Passed an Item ID to FactoryHarvestableStandard's source block argument");
        }
        this.sourceId = sourceId;
        this.harvestType = harvestType;
    }
    
    @Override
    public int getPlantId()
    {
        return sourceId;
    }
    
    @Override
    public HarvestType getHarvestType()
    {
        return harvestType;
    }
    
    @Override
    public boolean breakBlock()
    {
        return true;
    }
    
    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        if(harvesterSettings.get("silkTouch") != null && harvesterSettings.get("silkTouch") && harvestType == HarvestType.TreeLeaf)
        {
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(new ItemStack(sourceId, 1, world.getBlockMetadata(x, y, z)));
            return drops;
        }
//        else if(getPlantId() == Blocks.leavesFruit.get().blockID)
//        {
//            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
//            
//            int meta = world.getBlockMetadata(x, y, z);
//            if ((meta & 3) == 3)
//            {
//                drops.add(new ItemStack(Item.appleRed));
//            }
//            else if ((meta & 3) == 2)
//            {
//                if(rand.nextInt(16) == 0) drops.add(new ItemStack(Item.appleRed));
//                if(rand.nextInt(80) == 0) drops.add(new ItemStack(Block.sapling));
//            }
//            else if ((meta & 3) == 1)
//            {
//                if(rand.nextInt(64) == 0) drops.add(new ItemStack(Item.appleRed));
//                if(rand.nextInt(40) == 0) drops.add(new ItemStack(Block.sapling));
//            }
//            else if ((meta & 3) == 0)
//            {
//                if(rand.nextInt(20) == 0) drops.add(new ItemStack(Block.sapling));
//            }
//
//            return drops;
//        }
        else
        {
            return Block.blocksList[getPlantId()].getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }
    }
    
    @Override
    public void preHarvest(World world, int x, int y, int z)
    {
    }
    
    @Override
    public void postHarvest(World world, int x, int y, int z)
    {
    }
}
