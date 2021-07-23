/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static biomesoplenty.api.sound.BOPSounds.MUSIC_DISC_WANDERER;

public class ModSounds
{
    public static void registerSounds()
    {
        MUSIC_DISC_WANDERER = registerSound("music_disc.wanderer");
    }

    private static SoundEvent registerSound(String soundName)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, soundName);
        SoundEvent event = new SoundEvent(location);
        Registry.register(Registry.SOUND_EVENT,location, event);
        return event;
    }
}
