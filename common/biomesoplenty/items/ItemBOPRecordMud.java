package biomesoplenty.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukeBox;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPRecordMud extends ItemRecord
{
	/** List of all record items and their names. */
	@SuppressWarnings("rawtypes")
	private static final Map records = new HashMap();

	/** The name of the record. */
	public final String recordName;

	@SuppressWarnings("unchecked")
	public ItemBOPRecordMud(int par1, String par2Str)
	{
		super(par1, par2Str);
		recordName = par2Str;
		maxStackSize = 1;
		setUnlocalizedName("bopDiscMud");
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		records.put(par2Str, this);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:mudrecord");
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par3World.getBlockId(par4, par5, par6) == Block.jukebox.blockID && par3World.getBlockMetadata(par4, par5, par6) == 0)
		{
			if (par3World.isRemote)
				return true;
			else
			{
				((BlockJukeBox)Block.jukebox).insertRecord(par3World, par4, par5, par6, par1ItemStack);
				par3World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par4, par5, par6, itemID);
				--par1ItemStack.stackSize;
				return true;
			}
		} else
			return false;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(this.getRecordTitle());
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * Return the title for this record.
	 */
	public String getRecordTitle()
	{
		return "???";
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * Return an item rarity from EnumRarity
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)

	/**
	 * Return the record item corresponding to the given name.
	 */
	public static ItemBOPRecordMud getRecord(String par0Str)
	{
		return (ItemBOPRecordMud)records.get(par0Str);
	}
}
