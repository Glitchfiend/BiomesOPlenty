package biomesoplenty.common.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPRecord extends ItemRecord
{
	private static final Map records = new HashMap();

	public final String recordName;

	public ItemBOPRecord(String recordName)
	{
		super(recordName);
		
		this.recordName = recordName;
		this.maxStackSize = 1;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		
		records.put(recordName, this);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:" + recordName);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		//TODO: world.getBlock()
		if (world.func_147439_a(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0)
		{
			if (world.isRemote)
				return true;
			else
			{
				//TODO:						  .insertRecord()
				((BlockJukebox)Blocks.jukebox).func_149926_b(world, x, y, z, itemStack);
                //TODO:													    Item.getIdFromItem()
				world.playAuxSFXAtEntity((EntityPlayer)null, 1005, x, y, z, Item.func_150891_b(this));
				--itemStack.stackSize;
				return true;
			}
		} 
		else
			return false;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(this.func_150927_i());
	}

	@Override
	//TODO:		  getRecordTitle()
	public String func_150927_i()
	{
		return StatCollector.translateToLocal("item.record." + this.field_150929_a + ".desc");
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		return EnumRarity.rare;
	}

	public static ItemBOPRecord getRecord(String par0Str)
	{
		return (ItemBOPRecord)records.get(par0Str);
	}
}
