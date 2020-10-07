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
import net.minecraft.util.SoundEvents;
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
        music_disc_wanderer = registerSound("music_disc.wanderer");
        deer_hurt = registerSound("entity.biomesoplenty.deer.hurt");
        deer_dead = registerSound("entity.biomesoplenty.deer.dead");
        deer_step = SoundEvents.LLAMA_STEP;
        turkey_ambient = registerSound("entity.biomesoplenty.turkey.ambient");
        turkey_hurt = registerSound("entity.biomesoplenty.turkey.hurt");
        turkey_dead = registerSound("entity.biomesoplenty.turkey.dead");
        turkey_step = SoundEvents.CHICKEN_STEP;
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
