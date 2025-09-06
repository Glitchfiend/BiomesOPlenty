/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.model;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class BOPItemModelGenerators extends ItemModelGenerators
{
    private final ItemModelOutput itemModelOutput;
    private final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public BOPItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
        this.itemModelOutput = itemModelOutput;
        this.modelOutput = modelOutput;
    }


    @Override
    public void run()
    {
        this.generateFlatItem(BOPItems.BOP_ICON, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(BOPItems.BLOOD_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.ROSE_QUARTZ_CHUNK, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.LIQUID_NULL_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MUSIC_DISC_WANDERER, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(BOPItems.ORIGIN_OAK_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.FIR_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.PINE_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MAPLE_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.REDWOOD_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MAHOGANY_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.JACARANDA_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.PALM_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.WILLOW_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.DEAD_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MAGIC_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.UMBRAN_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.HELLBARK_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.EMPYREAL_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.ORIGIN_OAK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.FIR_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.PINE_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MAPLE_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.REDWOOD_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MAHOGANY_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.JACARANDA_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.PALM_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.WILLOW_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.DEAD_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.MAGIC_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.UMBRAN_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.HELLBARK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(BOPItems.EMPYREAL_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }
}
