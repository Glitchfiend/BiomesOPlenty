/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class TwigletTreeConfiguration extends BOPTreeConfiguration
{
    public static final Codec<TwigletTreeConfiguration> CODEC = RecordCodecBuilder.create((builder) -> {
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
                Codec.FLOAT.fieldOf("leaf_chance_even").forGetter((instance) -> instance.leafChanceEven),
                Codec.FLOAT.fieldOf("leaf_chance_odd").forGetter((instance) -> instance.leafChanceOdd)
        ).apply(builder, TwigletTreeConfiguration::new);
    });

    public final float leafChanceEven;
    public final float leafChanceOdd;

    protected TwigletTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators, float leafChanceEven, float leafChanceOdd)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);
        this.leafChanceEven = leafChanceEven;
        this.leafChanceOdd = leafChanceOdd;
    }

    public static class Builder extends BOPTreeConfiguration.Builder<TwigletTreeConfiguration.Builder>
    {
        private float leafChanceEven;
        private float leafChanceOdd;

        public Builder()
        {
            this.minHeight = 2;
            this.maxHeight = 6;
            this.leafChanceEven = 0.2F;
            this.leafChanceOdd = 0.9F;
        }

        public Builder leafChance(float a, float b)
        {
            this.leafChanceEven = a;
            this.leafChanceOdd = b;
            return this;
        }

        public TwigletTreeConfiguration build()
        {
            return new TwigletTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators, this.leafChanceEven, this.leafChanceOdd);
        }
    }
}
