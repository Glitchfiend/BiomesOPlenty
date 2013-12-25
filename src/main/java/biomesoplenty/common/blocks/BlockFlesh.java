package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockFlesh extends Block
{
	public BlockFlesh(int par1)
	{
		super(Material.sponge);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:flesh");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		float var5 = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(par2, par3, par4, par2 + 1, par3 + 1 - var5, par4 + 1);
	}

	@Override
	//TODO: 	randomDisplayTick()
	public void func_149734_b(World world, int x, int y, int z, Random random)
	{
		super.func_149734_b(par1World, par2, par3, par4, par5Random);

		if (par5Random.nextInt(4) == 0)
		{
			par1World.spawnParticle("tilecrack_" + String.valueOf(Blocks.flesh.get().blockID) + "_0", par2 + par5Random.nextFloat(), par3 - 0.4F, par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
		
		if (par5Random.nextInt(12) == 0)
		{
			par1World.spawnParticle("tilecrack_" + String.valueOf(Blocks.flesh.get().blockID) + "_0", par2 + par5Random.nextFloat(), par3 + 1.0F, par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

			if (inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Items.wadingBoots.get().itemID)
			{
				return;
			}
		}

		entity.motionX *= 0.9D;
		entity.motionZ *= 0.9D;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Items.miscItems.get().itemID;
	}
	
	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return 3;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(5);
	}
}
