package biomesoplenty.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockPlant extends ItemBlock
{
	private static final String[] plants = new String[] {"deadgrass", "desertgrass", "desertsprouts", "dunegrass", "holytallgrass", "thorn", "barley", "cattail", "rivercane", "cattailtop", "cattailbottom", "wildcarrot", "cactus", "witherwart", "reed"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemBlockPlant(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[5];

		textures[0] = iconRegister.registerIcon("biomesoplenty:item_barley");
		textures[1] = iconRegister.registerIcon("biomesoplenty:item_cattail");
		textures[2] = iconRegister.registerIcon("biomesoplenty:item_rivercane");
		textures[3] = iconRegister.registerIcon("biomesoplenty:item_witherwart");
		textures[4] = iconRegister.registerIcon("biomesoplenty:item_reed");
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
	public Icon getIconFromDamage(int meta)
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
		else
			return Block.blocksList[itemID].getIcon(0, meta);
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
			if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!world.canMineBlock(player, i, j, k))
					return itemStack;

				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
					return itemStack;

				if (world.getBlockMaterial(i, j, k) == Material.water && world.getBlockMetadata(i, j, k) == 0 && world.isAirBlock(i, j + 1, k))
				{
					world.setBlock(i, j + 1, k, itemStack.itemID, 14, 2);

					if (!player.capabilities.isCreativeMode)
					{
						--itemStack.stackSize;
					}
				}
			}

			return itemStack;
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int id = world.getBlockId(x, y, z);

		if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1) {
			side = 1;
		} else if (!Block.blocksList[id].isBlockReplaceable(world, x, y, z))
		{
			if (side == 0) {
				--y;
			}

			if (side == 1) {
				++y;
			}

			if (side == 2) {
				--z;
			}

			if (side == 3) {
				++z;
			}

			if (side == 4) {
				--x;
			}

			if (side == 5) {
				++x;
			}
		}

		if (!player.canPlayerEdit(x, y, z, side, itemStack))
			return false;
		else if (itemStack.stackSize == 0)
			return false;
		else
		{
			if (world.canPlaceEntityOnSide(this.getBlockID(), x, y, z, false, side, (Entity)null, itemStack))
			{
				Block block = Block.blocksList[this.getBlockID()];
				int j1 = block.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, 0);

				if (world.setBlock(x, y, z, this.getBlockID(), itemStack.getItemDamage(), 3))
				{
					if (world.getBlockId(x, y, z) == this.getBlockID())
					{
						Block.blocksList[this.getBlockID()].onBlockPlacedBy(world, x, y, z, player, itemStack);
						Block.blocksList[this.getBlockID()].onPostBlockPlaced(world, x, y, z, j1);
					}

					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
					--itemStack.stackSize;
				}
			}

			return true;
		}
	}
}
