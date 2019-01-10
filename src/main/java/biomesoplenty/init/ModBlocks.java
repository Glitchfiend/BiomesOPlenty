/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.block.BOPBlocks.ash_block;
import static biomesoplenty.api.block.BOPBlocks.mud;

import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static void init()
    {
        ash_block = registerBlock(new BlockAsh(), "ash_block");
        mud = registerBlock(new BlockMud(), "mud");
    }

    public static Block registerBlock(Block block, String name)
    {
        ItemBlock itemBlock = new ItemBlock(block, new Item.Builder().group(ItemGroupBOP.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
}
