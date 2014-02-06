package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockGrave extends ItemBlock
{
	private IIcon grave;
	
	public ItemBlockGrave(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		grave = iconRegister.registerIcon("biomesoplenty:item_grave");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return grave;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		//TODO:				getBlock()
		Block block = world.getBlock(x, y, z);

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
		else if (y == 255 && block.getMaterial().isSolid())
			return false;
		//TODO:		   canPlaceEntityOnSide()?
		else if (world.canPlaceEntityOnSide(block, x, y, z, false, side, player, itemstack))
		{
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

			if (placeGrave(itemstack, player, block, world, x, y, z, fO, side, hitVecX, hitVecY, hitVecZ)) return true;
		} 

		return false;
	}
	
	private boolean placeGrave(ItemStack itemstack, EntityPlayer player, Block block, World world, int x, int y, int z, int meta, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		//TODO:   isAirBlock()
		if (world.isAirBlock(x, y + 1, z))
		{
			//TODO:		   onBlockPlaced()
			int k1 = block.onBlockPlaced(world, x, y, z, side, hitVecX, hitVecY, hitVecZ, meta);
			//TODO:		   onBlockPlaced()
			int k2 = block.onBlockPlaced(world, x, y, z, side, hitVecX, hitVecY, hitVecZ, meta + 1);

			if (placeBlockAt(itemstack, player, world, x, y, z, side, hitVecX, hitVecY, hitVecZ, k1) && placeBlockAt(itemstack, player, world, x, y + 1, z, side, hitVecX, hitVecY, hitVecZ, k2))
			{
				//TODO:												linkedBlock	  stepSound.getPlaceSound()			linkedBlock	stepSound.getVolume()							  linkedBlock   stepSound.getPitch()
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, field_150939_a.stepSound.func_150496_b(), (field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, field_150939_a.stepSound.getPitch() * 0.8F);
				--itemstack.stackSize;
			}

			return true;
		}
		
		return false;
	}
}
