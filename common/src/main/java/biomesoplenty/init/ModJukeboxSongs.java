/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class ModJukeboxSongs
{
    public static ResourceKey<JukeboxSong> WANDERER = create("wanderer");

    private static ResourceKey<JukeboxSong> create(String $$0)
    {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, $$0));
    }

    private static void register(BootstrapContext<JukeboxSong> $$0, ResourceKey<JukeboxSong> $$1, Holder.Reference<SoundEvent> $$2, int $$3, int $$4)
    {

        $$0.register($$1, new JukeboxSong($$2, Component.translatable(Util.makeDescriptionId("jukebox_song", $$1.location())), (float)$$3, $$4));
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> $$0)
    {
        register($$0, WANDERER, BOPSounds.MUSIC_DISC_WANDERER, 178, 1);
    }
}
