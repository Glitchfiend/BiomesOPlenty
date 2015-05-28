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

public class WorldTypeBOP extends WorldType
{
    public WorldTypeBOP()
    {
        super("BIOMESOP");

        this.setNotificationData();
    }
    
    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerBOP(world);
    }
}
