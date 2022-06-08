/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.item;

import biomesoplenty.common.util.CreativeModeTabBOP;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class MusicDiscItemBOP extends RecordItem
{
    public MusicDiscItemBOP(Supplier<SoundEvent> soundSupplier)
    {
        super(0, soundSupplier, new Item.Properties().tab(CreativeModeTabBOP.INSTANCE).rarity(Rarity.RARE).stacksTo(1));
    }
}