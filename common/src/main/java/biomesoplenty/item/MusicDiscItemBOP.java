/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class MusicDiscItemBOP extends RecordItem
{
    public MusicDiscItemBOP(SoundEvent soundEvent)
    {
        super(0, soundEvent, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 289*20);
    }
}