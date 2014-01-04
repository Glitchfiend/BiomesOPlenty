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
    	//TODO:	  getBlock()
    	if (world.func_147439_a(x, y - 1, z) == Blocks.air) return false;
		
		//TODO:	canPlaceBlockAt()
		return func_149742_c(world, x, y, z);
	}
	
    @Override
	//TODO:		updateTick()
	public void func_149674_a(World world, int x, int y, int z, Random random)
    {
    	//TODO:				getBlock()
    	Block block = world.func_147439_a(x, y, z);
    	
        this.dropIfCantStay(world, x, y, z, new ItemStack(block, 1, world.getBlockMetadata(x, y, z)));
    }
	
    @Override
    //TODO:			canReplace()
    public boolean func_149705_a(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
    	//TODO:	  getBlock()
    	if (world.func_147439_a(x, y - 1, z) == Blocks.air) return false;

    	return isValidPosition(world, x, y, z, itemStack.getItemDamage());
    } 
	
    public void dropIfCantStay(World world, int x, int y, int z, ItemStack stack)
    {
    	//TODO:	  canReplace
        if (!this.func_149705_a(world, x, y, z, 0, stack))
        {
        	//TODO:	dropBlockAsItem()
            this.func_149697_b(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            //TODO:	setBlockToAir()
            world.func_147468_f(x, y, z);
        }
    }

	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		//TODO:												getBlock()
		dropIfCantStay(world, x, y, z, new ItemStack(world.func_147439_a(x, y, z), 1, world.getBlockMetadata(x, y, z)));
	}
}
