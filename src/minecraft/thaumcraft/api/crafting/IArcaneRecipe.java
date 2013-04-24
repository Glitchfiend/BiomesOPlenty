package thaumcraft.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IArcaneRecipe
{
	
	
    /**
     * Used to check if a recipe matches current crafting inventory
     * @param player 
     */
    boolean matches(IInventory var1, EntityPlayer player);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(IInventory var1);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();
    int getCost();
    String getKey();
}
