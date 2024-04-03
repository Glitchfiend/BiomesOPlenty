/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.configurations;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class CypressTreeConfiguration extends BOPTreeConfiguration
{
    public static final Codec<CypressTreeConfiguration> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((instance) -> instance.trunkProvider),
                BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((instance) -> instance.foliageProvider),
                BlockStateProvider.CODEC.fieldOf("vine_provider").forGetter((instance) -> instance.vineProvider),
                BlockStateProvider.CODEC.fieldOf("hanging_provider").forGetter((instance) -> instance.hangingProvider),
                BlockStateProvider.CODEC.fieldOf("trunk_fruit_provider").forGetter((instance) -> instance.trunkFruitProvider),
                BlockStateProvider.CODEC.fieldOf("alt_foliage_provider").forGetter((instance) -> instance.altFoliageProvider),
                Codec.INT.fieldOf("min_height").forGetter((instance) -> instance.minHeight),
                Codec.INT.fieldOf("max_height").forGetter((instance) -> instance.maxHeight),
                TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(instance -> instance.decorators),
                Codec.BOOL.fieldOf("leaves_at_bottom").forGetter((instance) -> instance.leavesAtBottom)
        ).apply(builder, CypressTreeConfiguration::new);
    });

    public final boolean leavesAtBottom;

    protected CypressTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators, boolean leavesAtBottom)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);
        this.leavesAtBottom = leavesAtBottom;
    }

    public static class Builder extends BOPTreeConfiguration.Builder<CypressTreeConfiguration.Builder>
    {
        private boolean leavesAtBottom;

        public Builder()
        {
            this.minHeight = 12;
            this.maxHeight = 15;
            this.trunkProvider = BlockStateProvider.simple(Blocks.SPRUCE_LOG);
            this.foliageProvider = BlockStateProvider.simple(BOPBlocks.CYPRESS_LEAVES);
            this.leavesAtBottom = false;
        }

        public CypressTreeConfiguration.Builder leavesAtBottom(boolean a)
        {
            this.leavesAtBottom = a;
            return this;
        }

        public CypressTreeConfiguration build()
        {
            return new CypressTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators, this.leavesAtBottom);
        }
    }
}
