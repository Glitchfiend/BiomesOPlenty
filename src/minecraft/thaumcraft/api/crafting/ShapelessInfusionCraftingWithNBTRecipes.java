package thaumcraft.api.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApiHelper;

public class ShapelessInfusionCraftingWithNBTRecipes implements IInfusionRecipe
{
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;

    /** Is a List of ItemStack that composes the recipe. */
    public final List recipeItems;
    
    /** Is a List of nbt key/value pairs. */
    public final List<NBTBase> nbtList;
    
    public String key;
    
    @Override
	public String getKey() {
		return key;
	}
    
    public int cost;
    
    public ObjectTags tags;

    public ShapelessInfusionCraftingWithNBTRecipes(String key, 
    		ItemStack par1ItemStack, 
    		List par2List, int cost, ObjectTags tags,
    		List<NBTBase> nbtList)
    {
        this.recipeOutput = par1ItemStack;
        this.recipeItems = par2List;
        this.key = key;
        this.cost = cost;
        this.tags = tags;
        this.nbtList = nbtList;
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
                    b1:
                    while (var7.hasNext())
                    {
                        ItemStack var8 = (ItemStack)var7.next();

                        if (var5.itemID == var8.itemID && (var8.getItemDamage() == OreDictionary.WILDCARD_VALUE || var5.getItemDamage() == var8.getItemDamage()))
                        {
                        	for (NBTBase nbt:nbtList) {
                        		try {
                        			Class nc = NBTBase.newTag(nbt.getId(), nbt.getName()).getClass();
	                        		if (var5.hasTagCompound() && 
	                        				nc.cast(var5.getTagCompound().getTag(nbt.getName())).equals(nc.cast(nbt))) {
	                        			var6 = true;
	                        			var2.remove(var8);
	                        			break b1;
	                        		}
                        		} catch (Exception e) {/*probably classcast*/}
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
    
    @Override
	public ObjectTags getTags() {
		return tags;
	}

}
