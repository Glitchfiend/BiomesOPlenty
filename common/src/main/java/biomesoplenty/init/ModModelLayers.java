/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers 
{
    public static final ModelLayerLocation ORIGIN_OAK_BOAT = register("boat/origin_oak");
    public static final ModelLayerLocation ORIGIN_OAK_CHEST_BOAT = register("chest_boat/origin_oak");
    public static final ModelLayerLocation FIR_BOAT = register("boat/fir");
    public static final ModelLayerLocation FIR_CHEST_BOAT = register("chest_boat/fir");
    public static final ModelLayerLocation PINE_BOAT = register("boat/pine");
    public static final ModelLayerLocation PINE_CHEST_BOAT = register("chest_boat/pine");
    public static final ModelLayerLocation MAPLE_BOAT = register("boat/maple");
    public static final ModelLayerLocation MAPLE_CHEST_BOAT = register("chest_boat/maple");
    public static final ModelLayerLocation REDWOOD_BOAT = register("boat/redwood");
    public static final ModelLayerLocation REDWOOD_CHEST_BOAT = register("chest_boat/redwood");
    public static final ModelLayerLocation MAHOGANY_BOAT = register("boat/mahogany");
    public static final ModelLayerLocation MAHOGANY_CHEST_BOAT = register("chest_boat/mahogany");
    public static final ModelLayerLocation JACARANDA_BOAT = register("boat/jacaranda");
    public static final ModelLayerLocation JACARANDA_CHEST_BOAT = register("chest_boat/jacaranda");
    public static final ModelLayerLocation PALM_BOAT = register("boat/palm");
    public static final ModelLayerLocation PALM_CHEST_BOAT = register("chest_boat/palm");
    public static final ModelLayerLocation WILLOW_BOAT = register("boat/willow");
    public static final ModelLayerLocation WILLOW_CHEST_BOAT = register("chest_boat/willow");
    public static final ModelLayerLocation DEAD_BOAT = register("boat/dead");
    public static final ModelLayerLocation DEAD_CHEST_BOAT = register("chest_boat/dead");
    public static final ModelLayerLocation MAGIC_BOAT = register("boat/magic");
    public static final ModelLayerLocation MAGIC_CHEST_BOAT = register("chest_boat/magic");
    public static final ModelLayerLocation UMBRAN_BOAT = register("boat/umbran");
    public static final ModelLayerLocation UMBRAN_CHEST_BOAT = register("chest_boat/umbran");
    public static final ModelLayerLocation HELLBARK_BOAT = register("boat/hellbark");
    public static final ModelLayerLocation HELLBARK_CHEST_BOAT = register("chest_boat/hellbark");
    public static final ModelLayerLocation EMPYREAL_BOAT = register("boat/empyreal");
    public static final ModelLayerLocation EMPYREAL_CHEST_BOAT = register("chest_boat/empyreal");
    
    private static ModelLayerLocation register(String name)
    {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String layer)
    {
        return createLocation(name, layer);
    }

    private static ModelLayerLocation createLocation(String name, String layer)
    {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), layer);
    }
}
