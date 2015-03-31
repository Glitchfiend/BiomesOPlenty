/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import java.util.ArrayList;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.client.util.ModelHelper;

public class ClientProxy extends CommonProxy
{
    private static ArrayList<ModelEntry> blocksToRegister = new ArrayList();
    private static ArrayList<ItemEntry> itemsToRegister = new ArrayList();

    @Override
    public void registerRenderers()
    {
        for (ModelEntry modelEntry : blocksToRegister)
        {
            ModelHelper.registerBlock(modelEntry.block, modelEntry.metadata, BiomesOPlenty.MOD_ID + ":" + modelEntry.name);
        }
        
        for (ItemEntry itemEntry : itemsToRegister)
        {
            ModelHelper.registerItem(itemEntry.item, itemEntry.metadata, BiomesOPlenty.MOD_ID + ":" + itemEntry.name);
        }
       
    }

    @Override
    public void addVariantName(Item item, String... names) 
    {
        ModelBakery.addVariantName(item, names);
    }
    
    @Override
    public void registerBlockForMeshing(Block block, int metadata, String name)
    {
        blocksToRegister.add(new ModelEntry(block, metadata, name));
    }
    
    @Override
    public void registerItemForMeshing(Item item, String name)
    {
        itemsToRegister.add(new ItemEntry(item, 0, name));
    }
    
    @Override
    public void registerItemForMeshing(Item item, int meta, String name)
    {
        itemsToRegister.add(new ItemEntry(item, meta, name));
    }

    private static class ModelEntry
    {
        public Block block;
        public int metadata;
        public String name;

        public ModelEntry(Block block, int metadata, String name)
        {
            this.block = block;
            this.metadata = metadata;
            this.name = name;
        }
    }
    
    private static class ItemEntry
    {
        public Item item;
        public int metadata;
        public String name;

        public ItemEntry(Item item, int metadata, String name)
        {
            this.item = item;
            this.metadata = metadata;
            this.name = name;
        }
    }
}
