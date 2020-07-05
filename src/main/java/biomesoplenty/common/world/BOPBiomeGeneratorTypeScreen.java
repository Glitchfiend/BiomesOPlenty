/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.server.IDynamicRegistries;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;

public class BOPBiomeGeneratorTypeScreen extends BiomeGeneratorTypeScreens
{
    public BOPBiomeGeneratorTypeScreen()
    {
        super("biomesoplenty");
    }

    @Override
    protected ChunkGenerator generator(long seed)
    {
        return new NoiseChunkGenerator(new BOPBiomeProvider(seed), seed, DimensionSettings.Preset.OVERWORLD.settings());
    }

    @Override
    public DimensionGeneratorSettings create(IDynamicRegistries.Impl registries, long seed, boolean generateFeatures, boolean generateBonusChest)
    {
        return new DimensionGeneratorSettings(seed, generateFeatures, generateBonusChest, DimensionGeneratorSettings.withOverworld(BOPDimensionType.bopDimensions(seed), this.generator(seed)));
    }
}
