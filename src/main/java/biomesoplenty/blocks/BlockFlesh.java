package biomesoplenty.blocks;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.api.BOPItems;

public class BlockFlesh extends Block
{
	public BlockFlesh(int par1)
	{
		super(par1, Material.sponge);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon("biomesoplenty:flesh");
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		float var5 = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(par2, par3, par4, par2 + 1, par3 + 1 - var5, par4 + 1);
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

		if (par5Random.nextInt(4) == 0)
		{
			par1World.spawnParticle("tilecrack_" + String.valueOf(BOPBlocks.flesh.get().blockID) + "_0", par2 + par5Random.nextFloat(), par3 - 0.4F, par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
		
		if (par5Random.nextInt(12) == 0)
		{
			par1World.spawnParticle("tilecrack_" + String.valueOf(BOPBlocks.flesh.get().blockID) + "_0", par2 + par5Random.nextFloat(), par3 + 1.0F, par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

			if (inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == BOPItems.wadingBoots.get().itemID)
			{
				return;
			}
		}

		entity.motionX *= 0.9D;
		entity.motionZ *= 0.9D;
	}
	
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return BOPItems.miscItems.get().itemID;
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return 3;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(5);
	}
}
