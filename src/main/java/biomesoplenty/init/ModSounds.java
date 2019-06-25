/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.sound.BOPSounds.music_disc_wanderer;

public class ModSounds
{
    public static void init()
    {
        music_disc_wanderer = registerSound("music_disc.wanderer");
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
