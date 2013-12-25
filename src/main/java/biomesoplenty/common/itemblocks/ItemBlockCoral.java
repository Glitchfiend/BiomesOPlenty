package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockCoral extends ItemBlock
{
	private static final String[] coral = new String[] {"kelpbottom", "kelpmiddle", "kelptop", "kelpsingle", "pinkcoral", "orangecoral", "bluecoral", "glowcoral"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBlockCoral(Block block)
	{
		super(block);
		
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
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[1];

		textures[0] = iconRegister.registerIcon("biomesoplenty:item_kelp");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= coral.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + coral[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta == 3)
			return textures[0];
		else
			//TODO: block		  getIcon()
			return field_150939_a.func_149691_a(0, meta);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return null;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 20;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		//TODO:				getBlock()
		Block block = world.func_147439_a(x, y, z);

		if (block == Blocks.snow && (world.getBlockMetadata(x, y, z) & 7) < 1)
		{
			side = 1;
		}
		else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
		{
			if (side == 0)
			{
				--y;
			}

			if (side == 1)
			{
				++y;
			}

			if (side == 2)
			{
				--z;
			}

			if (side == 3)
			{
				++z;
			}

			if (side == 4)
			{
				--x;
			}

			if (side == 5)
			{
				++x;
			}
		}

		if (itemstack.stackSize == 0)
			return false;
		else if (!player.canPlayerEdit(x, y, z, side, itemstack))
			return false;
		//TODO:					   getBlockMaterial()
		else if (y == 255 && block.func_149688_o().isSolid())
			return false;
		//TODO:		   canPlaceEntityOnSide()?
		else if (world.func_147472_a(block, x, y, z, false, side, player, itemstack))
		{
			//TODO:   getBlock()											   getBlock()
			if (world.func_147439_a(x, y + 1, z) == Blocks.water)
			{
				onItemUsePlaceBlock(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);

				return true;
			}
		}

		return false;
	}

	public void onItemUsePlaceBlock(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		//TODO:		  block
		Block block = field_150939_a;
		
		int j1 = this.getMetadata(itemstack.getItemDamage());
		//TODO:		   onBlockPlaced()
		int k1 = block.func_149660_a(world, x, y, z, side, hitVecX, hitVecY, hitVecZ, j1);

		if (placeBlockAt(itemstack, player, world, x, y, z, side, hitVecX, hitVecY, hitVecZ, k1))
		{
			//TODO:													  stepSound.getPlaceSound()				stepSound.getVolume()						stepSound.getPitch()
			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.field_149762_H.func_150496_b(), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F);
			--itemstack.stackSize;
		}
	}
}
