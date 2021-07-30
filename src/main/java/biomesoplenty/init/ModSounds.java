/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.sound.BOPSounds.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSounds
{
    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        MUSIC_DISC_WANDERER = registerSound("music_disc.wanderer");
        MUSIC_BIOME_CRYSTALLINE_CHASM = registerSound("music.nether.crystalline_chasm");
        MUSIC_BIOME_ERUPTING_INFERNO = registerSound("music.nether.erupting_inferno");
        MUSIC_BIOME_UNDERGROWTH = registerSound("music.nether.undergrowth");
        MUSIC_BIOME_VISCERAL_HEAP = registerSound("music.nether.visceral_heap");
        MUSIC_BIOME_WITHERED_ABYSS = registerSound("music.nether.withered_abyss");
    }

    private static SoundEvent registerSound(String soundName)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, soundName);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}