package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBones extends Block {
	//Meta 3 & 4 used by alternate medium bone rotations
    private static final String[] boneTypes = new String[] {"bones_small", "bones_medium", "bones_large"};
    private Icon[] textures;

    public BlockBones(int blockID)
    {
        super(blockID, Material.rock); 
        setHardness(3.0F);
        setResistance(5.0F);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[boneTypes.length];
        
        for (int i = 0; i < boneTypes.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+boneTypes[i]);
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        if (meta == 3 || meta == 4)
        	meta = 1;
        	
        return textures[meta];
    }

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0)
		{
			return AxisAlignedBB.getBoundingBox((double) x + 0.25D, y, (double) z + 0.25D, (double) x + 0.75D, (double) y + 0.5D, (double) z + 0.75D);
		}
		else if (meta == 1)
		{
			return AxisAlignedBB.getBoundingBox((double) x + 0.125D, y, (double) z + 0.125D, (double) x + 0.875D, (double) y + 1.0D, (double) z + 0.875D);
		}
		else if (meta == 3)
		{
			return AxisAlignedBB.getBoundingBox((double) x + 0.125D, y + 0.125D, (double) z, (double) x + 0.875D, (double) y + 0.875D, (double) z + 1.00D);
		}
		else if (meta == 4)
		{
			return AxisAlignedBB.getBoundingBox((double) x, y + 0.125D, (double) z + 0.125D, (double) x + 1.00D, (double) y + 0.875D, (double) z + 0.875D);
		}
		else
		{
			return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 1.0D, (double) z + 1.0D);
		}
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 0)
		{
			return AxisAlignedBB.getBoundingBox((double) x + 0.25D, y, (double) z + 0.25D, (double) x + 0.75D, (double) y + 0.5D, (double) z + 0.75D);
		}
		else if (meta == 1)
		{
			return AxisAlignedBB.getBoundingBox((double) x + 0.125D, y, (double) z + 0.125D, (double) x + 0.875D, (double) y + 1.00D, (double) z + 0.875D);
		}
		else if (meta == 3)
		{
			return AxisAlignedBB.getBoundingBox((double) x + 0.125D, y + 0.125D, (double) z, (double) x + 0.875D, (double) y + 0.875D, (double) z + 1.00D);
		}
		else if (meta == 4)
		{
			return AxisAlignedBB.getBoundingBox((double) x, y + 0.125D, (double) z + 0.125D, (double) x + 1.00D, (double) y + 0.875D, (double) z + 0.875D);
		}
		else
		{
			return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 1.0D, (double) z + 1.0D);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int x, int y, int z)
	{
		int meta = iblockaccess.getBlockMetadata(x, y, z);

		float minX;
		float minY;
		float minZ;
		float maxX;
		float maxY;
		float maxZ;

		if (meta == 0)
		{
			minY = 0F;
			minX = minZ = 0.25F;
			maxX = maxZ = 0.75F;
			maxY = 0.5F;
		}
		else if (meta == 1)
		{
			minY = 0F;
			minX = minZ = 0.125F;
			maxX = maxZ = 0.875F;
			maxY = 1.00F;
		}
		else
		{
			minY = 0F;
			minX = minZ = 0.0F;
			maxX = maxZ = 1.0F;
			maxY = 1.0F;
		}
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 3 || meta == 4)
            meta = 1;
        return meta;
    }
    
    @Override
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) 
    {
        for (int i = 0; i < boneTypes.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
    
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return SmallBlockRenderer.bonesModel;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return true;
	}
	
    @Override
    public int damageDropped(int meta)
    {
        if (meta == 3 || meta == 4)
            meta = 1;
        return meta;
    }
}
