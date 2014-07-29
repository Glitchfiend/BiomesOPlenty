package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPlank extends ItemBlock
{
	private static final String[] woodTypes = new String[] {"sacredoakPlank", "cherryPlank", "darkPlank", "firPlank", "etherealPlank", "magicPlank", "mangrovePlank", "palmPlank", "redwoodPlank", "willowPlank", "bambooThatching", "pinePlank", "hellBarkPlank", "jacarandaPlank", "mahoganyPlank"};

	public ItemBlockPlank(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= woodTypes.length) 
		{
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + woodTypes[meta];
	}
}
