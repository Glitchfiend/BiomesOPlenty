/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
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
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import biomesoplenty.common.world.layer.GenLayerBiomeBOP;

public class WorldTypeBOP extends WorldType
{
    public WorldTypeBOP()
    {
        super("BIOMESOP");

        this.setNotificationData();
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String chunkProviderSettingsJson)
    {
        GenLayer ret = new GenLayerBiomeBOP(200L, parentLayer, this, chunkProviderSettingsJson);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
    
    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerBOP(world);
    }
}
