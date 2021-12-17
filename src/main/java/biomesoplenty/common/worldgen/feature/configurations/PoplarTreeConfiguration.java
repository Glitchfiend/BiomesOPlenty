/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class PoplarTreeConfiguration extends BOPTreeConfiguration
{
    public static final Codec<PoplarTreeConfiguration> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((instance) -> instance.trunkProvider),
                BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((instance) -> instance.foliageProvider),
                BlockStateProvider.CODEC.fieldOf("vine_provider").forGetter((instance) -> instance.vineProvider),
                BlockStateProvider.CODEC.fieldOf("hanging_provider").forGetter((instance) -> instance.hangingProvider),
                BlockStateProvider.CODEC.fieldOf("trunk_fruit_provider").forGetter((instance) -> instance.trunkFruitProvider),
                BlockStateProvider.CODEC.fieldOf("alt_foliage_provider").forGetter((instance) -> instance.altFoliageProvider),
                Codec.INT.fieldOf("min_height").forGetter((instance) -> instance.minHeight),
                Codec.INT.fieldOf("max_height").forGetter((instance) -> instance.maxHeight),
                TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(instance -> instance.decorators)
        ).apply(builder, PoplarTreeConfiguration::new);
    });

    protected PoplarTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);
    }

    public static class Builder extends BOPTreeConfiguration.Builder<PoplarTreeConfiguration.Builder>
    {
        public Builder()
        {
            this.minHeight = 12;
            this.maxHeight = 15;
        }

        public PoplarTreeConfiguration build()
        {
            return new PoplarTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators);
        }
    }
}
