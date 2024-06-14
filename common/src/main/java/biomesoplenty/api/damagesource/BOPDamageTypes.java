/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.damagesource;

import biomesoplenty.api.BOPAPI;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class BOPDamageTypes
{
    public static final ResourceKey<DamageType> BRAMBLE = register("bramble");
    public static final ResourceKey<DamageType> FUMAROLE = register("fumarole");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(BOPAPI.MOD_ID, name));
    }
}
