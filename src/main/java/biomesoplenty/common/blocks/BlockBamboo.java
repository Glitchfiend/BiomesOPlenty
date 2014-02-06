package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.client.render.RenderUtils;

public class BlockBamboo extends Block
{
	private IIcon bambooSide;
	private IIcon bambooTop;
	
	public BlockBamboo()
	{
		//TODO: Material.plants
		super(Material.plants);
		
		//TODO: this.setHardness
		this.setHardness(0.2F);
		
		//TODO setStepSound(Block.soundWoodFootstep)
		this.setStepSound(Block.soundTypeWood);

		//TODO: setTickRandomly()
		this.setTickRandomly(true);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		bambooSide = iconRegister.registerIcon("biomesoplenty:bamboo");
		bambooTop = iconRegister.registerIcon("biomesoplenty:bambootop");
	}
	
	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (side > 1)
			return bambooSide;
		else
			return bambooTop;
	}

	@Override
	//TODO:		updateTick()
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		//TODO:   isAirBlock()
		if (world.isAirBlock(x, y + 1, z))
		{
			int var6;

			//TODO:				 getBlock()
			for (var6 = 1; world.getBlock(x, y - var6, z) == this; ++var6)
			{
				;
			}

			if (var6 < 3)
			{
				int var7 = world.getBlockMetadata(x, y, z);

				if (var7 == 15)
				{
					//TODO: setBlock()
					world.setBlock(x, y + 1, z, this, 0, 2);
					world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				}
				else
				{
					world.setBlockMetadataWithNotify(x, y, z, var7 + 1, 2);
				}
			}
		}
	}
	
	@Override
	//TODO:				 getSelectedBoundingBoxFromPool()
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
		float pixel = 0.0625F;
		
        return AxisAlignedBB.getAABBPool().getAABB((double)x + (1.0F - (pixel * 4)), (double)y, (double)z + (1.0F - (pixel * 4)), (double)x + (pixel * 4), (double)y + 1.0F, (double)z + (pixel * 4));
    }
	
    @Override
    //TODO:		addCollisionBoxesToList()
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
		float pixel = 0.0625F;
        //TODO: setBlockBounds
		this.setBlockBounds((pixel * 4), 0.0F, (pixel * 4), 1.0F - (pixel * 4), 1.0F, 1.0F - (pixel * 4));
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
        //TODO: setBlockBoundsForItemRender()
        this.setBlockBoundsForItemRender();
    }
    
    @Override
    //TODO:		setBlockBoundsForItemRender()
    public void setBlockBoundsForItemRender()
    {
        //TODO: setBlockBounds
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

	@Override
	//TODO:		   canPlaceBlockAt
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		//TODO:					getBlock
		Block block = world.getBlock(x, y - 1, z);
		
		if (block == this)
			return true;
		else if (block == Blocks.grass)
			return true;
		else
			return false;
	}

	@Override
	//TODO:		onNeighborBlockChange()
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		this.checkBlockCoordValid(world, x, y, z);
	}

	public final void checkBlockCoordValid(World world, int x, int y, int z)
	{
		//TODO:	  canBlockStay()
		if (!this.canBlockStay(world, x, y, z))
		{
			//TODO: dropBlockAsItem()
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			//TODO: setBlockToAir
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	//TODO:		   canBlockStay()
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		//TODO:		canPlaceBlockAt
		return this.canPlaceBlockAt(world, x, y, z);
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	//TODO:		   renderAsNormalBlock()
    public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	//TODO		getRenderType()
	public int getRenderType()
	{
		return RenderUtils.bambooModel;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

}
