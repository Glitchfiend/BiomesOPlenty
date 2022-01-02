/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.mixin;

import biomesoplenty.common.data.DataPackManager;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelStorageSource.class)
public class MixinLevelStorageSource
{
    @Shadow
    @Final
    static Logger LOGGER;

    @Shadow
    @Final
    private static ImmutableList<String> OLD_SETTINGS_KEYS;

    @Redirect(method="readWorldGenSettings", at=@At(value="INVOKE", target="Lcom/mojang/serialization/Codec;parse(Lcom/mojang/serialization/Dynamic;)Lcom/mojang/serialization/DataResult;"))
    private static <T> DataResult readWorldGenSettings_parse(Codec<WorldGenSettings> codec, final Dynamic<T> input)
    {
        return DataPackManager.replaceDatapackWorldGenSettings(input);
    }
}
