package biomesoplenty.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtil 
{
    /**
     * Retrieve a stack's nbt data if it has any. If it doesn't, return a new
     * compound.
     */
    public static NBTTagCompound getOrCreateStackNBT(ItemStack stack)
    {
        if (stack == null)
            throw new IllegalArgumentException("ItemStack cannot be null!");

        if (!stack.hasTagCompound())
        {
            NBTTagCompound stackCompound = new NBTTagCompound();
            stack.setTagCompound(stackCompound);
            return stackCompound;
        }
        
        return stack.getTagCompound();
    }
}
