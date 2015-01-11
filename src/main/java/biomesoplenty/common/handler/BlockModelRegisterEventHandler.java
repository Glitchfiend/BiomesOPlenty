/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.BlockModelRegisterEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockModelRegisterEventHandler 
{
	@SubscribeEvent
	public void onBlockModelRegister(BlockModelRegisterEvent event)
	{
		BlockModelShapes modelShapes = event.modelShapes;
		
		modelShapes.registerBlockWithStateMapper(BOPBlocks.coral, (new StateMap.Builder()).addPropertiesToIgnore(new IProperty[] {BlockLiquid.LEVEL}).build());
	}
}
