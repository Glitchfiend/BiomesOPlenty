package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCItems;

public class BlockFlesh extends Block
{
	public BlockFlesh()
	{
		super(Material.sponge);
		
		this.setHardness(0.4F);	
		
		this.setStepSound(Block.soundTypeGravel);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:flesh");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float var5 = 0.125F;
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - var5, z + 1);
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		super.randomDisplayTick(world, x, y, z, random);

		if (random.nextInt(4) == 0)
		{
			world.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(this)) + "_0", x + random.nextFloat(), y - 0.4F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
		
		if (random.nextInt(12) == 0)
		{
			world.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(this)) + "_0", x + random.nextFloat(), y + 1.0F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

			if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPCItems.wadingBoots)
			{
				return;
			}
		}

		entity.motionX *= 0.9D;
		entity.motionZ *= 0.9D;
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return BOPCItems.misc;
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return 3;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(5);
	}
}
