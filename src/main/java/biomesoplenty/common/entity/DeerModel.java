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
public class DeerModel<T extends Entity> extends AgeableModel<T>
{
    private final ModelRenderer head;
    private final ModelRenderer snout;
    private final ModelRenderer neck;
    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer ear0;
    private final ModelRenderer ear1;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer antler0;
    private final ModelRenderer antler1;
    private boolean scaleHead = true;
    private float yHeadOffset = 14.0F;
    private float zHeadOffset = 3.0F;
    private float babyHeadScale = 2.0F;
    private float babyBodyScale = 2.0F;
    private float bodyYOffset = 24.0F;

    public DeerModel()
    {
        this.texWidth = 128;
        this.texHeight = 32;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3F, -3F, -6F, 6, 6, 6);
        this.head.setPos(0F, 3F, -9F);

        this.snout = new ModelRenderer(this, 24, 0);
        this.snout.addBox(-2F, -1F, -10F, 4, 4, 4);

        this.ear0 = new ModelRenderer(this, 32, 16);
        this.ear0.addBox(-1F, -8F, -2F, 3, 6, 1);
        this.ear0.zRot = -1.047198F;

        this.ear1 = new ModelRenderer(this, 32, 23);
        this.ear1.addBox(-2F, -8F, -2F, 3, 6, 1);
        this.ear1.zRot = 1.047198F;

        this.antler0 = new ModelRenderer(this, 70, 18);
        this.antler0.addBox(-7F, -14F, -2F, 8, 12, 1);
        this.antler0.xRot = -0.5235988F;
        this.antler0.yRot = -1.047198F;

        this.antler1 = new ModelRenderer(this, 88, 18);
        this.antler1.addBox(-1F, -14F, -2F, 8, 12, 1);
        this.antler1.xRot = -0.5235988F;
        this.antler1.yRot = 1.047198F;

        this.head.addChild(snout);
        this.head.addChild(ear0);
        this.head.addChild(ear1);
        this.head.addChild(antler0);
        this.head.addChild(antler1);

        this.neck = new ModelRenderer(this, 0, 12);
        this.neck.addBox(-2F, 0F, -9F, 4, 6, 9);
        this.neck.setPos(0F, 6F, -2F);
        this.neck.xRot = -0.6283185F;

        this.body = new ModelRenderer(this, 40, 0);
        this.body.addBox(-4F, -9F, -7F, 8, 18, 7);
        this.body.setPos(0F, 5F, 1F);
        this.body.xRot = 1.570796F;

        this.leg0 = new ModelRenderer(this, 70, 0);
        this.leg0.addBox(0F, 0F, 0F, 3, 12, 3);
        this.leg0.setPos(-4F, 12F, 6F);

        this.leg1 = new ModelRenderer(this, 82, 0);
        this.leg1.addBox(-3F, 0F, 0F, 3, 12, 3);
        this.leg1.setPos(4F, 12F, 6F);

        this.leg2 = new ModelRenderer(this, 94, 0);
        this.leg2.addBox(0F, 0F, 0F, 3, 12, 3);
        this.leg2.setPos(-4F, 12F, -7F);

        this.leg3 = new ModelRenderer(this, 106, 0);
        this.leg3.addBox(-3F, 0F, 0F, 3, 12, 3);
        this.leg3.setPos(4F, 12F, -7F);

        this.tail = new ModelRenderer(this, 32, 8);
        this.tail.addBox(0F, 0F, 0F, 2, 6, 2);
        this.tail.setPos(-1F, 7F, 8F);
        this.tail.xRot = 0.4363323F;
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.neck, this.leg0, this.leg1, this.leg2, this.leg3, this.tail);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young)
        {
            matrixStackIn.pushPose();
            if (this.scaleHead)
            {
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
                p_228229_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }
        else
        {
            this.headParts().forEach((p_228228_8_) -> {
                p_228228_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            this.bodyParts().forEach((p_228227_8_) -> {
                p_228227_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
        }
    }
}