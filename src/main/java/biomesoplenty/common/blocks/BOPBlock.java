package biomesoplenty.common.blocks;

import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BOPBlock extends Block
{
	protected BOPBlock(Material material)
	{
		super(material);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	public void updateTick(World world, int x, int y, int z, Random random)
    {
        this.checkAndDropBlock(world, x, y, z);
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        this.checkAndDropBlock(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, block);
    }
    
    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
    	return this.canBlockStay(world, x, y, z, itemStack.getItemDamage());
    } 
    
    @Override
	@Deprecated
    public boolean canBlockStay(World world, int x, int y, int z) 
    {
    	return super.canBlockStay(world, x, y, z);
    }
    
    public boolean canBlockStay(World world, int x, int y, int z, int metadata)
    {
    	return this.canBlockStay(world, x, y, z);
    }
	
    protected void checkAndDropBlock(World world, int x, int y, int z)
    {
        if (!this.canBlockStay(world, x, y, z, world.getBlockMetadata(x, y, z)))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
	
	@Override
    public boolean renderAsNormalBlock()
	{
		return this.isOpaqueCube();
	}
}
