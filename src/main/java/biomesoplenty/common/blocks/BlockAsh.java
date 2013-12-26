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

public class BlockAsh extends Block
{
	public BlockAsh()
	{
		//TODO:	Material.sand
		super(Material.field_151595_p);
		
		//TODO setStepSound(Block.soundSandFootstep)
		this.func_149672_a(Block.field_149776_m);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:ashblock");
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		float yOffset = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - yOffset, z + 1);
	}

	@Override
	//TODO: 	randomDisplayTick()
	public void func_149734_b(World world, int x, int y, int z, Random random)
	{
		super.func_149734_b(world, x, y, z, random);

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
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

			/*TODO: FEATURE if (inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Items.wadingBoots.get().itemID)
			{
				return;
			}*/
		}

		entity.motionX *= 0.4D;
		entity.motionZ *= 0.4D;
	}

	//@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		return BOPItemHelper.get("misc");
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return 1;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 4;
	}
}
