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
import biomesoplenty.api.BOPBlockHelper;

public class BlockBOPSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"apple", "yellowautumn", "bamboo", "magic", "dark", "dead", "fir", "holy", "orangeautumn", "origin", "pinkcherry", "maple", "whitecherry", "hellbark", "jacaranda", "persimmon"};
	private IIcon[] textures;
	private static final int TYPES = 15;

	public BlockBOPSapling()
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
		if (meta < 0 || meta >= saplings.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < saplings.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block block = world.func_147439_a(x, y - 1, z);
		
		switch (metadata)
		{
		case 7: // Loftwood
			return block == BOPBlockHelper.get("holyGrass") || block == BOPBlockHelper.get("holyDirt");

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean func_149707_d(World world, int x, int y, int z, int side)
	{
		return isValidPosition(world, x, y, z, -1);
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
					(soil != null && (soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) || soil == BOPBlockHelper.get("holyGrass")));
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
			case 0: // Apple Tree
			obj = new WorldGenApple(false);
			break;

			case 1: // Autumn Tree
			obj = new WorldGenAutumn(false);
			break;

			case 2: // Bamboo Tree
				rnd = random.nextInt(8);

				if (rnd == 0) {
					obj = new WorldGenBambooTree(false);
				} else {
					obj = new WorldGenBambooTree2(false);
				}
				break;

			case 3: // Magic Tree
				obj = new WorldGenMystic2(false);
				break;

			case 4: // Dark Tree
				rnd = random.nextInt(8);

				if (rnd == 0) {
					obj = new WorldGenOminous2();
				} else {
					obj = new WorldGenOminous1(false);
				}
				break;

			case 5: // Dead Tree
				obj = new WorldGenDeadTree2(false);
				break;

			case 6: // Fir Tree
				obj = new WorldGenTaiga9(false);
				break;

			case 7: // Holy Tree
				obj = new WorldGenPromisedTree(false);
				break;

			case 8: // Autumn Tree
				obj = new WorldGenAutumn2(false);
				break;

			case 9: // Origin Tree
				obj = new WorldGenOriginTree(false);
				break;

			case 10: // Pink Cherry Tree
				obj = new WorldGenCherry1(false);
				break;

			case 11: // Maple Tree
				obj = new WorldGenMaple(false);
				break;

			case 12: // White Cherry Tree
				obj = new WorldGenCherry2(false);
				break;

			case 13: // Hellbark
				obj = new WorldGenNetherBush();
				break;

			case 14: // Jacaranda
				obj = new WorldGenJacaranda(false);
				break;
				
			case 15: // Persimmon
				obj = new WorldGenPersimmon(false);
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
