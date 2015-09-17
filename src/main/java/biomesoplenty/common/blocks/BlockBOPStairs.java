package biomesoplenty.common.blocks;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPStairs extends BlockStairs
{
	public static enum Category
	{
		SACREDOAK ("wood"), CHERRY ("wood"), DARK ("wood"), FIR ("wood"), ETHEREAL ("wood"), MAGIC ("wood"), MANGROVE ("wood"), PALM ("wood"), REDWOOD ("wood"), WILLOW ("wood"), PINE ("wood"), HELL_BARK ("wood"), JACARANDA ("wood"), MAHOGANY ("wood"), MUD_BRICKS ("stone");

		private final List<String> values;
		private String type;

		private Category(String type)
		{
			this.type = type;
			values = Arrays.asList(type);
		}
	}

	private static final String[] woodTypes = new String[] {"sacredoak", "cherry", "dark", "fir", "ethereal", "magic", "mangrove", "palm", "redwood", "willow", "pine", "hell_bark", "jacaranda", "mahogany"};
	private static final String[] stoneTypes = new String[] {"mudbrick"};
	private IIcon[] textures;

	private final Category category;

	public BlockBOPStairs(Block model, Category cat)
	{
		super(model, 0);

		category = cat;

		if (isWoodCategory(category.toString())) 
		{
			this.setHardness(2.0F);

			this.setStepSound(Block.soundTypeWood);
		}
		else 
		{
			this.setHardness(3.0F);

			this.setStepSound(Block.soundTypePiston);
		}
		useNeighborBrightness = true;

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if (isStoneCategory(category.toString()))
		{
			textures = new IIcon[stoneTypes.length];

			for (int i = 0; i < stoneTypes.length; ++i) {
				textures[i] = iconRegister.registerIcon("biomesoplenty:"+stoneTypes[i]);
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

	public boolean isWoodCategory(String block)
	{
		String type = Category.valueOf(block).type;

		if (type == "wood")
			return true;
		else
			return false;
	}

	public boolean isStoneCategory(String block)
	{
		String type = Category.valueOf(block).type;

		if (type == "stone")
			return true;
		else
			return false;
	}

	public static int getWoodCategoryAmount()
	{
		int woodCatNo = 0;

		for (Category cat : Category.values())
		{
			if (cat.values.contains("wood"))
			{
				++woodCatNo;
			}
		}

		return woodCatNo;
	}

	public static int getStoneCategoryAmount()
	{
		int woodCatNo = 0;

		for (Category cat : Category.values())
		{
			if (cat.values.contains("stone"))
			{
				++woodCatNo;
			}
		}

		return woodCatNo;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		int adjCat = category.ordinal();

		if (isStoneCategory(category.toString()))
		{
			adjCat = adjCat - getWoodCategoryAmount();
		}

		return textures[adjCat];
	}
}
