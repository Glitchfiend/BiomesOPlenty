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
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCItems;

public class BlockAsh extends Block
{
	public BlockAsh()
	{
		super(Material.sand);

		this.setHardness(0.4F);	
		this.setHarvestLevel("shovel", 0);

		this.setStepSound(Block.soundTypeSand);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:ashblock");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float yOffset = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - yOffset, z + 1);
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		super.randomDisplayTick(world, x, y, z, random);

		if (random.nextInt(2) == 0)
		{
			world.spawnParticle("smoke", x + random.nextFloat(), y + 1.1F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
	{
		if (side == ForgeDirection.UP)
		{
			return true;
		}
		
		return false;
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

		entity.motionX *= 0.4D;
		entity.motionZ *= 0.4D;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return BOPCItems.misc;
	}

	@Override
	public int damageDropped(int meta)
	{
		return 1;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 4;
	}
}
