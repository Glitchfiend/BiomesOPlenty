/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.mixin;

import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.fabric.extensions.IBOPFabricEntityExtension;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.Stream;

@Mixin(Entity.class)
public class MixinEntity implements IBOPFabricEntityExtension
{
    @Unique
    private FluidState fluidStateOnEyes;

    @Inject(method = "updateFluidOnEyes", at = @At(value = "HEAD"))
    public void onUpdateFluidOnEyesHead(CallbackInfo ci)
    {
        this.fluidStateOnEyes = null;
    }

    @Redirect(method = "updateFluidOnEyes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;getTags()Ljava/util/stream/Stream;"))
    public Stream<TagKey<Fluid>> onUpdateFluidOnEyes(FluidState instance)
    {
        this.fluidStateOnEyes = instance;

        // Leave tags untouched, we're mainly using this as a hooking point
        return instance.getTags();
    }

    @Override
    public FluidState getFluidStateOnEyes()
    {
        return this.fluidStateOnEyes;
    }
}
