/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class ModTrimMaterials
{
    public static final ResourceKey<TrimMaterial> ROSE_QUARTZ = registryKey("rose_quartz");
    public static final ResourceKey<TrimMaterial> GLOWWORM_SILK = registryKey("glowworm_silk");

    public static void bootstrap(BootstrapContext<TrimMaterial> context)
    {
        register(context, ROSE_QUARTZ, Style.EMPTY.withColor(0xE33A61), MaterialAssetGroups.ROSE_QUARTZ);
        register(context, GLOWWORM_SILK, Style.EMPTY.withColor(0x52BEE2), MaterialAssetGroups.GLOWWORM_SILK);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> registryKey, Style hoverTextStyle, MaterialAssetGroup assets) {
        Component description = Component.translatable(Util.makeDescriptionId("trim_material", registryKey.identifier())).withStyle(hoverTextStyle);
        context.register(registryKey, new TrimMaterial(assets, description));
    }

    private static ResourceKey<TrimMaterial> registryKey(String id)
    {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, id));
    }

    class MaterialAssetGroups
    {
        public static final MaterialAssetGroup ROSE_QUARTZ = MaterialAssetGroup.create("rose_quartz");
        public static final MaterialAssetGroup GLOWWORM_SILK = MaterialAssetGroup.create("glowworm_silk");
    }
}
