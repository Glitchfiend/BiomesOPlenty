/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.item;

import biomesoplenty.common.util.inventory.ItemGroupBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class MusicDiscItemBOP extends RecordItem
{
	//Provide a resource location and the correct registry to retrieve a SoundEvent supplier
    public static RegistryObject<SoundEvent> soundProvider(String soundName) {
        return RegistryObject.of(
            new ResourceLocation(BiomesOPlenty.MOD_ID, soundName),
            ForgeRegistries.SOUND_EVENTS
        );
    };
	
    public MusicDiscItemBOP(String record)
    {
        super(0, soundProvider(record), new Item.Properties().tab(ItemGroupBOP.instance).rarity(Rarity.RARE).stacksTo(1));
    }
}
