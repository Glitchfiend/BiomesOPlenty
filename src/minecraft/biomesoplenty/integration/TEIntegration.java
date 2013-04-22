package biomesoplenty.integration;

import net.minecraft.item.ItemStack;
import thermalexpansion.api.crafting.CraftingManagers;
import thermalexpansion.api.item.ItemRegistry;
import biomesoplenty.api.Blocks;

public class TEIntegration
{
    protected static void init()
    {
        addSawMillRecipes();
    }
    
    private static void addSawMillRecipes()
    {
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 0), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 1), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 2), new ItemStack(Blocks.planks.get(), 6, 2), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 3), new ItemStack(Blocks.planks.get(), 6, 3), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 4), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 5), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 2), new ItemStack(Blocks.planks.get(), 6, 6), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 3), new ItemStack(Blocks.planks.get(), 6, 7), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs3.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 8), ItemRegistry.getItem("sawdust", 1), 100);
        CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs3.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 9), ItemRegistry.getItem("sawdust", 1), 100);
    }
}
