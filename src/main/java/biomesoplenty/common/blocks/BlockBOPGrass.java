package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;

public class BlockBOPGrass extends Block
{
	public static final String[] types = new String[] {"spectralmoss", "smolderinggrass"};
	private IIcon[][] icon = new IIcon[2][6];

	public BlockBOPGrass()
	{
		super(Material.grass);
		
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		this.setTickRandomly(true);
		this.setStepSound(Block.soundTypeGrass);

		//setLightValue(0.25F);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icon[0][0] = iconRegister.registerIcon("biomesoplenty:spectralmoss_bottom");
		this.icon[0][1] = iconRegister.registerIcon("biomesoplenty:spectralmoss_top");
		this.icon[0][2] = iconRegister.registerIcon("biomesoplenty:spectralmoss_side");
		this.icon[0][3] = iconRegister.registerIcon("biomesoplenty:spectralmoss_side");
		this.icon[0][4] = iconRegister.registerIcon("biomesoplenty:spectralmoss_side");
		this.icon[0][5] = iconRegister.registerIcon("biomesoplenty:spectralmoss_side");
		
		this.icon[1][0] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_bottom");
		this.icon[1][1] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_top");
		this.icon[1][2] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		this.icon[1][3] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		this.icon[1][4] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		this.icon[1][5] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.icon.length) meta = 1;
		if (side < 0 || side >= this.icon[meta].length) side = 1;
	    
		return this.icon[meta][side];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (metadata == 0)
		{
			if (side == ForgeDirection.UP && world.provider.dimensionId == -1)
				return true;
		}
		else if (metadata == 1) return true;

		return super.isFireSource(world, x, y, z, side);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		if (meta == 0)
		{
			if (world.provider.isHellWorld)
			{
				world.playSound(x, y, z, "mob.ghast.death", 20.0F, 0.95F + (float)Math.random() * 0.1F, true);

				for (int l = 0; l < 8; ++l)
				{
					world.spawnParticle("flame", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
					world.spawnParticle("smoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
				}
			}
		}
		
		return meta;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
			return;

		if (world.getBlockMetadata(x, y, z) == 1)
		{
			if (random.nextInt(4) == 0) 
			{
				world.spawnParticle("smoke", x + random.nextFloat(), y + 1.1F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}

			if (random.nextInt(6) == 0) 
			{
				world.spawnParticle("flame", x + random.nextFloat(), y + 1.1F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			if (world.provider.isHellWorld)
			{
				world.setBlock(x, y + 1, z, Blocks.fire, 0, 2);
				world.setBlock(x, y, z, this, 1, 2);
			}

			if (!world.isRemote)
			{
				if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
				{
					world.setBlock(x, y, z, Blocks.end_stone, 0, 2);
				}
				else if (world.getBlockLightValue(x, y + 1, z) >= 9)
				{
					for (int var6 = 0; var6 < 4; ++var6)
					{
						int rX = x + random.nextInt(3) - 1;
						int rY = y + random.nextInt(5) - 3;
						int rZ = z + random.nextInt(3) - 1;
	                    Block block = world.getBlock(rX, rY + 1, rZ);

						if (world.getBlock(rX, rY, rZ) == Blocks.end_stone && world.getBlockLightValue(rX, rY + 1, rZ) >= 4 && world.getBlockLightOpacity(rX, rY + 1, rZ) <= 2)
						{
							world.setBlock(rX, rY, rZ, BOPCBlocks.bopGrass, 0, 2);
						}
					}
				}
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 1)
		{
			float f = 0.02F;
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
		}

		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 1) {
			entity.setFire(2);
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return metadata == 0 ? Item.getItemFromBlock(Blocks.end_stone) : Item.getItemFromBlock(Blocks.dirt);
	}

}
