/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.mixin;

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

    private static final Codec<WorldGenSettings> DIRECT_WGS_CODEC = RecordCodecBuilder.<WorldGenSettings>create((p_64626_) -> {
        return p_64626_.group(Codec.LONG.fieldOf("seed").stable().forGetter(WorldGenSettings::seed), Codec.BOOL.fieldOf("generate_features").orElse(true).stable().forGetter(WorldGenSettings::generateFeatures), Codec.BOOL.fieldOf("bonus_chest").orElse(false).stable().forGetter(WorldGenSettings::generateBonusChest), MappedRegistry.dataPackCodec(Registry.LEVEL_STEM_REGISTRY, Lifecycle.stable(), LevelStem.CODEC).xmap(LevelStem::sortMap, Function.identity()).fieldOf("dimensions").forGetter(WorldGenSettings::dimensions), Codec.STRING.optionalFieldOf("legacy_custom_options").stable().forGetter((p_158959_) -> {
            return p_158959_.legacyCustomOptions;
        })).apply(p_64626_, p_64626_.stable(WorldGenSettings::new));
    }).comapFlatMap(WorldGenSettings::guardExperimental, Function.identity());

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
        DataResult<WorldGenSettings> cleanWorldGenSettings = DIRECT_WGS_CODEC.parse(fixedDynamicWorldGenSettings);

        if (cleanWorldGenSettings.result().isPresent() && cleanWorldGenSettings.result().get().overworld() instanceof BOPNoiseBasedChunkGenerator)
        {
            return Pair.of(cleanWorldGenSettings.result().get(), cleanWorldGenSettings.lifecycle());
        }
        else
        {
            DataResult<WorldGenSettings> dataPackedWorldGenSettings = WorldGenSettings.CODEC.parse(fixedDynamicWorldGenSettings);
            return Pair.of(dataPackedWorldGenSettings.resultOrPartial(Util.prefix("WorldGenSettings: ", LOGGER::error)).orElseGet(() -> {
                RegistryAccess registryaccess = RegistryAccess.RegistryHolder.readFromDisk(fixedDynamicWorldGenSettings);
                return WorldGenSettings.makeDefault(registryaccess);
            }), dataPackedWorldGenSettings.lifecycle());
        }
    }
}
