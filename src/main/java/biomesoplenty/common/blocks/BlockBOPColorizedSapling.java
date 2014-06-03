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
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenMangrove;
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
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
    
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);
		
        this.checkAndDropBlock(world, x, y, z);
	}
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        this.checkAndDropBlock(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, block);
    }
    
    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
    	return this.canBlockStay(world, x, y, z, itemStack.getItemDamage());
    } 
    
    @Override
	@Deprecated
    public boolean canBlockStay(World world, int x, int y, int z) 
    {
    	return super.canBlockStay(world, x, y, z);
    }
    
    public boolean canBlockStay(World world, int x, int y, int z, int metadata)
    {
		Block block = world.getBlock(x, y - 1, z);

		switch (metadata)
		{
		case 1: // Mangrove
			return block == Blocks.sand;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		}
    }
	
    protected void checkAndDropBlock(World world, int x, int y, int z)
    {
        if (!this.canBlockStay(world, x, y, z, world.getBlockMetadata(x, y, z)))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[saplings.length];

		for (int i = 0; i < saplings.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:sapling_" + saplings[i]);
		}

	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= saplings.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < saplings.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		Block block = world.getBlock(x, y - 1, z);
		int meta = world.getBlockMetadata(x, y - 1, z);

		switch (meta)
		{
		case 1: // Mangrove
			System.out.println("H");
			return block == Blocks.sand;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
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

			    case 1: // Mangrove Tree
			        obj = new WorldGenMangrove();
			        break;

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
			world.setBlockToAir(x, y, z);

			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) 
			{
				world.setBlock(x, y, z, this, meta, 2);
			}
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
