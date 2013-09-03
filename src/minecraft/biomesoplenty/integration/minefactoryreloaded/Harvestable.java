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
        Block b = Block.blocksList[getPlantId()];

        if(harvesterSettings.get("silkTouch") != null && harvesterSettings.get("silkTouch") && harvestType == HarvestType.TreeLeaf)
        {
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(new ItemStack(sourceId, 1, b.getDamageValue(world, x, y, z)));
            return drops;
        }
        else
        {
            return b.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
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
