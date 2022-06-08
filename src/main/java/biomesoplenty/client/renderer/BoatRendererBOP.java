/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.common.entity.BoatBOP;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class BoatRendererBOP extends BoatRenderer
{
    private final Map<BoatBOP.ModelType, Pair<ResourceLocation, BoatModel>> boatResources;

    public BoatRendererBOP(EntityRendererProvider.Context context)
    {
        super(context, false);
        this.boatResources = Stream.of(BoatBOP.ModelType.values()).collect(ImmutableMap.toImmutableMap((key) -> key, (model) -> {
            return Pair.of(new ResourceLocation(BiomesOPlenty.MOD_ID, "textures/entity/boat/" + model.getName() + ".png"), new BoatModel(context.bakeLayer(createBoatModelName(model)), false));
        }));
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat)
    {
        return this.boatResources.get(((BoatBOP)boat).getModel());
    }

    private static ModelLayerLocation createLocation(String name, String layer)
    {
        return new ModelLayerLocation(new ResourceLocation(BiomesOPlenty.MOD_ID, name), layer);
    }

    public static ModelLayerLocation createBoatModelName(BoatBOP.ModelType model)
    {
        return createLocation("boat/" + model.getName(), "main");
    }
}
