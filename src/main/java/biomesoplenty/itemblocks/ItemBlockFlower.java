package biomesoplenty.itemblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlower extends ItemBlock
{
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "rainbowflower", "bromeliad", "sunflowerbottom", "sunflowertop", "dandelion"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;
	private static final int SUNFLOWERTOP = 14;

	public ItemBlockFlower(int par1)
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
		textures = new Icon[2];

		textures[0] = iconRegister.registerIcon("biomesoplenty:item_sunflower");
		textures[1] = iconRegister.registerIcon("biomesoplenty:item_rainbowflower");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= plants.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + plants[meta];
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta == 11)
			return textures[1];
		else if (meta == 13)
			return textures[0];
		else
			return Block.blocksList[itemID].getIcon(0, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		if (par1ItemStack.getItemDamage() == 15)
			return EnumAction.block;
		else
			return null;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 20;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.getItemDamage() == 15) {
			//    		Vec3 vec = par3EntityPlayer.getLookVec();
			//
			//			for (int p = 0; p < 32; ++p)
				//			{
				//				BiomesOPlenty.proxy.spawnParticle("dandelion", par3EntityPlayer.posX + (vec.xCoord / 2), par3EntityPlayer.posY + (vec.yCoord / 2) + par3EntityPlayer.getEyeHeight(), par3EntityPlayer.posZ + (vec.zCoord / 2));
			//			};

			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

			//			if (!par3EntityPlayer.capabilities.isCreativeMode && !par2World.isRemote)
			//				--par1ItemStack.stackSize;
		}
		return par1ItemStack;
	}

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
	{
		Vec3 vec = player.getLook(0.5F);
		Random rnd = player.getRNG();

		for (int p = 0; p < 4; ++p)
		{
			float pos = (rnd.nextFloat() - 0.5F) / 8;
			BiomesOPlenty.proxy.spawnParticle("dandelion", player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
		}

		if (count < 10) {
			player.stopUsingItem();
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		if (!par3EntityPlayer.capabilities.isCreativeMode && !par2World.isRemote) {
			--par1ItemStack.stackSize;
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
					if (itemStack.getItemDamage() == 13 && world.getBlockMaterial(x, y + 1, z).isReplaceable()) {
						world.setBlock(x, y + 1, z, this.getBlockID(), SUNFLOWERTOP, 2);
					}

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
