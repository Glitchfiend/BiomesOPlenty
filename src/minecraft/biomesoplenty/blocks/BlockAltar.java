package biomesoplenty.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeHooks;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.AltarRenderer;
import biomesoplenty.blocks.renderers.RenderUtils;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import biomesoplenty.tileentity.TileEntityAltar;

public class BlockAltar extends Block
{
	private static final String[] altarTypes = new String[] {"altarframe"};

	public static Icon altarFrame;
	public static Icon altarFrameTop;

	public static Icon frameApatite;
	public static Icon framePeridot;
	public static Icon frameRuby;
	public static Icon frameSapphire;
	public static Icon frameTanzanite;
	public static Icon frameTopaz;

	public BlockAltar(int blockID)
	{
		super(blockID, Material.rock);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		this.setHardness(3.0F);
		this.setResistance(5.0F);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		altarFrame = iconRegister.registerIcon("BiomesOPlenty:altarframe");
		altarFrameTop = iconRegister.registerIcon("BiomesOPlenty:altarframetop");

		frameApatite = iconRegister.registerIcon("BiomesOPlenty:frameapatite");
		framePeridot = iconRegister.registerIcon("BiomesOPlenty:frameperidot");
		frameRuby = iconRegister.registerIcon("BiomesOPlenty:frameruby");
		frameSapphire = iconRegister.registerIcon("BiomesOPlenty:framesapphire");
		frameTanzanite = iconRegister.registerIcon("BiomesOPlenty:frametanzanite");
		frameTopaz = iconRegister.registerIcon("BiomesOPlenty:frametopaz");
	}

	@Override
	public Icon getIcon(int side, int meta)
	{

		if (side == 1 || side == 0)
		{
			return altarFrameTop;
		}
		else
		{
			return altarFrame;
		}
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityAltar();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{		
		boolean returnStatement = false;

		ItemStack equippedItem = player.getCurrentEquippedItem();
		TileEntityAltar tileentityaltar = (TileEntityAltar) world.getBlockTileEntity(x, y, z);

		if (equippedItem != null)
		{
			if (equippedItem.itemID == Items.miscItems.get().itemID && (equippedItem.getItemDamage() > 9 && equippedItem.getItemDamage() < 16))
			{
				if (tileentityaltar != null)
				{	
					if (!tileentityaltar.getPresent(equippedItem.getItemDamage()))
					{
						if (!world.isRemote)
						{
							tileentityaltar.setPresent(equippedItem.getItemDamage(), true);
							
							if (!player.capabilities.isCreativeMode)
							{
								--equippedItem.stackSize;
							}
						}
						
						world.markBlockForUpdate(x, y, z);

						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < altarTypes.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderUtils.altarModel;
	}
	
	@Override
    public void onBlockHarvested(World world, int x, int y, int z, int id, EntityPlayer par6EntityPlayer) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		
        if (par6EntityPlayer.getCurrentEquippedItem() != null)
        {
        	Item item = Item.itemsList[par6EntityPlayer.getCurrentEquippedItem().itemID];

        	if (item instanceof ItemPickaxe)
        	{
        		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        		{
        			float f = 0.7F;
        			double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        			double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        			double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        			EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, new ItemStack(Blocks.altar.get(), 1));
        			entityitem.delayBeforeCanPickup = 10;
        			world.spawnEntityInWorld(entityitem);
        		}
        	}
        }

        dropBlockAsItem(world, x, y, z, id, meta);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		 TileEntityAltar tileentityaltar = (TileEntityAltar) world.getBlockTileEntity(x, y, z);
		 
		if (tileentityaltar != null)
		{
			if (tileentityaltar.getPresent(10))
				ret.add(new ItemStack(Items.miscItems.get(), 1, 10));

			if (tileentityaltar.getPresent(11))
				ret.add(new ItemStack(Items.miscItems.get(), 1, 11));

			if (tileentityaltar.getPresent(12))
				ret.add(new ItemStack(Items.miscItems.get(), 1, 12));

			if (tileentityaltar.getPresent(13))
				ret.add(new ItemStack(Items.miscItems.get(), 1, 13));

			if (tileentityaltar.getPresent(14))
				ret.add(new ItemStack(Items.miscItems.get(), 1, 14));

			if (tileentityaltar.getPresent(15))
				ret.add(new ItemStack(Items.miscItems.get(), 1, 15));
		}

		return ret;
	}
}
