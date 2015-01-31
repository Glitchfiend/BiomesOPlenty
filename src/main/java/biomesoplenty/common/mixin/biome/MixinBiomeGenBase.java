/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.mixin.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;

@Mixin(BiomeGenBase.class)
public abstract class MixinBiomeGenBase implements IExtendedBiome
{
    @Shadow
    public BiomeDecorator theBiomeDecorator;
    
    private BiomeOwner biomeOwner = BiomeOwner.OTHER;

    @Inject(method = "<init>(IZ)V", at = @At("RETURN"))
    private void onConstructed(int biomeId, boolean register, CallbackInfo callbackInfo)
    {
        //Prevents Forge from wiping all of our added data
        this.theBiomeDecorator = new BiomeDecorator();
    }
    
    @Override
    public BiomeOwner getBiomeOwner()
    {
        return biomeOwner;
    }
}
