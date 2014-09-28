/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.common.collect.Lists;

import biomesoplenty.api.block.BOPBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHelper 
{
	public static HashMap<Item, ArrayList<String>> customVariantNames = new HashMap();
	
	public static void addVariantName(Item item, String... names)
	{
		if (customVariantNames.containsKey(item)) customVariantNames.get(item).addAll(Arrays.asList(names));
		else customVariantNames.put(item, Lists.newArrayList(names));
	}
	
    public static void registerItem(Item item, int metadata, String itemName)
    {
        getItemModelMesher().register(item, metadata, new ModelResourceLocation(itemName, "inventory"));
    }

    public static void registerBlock(Block block, int metadata, String blockName)
    {
        registerItem(Item.getItemFromBlock(block), metadata, blockName);
    }

    public static void registerBlock(Block block, String blockName)
    {
        registerBlock(block, 0, blockName);
    }

    public static void registerItem(Item item, String itemName)
    {
        registerItem(item, 0, itemName);
    }
    
    public static ItemModelMesher getItemModelMesher()
    {
    	return Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    }
    
    public static void regsiterBlockVariants(BOPBlock block, String name)
    {
    	getBlockModelShapes().func_178121_a(block, (new StateMap.Builder()).func_178440_a(block.getVariantProperty()).func_178439_a("_" + name).build());
    }
    
    public static BlockModelShapes getBlockModelShapes()
    {
    	return getItemModelMesher().getModelManager().getBlockModelShapes();
    }
}
