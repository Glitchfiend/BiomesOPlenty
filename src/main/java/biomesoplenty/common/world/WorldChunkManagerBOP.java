/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldChunkManagerBOP extends WorldChunkManager
{
    public WorldChunkManagerBOP(long seed, WorldType worldType, String chunkProviderSettings)
    {
        super();
        this.field_180301_f = chunkProviderSettings;
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, chunkProviderSettings);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }
    
    public WorldChunkManagerBOP(World world)
    {
        this(world.getSeed(), world.getWorldInfo().getTerrainType(), world.getWorldInfo().getGeneratorOptions());
    }
}
