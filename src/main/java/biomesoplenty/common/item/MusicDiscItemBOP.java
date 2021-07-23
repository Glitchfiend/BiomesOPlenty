/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.item;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.util.CreativeModeTabBOP;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class MusicDiscItemBOP extends RecordItem
{
    public MusicDiscItemBOP(String record)
    {
        super(0, BOPSounds.MUSIC_DISC_WANDERER, new Item.Properties().tab(CreativeModeTabBOP.INSTANCE).rarity(Rarity.RARE).stacksTo(1));
    }
}

