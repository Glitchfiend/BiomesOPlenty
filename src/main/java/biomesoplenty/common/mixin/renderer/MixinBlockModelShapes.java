/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.mixin.renderer;

import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraftforge.common.MinecraftForge;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import biomesoplenty.common.event.BlockModelRegisterEvent;

@Mixin(BlockModelShapes.class)
public abstract class MixinBlockModelShapes
{
	@Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructed(ModelManager modelManager, CallbackInfo callbackInfo) 
    {
		Object obj = (Object)this;
		
    	MinecraftForge.EVENT_BUS.post(new BlockModelRegisterEvent((BlockModelShapes)obj));
    }
}
