/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.neoforge.datagen.model.BOPBlockModelGenerators;
import biomesoplenty.neoforge.datagen.model.BOPItemModelGenerators;
import glitchcore.data.ModelProviderBase;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BOPModelProvider extends ModelProviderBase
{
    public BOPModelProvider(PackOutput output)
    {
        super(output, BiomesOPlenty.MOD_ID);
    }

    @Override
    protected BlockModelGenerators createBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput)
    {
        return new BOPBlockModelGenerators(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    protected ItemModelGenerators createItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> biConsumer)
    {
        return new BOPItemModelGenerators(itemModelOutput, biConsumer);
    }
}
