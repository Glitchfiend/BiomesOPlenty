/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.init;

import biomesoplenty.forge.core.BiomesOPlentyForge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

import static biomesoplenty.forge.api.sound.BOPSounds.*;

public class ModSounds
{
    public static void setup()
    {
        registerSounds();
    }

    public static void registerSounds()
    {
        MUSIC_DISC_WANDERER = registerSound("music_disc.wanderer");
        MUSIC_BIOME_ORIGIN_VALLEY = registerSound("music.overworld.origin_valley");
        MUSIC_BIOME_CRYSTALLINE_CHASM = registerSound("music.nether.crystalline_chasm");
        MUSIC_BIOME_ERUPTING_INFERNO = registerSound("music.nether.erupting_inferno");
        MUSIC_BIOME_UNDERGROWTH = registerSound("music.nether.undergrowth");
        MUSIC_BIOME_VISCERAL_HEAP = registerSound("music.nether.visceral_heap");
        MUSIC_BIOME_WITHERED_ABYSS = registerSound("music.nether.withered_abyss");

        FLESH_TENDON_DRIP = registerSound("block.flesh_tendon.drip");
        PUS_BUBBLE_POP = registerSound("block.pus_bubble.pop");
        SPIDER_EGG_BREAK = registerSound("block.spider_egg.break");
    }

    private static RegistryObject<SoundEvent> registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlentyForge.MOD_ID, name);
        SoundEvent event = SoundEvent.createVariableRangeEvent(location);
        return BiomesOPlentyForge.SOUND_EVENT_REGISTER.register(name, () -> event);
    }
}