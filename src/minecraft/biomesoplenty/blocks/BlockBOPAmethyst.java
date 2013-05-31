package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class BlockBOPAmethyst extends Block
{
	private static final String[] types = new String[] {"amethystore", "amethystblock"};
	private Icon[] textures;

	public BlockBOPAmethyst(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[types.length];

		for (int i = 0; i < types.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+types[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < types.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		float hardness = blockHardness;

		switch (meta)
		{
		case 0:
			hardness = 3.0F;
			break;

		case 1:
			hardness = 5.0F;
			break;
		}

		return hardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		float resistance = blockResistance;

		switch (meta)
		{
		case 0:
			resistance = 5.0F;
			break;

		case 1:
			resistance = 10.0F;
			break;
		}

		return resistance / 5.0F;
	}

	@Override
	public int idDropped(int meta, Random par2Random, int par3)
	{
		return meta == 0 ? Items.miscItems.get().itemID : blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta == 0 ? 2 : meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
			return quantityDroppedWithBonus(fortune, random);
		else
			return quantityDropped(random);
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

			return (1 + par2Random.nextInt(2)) * (rnd + 1);
		}
		else
			return (1 + par2Random.nextInt(2));
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

		if (this.idDropped(par5, world.rand, par7) != blockID)
		{
			int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 1, 4);
			this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
		}
	}
}
