package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPSlab extends BlockSlab
{
	public static enum SlabCategory
	{
		WOOD1, WOOD2, STONE;
	}
	
	private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "pine", "hell_bark", "jacaranda"};
	private static final String[] rockTypes = new String[] {"redcobble", "redbrick", "mudbrick", "holycobble", "holybrick"};
	private IIcon[] textures;
	protected final boolean isDoubleSlab;

	private final SlabCategory category;

	public BlockBOPSlab(boolean isDoubleSlab, Material material, SlabCategory cat)
	{
		super(isDoubleSlab, material);

		category = cat;
		
		//TODO: 		Material.wood
		if (material == Material.field_151575_d)
		{
			//TODO:		setBurnProperties() getIdFromBlock()
			Blocks.fire.func_149842_a(func_149682_b(this), 5, 20);
			//TODO: this.setHardness
			this.func_149711_c(2.0F);
			setResistance(5.0F);
			setStepSound(Block.soundWoodFootstep);
		}
		//TODO: 			Material.rock
		else if (material == Material.field_151576_e) 
		{
			//TODO setStepSound(Block.soundStoneFootstep)
			this.func_149672_a(Block.field_149780_i);
		}

		if (!isDoubleSlab) 
		{
			//TODO: this.setCreativeTab()
			this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
		}
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		if (category == SlabCategory.STONE)
		{
			textures = new IIcon[rockTypes.length];

			for (int i = 0; i < rockTypes.length; ++i) {
				textures[i] = iconRegister.registerIcon("biomesoplenty:"+rockTypes[i]);
			}
		}
		else
		{
			textures = new IIcon[woodTypes.length];

			for (int i = 0; i < woodTypes.length; ++i) {
				textures[i] = iconRegister.registerIcon("biomesoplenty:plank_"+woodTypes[i]);
			}
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (category == SlabCategory.STONE)
			return textures[getTypeFromMeta(meta)];
		else
			return textures[(getTypeFromMeta(meta) + category.ordinal() * 8)];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		int max = 0;

		if (category == SlabCategory.WOOD1) {
			max = 8;
		} else if (category == SlabCategory.WOOD2) {
			max = 5;
		} else if (category == SlabCategory.STONE) {
			max = 5;
		}

		for (int i = 0; i < max; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public String getFullSlabName(int meta)
	{
		if (category == SlabCategory.STONE)
			return (new StringBuilder()).append(rockTypes[getTypeFromMeta(meta)]).append("Slab").toString();
		else
			return (new StringBuilder()).append(woodTypes[getWoodType(meta)]).append("Slab").toString();
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & 7;
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		if (isDoubleSlab)
		{
			if (this == Blocks.woodenDoubleSlab1.get().blockID)
				return Blocks.woodenSingleSlab1.get().blockID;
			if (this == Blocks.woodenDoubleSlab2.get().blockID)
				return Blocks.woodenSingleSlab2.get().blockID;
			else
				return Blocks.stoneSingleSlab.get().blockID;
		}
		else
			//TODO:		getItemForBlock()
			return Item.func_150898_a(this);
	}

	@Override
	//TODO:		 getBlockHardness()
	public float func_149712_f(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		float hardness = blockHardness;

		if (category == SlabCategory.STONE)
		{
			switch (getTypeFromMeta(meta))
			{
			case 0:
			case 3:
				hardness = 1.6F;
				break;

			case 1:
			case 4:
				hardness = 1.1F;
				break;

			case 2:
				hardness = 1.0F;
				break;
			}
		}

		return hardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		float resistance = blockHardness;

		if (category == SlabCategory.STONE)
		{
			switch (getTypeFromMeta(meta))
			{
			case 0:
			case 3:
				resistance = 7.0F;
				break;

			case 1:
			case 4:
				resistance = 7.5F;
				break;

			case 2:
				resistance = 2.0F;
				break;
			}
		}

		return resistance / 5.0F;
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return !isDoubleSlab ? blockID : (blockID == Blocks.woodenDoubleSlab1.get().blockID ? Blocks.woodenSingleSlab1.get().blockID : (blockID == Blocks.woodenDoubleSlab2.get().blockID ? Blocks.woodenSingleSlab2.get().blockID : Blocks.stoneSingleSlab.get().blockID));
	}

	@Override
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(blockID, 2, par1);
	}

	private int getWoodType(int meta)
	{
		meta = getTypeFromMeta(meta) + category.ordinal() * 8;
		if (meta < woodTypes.length)
			return meta;

		return 0;
	}

	private static int getTypeFromMeta(int meta)
	{
		return meta & 7;
	}
}
