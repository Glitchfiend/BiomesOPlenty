package biomesoplenty.common.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import biomesoplenty.api.BOPItemHelper;

public class BiomeEssenceRecipe implements IRecipe
{
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
                    if (itemstack.getItem() == BOPItemHelper.get("biomeFinder")) biomeRadar = itemstack.copy();
                    else if (itemstack.getItem() == BOPItemHelper.get("biomeEssence")) biomeEssence = itemstack.copy();
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
