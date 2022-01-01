/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.mixin.client;

import biomesoplenty.common.data.DataPackManager;
import biomesoplenty.core.BiomesOPlenty;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryReadOps;
import net.minecraft.resources.RegistryWriteOps;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.DataPackConfig;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.PrimaryLevelData;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Function;

@Mixin(Minecraft.class)
public class MixinMinecraft
{
    @Shadow
    private void doLoadLevel(String p_91220_, RegistryAccess.RegistryHolder p_91221_, Function<LevelStorageSource.LevelStorageAccess, DataPackConfig> p_91222_, Function4<LevelStorageSource.LevelStorageAccess, RegistryAccess.RegistryHolder, ResourceManager, DataPackConfig, WorldData> p_91223_, boolean p_91224_, Minecraft.ExperimentalDialogType p_91225_, boolean creating) {}

    @Overwrite
    public void createLevel(String levelName, LevelSettings levelSettings, RegistryAccess.RegistryHolder registryAccess, WorldGenSettings currentSettings)
    {
        this.doLoadLevel(levelName, registryAccess, (p_91129_) -> {
            return levelSettings.getDataPackConfig();
        }, (levelStorageAccess, p_167887_, resourceManager, dataPackConfig) -> {
            RegistryWriteOps<JsonElement> registryWriteOps = RegistryWriteOps.create(JsonOps.INSTANCE, registryAccess);
            RegistryReadOps<JsonElement> registryReadOps = RegistryReadOps.createAndLoad(JsonOps.INSTANCE, resourceManager, registryAccess);
            DataResult<WorldGenSettings> worldGenSettingsDataResult = WorldGenSettings.CODEC.encodeStart(registryWriteOps, currentSettings).setLifecycle(Lifecycle.stable()).flatMap((p_167969_) -> {
                return WorldGenSettings.CODEC.parse(registryReadOps, p_167969_);
            });
            WorldGenSettings newSettings = worldGenSettingsDataResult.resultOrPartial(Util.prefix("Error reading worldgen settings after loading data packs: ", BiomesOPlenty.logger::error)).orElse(currentSettings);
            newSettings = DataPackManager.mergeWorldGenSettings(registryAccess, currentSettings, newSettings);
            return new PrimaryLevelData(levelSettings, newSettings, worldGenSettingsDataResult.lifecycle());
        }, false, Minecraft.ExperimentalDialogType.NONE, true);
    }
}
