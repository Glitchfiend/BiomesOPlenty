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
		super(Material.rock);
		
		//TODO setStepSound(Block.soundStoneFootstep)
		this.setStepSound(Block.soundTypePiston);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[types.length];

		for (int i = 0; i < types.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+types[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:	   getDamageValue()
	public int getDamageValue(World world, int x, int y, int z) 
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return meta == 0 ? 1 : meta;
	}

	@Override
	//TODO:		 getBlockHardness()
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		//TODO:			 blockHardness
		float hardness = blockHardness;

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
		float resistance = blockResistance;

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
