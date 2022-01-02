/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.mixin;

import biomesoplenty.api.biome.BiomeProviders;
import biomesoplenty.common.data.DataPackManager;
import biomesoplenty.common.worldgen.BOPNoiseBasedChunkGenerator;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;
import java.util.function.Function;

@Mixin(LevelStorageSource.class)
public class MixinLevelStorageSource
{
    @Shadow
    static Logger LOGGER;

    @Shadow
    private static ImmutableList<String> OLD_SETTINGS_KEYS;



    @Overwrite
    private static <T> Pair<WorldGenSettings, Lifecycle> readWorldGenSettings(Dynamic<T> dynamicData, DataFixer dataFixer, int version)
    {
        Dynamic<T> dynamicWorldGenSettings = dynamicData.get("WorldGenSettings").orElseEmptyMap();

        for (String s : OLD_SETTINGS_KEYS)
        {
            Optional<? extends Dynamic<?>> optional = dynamicData.get(s).result();
            if (optional.isPresent()) {
                dynamicWorldGenSettings = dynamicWorldGenSettings.set(s, optional.get());
            }
        }

        Dynamic<T> fixedDynamicWorldGenSettings = dataFixer.update(References.WORLD_GEN_SETTINGS, dynamicWorldGenSettings, version, SharedConstants.getCurrentVersion().getWorldVersion());
        return DataPackManager.readWorldGenSettings(fixedDynamicWorldGenSettings, dataFixer, version);
    }
}
