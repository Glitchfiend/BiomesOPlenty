/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderBOPHell extends WorldProviderHell
{
    @Override
    public void init()
    {
        this.biomeProvider = new BiomeProviderBOPHell(this.world);
        this.doesWaterVaporize = true;
        this.nether = true;
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorHellBOP(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
    }
}
