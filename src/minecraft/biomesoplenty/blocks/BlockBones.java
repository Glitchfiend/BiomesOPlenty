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
	//Meta 3 & 4 used by alternate small bone rotations, 5 & 6 are used by alternate medium bone rotations
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
        
        if (meta == 4 || meta == 5)
        	meta = 1;
        	
        return textures[meta];
    }

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
			case 0:
				return AxisAlignedBB.getBoundingBox((double) x + 0.374D, y, (double) z + 0.374D, (double) x + 0.626D, (double) y + 1.0D, (double) z + 0.626D);
				
			case 1:
				return AxisAlignedBB.getBoundingBox((double) x + 0.187D, y, (double) z + 0.187D, (double) x + 0.813D, (double) y + 1.0D, (double) z + 0.813D);
				
			case 3:
				return AxisAlignedBB.getBoundingBox((double) x + 0.374D, y + 0.374D, (double) z, (double) x + 0.626D, (double) y + 0.626D, (double) z + 1.00D);
				
			case 4:
				return AxisAlignedBB.getBoundingBox((double) x, y + 0.374D, (double) z + 0.374D, (double) x + 1.00D, (double) y + 0.626D, (double) z + 0.626D);
				
			case 5:
				return AxisAlignedBB.getBoundingBox((double) x + 0.187D, y + 0.187D, (double) z, (double) x + 0.813D, (double) y + 0.813D, (double) z + 1.00D);
				
			case 6:
				return AxisAlignedBB.getBoundingBox((double) x, y + 0.187D, (double) z + 0.187D, (double) x + 1.00D, (double) y + 0.813D, (double) z + 0.813D);
				
			default:
				return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 1.0D, (double) z + 1.0D);
		}		
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
			case 0:
				return AxisAlignedBB.getBoundingBox((double) x + 0.374D, y, (double) z + 0.374D, (double) x + 0.626D, (double) y + 1.0D, (double) z + 0.626D);
			
			case 1:
				return AxisAlignedBB.getBoundingBox((double) x + 0.187D, y, (double) z + 0.187D, (double) x + 0.813D, (double) y + 1.0D, (double) z + 0.813D);
			
			case 3:
				return AxisAlignedBB.getBoundingBox((double) x + 0.374D, y + 0.374D, (double) z, (double) x + 0.626D, (double) y + 0.626D, (double) z + 1.00D);
			
			case 4:
				return AxisAlignedBB.getBoundingBox((double) x, y + 0.374D, (double) z + 0.374D, (double) x + 1.00D, (double) y + 0.626D, (double) z + 0.626D);
			
			case 5:
				return AxisAlignedBB.getBoundingBox((double) x + 0.187D, y + 0.187D, (double) z, (double) x + 0.813D, (double) y + 0.813D, (double) z + 1.00D);
			
			case 6:
				return AxisAlignedBB.getBoundingBox((double) x, y + 0.187D, (double) z + 0.187D, (double) x + 1.00D, (double) y + 0.813D, (double) z + 0.813D);
				
			default:
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
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 3 || meta == 4)
            meta = 0;
        if (meta == 5 || meta == 6)
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
            meta = 0;
        if (meta == 5 || meta == 6)
            meta = 1;
        return meta;
    }
}
