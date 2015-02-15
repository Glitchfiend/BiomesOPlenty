/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.event.BlockModelRegisterEvent;

public class BlockModelRegisterEventHandler
{
    private static Map<Block, Set<IProperty>> hiddenProperties = Maps.newHashMap();

    @SubscribeEvent
    public void onBlockModelRegister(BlockModelRegisterEvent event)
    {
        BlockModelShapes modelShapes = event.modelShapes;

        for (Entry<Block, Set<IProperty>> entry : hiddenProperties.entrySet())
        {
            modelShapes.registerBlockWithStateMapper(entry.getKey(), (new StateMap.Builder()).addPropertiesToIgnore(entry.getValue().toArray(new IProperty[] {})).build());
        }
    }

    public static void addHiddenProperties(Block block, IProperty... properties)
    {
        hiddenProperties.put(block, Sets.newHashSet(properties));
    }
}
