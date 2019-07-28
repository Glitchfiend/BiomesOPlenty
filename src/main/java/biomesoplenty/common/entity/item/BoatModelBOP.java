package biomesoplenty.common.entity.item;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoatModelBOP extends EntityModel<BoatEntityBOP> {
    private final RendererModel[] field_78103_a = new RendererModel[5];
    private final RendererModel[] paddles = new RendererModel[2];
    private final RendererModel noWater;

    public BoatModelBOP() {
        this.field_78103_a[0] = (new RendererModel(this, 0, 0)).setTextureSize(128, 64);
        this.field_78103_a[1] = (new RendererModel(this, 0, 19)).setTextureSize(128, 64);
        this.field_78103_a[2] = (new RendererModel(this, 0, 27)).setTextureSize(128, 64);
        this.field_78103_a[3] = (new RendererModel(this, 0, 35)).setTextureSize(128, 64);
        this.field_78103_a[4] = (new RendererModel(this, 0, 43)).setTextureSize(128, 64);
        int i = 32;
        int j = 6;
        int k = 20;
        int l = 4;
        int i1 = 28;
        this.field_78103_a[0].addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
        this.field_78103_a[0].setRotationPoint(0.0F, 3.0F, 1.0F);
        this.field_78103_a[1].addBox(-13.0F, -7.0F, -1.0F, 18, 6, 2, 0.0F);
        this.field_78103_a[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
        this.field_78103_a[2].addBox(-8.0F, -7.0F, -1.0F, 16, 6, 2, 0.0F);
        this.field_78103_a[2].setRotationPoint(15.0F, 4.0F, 0.0F);
        this.field_78103_a[3].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
        this.field_78103_a[3].setRotationPoint(0.0F, 4.0F, -9.0F);
        this.field_78103_a[4].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
        this.field_78103_a[4].setRotationPoint(0.0F, 4.0F, 9.0F);
        this.field_78103_a[0].rotateAngleX = ((float)Math.PI / 2F);
        this.field_78103_a[1].rotateAngleY = ((float)Math.PI * 1.5F);
        this.field_78103_a[2].rotateAngleY = ((float)Math.PI / 2F);
        this.field_78103_a[3].rotateAngleY = (float)Math.PI;
        this.paddles[0] = this.makePaddle(true);
        this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
        this.paddles[1] = this.makePaddle(false);
        this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
        this.paddles[1].rotateAngleY = (float)Math.PI;
        this.paddles[0].rotateAngleZ = 0.19634955F;
        this.paddles[1].rotateAngleZ = 0.19634955F;
        this.noWater = (new RendererModel(this, 0, 0)).setTextureSize(128, 64);
        this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
        this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.noWater.rotateAngleX = ((float)Math.PI / 2F);
    }

    public void render(BoatEntityBOP entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        for(int i = 0; i < 5; ++i) {
            this.field_78103_a[i].render(scale);
        }

        this.renderPaddle(entityIn, 0, scale, limbSwing);
        this.renderPaddle(entityIn, 1, scale, limbSwing);
    }

    public void renderMultipass(Entity entityIn, float partialTicks, float p_187054_3_, float p_187054_4_, float p_187054_5_, float p_187054_6_, float scale) {
        GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.colorMask(false, false, false, false);
        this.noWater.render(scale);
        GlStateManager.colorMask(true, true, true, true);
    }

    protected RendererModel makePaddle(boolean p_187056_1_) {
        RendererModel renderermodel = (new RendererModel(this, 62, p_187056_1_ ? 0 : 20)).setTextureSize(128, 64);
        int i = 20;
        int j = 7;
        int k = 6;
        float f = -5.0F;
        renderermodel.addBox(-1.0F, 0.0F, -5.0F, 2, 2, 18);
        renderermodel.addBox(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1, 6, 7);
        return renderermodel;
    }

    protected void renderPaddle(BoatEntityBOP boat, int paddle, float scale, float limbSwing) {
        float f = boat.getRowingTime(paddle, limbSwing);
        RendererModel renderermodel = this.paddles[paddle];
        renderermodel.rotateAngleX = (float) MathHelper.clampedLerp((double)(-(float)Math.PI / 3F), (double)-0.2617994F, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
        renderermodel.rotateAngleY = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 4F), (double)((float)Math.PI / 4F), (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));
        if (paddle == 1) {
            renderermodel.rotateAngleY = (float)Math.PI - renderermodel.rotateAngleY;
        }

        renderermodel.render(scale);
    }
}