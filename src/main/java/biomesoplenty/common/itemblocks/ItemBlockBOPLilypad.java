package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockBOPLilypad extends ItemBlock
{
	private static final String[] lily = new String[] {"mediumlily", "smalllily", "tinylily"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBlockBOPLilypad(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= lily.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + lily[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
			return field_150939_a.getIcon(0, meta);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

		if (movingobjectposition == null)
			return itemStack;
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!world.canMineBlock(player, i, j, k))
					return itemStack;

				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
					return itemStack;

				if (world.getBlock(i, j, k).getMaterial() == Material.water && world.isAirBlock(i, j + 1, k))
				{
					world.setBlock(i, j + 1, k, field_150939_a, itemStack.getItemDamage(), 2);

					if (!player.capabilities.isCreativeMode)
					{
						--itemStack.stackSize;
					}
				}
			}

			return itemStack;
		}
	}
}
