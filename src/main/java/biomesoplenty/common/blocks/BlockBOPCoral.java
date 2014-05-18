package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.blocks.BlockBOPLog.LogCategory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPCoral extends BOPBlock
{
	public static enum CoralCategory
	{
		CAT1, CAT2;
	}
	
	//coral1
	//Kelp Bottom		(8)
	//Kelp Middle		(9)
	//Kelp Top			(10)
	//Kelp Single		(11)
	//Pink Coral		(12)
	//Orange Coral		(13)
	//Blue Coral		(14)
	//Glow Coral		(15)
	
	//coral2
	//Algae				(8)
	
	private static final String[] coral = new String[] {"kelpbottom", "kelpmiddle", "kelptop", "kelpsingle", "pinkcoral", "orangecoral", "bluecoral", "glowcoral", "algae"};
	private IIcon[] textures;
	
	private final CoralCategory category;

	public BlockBOPCoral(CoralCategory category)
	{
		super(Material.water);
		
		this.category = category;
		
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
		
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}
	
	@Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return meta < 8 ? meta + 8 : meta;
    }
	
    @Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
	
    @Override
	public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
    	return world.getBlock(x, y + 1, z) == Blocks.water && this.canBlockStay(world, x, y, z, itemStack.getItemDamage());
    }
    
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (category == CoralCategory.CAT1)
		{
			if (world.getBlock(x, y - 1, z) != this)
			{
				if (world.getBlock(x, y + 1, z) != this) //Convert to single piece
				{
					world.setBlock(x, y, z, this, 11, 2);
				}
				else //Convert to bottom piece
				{
					world.setBlock(x, y, z, this, 8, 2);
				}
			}
			else if (world.getBlock(x, y + 1, z) != this && world.getBlock(x, y - 1, z) == this) //Convert to top piece
			{
				world.setBlock(x, y, z, this, 10, 2);
			}
			else if (world.getBlock(x, y + 1, z) == this) //Convert to middle piece
			{
				world.setBlock(x, y, z, this, 9, 2);
			}
		}
		
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);

		if (category == CoralCategory.CAT1)
		{
			switch (metadata)
			{
			case 8:
				return block == Blocks.dirt || block == Blocks.sand || block == Blocks.sponge || block == Blocks.stone || block == Blocks.clay || block == Blocks.gravel || block == Blocks.grass || block == BOPBlockHelper.get("mud");
			
			case 9: // Kelp Middle
				return block == this;

			case 10: // Kelp Top
				return block == this;
				
			case 11:
				return block == this || block == Blocks.dirt || block == Blocks.sand || block == Blocks.sponge || block == Blocks.stone || block == Blocks.clay || block == Blocks.gravel || block == Blocks.grass || block == BOPBlockHelper.get("mud");
			}
		}

		return block == Blocks.dirt || block == Blocks.sand || block == Blocks.sponge || block == Blocks.stone || block == Blocks.clay || block == Blocks.gravel || block == Blocks.grass || block == BOPBlockHelper.get("mud");
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (category == CoralCategory.CAT1 && meta == 15) return 10;
		else return 0;
	}
	
	@Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int silkTouch) 
	{
		world.setBlock(x, y, z, Blocks.water);
    }
	
	@Override
	public int damageDropped(int meta)
	{
		return category == CoralCategory.CAT1 && meta < 11 ? 11 : meta;
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		return category == CoralCategory.CAT1 && meta < 11 ? 11 : meta;
	}
	
	//Client Only
	
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 8; i < getCategorySize() + 8; ++i)
		{
			if (category == CoralCategory.CAT1 ? i > 10 : true) list.add(new ItemStack(block, 1, i));
		}
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[coral.length];

		for (int i = 0; i < coral.length; ++i) textures[i] = iconRegister.registerIcon("biomesoplenty:" + coral[i]);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 8 || meta >= coral.length + 8) meta = 8;
		
		return textures[getMetaIndex(meta)];
	}
	
	public String getCoralType(int meta)
	{
		if (meta < 8 || meta >= coral.length + 8) meta = 8;
		
		return coral[getMetaIndex(meta)];
	}
	
	public int getMetaIndex(int meta)
	{
		return (meta - 8) + category.ordinal() * 8;
	}
	
	public int getCategorySize()
	{
		return category.ordinal() == category.values().length - 1 ? coral.length - category.ordinal() * 8 : 8;
	}
}
