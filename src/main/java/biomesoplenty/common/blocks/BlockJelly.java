package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockJelly extends Block
{
	private static final String[] jellyTypes = new String[] {"strawberry", "raspberry", "grape"};
	private IIcon[] textures;
	
	public BlockJelly()
	{
		//TODO: Material.glass
		super(Material.sponge);
		
		//TODO: this.setHardness
		this.setHardness(0.3F);
		
		//TODO setStepSound(Block.soundStoneFootstep)
		this.setStepSound(Block.soundTypeSnow);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[jellyTypes.length];

		for (int i = 0; i < jellyTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:jellyblock"+jellyTypes[i]);
		}
	}
	
	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
        if (meta < 0 || meta >= jellyTypes.length) 
        {
            meta = 0;
        }
        
		return textures[meta];
	}
	
	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < jellyTypes.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float yOffset = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - yOffset, z + 1);
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
