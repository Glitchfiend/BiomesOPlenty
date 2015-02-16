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
		//TODO: Material.sponge
		super(Material.sponge);
		
		//TODO: this.setHardness
		this.setHardness(0.4F);	
		
		//TODO setStepSound(Block.soundGravelFootstep)
		this.setStepSound(Block.soundTypeGravel);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:flesh");
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float var5 = 0.125F;
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - var5, z + 1);
	}

	@Override
	//TODO: 	randomDisplayTick()
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		super.randomDisplayTick(world, x, y, z, random);

		if (random.nextInt(4) == 0)
		{
			//TODO:											         getIdFromBlock()
			world.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(this)) + "_0", x + random.nextFloat(), y - 0.4F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
		
		if (random.nextInt(12) == 0)
		{
			//TODO:											         getIdFromBlock()
			world.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(this)) + "_0", x + random.nextFloat(), y + 1.0F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
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
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return BOPCItems.misc;
	}
	
	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return 3;
	}

	@Override
	//TODO:    getQuantityDropped()
	public int quantityDropped(Random random)
	{
		return random.nextInt(5);
	}
}
