/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;


public class CommonProxy
{
    public void registerRenderers() {}
    
    public void registerItemVariantModel(Item item, String name, int metadata) {}
    public void registerNonRenderingProperties(Block block) {}
}