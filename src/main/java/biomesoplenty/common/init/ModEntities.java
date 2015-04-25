/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities
{
    
    public static void init()
    {
        
        // TODO: how to set id?
        EntityRegistry.registerModEntity(EntityDart.class, "dart", 26, BiomesOPlenty.instance, 80, 3, true);
       
        EntityRegistry.registerModEntity(EntityWasp.class, "wasp", 27, BiomesOPlenty.instance, 80, 3, true);

        
    }
}