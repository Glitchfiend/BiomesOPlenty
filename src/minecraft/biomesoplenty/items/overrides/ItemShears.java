package biomesoplenty.items.overrides;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Blocks;

public class ItemShears extends net.minecraft.item.ItemShears
{
	public ItemShears(int var1)
	{
		super(var1);
		maxStackSize = 1;
	}


	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	 * sword
	 */
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		Float Strength = null;

		if (Blocks.shearBlockIds.get(par2Block.blockID) != null)
		{
			Strength = Float.parseFloat(Blocks.shearBlockIds.get(par2Block.blockID).toString());
		}
		else if (par2Block.blockID == Block.web.blockID | par2Block.blockID == Block.leaves.blockID)
		{
			Strength = 15.0F;
		}
		else if (par2Block.blockID == Block.cloth.blockID)
		{
			Strength = 5.0F;
		}
		else
		{
			Strength = super.getStrVsBlock(par1ItemStack, par2Block);
		}

		return Strength;
	}
}
