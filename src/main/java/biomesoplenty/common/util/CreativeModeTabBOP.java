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
    public static final CreativeModeTab INSTANCE;

    public CreativeModeTabBOP(int i, String label)
    {
        super(i, label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(BOPItems.BOP_ICON);
    }

    static
    {
        INSTANCE = new CreativeModeTabBOP(CreativeModeTab.TABS.length - 1, BiomesOPlenty.MOD_ID);
    }
}
