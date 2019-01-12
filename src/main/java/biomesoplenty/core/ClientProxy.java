/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entity.projectile.EntityMudball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {

    }

    @Override
    public void preInit()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, manager -> new RenderSprite<Entity>(manager, BOPItems.mudball, Minecraft.getInstance().getItemRenderer()));
    }
}
