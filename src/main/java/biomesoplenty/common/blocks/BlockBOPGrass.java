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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;

public class BlockBOPGrass extends Block
{
	private IIcon[][] icon = new IIcon[2][6];

	public BlockBOPGrass()
	{
		//TODO:	Material.grass
		super(Material.field_151577_b);
		
		//TODO: this.setHardness
		this.func_149711_c(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);

		//setLightValue(0.25F);

		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		this.icon[0][0] = iconRegister.registerIcon("biomesoplenty:holydirt");
		
		this.icon[0][1] = iconRegister.registerIcon("biomesoplenty:holygrass_top");
		this.icon[0][2] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		this.icon[0][3] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		this.icon[0][4] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		this.icon[0][5] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		
		this.icon[1][0] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_bottom");
		this.icon[1][1] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_top");
		this.icon[1][2] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		this.icon[1][3] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		this.icon[1][4] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		this.icon[1][5] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= this.icon.length) meta = 1;
		if (side < 0 || side >= this.icon[meta].length) side = 1;
	    
		return this.icon[meta][side];
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < 2; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
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
	//TODO:		onBlockPlaced()
	public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
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
	//TODO: 	randomDisplayTick()
	public void func_149734_b(World world, int x, int y, int z, Random random)
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
	//TODO:		updateTick()
	public void func_149674_a(World world, int x, int y, int z, Random random)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			if (world.provider.isHellWorld)
			{
				//TODO: setBlock()
				world.func_147465_d(x, y + 1, z, Blocks.fire, 0, 2);
				//TODO: setBlock()
				world.func_147465_d(x, y, z, this, 1, 2);
			}

			if (!world.isRemote)
			{
				if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
				{
					//TODO: setBlock()
					world.func_147465_d(x, y, z, BOPBlockHelper.get("holyDirt"), 0, 2);
				}
				else if (world.getBlockLightValue(x, y + 1, z) >= 9)
				{
					for (int var6 = 0; var6 < 4; ++var6)
					{
						int rX = x + random.nextInt(3) - 1;
						int rY = y + random.nextInt(5) - 3;
						int rZ = z + random.nextInt(3) - 1;
						//TODO:			    getBlock()
	                    Block block = world.func_147439_a(rX, rY + 1, rZ);

	                    //TODO:	  getBlock()
						if (world.func_147439_a(rX, rY, rZ) == BOPBlockHelper.get("holyDirt") && world.getBlockLightValue(rX, rY + 1, rZ) >= 4 && world.getBlockLightOpacity(rX, rY + 1, rZ) <= 2)
						{
							//TODO: setBlock()
							world.func_147465_d(rX, rY, rZ, BOPBlockHelper.get("grass"), 0, 2);
						}
					}
				}
			}
		}
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 1)
		{
			float f = 0.02F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - f, z + 1);
		}

		//TODO:		getCollisionBoundingBoxFromPool()
		return super.func_149668_a(world, x, y, z);
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 1) {
			entity.setFire(2);
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		//TODO:						getItemFromBlock()									getItemFromBlock()
		return metadata == 0 ? Item.func_150898_a(BOPBlockHelper.get("holyDirt")) : Item.func_150898_a(Blocks.dirt);
	}

}
