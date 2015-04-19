/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.common.entities.projectiles.RenderDart;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities
{
    
    public static void init()
    {
        
        // TODO: how to set id?
        EntityRegistry.registerModEntity(EntityDart.class, "dart", 26, BiomesOPlenty.instance, 80, 3, true);
       
        
    }
    
    
    public static void initRender(RenderManager renderManager)
    {
        
        RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart(renderManager));
        
    }
    
    
}