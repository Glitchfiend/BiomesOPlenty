package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.tileentity.TileEntityAltar;

public class BlockBOPGlass extends Block
{
	private static final String[] glassTypes = new String[] {"celestiallens", "sacrificialFocus"};
	private Icon[] textures;

	public BlockBOPGlass(int blockID)
	{
		super(blockID, Material.glass);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		this.blockHardness = 0.37F;
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9)
	{
		ItemStack equippedItem = player.getCurrentEquippedItem();
		
		if (equippedItem.itemID == Items.soulManipulator.get().itemID && equippedItem.getItemDamage() == 1)
		{
			TileEntityAltar tileentityaltar0 = (TileEntityAltar) world.getBlockTileEntity(x + 1, y, z);
			TileEntityAltar tileentityaltar1 = (TileEntityAltar) world.getBlockTileEntity(x - 1, y, z);
			TileEntityAltar tileentityaltar2 = (TileEntityAltar) world.getBlockTileEntity(x, y, z + 1);
			TileEntityAltar tileentityaltar3 = (TileEntityAltar) world.getBlockTileEntity(x, y, z - 1);

			if (checkAltarStructreIntegrity(world, x, y, z))
			{
				player.setCurrentItemOrArmor(0, new ItemStack(Items.soulManipulator.get(), 1, 0));

				world.spawnEntityInWorld(new EntityLightningBolt(world, x + 1, y + 2, z));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x -1, y + 2, z));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z + 1));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z - 1));

				return true;
			}
		}

		return false;
	}
	
	public static boolean checkAltarStructreIntegrity(World world, int x, int y, int z)
	{
		TileEntityAltar tileentityaltar0 = (TileEntityAltar) world.getBlockTileEntity(x + 1, y, z);
		TileEntityAltar tileentityaltar1 = (TileEntityAltar) world.getBlockTileEntity(x - 1, y, z);
		TileEntityAltar tileentityaltar2 = (TileEntityAltar) world.getBlockTileEntity(x, y, z + 1);
		TileEntityAltar tileentityaltar3 = (TileEntityAltar) world.getBlockTileEntity(x, y, z - 1);
		
		if (tileentityaltar0.getAllPresent() && tileentityaltar1.getAllPresent() && tileentityaltar2.getAllPresent() && tileentityaltar3.getAllPresent())
		{
			if (world.getBlockId(x + 1, y + 1, z) == Blocks.bones.get().blockID && world.getBlockId(x - 1, y + 1, z) == Blocks.bones.get().blockID && world.getBlockId(x, y + 1, z + 1) == Blocks.bones.get().blockID && world.getBlockId(x, y + 1, z - 1) == Blocks.bones.get().blockID)
			{
				if (world.getBlockMetadata(x + 1, y + 1, z) == 1 && world.getBlockMetadata(x - 1, y + 1, z) == 1 && world.getBlockMetadata(x, y + 1, z + 1) == 1 && world.getBlockMetadata(x, y + 1, z - 1) == 1)
				{
					if (world.getBlockId(x + 1, y + 2, z) == Blocks.bones.get().blockID && world.getBlockId(x - 1, y + 2, z) == Blocks.bones.get().blockID && world.getBlockId(x, y + 2, z + 1) == Blocks.bones.get().blockID && world.getBlockId(x, y + 2, z - 1) == Blocks.bones.get().blockID)
					{
						if (world.getBlockMetadata(x + 1, y + 2, z) == 0 && world.getBlockMetadata(x - 1, y + 2, z) == 0 && world.getBlockMetadata(x, y + 2, z + 1) == 0 && world.getBlockMetadata(x, y + 2, z - 1) == 0)
						{
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[glassTypes.length];

		for (int i = 0; i < glassTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+glassTypes[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < glassTypes.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
