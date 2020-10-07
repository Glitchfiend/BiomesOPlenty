package biomesoplenty.common.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TurkeyModel<T extends Entity> extends AgeableModel<T>
{
    private final ModelRenderer head;
    private final ModelRenderer chest;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer wing0;
    private final ModelRenderer wing1;
    private final ModelRenderer beak;
    private final ModelRenderer wattle;
    private final ModelRenderer plume;
    private boolean scaleHead = true;
    private float yHeadOffset = 12.0F;
    private float zHeadOffset = 3.0F;
    private float babyHeadScale = 2.0F;
    private float babyBodyScale = 2.0F;
    private float bodyYOffset = 24.0F;

    public TurkeyModel()
    {
        this.texWidth = 128;
        this.texHeight = 64;

        this.chest = new ModelRenderer(this, 0, 22);
        this.chest.addBox(-4F, -4F, -4F, 8, 5, 5);
        this.chest.setPos(0F, 14F, -3F);

        this.head = new ModelRenderer(this, 0, 32);
        this.head.addBox(-2F, -10F, -5F, 4, 10, 3);
        this.head.setPos(0F, 14F, -3F);

        this.beak = new ModelRenderer(this, 0, 45);
        this.beak.addBox(-1F, -8F, -7F, 2, 1, 2);
        this.beak.setPos(0F, 14F, -3F);

        this.wattle = new ModelRenderer(this, 14, 32);
        this.wattle.addBox(-1F, -7F, -6F, 2, 7, 1);
        this.wattle.setPos(0F, 14F, -3F);

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-6F, 0F, -6F, 12, 8, 14);
        this.body.setPos(0F, 10F, 0F);

        this.wing0 = new ModelRenderer(this, 26, 22);
        this.wing0.addBox(-1F, 0F, 0F, 1, 6, 8);
        this.wing0.setPos(-6F, 11F, -4F);

        this.wing1 = new ModelRenderer(this, 26, 22);
        this.wing1.addBox(0F, 0F, 0F, 1, 6, 8);
        this.wing1.setPos(6F, 11F, -4F);

        this.plume = new ModelRenderer(this, 52, 0);
        this.plume.addBox(-11F, -13F, 6F, 22, 14, 0);
        this.plume.setPos(0F, 12F, 0F);
        this.plume.xRot = -0.2617994F;

        this.leg0 = new ModelRenderer(this, 44, 22);
        this.leg0.addBox(-5F, 0F, -5F, 5, 6, 4);
        this.leg0.setPos(0F, 18F, 4F);

        this.leg1 = new ModelRenderer(this, 44, 22);
        this.leg1.addBox(0F, 0F, -5F, 5, 6, 4);
        this.leg1.setPos(0F, 18F, 4F);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head, this.beak, this.wattle);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.chest, this.leg0, this.leg1, this.wing0, this.wing1, this.plume);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.chest.xRot = (headPitch * ((float)Math.PI / 180F)) + 0.7853982F;
        this.chest.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = this.chest.xRot - 0.7853982F;
        this.head.yRot = this.chest.yRot;
        this.beak.xRot = this.chest.xRot - 0.7853982F;
        this.beak.yRot = this.chest.yRot;
        this.wattle.xRot = this.chest.xRot - 0.7853982F;
        this.wattle.yRot = this.chest.yRot;
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.wing0.zRot = ageInTicks;
        this.wing1.zRot = -ageInTicks;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            matrixStackIn.pushPose();
            if (this.scaleHead) {
                float f = 1.5F / this.babyHeadScale;
                matrixStackIn.scale(f, f, f);
            }

            matrixStackIn.translate(0.0D, (double)(this.yHeadOffset / 16.0F), (double)(this.zHeadOffset / 16.0F));
            this.headParts().forEach((p_228230_8_) -> {
                p_228230_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            matrixStackIn.pushPose();
            float f1 = 1.0F / this.babyBodyScale;
            matrixStackIn.scale(f1, f1, f1);
            matrixStackIn.translate(0.0D, (double)(this.bodyYOffset / 16.0F), 0.0D);
            this.bodyParts().forEach((p_228229_8_) -> {
                if (p_228229_8_ != this.plume) { p_228229_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha); }
            });
            matrixStackIn.popPose();
        } else {
            this.headParts().forEach((p_228228_8_) -> {
                p_228228_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            this.bodyParts().forEach((p_228227_8_) -> {
                p_228227_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
        }
    }
}