package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPAmethyst extends Block
{
	public static final String[] types = new String[] {"amethystore", "amethystblock", "rubyore", "rubyblock", "peridotore", "peridotblock", "topazore", "topazblock", "tanzaniteore", "tanzaniteblock", "malachiteore", "malachiteblock", "sapphireore", "sapphireblock"};
	private IIcon[] textures;

	public BlockBOPAmethyst()
	{
		super(Material.rock);
		
		this.setStepSound(Block.soundStoneFootstep);
		
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
	public int getDamageValue(World world, int x, int y, int z) 
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
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
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return (meta % 2 == 0) ? Items.gems.get().itemID : this.blockID;
	}

	@Override
	public int damageDropped(int meta)
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
		return (meta % 2 == 0) ? quantityDroppedWithBonus(fortune, random) : 1;
	}

	@Override
	public int quantityDroppedWithBonus(int bonus, Random par2Random)
	{
		if (bonus > 0 && blockID != this.idDropped(0, par2Random, bonus))
		{
			int rnd = par2Random.nextInt(bonus + 2) - 1;

			if (rnd < 0) {
				rnd = 0;
			}

			return this.quantityDropped(par2Random) * (rnd + 1);
		}
		else
			return this.quantityDropped(par2Random);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

		if (this.idDropped(par5, world.rand, par7) != blockID)
		{
			int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
		}
	}
}
