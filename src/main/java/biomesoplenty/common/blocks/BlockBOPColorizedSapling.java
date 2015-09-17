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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenMangrove;
import biomesoplenty.common.world.features.trees.WorldGenMixedTree;
import biomesoplenty.common.world.features.trees.WorldGenPalmTree1;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;
import biomesoplenty.common.world.features.trees.WorldGenRainforestTree1;
import biomesoplenty.common.world.features.trees.WorldGenRedwoodTree2;
import biomesoplenty.common.world.features.trees.WorldGenSacredOak;

public class BlockBOPColorizedSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"sacredoak", "mangrove", "palm", "redwood", "willow", "pine", "mahogany", "flowering"};
	private IIcon[] textures;

	public BlockBOPColorizedSapling()
	{
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
    
    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
    	int metadata = itemStack != null ? itemStack.getItemDamage() : 0;
    	return this.canPlaceBlockAt(world, x, y, z) && this.isValidPosition(world, x, y, z, metadata);
    } 
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z);
    }
    
    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
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
	
    @Override
	protected void checkAndDropBlock(World world, int x, int y, int z)
    {
    	int meta = world.getBlockMetadata(x, y, z);
        if (!this.isValidPosition(world, x, y, z, meta))
        {
            this.dropBlockAsItem(world, x, y, z, meta, 0);
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
	public void func_149878_d(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z) & 7;
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
			        obj = new WorldGenRedwoodTree2(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves1, 0, 3, false, 20, 15);
			        break;

			    case 4: // Willow Tree
			        obj = new WorldGenBOPSwampTree(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves2, 1, 0, 6, 9, BOPCBlocks.colorizedLeaves2, 0);
			        break;

			    case 5: // Pine Tree
			        obj = new WorldGenPineTree();
			        break;
			        
			    case 6: //Mahogany Tree
			    	obj = new WorldGenRainforestTree1(BOPCBlocks.logs4, BOPCBlocks.colorizedLeaves2, 3, 2, false, 8, 8);
			    	break;
			    	
			    case 7: //Flowering Oak Tree
			    	obj = new WorldGenMixedTree(Blocks.log, Blocks.leaves, 0, 0, BOPCBlocks.colorizedLeaves2, 3);
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
        return MathHelper.clamp_int(meta & 7, 0, saplings.length - 1);
    }
}
