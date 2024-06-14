/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.BiConsumer;

import static biomesoplenty.api.sound.BOPSounds.*;

public class ModSounds
{
    public static void registerSounds(BiConsumer<ResourceLocation, SoundEvent> func)
    {
        MUSIC_BIOME_ORIGIN_VALLEY = registerForHolder(func, "music.overworld.origin_valley");
        MUSIC_BIOME_CRYSTALLINE_CHASM = registerForHolder(func, "music.nether.crystalline_chasm");
        MUSIC_BIOME_ERUPTING_INFERNO = registerForHolder(func, "music.nether.erupting_inferno");
        MUSIC_BIOME_UNDERGROWTH = registerForHolder(func, "music.nether.undergrowth");
        MUSIC_BIOME_VISCERAL_HEAP = registerForHolder(func, "music.nether.visceral_heap");
        MUSIC_BIOME_WITHERED_ABYSS = registerForHolder(func, "music.nether.withered_abyss");

        MUSIC_DISC_WANDERER = registerForHolder(func, "music_disc.wanderer");

        BLOOD_AMBIENT = register(func, "block.blood.ambient");
        FLESH_TENDON_DRIP = register(func, "block.flesh_tendon.drip");
        PUS_BUBBLE_POP = register(func, "block.pus_bubble.pop");
        SPIDER_EGG_BREAK = register(func, "block.spider_egg.break");
    }

    private static Holder.Reference<SoundEvent> registerForHolder(BiConsumer<ResourceLocation, SoundEvent> func, String name)
    {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name);
        ResourceKey<SoundEvent> key = ResourceKey.create(Registries.SOUND_EVENT, location);

        SoundEvent event = SoundEvent.createVariableRangeEvent(location);
        func.accept(location, event);
        return BuiltInRegistries.SOUND_EVENT.getHolder(key).orElseThrow();
    }

    private static SoundEvent register(BiConsumer<ResourceLocation, SoundEvent> func, String name)
    {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name);
        SoundEvent event = SoundEvent.createVariableRangeEvent(location);
        func.accept(location, event);
        return event;
    }
}