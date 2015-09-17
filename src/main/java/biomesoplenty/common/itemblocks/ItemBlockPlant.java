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

public class ItemBlockPlant extends ItemBlock
{
	private static final String[] plants = new String[] {"deadgrass", "desertgrass", "desertsprouts", "dunegrass", "spectralfern", "thorn", "wildrice", "cattail", "rivercane", "cattailtop", "cattailbottom", "wildcarrot", "cactus", "witherwart", "reed", "root"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBlockPlant(Block block)
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
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[6];

		textures[0] = iconRegister.registerIcon("biomesoplenty:item_wildrice");
		textures[1] = iconRegister.registerIcon("biomesoplenty:item_cattail");
		textures[2] = iconRegister.registerIcon("biomesoplenty:item_rivercane");
		textures[3] = iconRegister.registerIcon("biomesoplenty:item_witherwart");
		textures[4] = iconRegister.registerIcon("biomesoplenty:item_reed");
		textures[5] = iconRegister.registerIcon("biomesoplenty:item_root");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= plants.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + plants[itemStack.getItemDamage()];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta == 6)
			return textures[0];
		else if (meta == 7)
			return textures[1];
		else if (meta == 8)
			return textures[2];
		else if (meta == 13)
			return textures[3];
		else if (meta == 14)
			return textures[4];
		else if (meta == 15)
			return textures[5];
		else
			return field_150939_a.getIcon(0, meta);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (itemStack.getItemDamage() != 14)
			return super.onItemRightClick(itemStack, world, player);

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

				if (world.getBlock(i, j, k).getMaterial() == Material.water && world.getBlockMetadata(i, j, k) == 0 && world.isAirBlock(i, j + 1, k))
				{
					if (world.getBlock(i, j - 1, k).getMaterial() != Material.water)
					{
						world.setBlock(i, j + 1, k, field_150939_a, 14, 2);
	
						if (!player.capabilities.isCreativeMode)
						{
							--itemStack.stackSize;
						}
					}
				}
			}

			return itemStack;
		}
	}
}
