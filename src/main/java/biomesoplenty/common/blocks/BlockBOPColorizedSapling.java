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
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenPalmTree1;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;
import biomesoplenty.common.world.features.trees.WorldGenRainforestTree1;
import biomesoplenty.common.world.features.trees.WorldGenRedwoodTree2;
import biomesoplenty.common.world.features.trees.WorldGenSacredOak;

public class BlockBOPColorizedSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"sacredoak", "mangrove", "palm", "redwood", "willow", "pine", "mahogany"};
	private IIcon[] textures;
	private static final int TYPES = 15;

	public BlockBOPColorizedSapling()
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
		if (meta < 0 || meta >= saplings.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < saplings.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		//TODO:					  getBlock()
		Block block = world.getBlock(x, y - 1, z);
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
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		//TODO:			   getBlock()
		Block soil = world.getBlock(x, y - 1, z);
		
		if (world.getBlockMetadata(x, y, z) != 1)
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
					(soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
		else
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) &&
					(soil != null && (soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) || soil == Blocks.sand));
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
			    case 0: // Sacred Oak Tree
			        obj = new WorldGenSacredOak(false);
			        break;

			    /*case 1: // Mangrove Tree
			        obj = new WorldGenMangrove(false);
			        break;*/

			    case 2: // Palm Tree
			        rnd = random.nextInt(4);

			        if (rnd == 0) obj = new WorldGenPalmTree1();
			        else obj = new WorldGenPalmTree1(25, 8, 1.5D);

			        break;

			    case 3: // Redwood Tree
			        obj = new WorldGenRedwoodTree2(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves1"), 0, 3, false, 20, 15);
			        break;

			    case 4: // Willow Tree
			        obj = new WorldGenBOPSwampTree(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves2"), 1, 0, 6, 9, BOPBlockHelper.get("colorizedLeaves2"), 0);
			        break;

			    case 5: // Pine Tree
			        obj = new WorldGenPineTree();
			        break;
			        
			    case 6: //Mahogany Tree
			    	obj = new WorldGenRainforestTree1(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("colorizedLeaves2"), 3, 2, false, 8, 8);
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
