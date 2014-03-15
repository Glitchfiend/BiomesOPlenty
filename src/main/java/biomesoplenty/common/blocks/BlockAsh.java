package biomesoplenty.common.blocks;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
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

import java.util.Random;

public class BlockAsh extends Block
{
	public BlockAsh()
	{
		//TODO:	Material.sand
		super(Material.sand);

		//TODO: this.setHardness
		this.setHardness(0.4F);	
		this.setHarvestLevel("shovel", 0);

		//TODO setStepSound(Block.soundSandFootstep)
		this.setStepSound(Block.soundTypeSand);

		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:ashblock");
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float yOffset = 0.125F;
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - yOffset, z + 1);
	}

	@Override
	//TODO: 	randomDisplayTick()
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
	//TODO:		onEntityCollidedWithBlock()
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

			if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItemHelper.get("wadingBoots"))
			{
				return;
			}
		}

		entity.motionX *= 0.4D;
		entity.motionZ *= 0.4D;
	}

	//@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return BOPItemHelper.get("misc");
	}

	@Override
	//TODO     damageDropped()
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
