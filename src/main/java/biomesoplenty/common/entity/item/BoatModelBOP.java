package biomesoplenty.common.entity.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

// Much care has been put into copying and pasting this, as you can tell
@OnlyIn(Dist.CLIENT)
public class BoatModelBOP extends SegmentedModel<BoatEntityBOP>
{
    private final ModelRenderer[] paddles = new ModelRenderer[2];
    private final ModelRenderer noWater;
    private final ImmutableList<ModelRenderer> field_228243_f_;

    public BoatModelBOP()
    {
        ModelRenderer[] amodelrenderer = new ModelRenderer[]{(new ModelRenderer(this, 0, 0)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 19)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 27)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 35)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 43)).setTextureSize(128, 64)};
        int i = 32;
        int j = 6;
        int k = 20;
        int l = 4;
        int i1 = 28;
        amodelrenderer[0].func_228301_a_(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
        amodelrenderer[0].setRotationPoint(0.0F, 3.0F, 1.0F);
        amodelrenderer[1].func_228301_a_(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
        amodelrenderer[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
        amodelrenderer[2].func_228301_a_(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F, 0.0F);
        amodelrenderer[2].setRotationPoint(15.0F, 4.0F, 0.0F);
        amodelrenderer[3].func_228301_a_(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
        amodelrenderer[3].setRotationPoint(0.0F, 4.0F, -9.0F);
        amodelrenderer[4].func_228301_a_(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
        amodelrenderer[4].setRotationPoint(0.0F, 4.0F, 9.0F);
        amodelrenderer[0].rotateAngleX = ((float)Math.PI / 2F);
        amodelrenderer[1].rotateAngleY = ((float)Math.PI * 1.5F);
        amodelrenderer[2].rotateAngleY = ((float)Math.PI / 2F);
        amodelrenderer[3].rotateAngleY = (float)Math.PI;
        this.paddles[0] = this.makePaddle(true);
        this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
        this.paddles[1] = this.makePaddle(false);
        this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
        this.paddles[1].rotateAngleY = (float)Math.PI;
        this.paddles[0].rotateAngleZ = 0.19634955F;
        this.paddles[1].rotateAngleZ = 0.19634955F;
        this.noWater = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
        this.noWater.func_228301_a_(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
        this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.noWater.rotateAngleX = ((float)Math.PI / 2F);
        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(amodelrenderer));
        builder.addAll(Arrays.asList(this.paddles));
        this.field_228243_f_ = builder.build();
    }

    @Override
    public void func_225597_a_(BoatEntityBOP p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {
        this.func_228244_a_(p_225597_1_, 0, p_225597_2_);
        this.func_228244_a_(p_225597_1_, 1, p_225597_2_);
    }

    @Override
    public ImmutableList<ModelRenderer> func_225601_a_()
    {
        return this.field_228243_f_;
    }

    public ModelRenderer func_228245_c_()
    {
        return this.noWater;
    }

    protected ModelRenderer makePaddle(boolean p_187056_1_)
    {
        ModelRenderer modelrenderer = (new ModelRenderer(this, 62, p_187056_1_ ? 0 : 20)).setTextureSize(128, 64);
        int i = 20;
        int j = 7;
        int k = 6;
        float f = -5.0F;
        modelrenderer.func_228300_a_(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
        modelrenderer.func_228300_a_(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
        return modelrenderer;
    }

    protected void func_228244_a_(BoatEntityBOP p_228244_1_, int p_228244_2_, float p_228244_3_)
    {
        float f = p_228244_1_.getRowingTime(p_228244_2_, p_228244_3_);
        ModelRenderer modelrenderer = this.paddles[p_228244_2_];
        modelrenderer.rotateAngleX = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 3F), (double)-0.2617994F, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
        modelrenderer.rotateAngleY = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 4F), (double)((float)Math.PI / 4F), (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));
        if (p_228244_2_ == 1) {
            modelrenderer.rotateAngleY = (float)Math.PI - modelrenderer.rotateAngleY;
        }
    }
}