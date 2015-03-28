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
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.features.trees.WorldGenBOPBigTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BlockBOPSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"apple", "yellowautumn", "bamboo", "magic", "dark", "dead", "fir", "ethereal", "orangeautumn", "origin", "pinkcherry", "maple", "whitecherry", "hellbark", "jacaranda", "persimmon"};
	private IIcon[] textures;
	private static final int TYPES = 15;

	public BlockBOPSapling()
	{
		//TODO: this.setHardness
		this.setHardness(0.0F);
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);

		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[saplings.length];

		for (int i = 0; i < saplings.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:sapling_" + saplings[i]);
		}

	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= saplings.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < saplings.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block block = world.getBlock(x, y - 1, z);

		switch (metadata)
		{
		case 7: // Loftwood
			return block == BOPCBlocks.bopGrass;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		return isValidPosition(world, x, y, z, -1);
	}

	@Override
	//TODO:		   canBlockStay()
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		//TODO:			   getBlock()
		Block soil = world.getBlock(x, y - 1, z);

		if (world.getBlockMetadata(x, y, z) != 1)
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
					(soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
		else
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
					(soil != null && (soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) || soil == BOPCBlocks.bopGrass));
	}

	@Override
	//TODO:		updateTick()
	public void updateTick(World world, int x, int y, int z, Random random)
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

		if (obj == null)
		{
			switch (meta)
			{
			case 0: // Apple Tree
				obj = new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, 1);
				break;

			case 1: // Autumn Tree
				obj = new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves1, 2, 0, false, 5, 3, false);
				break;

			case 2: // Bamboo Tree
				rnd = random.nextInt(8);

				if (rnd == 0) {
					obj = new WorldGenBulbTree(BOPCBlocks.bamboo, BOPCBlocks.leaves1, 0, 1, false, 10, 12, false);
				} else {
					obj = new WorldGenBulbTree(BOPCBlocks.bamboo, BOPCBlocks.leaves1, 0, 1, false, 11, 3, false);
				}
				break;

			case 3: // Magic Tree
				obj = new WorldGenOriginalTree(BOPCBlocks.logs2, BOPCBlocks.leaves1, 1, 2, false, 5, 3, false);
				break;

			case 4: // Dark Tree
				rnd = random.nextInt(8);

				if (rnd == 0) obj = new WorldGenBOPSwampTree(BOPCBlocks.logs1, BOPCBlocks.leaves1, 2, 3, 5, 4);
				else obj = new WorldGenBOPTaiga2(BOPCBlocks.logs1, BOPCBlocks.leaves1, 2, 3, false, 14, 6, 0, -1);

				break;

			case 5: // Dead Tree
				obj = new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves2, 0, 0, false, 5, 3, false);
				break;

			case 6: // Fir Tree
				obj = new WorldGenBOPTaiga2(BOPCBlocks.logs1, BOPCBlocks.leaves2, 3, 1, false, 10, 10, 5, 4);
				break;

				/*case 7: // Ethereal Tree
				obj = new WorldGenPromisedTree(false);
				break;*/

			case 8: // Autumn Tree
				obj = new WorldGenOriginalTree(Blocks.log2, BOPCBlocks.leaves2, 1, 3, false, 5, 3, false);
				break;

			case 9: // Origin Tree
				obj = new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves3, 0, 0, false, 5, 3, false);
				break;

			case 10: // Pink Cherry Tree
				obj = new WorldGenBOPBigTree(BOPCBlocks.logs1, BOPCBlocks.leaves3, 1, 1);
				break;

			case 11: // Maple Tree
				obj = new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves3, 0, 2, false, 5, 3, false);
				break;

			case 12: // White Cherry Tree
				obj = new WorldGenBOPBigTree(BOPCBlocks.logs1, BOPCBlocks.leaves3, 1, 3);
				break;

			case 13: // Hellbark
				obj = new WorldGenMiniShrub(BOPCBlocks.logs4, BOPCBlocks.leaves4, 1, 0, BOPCBlocks.overgrownNetherrack);
				break;

			case 14: // Jacaranda
				obj = new WorldGenOriginalTree(BOPCBlocks.logs4, BOPCBlocks.leaves4, 2, 1);
				break;

			case 15: // Persimmon
				obj = new WorldGenOriginalTree(Blocks.log, BOPCBlocks.leaves2, 0, 0, 1);
				break;
			}
		}

		if (obj != null)
		{
			//TODO: setBlockToAir()
			world.setBlockToAir(x, y, z);

			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) 
			{
				//TODO: setBlock()
				world.setBlock(x, y, z, this, meta, 2);
			}
		}
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return meta & TYPES;
	}

	@Override
	//TODO:	   getDamageValue()
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) & TYPES;
	}
}
