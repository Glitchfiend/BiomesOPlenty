package biomesoplenty.common.blocks;

import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.blocks.utils.BOPBlock;
import biomesoplenty.common.blocks.utils.SubBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMud extends BOPBlock
{
	public BlockMud()
	{
		super(Material.sand);

		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);

		this.setStepSound(Block.soundTypeSand);
	}

    @Override
    protected void initializeSubBlocks()
    {
        this.registerSubBlock(0, "mud");
        this.registerSubBlock(1, "quicksand");
    }

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
        SubBlock mud = getSubBlock(0);

        mud.setMainIcon(iconRegister.registerIcon("biomesoplenty:mud"));

        SubBlock quicksand = getSubBlock(1);

        quicksand.setMainIcon(iconRegister.registerIcon("biomesoplenty:quicksand"));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			float var5 = 0.35F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - var5, z + 1);
		}
		else
			return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItemHelper.get("wadingBoots"))
				{
					return;
				}
			}

			entity.motionX *= 0.1D;
			entity.motionZ *= 0.1D;
		}
		else
		{
			entity.setInWeb();
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
			return BOPItemHelper.get("mudball");
		else
			return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
			return 4;
		else
			return 1;
	}
}