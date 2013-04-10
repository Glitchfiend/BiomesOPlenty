package forestry.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public interface IFabricatorManager extends ICraftingProvider {

	void addRecipe(ItemStack plan, LiquidStack molten, ItemStack result, Object[] pattern);

	void addSmelting(ItemStack resource, LiquidStack molten, int meltingPoint);

}
