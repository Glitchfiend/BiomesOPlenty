package biomesoplenty.common.itemblocks;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockGrave extends ItemBlock
{
	private Icon grave;
	
	public ItemBlockGrave(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		grave = iconRegister.registerIcon("biomesoplenty:item_grave");
	}
	
	@Override
	public Icon getIconFromDamage(int meta)
	{
		return grave;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	{
		int id = world.getBlockId(x, y, z);

		if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
		{
			side = 1;
		}
		else if (id != Block.vine.blockID && id != Block.tallGrass.blockID && id != Block.deadBush.blockID
				&& (Block.blocksList[id] == null || !Block.blocksList[id].isBlockReplaceable(world, x, y, z)))
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
		else if (y == 255 && Block.blocksList[itemID].blockMaterial.isSolid())
			return false;
		else if (world.canPlaceEntityOnSide(itemID, x, y, z, false, side, player, itemstack))
		{
			Block block = Block.blocksList[itemID];
	        int o = ((MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
	        int fO;
	        
	        if (o == 0 || o == 2)
	        {
	        	fO = 0;
	        }
	        else
	        {
	        	fO = 2;
	        }

			if (placeGrave(itemstack, player, block, world, x, y, z, fO, side, par8, par9, par10)) return true;
		} 

		return false;
	}
	
	private boolean placeGrave(ItemStack itemstack, EntityPlayer player, Block block, World world, int x, int y, int z, int meta, int side, float par8, float par9, float par10)
	{
		if (world.isAirBlock(x, y + 1, z))
		{
			int k1 = Block.blocksList[itemID].onBlockPlaced(world, x, y, z, side, par8, par9, par10, meta);
			int k2 = Block.blocksList[itemID].onBlockPlaced(world, x, y, z, side, par8, par9, par10, meta + 1);

			if (placeBlockAt(itemstack, player, world, x, y, z, side, par8, par9, par10, k1) && placeBlockAt(itemstack, player, world, x, y + 1, z, side, par8, par9, par10, k2))
			{
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				--itemstack.stackSize;
			}

			return true;
		}
		
		return false;
	}
}
