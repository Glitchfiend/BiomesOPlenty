package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.tileentities.TileEntityAltar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPGlass extends Block
{
	private static final String[] glassTypes = new String[] {"celestiallens", "sacrificialfocus_empty", "sacrificialfocus_active", "sacrificialfocus_villager"};
	private Icon[] textures;

	public BlockBOPGlass(int blockID)
	{
		super(blockID, Material.glass);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		this.blockHardness = 0.37F;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 2)
		{
			float var5 = 0.01F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - var5, z + 1);
		}
		else
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1, z + 1);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 2)
		{
			if (checkAltarStructreIntegrity(world, x, y, z))
			{
				if (entity instanceof EntityVillager)
				{
					world.setBlockMetadataWithNotify(x, y, z, 3, 2);
					
					world.spawnEntityInWorld(new EntityLightningBolt(world, x + 1, y + 2, z));
					world.spawnEntityInWorld(new EntityLightningBolt(world, x -1, y + 2, z));
					world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z + 1));
					world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z - 1));

					entity.setDead();
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9)
	{
		ItemStack equippedItem = player.getCurrentEquippedItem();
		Random rand = new Random();

		if (equippedItem.itemID == Items.soulManipulator.get().itemID)
		{
			if (equippedItem.getItemDamage() == 0)
			{
				if (world.getBlockMetadata(x, y, z) == 2)
				{
					if (checkAltarStructreIntegrity(world, x, y, z))
					{
						if (!player.capabilities.isCreativeMode)
						{
							player.setCurrentItemOrArmor(0, new ItemStack(Items.soulManipulator.get(), 1, 1));
						}

						world.setBlockMetadataWithNotify(x, y, z, 1, 2);

						return true;
					}
				}
				else if (world.getBlockMetadata(x, y, z) == 3)
				{
					if (checkAltarStructreIntegrity(world, x, y, z))
					{
						if (!player.capabilities.isCreativeMode)
						{
							player.setCurrentItemOrArmor(0, new ItemStack(Items.soulManipulator.get(), 1, 2));
						}

						FMLClientHandler.instance().getClient().sndManager.playSound("mob.villager.idle", (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, 1.0F, 1.0F);

						world.setBlockMetadataWithNotify(x, y, z, 1, 2);

						return true;
					}
				}	
			}
			else if (equippedItem.getItemDamage() == 1)
			{
				if (world.getBlockMetadata(x, y, z) == 1)
				{
					if (checkAltarStructreIntegrity(world, x, y, z))
					{
						if (!player.capabilities.isCreativeMode)
						{
							player.setCurrentItemOrArmor(0, new ItemStack(Items.soulManipulator.get(), 1, 0));
						}

						world.spawnEntityInWorld(new EntityLightningBolt(world, x + 1, y + 2, z));
						world.spawnEntityInWorld(new EntityLightningBolt(world, x -1, y + 2, z));
						world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z + 1));
						world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z - 1));

						world.setBlockMetadataWithNotify(x, y, z, 2, 2);

						return true;
					}
				}
				if (world.getBlockMetadata(x, y, z) == 3)
				{
					if (checkAltarStructreIntegrity(world, x, y, z))
					{
						if (!player.capabilities.isCreativeMode)
						{
							player.setCurrentItemOrArmor(0, new ItemStack(Items.soulManipulator.get(), 1, 0));
						}

						world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 1, z));
						
						FMLClientHandler.instance().getClient().sndManager.playSound("mob.wither.death", (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, 5.0F, 10.0F);
						FMLClientHandler.instance().getClient().sndManager.playSound("mob.enderdragon.growl", (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, 5.0F, 1.0F);
						
						world.spawnEntityInWorld(new EntityDragon(world));

						world.setBlockMetadataWithNotify(x, y, z, 1, 2);
						
						Entity entitytnt = new EntityTNTPrimed(world);
						
						world.createExplosion(entitytnt, (double)x, (double)y, (double)z, 10.0F, true);

						return true;
					}
				}
			}
			if (equippedItem.getItemDamage() == 2)
			{
				if (world.getBlockMetadata(x, y, z) == 2)
				{
					if (checkAltarStructreIntegrity(world, x, y, z))
					{						
						if (player.dimension != 1)
						{
							world.spawnEntityInWorld(new EntityLightningBolt(world, x + 1, y + 2, z));
							world.spawnEntityInWorld(new EntityLightningBolt(world, x -1, y + 2, z));
							world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z + 1));
							world.spawnEntityInWorld(new EntityLightningBolt(world, x, y + 2, z - 1));

							if (!player.capabilities.isCreativeMode)
							{
								player.setCurrentItemOrArmor(0, new ItemStack(Items.soulManipulator.get(), 1, 0));
							}

							world.setBlockMetadataWithNotify(x, y, z, 3, 2);

							return true;
						}
					}
				}
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
		
		if (tileentityaltar0 != null && tileentityaltar1 != null && tileentityaltar2 != null && tileentityaltar3 != null)
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
	
	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 2 || meta == 3) 
		{
			meta = 1;
		}
		
		return meta;
	}
	
    @Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
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

    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
	protected boolean canSilkHarvest()
    {
        return true;
    }

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[glassTypes.length];

		for (int i = 0; i < glassTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+glassTypes[i]);
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
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < glassTypes.length; ++i) 
		{
			if (i != 2 && i != 3)
				list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
