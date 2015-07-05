package biomesoplenty.common.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCItems;

public class BiomeEssenceRecipe implements IRecipe
{
	static {
		RecipeSorter.register("BiomesOPlenty:biomeessenceRecipe", BiomeEssenceRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
	}

    private ItemStack recipeOutput;
    
    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world)
    {
        ItemStack biomeRadar = null;
        ItemStack biomeEssence = null;

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                ItemStack itemstack = inventoryCrafting.getStackInRowAndColumn(j, i);

                if (itemstack != null)
                {
                    if (itemstack.getItem() == BOPCItems.biomeFinder) biomeRadar = itemstack.copy();
                    else if (itemstack.getItem() == BOPCItems.biomeEssence) biomeEssence = itemstack.copy();
                }
            }
        }
        
        if (biomeRadar != null && biomeEssence != null)
        {
            if (!biomeEssence.hasTagCompound() || !biomeEssence.getTagCompound().hasKey("biomeID")) return false;
            
            int biomeID = biomeEssence.getTagCompound().getInteger("biomeID");
            
            if (!biomeRadar.hasTagCompound()) biomeRadar.setTagCompound(new NBTTagCompound());
            
            biomeRadar.getTagCompound().setInteger("biomeIDToFind", biomeID);
            biomeRadar.getTagCompound().setBoolean("foundBiome", false);
            
            recipeOutput = biomeRadar;
            
            return true;
        }
        else return false;
    }
    
    @Override
    public int getRecipeSize()
    {
        return 2;
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        return recipeOutput.copy();
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return recipeOutput;
    }
}
