/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.item;

import biomesoplenty.common.util.CreativeModeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.RegistryObject;
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
        super(0, soundProvider(record), new Item.Properties().tab(CreativeModeTabBOP.INSTANCE).rarity(Rarity.RARE).stacksTo(1));
    }
}