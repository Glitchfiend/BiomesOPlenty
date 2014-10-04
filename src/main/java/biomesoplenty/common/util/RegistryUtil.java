/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util;

import java.util.Iterator;
import java.util.Map;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryUtil
{
    public static Block registerBlock(Block block, String name)
    {
        return registerBlock(block, ItemBlock.class, name);
    }

    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name)
    {
        return registerBlock(block, itemclass, name, new Object[]{});
    }
	
	public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, Object... itemCtorArgs)
	{
		block = GameRegistry.registerBlock(block, itemclass, name, itemCtorArgs);

		Iterator iterator = block.getBlockState().getValidStates().iterator();

		while (iterator.hasNext())
		{
			IBlockState iblockstate = (IBlockState)iterator.next();
			int id = Block.blockRegistry.getIDForObject(block) << 4 | block.getMetaFromState(iblockstate);
			Block.BLOCK_STATE_IDS.put(iblockstate, id);
		}

		return block;
	}
}
