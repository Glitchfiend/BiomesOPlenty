package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.utils.ISubLocalization;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBOPNewFarmland extends BlockFarmland implements ISubLocalization
{

	IIcon[] dry = new IIcon[BlockBOPNewGrass.grassTypes.length];
	IIcon[] wet = new IIcon[BlockBOPNewGrass.grassTypes.length];
	IIcon[] dirts = new IIcon[BlockBOPNewGrass.grassTypes.length];

	public BlockBOPNewFarmland()
	{
		super();
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setStepSound(soundTypeGravel);
	}

	@Override
	public String getUnlocalizedName(String baseName, ItemStack itemStack) 
	{
		int meta = itemStack.getItemDamage();
		if (meta > BlockBOPNewGrass.grassTypes.length-1) meta = 0;
		return baseName + "." + BlockBOPNewGrass.grassTypes[meta / 2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTab, List blockList)
	{
		for (int i = 0; i < BlockBOPNewGrass.grassTypes.length * 2; i++)
		{
			blockList.add(new ItemStack(block, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		for (int dirt = 0; dirt < BlockBOPNewGrass.grassTypes.length; dirt++)
		{
			dry[dirt] = iconRegister.registerIcon("biomesoplenty:farmland_dry_" + BlockBOPNewGrass.grassTypes[dirt]);
			wet[dirt] = iconRegister.registerIcon("biomesoplenty:farmland_wet_" + BlockBOPNewGrass.grassTypes[dirt]);
			dirts[dirt] = iconRegister.registerIcon("biomesoplenty:dirt_" + BlockBOPNewGrass.grassTypes[dirt]);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta > BlockBOPNewGrass.grassTypes.length-1) meta = 0;

		return side == 1 ? ((meta & 1) == 0 ? dry[meta / 2] : wet[meta / 2]) : dirts[meta / 2];
	}

	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (!this.waterNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
		{
			if ((meta & 1) == 1)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta - 1, 2);
			}
			else if (!this.hasFlower(world, x, y, z))
			{
				world.setBlock(x, y, z, BOPCBlocks.newBopDirt, meta, 2);
			}
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, ((meta / 2) * 2) + 1, 2);
		}
	}

	private boolean waterNearby(World world, int x, int y, int z)
	{
		for (int l = x - 4; l <= x + 4; ++l)
		{
			for (int i1 = y; i1 <= y + 1; ++i1)
			{
				for (int j1 = z - 4; j1 <= z + 4; ++j1)
				{
					if (world.getBlock(l, i1, j1).getMaterial() == Material.water)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean hasFlower(World world, int x, int y, int z)
	{
		byte b0 = 0;

		for (int l = x - b0; l <= x + b0; ++l)
		{
			for (int i1 = z - b0; i1 <= z + b0; ++i1)
			{
				Block block = world.getBlock(l, y + 1, i1);

				if (block instanceof IPlantable && canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) block))
				{
					return true;
				}
			}
		}

		return false;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		Material material = world.getBlock(x, y + 1, z).getMaterial();

		if (material.isSolid())
		{
			world.setBlock(x, y, z, BOPCBlocks.newBopDirt, (world.getBlockMetadata(x, y, z) / 2) * 2, 2);
		}
	}

	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
	{
		if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F)
		{
			if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
			{
				return;
			}

			world.setBlock(x, y, z, BOPCBlocks.newBopDirt, (world.getBlockMetadata(x, y, z) / 2) * 2, 2);
		}
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(BOPCBlocks.newBopDirt, 1, (metadata / 2) * 2));
		return ret;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return new ItemStack(BOPCBlocks.newBopDirt, 1, (world.getBlockMetadata(x, y, z) / 2) * 2).getItem();
	}

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

		switch (plantType)
		{
			case Crop:
				return true;
			default:
				return super.canSustainPlant(world, x, y, z, direction, plantable);
		}
	}
}
