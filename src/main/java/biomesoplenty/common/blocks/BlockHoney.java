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
		super(Material.glass);
		
		//TODO: this.setHardness
		this.setHardness(0.5F);
		
		//TODO setStepSound(Block.soundStoneFootstep)
		this.setStepSound(Block.soundTypePiston);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:honeyblock");
	}
	
    @Override
	//TODO:	   getRenderBlockPass()
	public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
	//TODO			shouldSideBeRendered
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return super.shouldSideBeRendered(world, x, y, z, 1 - side);
    }

    @Override
	//TODO:		   isOpaqueCube()
	public boolean isOpaqueCube()
    {
        return false;
    }
}
