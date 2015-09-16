/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import forestry.api.genetics.IFlowerProvider;
import forestry.api.genetics.IFlowerRegistry;

public class FlowerManager {
	/**
	 * ItemStacks representing simple flower blocks. Meta-sensitive, processed by the basic {@link IFlowerProvider}.
	 * 
	 * @deprecated since Forestry 3.4. Use {@link #IFlowerRegistry.registerPlantableFlower(ItemStack flower, double weight, String... flowerTypes)} instead.
	 * <blockquote><pre>e.g. FlowerManager.flowerRegister.registerPlantableFlower(new ItemStack(Blocks.red_flower), 1.0, FlowerManager.FlowerTypeVanilla, FlowerManager.FlowerTypeSnow);</pre></blockquote>
	 */
	@Deprecated
	public static ArrayList<ItemStack> plainFlowers = new ArrayList<ItemStack>();
	
	/** 
	 * <blockquote><pre>e.g. FlowerManager.flowerRegister.registerPlantableFlower(new ItemStack(Blocks.red_flower), 1.0, FlowerManager.FlowerTypeVanilla, FlowerManager.FlowerTypeSnow);</pre></blockquote>
	 */
	public static IFlowerRegistry flowerRegistry;

	public static final String FlowerTypeVanilla = "flowersVanilla";
	public static final String FlowerTypeNether = "flowersNether";
	public static final String FlowerTypeCacti = "flowersCacti";
	public static final String FlowerTypeMushrooms = "flowersMushrooms";
	public static final String FlowerTypeEnd = "flowersEnd";
	public static final String FlowerTypeJungle = "flowersJungle";
	public static final String FlowerTypeSnow = "flowersSnow";
	public static final String FlowerTypeWheat = "flowersWheat";
	public static final String FlowerTypeGourd = "flowersGourd";
}
