/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderHell;

public class WorldProviderBOPHell extends WorldProviderHell
{
    @Override
    public void init()
    {
        this.biomeProvider = new BiomeProviderBOPHell(this.world);
        this.doesWaterVaporize = true;
        this.hasNoSky = true;
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkProviderGenerateBOPHell(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
    }
}
