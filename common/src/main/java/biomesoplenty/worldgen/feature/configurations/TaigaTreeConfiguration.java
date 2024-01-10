/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class TaigaTreeConfiguration extends BOPTreeConfiguration
{
    public static final Codec<TaigaTreeConfiguration> CODEC = RecordCodecBuilder.create((builder) -> {
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
        ).apply(builder, TaigaTreeConfiguration::new);
    });

    public final int trunkWidth;

    protected TaigaTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators, int trunkWidth)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);
        this.trunkWidth = trunkWidth;
    }

    public static class Builder extends BOPTreeConfiguration.Builder<Builder>
    {
        private int trunkWidth;

        public Builder()
        {
            this.minHeight = 6;
            this.maxHeight = 12;
            this.trunkProvider = BlockStateProvider.simple(Blocks.SPRUCE_LOG.defaultBlockState());
            this.foliageProvider = BlockStateProvider.simple(Blocks.SPRUCE_LEAVES.defaultBlockState());
            this.vineProvider = BlockStateProvider.simple(Blocks.VINE.defaultBlockState());
            this.trunkWidth = 1;
        }

        public Builder trunkWidth(int a)
        {
            this.trunkWidth = a;
            return this;
        }

        public TaigaTreeConfiguration build()
        {
            return new TaigaTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators,this.trunkWidth);
        }
    }
}
