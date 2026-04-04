/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class BOPDamageTypeTagsProvider extends KeyTagProvider<DamageType>
{
    public BOPDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, BiomesOPlenty.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries)
    {
        this.tag(DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES).add(BOPDamageTypes.BRAMBLE, BOPDamageTypes.FUMAROLE);
    }
}
