package com.bopteam.biomesop.items;

import com.bopteam.biomesop.blocks.BlockBOPLog;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPLog extends ItemBlock
{
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "dead"};

    public ItemBOPLog(int par1)
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
        BlockBOPLog wood = (BlockBOPLog)Block.blocksList[itemStack.itemID];
        return (new StringBuilder()).append(woodTypes[wood.getWoodType(itemStack.getItemDamage())]).append("Wood").toString();
    }
}
