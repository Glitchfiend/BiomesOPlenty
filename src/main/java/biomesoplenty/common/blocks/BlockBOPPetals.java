package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPPetals extends BlockLeavesBase implements IShearable
{
	private static final String[] petals = new String[] {"bigflowerred", "bigfloweryellow"};
	private IIcon[][] textures;

	public BlockBOPPetals()
	{
        super(Material.leaves, false);
		
		this.setTickRandomly(true);
		this.setHardness(0.2F);
		this.setStepSound(Block.soundTypeGrass);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[petals.length][2];

		for (int i = 0; i < petals.length; ++i) {
			textures[i][0] = iconRegister.registerIcon("biomesoplenty:" + petals[i]);
			textures[i][1] = iconRegister.registerIcon("biomesoplenty:better_" + petals[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta < 0 || meta >= textures.length ? 0 : meta][0];
	}
	
	public IIcon getIconBetterLeaves(int meta, float randomIndex)
	{
		return textures[meta < 0 || meta >= textures.length ? 0 : meta][1];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < textures.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
			return Item.getItemFromBlock(Blocks.red_flower);
		else
			return Item.getItemFromBlock(Blocks.yellow_flower);
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 15;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 15));
		return ret;
	}
}
