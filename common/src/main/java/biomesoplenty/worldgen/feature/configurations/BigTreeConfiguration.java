/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class BigTreeConfiguration extends BOPTreeConfiguration
{
    public static final Codec<BigTreeConfiguration> CODEC = RecordCodecBuilder.create((builder) -> {
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
                Codec.INT.fieldOf("trunk_width").forGetter((instance) -> instance.trunkWidth),
                Codec.INT.fieldOf("foliage_height").forGetter((instance) -> instance.foliageHeight),
                Codec.DOUBLE.fieldOf("foliage_density").forGetter((instance) -> instance.foliageDensity)
        ).apply(builder, BigTreeConfiguration::new);
    });
    public final int trunkWidth;
    public final int foliageHeight;
    public final double foliageDensity;

    protected BigTreeConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider vineProvider, BlockStateProvider hangingProvider, BlockStateProvider trunkFruitProvider, BlockStateProvider altFoliageProvider, int minHeight, int maxHeight, List<TreeDecorator> decorators, int trunkWidth, int foliageHeight, double foliageDensity)
    {
        super(trunkProvider, foliageProvider, vineProvider, hangingProvider, trunkFruitProvider, altFoliageProvider, minHeight, maxHeight, decorators);

        this.trunkWidth = trunkWidth;
        this.foliageHeight = foliageHeight;
        this.foliageDensity = foliageDensity;
    }

    public static class Builder extends BOPTreeConfiguration.Builder<Builder>
    {
        private int trunkWidth;
        private int foliageHeight;
        private double foliageDensity;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}
        public Builder foliageHeight(int a) {this.foliageHeight = a; return this;}
        public Builder foliageDensity(int a) {this.foliageDensity = a; return this;}

        public Builder()
        {
            this.minHeight = 5;
            this.maxHeight = 12;
            this.trunkWidth = 1;
            this.foliageHeight = 5;
            this.foliageDensity = 1.0F;
        }

        public BigTreeConfiguration build()
        {
            return new BigTreeConfiguration(this.trunkProvider, this.foliageProvider, this.vineProvider, this.hangingProvider, this.trunkFruitProvider, this.altFoliageProvider, this.minHeight, this.maxHeight, this.decorators, this.trunkWidth, this.foliageHeight, this.foliageDensity);
        }
    }
}
