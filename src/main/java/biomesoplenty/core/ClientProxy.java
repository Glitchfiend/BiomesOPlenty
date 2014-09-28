/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import biomesoplenty.client.util.ModelHelper;

public class ClientProxy extends CommonProxy
{
	private static ArrayList<ModelEntry> blocksToRegister = new ArrayList();
	
	@Override
	public void registerRenderers()
	{		
		for (ModelEntry modelEntry : blocksToRegister)
		{
			ModelHelper.registerItem(modelEntry.item, modelEntry.metadata, BiomesOPlenty.MOD_ID + ":" + modelEntry.name);
		}
	}
	
	@Override
	public void registerBlockForMeshing(Block block, int metadata, String name)
	{
		blocksToRegister.add(new ModelEntry(Item.getItemFromBlock(block), metadata, name));
	}
	
	private static class ModelEntry
	{
		public Item item;
		public int metadata;
		public String name;
		
		public ModelEntry(Item item, int metadata, String name)
		{
			this.item = item;
			this.metadata = metadata;
			this.name = name;
		}
	}
}
