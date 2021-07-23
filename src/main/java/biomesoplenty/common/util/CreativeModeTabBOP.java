/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeModeTabBOP extends CreativeModeTab
{
    public static final CreativeModeTab INSTANCE = new CreativeModeTab(CreativeModeTab.TABS.length, "biomesoplenty")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(BOPItems.BOP_ICON);
        }
    };

    private CreativeModeTabBOP(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(BOPItems.BOP_ICON);
    }
}
