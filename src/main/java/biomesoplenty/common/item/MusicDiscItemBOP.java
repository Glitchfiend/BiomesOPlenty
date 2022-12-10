/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class MusicDiscItemBOP extends RecordItem
{
    public MusicDiscItemBOP(Supplier<SoundEvent> soundSupplier)
    {
        super(0, soundSupplier, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 289*20);
    }
}