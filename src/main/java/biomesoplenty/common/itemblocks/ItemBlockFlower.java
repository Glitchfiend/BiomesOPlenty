package biomesoplenty.common.itemblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlower extends ItemBlock
{
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "enderlotus", "bromeliad", "eyebulbbottom", "eyebulbtop", "dandelion"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;
	private static final int EYEBULBTOP = 14;

	public ItemBlockFlower(Block block)
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
		textures = new IIcon[1];

		textures[0] = iconRegister.registerIcon("biomesoplenty:eyebulbtop");
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
	public IIcon getIconFromDamage(int meta)
	{
		if (meta == 13)
			return textures[0];
		else
			return field_150939_a.getIcon(0, meta);
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
		if (par1ItemStack.getItemDamage() == 15) return EnumAction.block;
		else return super.getItemUseAction(par1ItemStack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 20;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.getItemDamage() == 15) 
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		
		return par1ItemStack;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		Vec3 vec = player.getLook(0.5F);
		Random rnd = player.getRNG();

		for (int p = 0; p < 4; ++p)
		{
			float pos = (rnd.nextFloat() - 0.5F) / 8;
			BiomesOPlenty.proxy.spawnParticle("dandelion", player.posX + vec.xCoord + pos, player.posY + vec.yCoord + player.getEyeHeight() + pos, player.posZ + vec.zCoord + pos);
		}

		if (count < 10 && !player.capabilities.isCreativeMode) {
			player.stopUsingItem();
			--stack.stackSize;
		}
	}

    @Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	if (metadata == 13) 
    	{
    		if (!placeBlockAt(stack, player, world, x, y + 1, z, side, hitX, hitY + 1, hitZ, EYEBULBTOP)) return false;
    	}

    	return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
    }
}
