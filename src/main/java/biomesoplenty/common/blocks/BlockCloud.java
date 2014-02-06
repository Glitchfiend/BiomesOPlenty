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
		super(Material.cloth);
		
		//TODO: this.setHardness
		this.setHardness(0.1F);
		
		//TODO setStepSound(Block.soundClothFootstep)
		this.setStepSound(Block.soundTypeCloth);
		
		//TODO:	setLightOpacity()
		this.setLightOpacity(3);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:cloud");
	}


	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float yOffset = 0.25F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - yOffset, z + 1);
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
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
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	//TODO:		   isOpaqueCube()
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	//TODO			shouldSideBeRendered
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
		//TODO:					  getBlock()
        Block block = world.getBlock(x, y, z);
        //TODO:					             shouldSideBeRendered
        return block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }
}