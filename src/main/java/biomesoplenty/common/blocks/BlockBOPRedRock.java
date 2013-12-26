package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPRedRock extends Block
{
	private static final String[] types = new String[] {"redrock", "redcobble", "redbrick"};
	private IIcon[] textures = {null, null, null};

	public BlockBOPRedRock()
	{
		//TODO: Material.rock
		super(Material.field_151576_e);
		
		//TODO setStepSound(Block.soundStoneFootstep)
		this.func_149672_a(Block.field_149780_i);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[types.length];

		for (int i = 0; i < types.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+types[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta == 0 ? 1 : meta;
	}

	@Override
	//TODO:		 getBlockHardness()
	public float func_149712_f(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		//TODO:			 blockHardness
		float hardness = field_149782_v;

		switch (meta)
		{
		case 0:
			hardness = 1.0F;
			break;

		case 1:
			hardness = 1.6F;
			break;

		case 2:
			hardness = 1.1F;
			break;
		}

		return hardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		//TODO:			   blockResistance
		float resistance = field_149781_w;

		switch (meta)
		{
		case 1:
			resistance = 7.5F;
			break;

		case 2:
			resistance = 7.0F;
			break;
		}

		return resistance / 5.0F;
	}
}
