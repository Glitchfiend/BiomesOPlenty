/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.model;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class BOPTextureMapping
{
    public static TextureMapping leavesOverlay(Block block)
    {
        return new TextureMapping()
                .put(TextureSlot.LAYER1, getBlockTexture(block, "_overlay"))
                .put(TextureSlot.LAYER0, getBlockTexture(block));
    }

    public static TextureMapping logColumnKnot(Block block)
    {
        return new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, "_knot"))
                .put(TextureSlot.END, getBlockTexture(block, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(block));
    }
}
