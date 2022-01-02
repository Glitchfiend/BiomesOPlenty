/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.surface;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;

import javax.annotation.Nullable;
import java.util.List;

public record DifferentialSurfaceRuleSource(SurfaceRules.RuleSource base, List<SurfaceRules.RuleSource> differentials) implements SurfaceRules.RuleSource
{
    public static final Codec<DifferentialSurfaceRuleSource> CODEC = RecordCodecBuilder.create((builder) ->
    {
        return builder.group(
            SurfaceRules.RuleSource.CODEC.fieldOf("base").forGetter(DifferentialSurfaceRuleSource::base),
            SurfaceRules.RuleSource.CODEC.listOf().fieldOf("differentials").forGetter(DifferentialSurfaceRuleSource::differentials)
        ).apply(builder, DifferentialSurfaceRuleSource::new);
    });

    @Override
    public Codec<? extends SurfaceRules.RuleSource> codec()
    {
        return CODEC;
    }

    @Override
    public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context)
    {
        ImmutableList.Builder<SurfaceRules.SurfaceRule> builder = ImmutableList.builder();

        for (SurfaceRules.RuleSource source : this.differentials)
        {
            builder.add(source.apply(context));
        }

        return new DifferentialRule(context, this.base.apply(context), builder.build());
    }

    record DifferentialRule(SurfaceRules.Context context, SurfaceRules.SurfaceRule baseRule, List<SurfaceRules.SurfaceRule> rules) implements SurfaceRules.SurfaceRule
    {
        @Nullable
        public BlockState tryApply(int x, int y, int z)
        {
            Biome biome = context.biomeGetter.apply(new BlockPos(x, y, z));
            ResourceLocation biomeKey = context.biomes.getKey(biome);
            String namespace = biomeKey.getNamespace();

            BlockState baseState = baseRule.tryApply(x, y, z);

            if (namespace.equals("minecraft") || namespace.equals("biomesoplenty"))
                return baseState;

            for (SurfaceRules.SurfaceRule rule : this.rules)
            {
                BlockState state = rule.tryApply(x, y, z);

                // Return the first state that doesn't match the base state
                if (state != null && state != baseState)
                    return state;
            }

            return baseState;
        }
    }
}
