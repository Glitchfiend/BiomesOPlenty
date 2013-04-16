package com.bopteam.biomesop.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPColorizedLeaves extends ItemBlock
{
    private static final String[] leaves = new String[] {"acacia", "mangrove", "palm", "redwood", "willow"};
    
    public ItemBOPColorizedLeaves(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return (new StringBuilder()).append(leaves[itemStack.getItemDamage()]).append("Planks").toString();
    }
}
