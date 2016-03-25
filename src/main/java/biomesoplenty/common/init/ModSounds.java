/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.init;

import static biomesoplenty.api.sound.BOPSounds.*;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSounds 
{
    public static void init()
    {
        pixie_ambient = registerSound("entity.pixie.ambient");
        pixie_hurt = registerSound("entity.pixie.hurt");
        wasp_ambient = registerSound("entity.wasp.ambient");
        wasp_hurt = registerSound("entity.wasp.hurt");
        records_corruption = registerSound("records.corruption");
        records_wanderer = registerSound("records.wanderer");
    }
    
    private static SoundEvent registerSound(String soundName)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, soundName);
        SoundEvent.registerSound(location.toString());
        return SoundEvent.soundEventRegistry.getObject(location);
    }
}
