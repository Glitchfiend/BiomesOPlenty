package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPGrass extends Block
{
	private IIcon[][] blockIcon = new IIcon[2][6];

	public BlockBOPGrass()
	{
		super(Material.grass);
		this.setTickRandomly(true);
		setStepSound(Block.soundGrassFootstep);
		setHardness(0.6F);
		//setLightValue(0.25F);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		blockIcon[0][0] = iconRegister.registerIcon("biomesoplenty:holydirt");
		blockIcon[0][1] = iconRegister.registerIcon("biomesoplenty:holygrass_top");
		blockIcon[0][2] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		blockIcon[0][3] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		blockIcon[0][4] = iconRegister.registerIcon("biomesoplenty:holygrass_side");
		blockIcon[0][5] = iconRegister.registerIcon("biomesoplenty:holygrass_side");

		blockIcon[1][0] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_bottom");
		blockIcon[1][1] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_top");
		blockIcon[1][2] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		blockIcon[1][3] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		blockIcon[1][4] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
		blockIcon[1][5] = iconRegister.registerIcon("biomesoplenty:smolderinggrass_side");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= blockIcon.length)
	        meta = 1;
		if (side < 0 || side >= blockIcon[meta].length)
	        side = 1;
	    
		return blockIcon[meta][side];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 2; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
	{
		int blockId = world.getBlockId(x, y, z);
		
		if (metadata == 0)
		{
			if (blockId == this.blockID && side == UP && world.provider.dimensionId == -1)
				return true;
		}
		else if (metadata == 1) return true;

		return super.isFireSource(world, x, y, z, metadata, side);
	}

	@Override
	public int onBlockPlaced(World world, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int meta)
	{
		if (meta == 0)
			if (world.provider.isHellWorld)
			{
				world.playSound(par2, par3, par4, "mob.ghast.death", 20.0F, 0.95F + (float)Math.random() * 0.1F, true);

				for (int l = 0; l < 8; ++l)
				{
					world.spawnParticle("flame", par2 + Math.random(), par3 + Math.random(), par4 + Math.random(), 0.0D, 0.0D, 0.0D);
					world.spawnParticle("smoke", par2 + Math.random(), par3 + Math.random(), par4 + Math.random(), 0.0D, 0.0D, 0.0D);
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
			if (random.nextInt(4) == 0) {
				world.spawnParticle("smoke", x + random.nextFloat(), y + 1.1F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}

			if (random.nextInt(6) == 0) {
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
				world.setBlock(x, y + 1, z, Block.fire.blockID);
				world.setBlock(x, y, z, Blocks.holyGrass.get().blockID, 1, 2);
			}

			if (!world.isRemote)
			{
				if (world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2)
				{
					world.setBlock(x, y, z, Blocks.holyDirt.get().blockID);
				}
				else if (world.getBlockLightValue(x, y + 1, z) >= 9)
				{
					for (int var6 = 0; var6 < 4; ++var6)
					{
						int var7 = x + random.nextInt(3) - 1;
						int var8 = y + random.nextInt(5) - 3;
						int var9 = z + random.nextInt(3) - 1;
						int var10 = world.getBlockId(var7, var8 + 1, var9);

						if (world.getBlockId(var7, var8, var9) == Blocks.holyDirt.get().blockID && world.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
						{
							world.setBlock(var7, var8, var9, Blocks.holyGrass.get().blockID, 0, 2);
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
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - f, z + 1);
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
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return meta == 0 ? Blocks.holyDirt.get().blockID : Block.dirt.blockID;
	}

}
