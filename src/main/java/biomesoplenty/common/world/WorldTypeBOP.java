/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class WorldTypeBOP extends WorldType
{
    public WorldTypeBOP()
    {
        super("BIOMESOP");
    }

    @Override
    public IChunkGenerator<?> createChunkGenerator(World world)
    {
        // Mojang does this through a billion builders. Fuck that.
        OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
        biomeProviderSettings.setWorldInfo(world.getWorldInfo());
        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);

        return new ChunkGeneratorOverworldBOP(world, new BOPBiomeProvider(biomeProviderSettings), overworldGenSettings);
    }
}
