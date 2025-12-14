/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.model;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class BOPModelTemplates
{
    public static final ModelTemplate LEAVES_OVERLAY = create("leaves_overlay", TextureSlot.LAYER0, TextureSlot.LAYER1);

    private static ModelTemplate create(TextureSlot... p_386690_) {
        return new ModelTemplate(Optional.empty(), Optional.empty(), p_386690_);
    }

    private static ModelTemplate create(String name, TextureSlot... p_388561_) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "block/" + name)), Optional.empty(), p_388561_);
    }

    private static ModelTemplate createItem(String p_388248_, TextureSlot... p_386756_) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "item/" + p_388248_)), Optional.empty(), p_386756_);
    }

    private static ModelTemplate createItem(String p_386727_, String p_387707_, TextureSlot... p_387856_) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "item/" + p_386727_)), Optional.of(p_387707_), p_387856_);
    }

    private static ModelTemplate create(String p_386833_, String p_386662_, TextureSlot... p_387086_) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "block/" + p_386833_)), Optional.of(p_386662_), p_387086_);
    }
}
