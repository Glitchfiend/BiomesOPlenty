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

public class BlockBones extends Block 
{
	//Meta 3 & 4 used by alternate small bone rotations, 5 & 6 are used by alternate medium bone rotations
	private static final String[] boneTypes = new String[] {"bones_small", "bones_medium", "bones_large"};
	private IIcon[] textures;

	public BlockBones()
	{
		//TODO: Material.rock
		super(Material.field_151576_e);
		
		//TODO: this.setHardness
		this.func_149711_c(3.0F);
		
		setResistance(5.0F);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[boneTypes.length];

		for (int i = 0; i < boneTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+boneTypes[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		if (meta == 4 || meta == 5) {
			meta = 1;
		}

		return textures[meta];
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			return AxisAlignedBB.getBoundingBox(x + 0.374D, y, z + 0.374D, x + 0.626D, y + 1.0D, z + 0.626D);

		case 1:
			return AxisAlignedBB.getBoundingBox(x + 0.187D, y, z + 0.187D, x + 0.813D, y + 1.0D, z + 0.813D);

		case 3:
			return AxisAlignedBB.getBoundingBox(x + 0.374D, y + 0.374D, z, x + 0.626D, y + 0.626D, z + 1.00D);

		case 4:
			return AxisAlignedBB.getBoundingBox(x, y + 0.374D, z + 0.374D, x + 1.00D, y + 0.626D, z + 0.626D);

		case 5:
			return AxisAlignedBB.getBoundingBox(x + 0.187D, y + 0.187D, z, x + 0.813D, y + 0.813D, z + 1.00D);

		case 6:
			return AxisAlignedBB.getBoundingBox(x, y + 0.187D, z + 0.187D, x + 1.00D, y + 0.813D, z + 0.813D);

		default:
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
		}
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			return AxisAlignedBB.getBoundingBox(x + 0.374D, y, z + 0.374D, x + 0.626D, y + 1.0D, z + 0.626D);

		case 1:
			return AxisAlignedBB.getBoundingBox(x + 0.187D, y, z + 0.187D, x + 0.813D, y + 1.0D, z + 0.813D);

		case 3:
			return AxisAlignedBB.getBoundingBox(x + 0.374D, y + 0.374D, z, x + 0.626D, y + 0.626D, z + 1.00D);

		case 4:
			return AxisAlignedBB.getBoundingBox(x, y + 0.374D, z + 0.374D, x + 1.00D, y + 0.626D, z + 0.626D);

		case 5:
			return AxisAlignedBB.getBoundingBox(x + 0.187D, y + 0.187D, z, x + 0.813D, y + 0.813D, z + 1.00D);

		case 6:
			return AxisAlignedBB.getBoundingBox(x, y + 0.187D, z + 0.187D, x + 1.00D, y + 0.813D, z + 0.813D);

		default:
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
		}
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = iblockaccess.getBlockMetadata(x, y, z);

		float minX;
		float minY;
		float minZ;
		float maxX;
		float maxY;
		float maxZ;

		switch (meta)
		{
		case 0:
			minY = 0F;
			minX = minZ = 0.374F;
			maxX = maxZ = 0.626F;
			maxY = 1.0F;
			break;

		case 1:
			minY = 0F;
			minX = minZ = 0.187F;
			maxX = maxZ = 0.813F;
			maxY = 1.00F;
			break;

		case 3:
			minX = minY = 0.374F;
			minZ = 0F;
			maxX = maxY = 0.626F;
			maxZ = 1.00F;
			break;

		case 4:
			minX = 0F;
			minY = minZ = 0.374F;
			maxX = 1.00F;
			maxY = maxZ = 0.626F;
			break;

		case 5:
			minX = minY = 0.187F;
			minZ = 0F;
			maxX = maxY = 0.813F;
			maxZ = 1.00F;
			break;

		case 6:
			minX = 0F;
			minY = minZ = 0.187F;
			maxX = 1.00F;
			maxY = maxZ = 0.813F;
			break;

		default:
			minY = 0F;
			minX = minZ = 0.0F;
			maxX = maxZ = 1.0F;
			maxY = 1.0F;
			break;
		}

		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3 || meta == 4) {
			meta = 0;
		}
		if (meta == 5 || meta == 6) {
			meta = 1;
		}
		return meta;
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < boneTypes.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
	{
		return false;
	}

	@Override
	//TODO:		   renderAsNormalBlock()
    public boolean func_149686_d()
    {
        return false;
    }

	@Override
	//TODO		getRenderType()
	public int func_149645_b()
	{
		return RenderUtils.bonesModel;
	}

	@Override
	//TODO			shouldSideBeRendered
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		if (meta == 3 || meta == 4) 
		{
			meta = 0;
		}
		if (meta == 5 || meta == 6) 
		{
			meta = 1;
		}
		return meta;
	}
}
