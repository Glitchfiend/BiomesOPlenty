package forestry.api.apiculture;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.genetics.IAlleleSpecies;

public interface IAlleleBeeSpecies extends IAlleleSpecies {

	// / Products, Chance
	HashMap<ItemStack, Integer> getProducts();

	// / Specialty, Chance
	HashMap<ItemStack, Integer> getSpecialty();

	// / Only jubilant bees give their specialty product
	boolean isJubilant(IBeeGenome genome, IBeeHousing housing);

	int getIconColour(int renderPass);

	@SideOnly(Side.CLIENT)
	Icon getIcon(EnumBeeType type, int renderPass);
}
