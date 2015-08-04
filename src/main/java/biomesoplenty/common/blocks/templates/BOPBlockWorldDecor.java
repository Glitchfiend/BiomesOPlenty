package biomesoplenty.common.blocks.templates;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BOPBlockWorldDecor extends BlockBush
{
	public BOPBlockWorldDecor(Material material)
	{
		super(material);
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
    	if (world.getBlock(x, y - 1, z) == Blocks.air) return false;
		
		return canPlaceBlockAt(world, x, y, z);
	}
	
    @Override
	public void updateTick(World world, int x, int y, int z, Random random)
    {
    	Block block = world.getBlock(x, y, z);
    	
        this.dropIfCantStay(world, x, y, z, new ItemStack(block, 1, world.getBlockMetadata(x, y, z)));
    }
	
    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
    	if (world.getBlock(x, y - 1, z) == Blocks.air) return false;
    	
    	return isValidPosition(world, x, y, z, itemStack != null ? itemStack.getItemDamage() : 0);
    } 
	
    public void dropIfCantStay(World world, int x, int y, int z, ItemStack stack)
    {
        if (!this.canReplace(world, x, y, z, 0, stack))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		dropIfCantStay(world, x, y, z, new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z)));
	}
}
