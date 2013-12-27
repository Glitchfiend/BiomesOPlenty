package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.BiomesOPlenty;

public class BlockHoney extends Block
{
	public BlockHoney()
	{
		//TODO: Material.glass
		super(Material.field_151592_s);
		
		//TODO: this.setHardness
		this.func_149711_c(0.5F);
		
		//TODO setStepSound(Block.soundStoneFootstep)
		this.func_149672_a(Block.field_149780_i);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:honeyblock");
	}
	
    @Override
	//TODO:	   getRenderBlockPass()
	public int func_149701_w()
    {
        return 1;
    }
    
    @Override
	//TODO			shouldSideBeRendered
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
    {
        return super.func_149646_a(world, x, y, z, 1 - side);
    }

    @Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
    {
        return false;
    }
}
