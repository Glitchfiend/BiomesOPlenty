/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.configurations;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class BayouTreeConfiguration extends BOPTreeConfiguration
{
    public static final MapCodec<BayouTreeConfiguration> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(
                BlockStateProvider.DIRECT_CODEC.fieldOf("trunk_provider").forGetter((instance) -> instance.trunkProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("foliage_provider").forGetter((instance) -> instance.foliageProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("vine_provider").forGetter((instance) -> instance.vineProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("hanging_provider").forGetter((instance) -> instance.hangingProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("trunk_fruit_provider").forGetter((instance) -> instance.trunkFruitProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("alt_foliage_provider").forGetter((instance) -> instance.altFoliageProvider),
                Codec.INT.fieldOf("min_height").forGetter((instance) -> instance.minHeight),
                Codec.INT.fieldOf("max_height").forGetter((instance) -> instance.maxHeight),
                TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(instance -> instance.decorators),
                Codec.INT.fieldOf("trunk_width").forGetter((instance) -> instance.trunkWidth)
        ).apply(builder, BayouTreeConfiguration::new);
    });
    public final int trunkWidth;

    protected BayouTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators, int trunkWidth)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);
        this.trunkWidth = trunkWidth;
    }

    public static class Builder extends BOPTreeConfiguration.Builder<BayouTreeConfiguration.Builder>
    {
        private int trunkWidth;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}

        public Builder()
        {
            this.minHeight = 8;
            this.maxHeight = 15;
            this.trunkProvider = BlockStateProvider.of(BOPBlocks.WILLOW_LOG.defaultBlockState());
            this.foliageProvider = BlockStateProvider.of(BOPBlocks.WILLOW_LEAVES.defaultBlockState());
            this.vineProvider = BlockStateProvider.of(BOPBlocks.WILLOW_VINE.defaultBlockState());
            this.trunkWidth = 1;
        }

        public BayouTreeConfiguration build()
        {
            return new BayouTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators, this.trunkWidth);
        }
    }
}
