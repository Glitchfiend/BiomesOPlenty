package biomesoplenty.blocks;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPStairs extends BlockStairs
{
	public static enum Category
	{
		ACACIA ("wood"), CHERRY ("wood"), DARK ("wood"), FIR ("wood"), HOLY ("wood"), MAGIC ("wood"), MANGROVE ("wood"), PALM ("wood"), REDWOOD ("wood"), WILLOW ("wood"), PINE ("wood"), HELL_BARK ("wood"), JACARANDA ("wood"), RED_COBBLE ("stone"), RED_BRICKS ("stone"), MUD_BRICKS ("stone"), HOLY_COBBLE ("stone"), HOLY_BRICKS ("stone");

		private final List<String> values;
		private String type;

		private Category(String type)
		{
			this.type = type;
			values = Arrays.asList(type);
		}
	}

	private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "pine", "hell_bark", "jacaranda"};
	private static final String[] stoneTypes = new String[] {"redcobble", "redbrick", "mudbrick", "holycobble", "holybrick"};
	private Icon[] textures;

	private final Category category;

	public BlockBOPStairs(int blockID, Block model, Category cat)
	{
		super(blockID, model, 0);
		category = cat;
		setBurnProperties(this.blockID, 5, 20);
		this.setLightOpacity(0);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		this.setHardness(model.blockHardness);
        this.setResistance(model.blockResistance / 3.0F);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if (isStoneCategory(category.toString()))
		{
			textures = new Icon[stoneTypes.length];

			for (int i = 0; i < stoneTypes.length; ++i) {
				textures[i] = iconRegister.registerIcon("biomesoplenty:"+stoneTypes[i]);
			}
		}
		else
		{
			textures = new Icon[woodTypes.length];

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
	public Icon getIcon(int side, int meta)
	{
		int adjCat = category.ordinal();

		if (isStoneCategory(category.toString()))
		{
			adjCat = adjCat - getWoodCategoryAmount();
		}

		return textures[adjCat];
	}
}
