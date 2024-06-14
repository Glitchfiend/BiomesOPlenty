/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.entity.BoatBOP;
import biomesoplenty.entity.ChestBoatBOP;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class BoatRendererBOP extends EntityRenderer<Boat>
{
    private final Map<BoatBOP.ModelType, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public BoatRendererBOP(EntityRendererProvider.Context context, boolean hasChest)
    {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(BoatBOP.ModelType.values()).collect(ImmutableMap.toImmutableMap((key) -> key, (model) -> {
            return Pair.of(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, getTextureLocation(model, hasChest)), createBoatModel(context, model, hasChest));
        }));
    }

    @Override
    public void render(Boat boat, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight)
    {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        float f = (float)boat.getHurtTime() - partialTicks;
        float f1 = boat.getDamage() - partialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)boat.getHurtDir()));
        }

        float f2 = boat.getBubbleAngle(partialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            poseStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTicks) * 0.017453292F, 1.0F, 0.0F, 1.0F));
        }

        Pair<ResourceLocation, ListModel<Boat>> pair = this.getModelWithLocation(boat);
        ResourceLocation resourcelocation = (ResourceLocation)pair.getFirst();
        ListModel<Boat> listmodel = (ListModel)pair.getSecond();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        listmodel.setupAnim(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(listmodel.renderType(resourcelocation));
        listmodel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        if (!boat.isUnderWater()) {
            VertexConsumer vertexconsumer1 = bufferSource.getBuffer(RenderType.waterMask());
            if (listmodel instanceof WaterPatchModel) {
                WaterPatchModel waterpatchmodel = (WaterPatchModel)listmodel;
                waterpatchmodel.waterPatch().render(poseStack, vertexconsumer1, packedLight, OverlayTexture.NO_OVERLAY);
            }
        }

        poseStack.popPose();
        super.render(boat, yaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat boat)
    {
        return (ResourceLocation)this.getModelWithLocation(boat).getFirst();
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat)
    {
        if (boat instanceof ChestBoatBOP)
            return this.boatResources.get(((ChestBoatBOP)boat).getModel());
        else
            return this.boatResources.get(((BoatBOP)boat).getModel());
    }

    private static String getTextureLocation(BoatBOP.ModelType model, boolean hasChest)
    {
        return hasChest ? "textures/entity/chest_boat/" + model.getName() + ".png" : "textures/entity/boat/" + model.getName() + ".png";
    }

    private static ModelLayerLocation createLocation(String name, String layer)
    {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), layer);
    }

    public static ModelLayerLocation createBoatModelName(BoatBOP.ModelType model)
    {
        return createLocation("boat/" + model.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(BoatBOP.ModelType model)
    {
        return createLocation("chest_boat/" + model.getName(), "main");
    }

    private BoatModel createBoatModel(EntityRendererProvider.Context context, BoatBOP.ModelType model, boolean hasChest)
    {
        ModelLayerLocation modellayerlocation = hasChest ? createChestBoatModelName(model) : createBoatModelName(model);
        ModelPart baked = context.bakeLayer(modellayerlocation);
        return hasChest ? new ChestBoatModel(baked) : new BoatModel(baked);
    }
}
