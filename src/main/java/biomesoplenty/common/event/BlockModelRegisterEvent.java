/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.event;

import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Called upon the construction of BlockModelShapes after the registration of
 * Vanilla blocks. This event should be used as an opportunity to register any
 * custom state mappings for modded blocks.
 */
public class BlockModelRegisterEvent extends Event
{
    public final BlockModelShapes modelShapes;

    public BlockModelRegisterEvent(BlockModelShapes modelShapes)
    {
        this.modelShapes = modelShapes;
    }
}
