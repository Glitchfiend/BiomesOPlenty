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
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPPetals extends BlockLeavesBase implements IShearable
{
	private static final String[] petals = new String[] {"bigflowerred", "bigfloweryellow"};
	private IIcon[][] textures;

	public BlockBOPPetals()
	{
    	//TODO:	Material.leaves
        super(Material.field_151584_j, false);
		
		//TODO:		setBurnProperties() getIdFromBlock()
		Blocks.fire.func_149842_a(func_149682_b(this), 30, 60);
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		//TODO: this.setHardness
		this.func_149711_c(0.2F);
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);

		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[petals.length][2];

		for (int i = 0; i < petals.length; ++i) {
			textures[i][0] = iconRegister.registerIcon("biomesoplenty:" + petals[i]);
			textures[i][1] = iconRegister.registerIcon("biomesoplenty:better_" + petals[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		return textures[meta < 0 || meta >= textures.length ? 0 : meta][0];
	}
	
	public IIcon getIconBetterLeaves(int meta, float randomIndex)
	{
		return textures[meta < 0 || meta >= textures.length ? 0 : meta][1];
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < textures.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
			//TODO:		getItemFromBlock()
			return Item.func_150898_a(Blocks.red_flower);
		else
			//TODO:     getItemFromBlock()
			return Item.func_150898_a(Blocks.yellow_flower);
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & 15;
	}

	@Override
	//TODO:    quantityDropped()
	public int func_149745_a(Random random)
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
