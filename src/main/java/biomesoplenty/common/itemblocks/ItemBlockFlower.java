package biomesoplenty.common.itemblocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "rainbowflower", "bromeliad", "sunflowerbottom", "sunflowertop", "dandelion"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;
	private static final int SUNFLOWERTOP = 14;

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
		textures = new IIcon[2];

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
	public IIcon getIconFromDamage(int meta)
	{
		if (meta == 11)
			return textures[1];
		else if (meta == 13)
			return textures[0];
		else
			//TODO: block		  getIcon()
			return field_150939_a.func_149691_a(0, meta);
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
		if (par1ItemStack.getItemDamage() == 15) 
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			--par1ItemStack.stackSize;
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

		if (count < 10) {
			player.stopUsingItem();
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		if (!par3EntityPlayer.capabilities.isCreativeMode && !par2World.isRemote) 
		{
			--par1ItemStack.stackSize;
		}
	}
	
    @Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	if (metadata == 13) 
    	{
    		if (!placeBlockAt(stack, player, world, x, y + 1, z, side, hitX, hitY + 1, hitZ, SUNFLOWERTOP)) return false;
    	}

    	return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
    }
}
