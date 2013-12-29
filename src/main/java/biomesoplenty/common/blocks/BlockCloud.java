package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class BlockCloud extends Block
{
	public BlockCloud()
	{
		//TODO:	Material.cloth
		super(Material.field_151580_n);
		
		//TODO: this.setHardness
		this.func_149711_c(0.1F);
		
		//TODO setStepSound(Block.soundClothFootstep)
		this.func_149672_a(Block.field_149775_l);
		
		//TODO:	setLightOpacity()
		this.func_149713_g(3);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:cloud");
	}


	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		float yOffset = 0.25F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - yOffset, z + 1);
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		entity.fallDistance = 0.0F;
		
		if (entity instanceof EntityPlayer)
		{
			InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

			if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItemHelper.get("wadingBoots"))
			{
				return;
			}
		}

		entity.motionX *= 0.8D;
		entity.motionZ *= 0.8D;
	}

	@Override
	//TODO:	   getRenderBlockPass()
	public int func_149701_w()
	{
		return 1;
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean func_149662_c()
	{
		return false;
	}

	@Override
	//TODO			shouldSideBeRendered
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
    {
		//TODO:					  getBlock()
        Block block = world.func_147439_a(x, y, z);
        //TODO:					             shouldSideBeRendered
        return block == this ? false : super.func_149646_a(world, x, y, z, side);
    }
}