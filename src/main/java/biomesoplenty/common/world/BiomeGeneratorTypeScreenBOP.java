/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;

public class BiomeGeneratorTypeScreenBOP extends BiomeGeneratorTypeScreens
{
    public BiomeGeneratorTypeScreenBOP()
    {
        super("biomesoplenty");
    }

    @Override
    protected ChunkGenerator generator(long seed)
    {
        return new NoiseChunkGenerator(new BOPBiomeProvider(seed), seed, DimensionSettings.Preset.OVERWORLD.settings());
    }
}
