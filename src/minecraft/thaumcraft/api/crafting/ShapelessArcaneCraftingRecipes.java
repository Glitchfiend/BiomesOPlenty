package thaumcraft.api.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;

public class ShapelessArcaneCraftingRecipes implements IArcaneRecipe
{
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;

    /** Is a List of ItemStack that composes the recipe. */
    public final List recipeItems;
    
    public String key;
    
    @Override
	public String getKey() {
		return key;
	}
    
    public int cost;

    public ShapelessArcaneCraftingRecipes(String key, ItemStack par1ItemStack, List par2List, int cost)
    {
        this.recipeOutput = par1ItemStack;
        this.recipeItems = par2List;
        this.key = key;
        this.cost = cost;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(IInventory par1InventoryCrafting, EntityPlayer player)
    {
    	if (key.length()>0 && !ThaumcraftApiHelper.isResearchComplete(player.username, key)) {
    		return false;
    	}
    	
        ArrayList var2 = new ArrayList(this.recipeItems);

        for (int var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 3; ++var4)
            {
                ItemStack var5 = ThaumcraftApiHelper.getStackInRowAndColumn(par1InventoryCrafting, var4, var3);

                if (var5 != null)
                {
                    boolean var6 = false;
                    Iterator var7 = var2.iterator();

                    while (var7.hasNext())
                    {
                        ItemStack var8 = (ItemStack)var7.next();

                        if (var5.itemID == var8.itemID && (var8.getItemDamage() == OreDictionary.WILDCARD_VALUE || var5.getItemDamage() == var8.getItemDamage()))
                        {
                        	boolean matches=true;
                        	if (var8.hasTagCompound()) {
                        		NBTTagCompound tc = var8.getTagCompound();
                        		for (Object tag:tc.getTags().toArray()) {
                        			NBTBase base = (NBTBase)tag;
                        			Class nc = NBTBase.newTag(base.getId(), base.getName()).getClass();
	                        		if (!(var5.hasTagCompound() && 
	                        				nc.cast(var5.getTagCompound().getTag(base.getName())).equals(nc.cast(base)))) {
	                        			matches=false;
	                        			break;
	                        		}
                        		}
                        	}
                        	
                        	if (matches) {
                        		var6 = true;
                        		var2.remove(var8);
                        		break;
                        	}
                        }
                    }

                    if (!var6)
                    {
                        return false;
                    }
                }
            }
        }

        return var2.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(IInventory par1InventoryCrafting)
    {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeItems.size();
    }

    @Override
	public int getCost() {
		return cost;
	}

}
