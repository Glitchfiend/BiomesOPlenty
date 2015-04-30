/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.entities.EntityPixie;
import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.entities.projectiles.EntityDart;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities
{
    
    public static void init()
    {
        EntityRegistry.registerGlobalEntityID(EntityDart.class, "dart", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID(EntityWasp.class, "wasp", EntityRegistry.findGlobalUniqueEntityId(), 0xFEE563, 0x000000);
        EntityRegistry.registerGlobalEntityID(EntityPixie.class, "pixie", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0xFF4DFF);  
    }
    
}