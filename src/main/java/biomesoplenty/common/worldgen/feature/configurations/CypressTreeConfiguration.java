/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.configurations;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
                Codec.INT.fieldOf("trunk_width").forGetter((instance) -> instance.trunkWidth)
        ).apply(builder, CypressTreeConfiguration::new);
    });
    public final int trunkWidth;

    protected CypressTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators, int trunkWidth)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);
        this.trunkWidth = trunkWidth;
    }

    public static class Builder extends BOPTreeConfiguration.Builder<CypressTreeConfiguration.Builder>
    {
        private int trunkWidth;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}

        public Builder()
        {
            this.minHeight = 8;
            this.maxHeight = 15;
            this.trunkProvider = BlockStateProvider.simple(BOPBlocks.WILLOW_LOG.defaultBlockState());
            this.foliageProvider = BlockStateProvider.simple(BOPBlocks.WILLOW_LEAVES.defaultBlockState());
            this.vineProvider = BlockStateProvider.simple(BOPBlocks.WILLOW_VINE.defaultBlockState());
            this.trunkWidth = 1;
        }

        public CypressTreeConfiguration build()
        {
            return new CypressTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators, this.trunkWidth);
        }
    }
}
