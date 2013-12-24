package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPColorizedSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"acacia", "mangrove", "palm", "redwood", "willow", "pine", "sacredoak"};
	private IIcon[] textures;
	private static final int TYPES = 15;

	public BlockBOPColorizedSapling()
	{
		//TODO: this.setHardness
		this.func_149711_c(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[saplings.length];

		for (int i = 0; i < saplings.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:sapling_" + saplings[i]);
		}

	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= saplings.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < saplings.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean func_149707_d(World world, int x, int y, int z, int side)
	{
		//TODO:					  getBlock()
		Block block = world.func_147439_a(x, y - 1, z);
		int meta = world.getBlockMetadata(x, y - 1, z);

		switch (meta)
		{
		case 1: // Mangrove
			return block == Blocks.sand;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		}
	}

	@Override
	//TODO:		   canBlockStay()
	public boolean func_149718_j(World world, int x, int y, int z)
	{
		//TODO:			   getBlock()
		Block soil = world.func_147439_a(x, y - 1, z);
		
		if (world.getBlockMetadata(x, y, z) != 1)
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
					(soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
		else
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
					(soil != null && (soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) || soil == Blocks.sand));
	}

	@Override
	//TODO:		updateTick()
	public void func_149674_a(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0) 
			{
				//TODO: growTree()
				this.func_149878_d(world, x, y, z, random);
			}
		}
	}

	@Override
	//TODO:		growTree()
	public void func_149878_d(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z) & TYPES;
		Object obj = null;
		int rnd = random.nextInt(8);

		/*TODO: FEATURE if (obj == null)
		{
			switch (meta)
			{
			    case 0: // Acacia Tree
			        obj = new WorldGenAcacia(false);
			        break;

			    case 1: // Mangrove Tree
			        obj = new WorldGenMangrove(false);
			        break;

			    case 2: // Palm Tree
			        rnd = random.nextInt(4);

			        if (rnd == 0) {
			            obj = new WorldGenPalmTree1();
			        } else {
			            obj = new WorldGenPalmTree3();
			        }
			        break;

			    case 3: // Redwood Tree
			        obj = new WorldGenRedwoodTree2(false);
			        break;

			    case 4: // Willow Tree
			        obj = new WorldGenWillow();
			        break;

			    case 5: // Pine Tree
			        obj = new WorldGenPineTree();
			        break;

			    case 6: // Sacred Oak
			        obj = new WorldGenMassiveTree(false);
			        break;
			}
		}*/

		if (obj != null)
		{
			//TODO: setBlockToAir()
			world.func_147468_f(x, y, z);

			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) 
			{
				//TODO: setBlock()
				world.func_147465_d(x, y, z, this, meta, 2);
			}
		}
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & TYPES;
	}

	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) & TYPES;
	}
}
