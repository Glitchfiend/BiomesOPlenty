package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class BlockBOPAmethyst extends Block
{
	public static final String[] types = new String[] {"amethystore", "amethystblock", "rubyore", "rubyblock", "peridotore", "peridotblock", "topazore", "topazblock", "tanzaniteore", "tanzaniteblock", "malachiteore", "malachiteblock", "sapphireore", "sapphireblock"};
	private IIcon[] textures;

	public BlockBOPAmethyst()
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
		if (meta < 0 || meta >= textures.length) {
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
	//TODO:		 getBlockHardness()
	public float func_149712_f(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return (meta % 2 == 0) ? 3.0F : 5.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return (meta % 2 == 0) ? 1.0F : 2.0F;
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		//TODO:														  getItemFromBlock()
		return (metadata % 2 == 0) ? BOPItemHelper.get("gems") : Item.func_150898_a(this);
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		if (meta == 0)
		{
			return 0;
		}
		if (meta == 1)
		{
			return meta;
		}
		if (meta == 2)
		{
			return 1;
		}
		if (meta == 3)
		{
			return meta;
		}
		if (meta == 4)
		{
			return 2;
		}
		if (meta == 5)
		{
			return meta;
		}
		if (meta == 6)
		{
			return 3;
		}
		if (meta == 7)
		{
			return meta;
		}
		if (meta == 8)
		{
			return 4;
		}
		if (meta == 9)
		{
			return meta;
		}
		if (meta == 10)
		{
			return 5;
		}
		if (meta == 11)
		{
			return meta;
		}
		if (meta == 12)
		{
			return 6;
		}
		if (meta == 13)
		{
			return meta;
		}
		
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		//TODO:					quantityDroppedWithBonus()
		return (meta % 2 == 0) ? func_149679_a(fortune, random) : 1;
	}

	@Override
	//TODO:		quantityDroppedWithBonus()
	public int func_149679_a(int bonus, Random random)
	{
		//TODO:				   getItemForBlock()		getItem()
		if (bonus > 0 && Item.func_150898_a(this) != this.func_149650_a(0, random, bonus))
		{
			int rnd = random.nextInt(bonus + 2) - 1;

			if (rnd < 0) {
				rnd = 0;
			}

			//TODO:		quantityDropped()
			return this.func_149745_a(random) * (rnd + 1);
		}
		else
			return this.func_149745_a(random);
	}

	@Override
	//TODO: 	dropBlockAsItemWithChance()
	public void func_149690_a(World world, int x, int y, int z, int metadata, float chance, int fortune)
	{
		//TODO: dropBlockAsItemWithChance()
		super.func_149690_a(world, x, y, z, metadata, chance, fortune);

		//TODO:	 getItemDropped()									  getItemFromBlock()
		if (this.func_149650_a(metadata, world.rand, fortune) != Item.func_150898_a(this))
		{
			int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			//TODO: dropXpOnBlockBreak()
			this.func_149657_c(world, x, y, z, var8);
		}
	}
}
