/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.mixin.client;

import biomesoplenty.common.data.DataPackManager;
import biomesoplenty.core.BiomesOPlenty;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.worldselection.WorldGenSettingsComponent;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryReadOps;
import net.minecraft.resources.RegistryWriteOps;
import net.minecraft.server.ServerResources;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldGenSettingsComponent.class)
public class MixinWorldGenSettingsComponent
{
    @Inject(method = "updateDataPacks", at = @At("HEAD"), cancellable = true)
    void updateDataPacks(ServerResources serverResources, CallbackInfo ci)
    {
        WorldGenSettingsComponent component = (WorldGenSettingsComponent)(Object)this;

        RegistryAccess.RegistryHolder registryAccess = RegistryAccess.builtin();
        RegistryWriteOps<JsonElement> registryWriteOps = RegistryWriteOps.create(JsonOps.INSTANCE, component.registryHolder);
        RegistryReadOps<JsonElement> registryReadOps = RegistryReadOps.createAndLoad(JsonOps.INSTANCE, serverResources.getResourceManager(), registryAccess);
        DataResult<WorldGenSettings> worldGenSettingsDataResult = WorldGenSettings.CODEC.encodeStart(registryWriteOps, component.settings).flatMap((p_170278_) -> {
            return WorldGenSettings.CODEC.parse(registryReadOps, p_170278_);
        });
        worldGenSettingsDataResult.resultOrPartial(Util.prefix("Error parsing worldgen settings after loading data packs: ", WorldGenSettingsComponent.LOGGER::error)).ifPresent((settings) -> {
            component.settings = DataPackManager.mergeWorldGenSettings(registryAccess, component.settings, settings);
            component.registryHolder = registryAccess;
        });

        ci.cancel();
    }
}
