package biomesoplenty.common.entity.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoatRendererBOP extends EntityRenderer<BoatEntityBOP>
{
    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{new ResourceLocation("biomesoplenty:textures/entity/boat/fir.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/redwood.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/cherry.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/mahogany.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/jacaranda.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/palm.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/willow.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/dead.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/magic.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/umbran.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/hellbark.png")};
    protected final BoatModelBOP model = new BoatModelBOP();

    public BoatRendererBOP(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowRadius = 0.8F;
    }

    @Override
    public void render(BoatEntityBOP p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        p_225623_4_.pushPose();
        p_225623_4_.translate(0.0D, 0.375D, 0.0D);
        p_225623_4_.mulPose(Vector3f.YP.rotationDegrees(180.0F - p_225623_2_));
        float f = (float)p_225623_1_.getHurtTime() - p_225623_3_;
        float f1 = p_225623_1_.getDamage() - p_225623_3_;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            p_225623_4_.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)p_225623_1_.getHurtDir()));
        }

        float f2 = p_225623_1_.getBubbleAngle(p_225623_3_);
        if (!MathHelper.equal(f2, 0.0F)) {
            p_225623_4_.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), p_225623_1_.getBubbleAngle(p_225623_3_), true));
        }

        p_225623_4_.scale(-1.0F, -1.0F, 1.0F);
        p_225623_4_.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        this.model.setupAnim(p_225623_1_, p_225623_3_, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder ivertexbuilder = p_225623_5_.getBuffer(this.model.renderType(this.getTextureLocation(p_225623_1_)));
        this.model.renderToBuffer(p_225623_4_, ivertexbuilder, p_225623_6_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        IVertexBuilder ivertexbuilder1 = p_225623_5_.getBuffer(RenderType.waterMask());
        this.model.waterPatch().render(p_225623_4_, ivertexbuilder1, p_225623_6_, OverlayTexture.NO_OVERLAY);
        p_225623_4_.popPose();
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    @Override
    public ResourceLocation getTextureLocation(BoatEntityBOP entity)
    {
        return BOAT_TEXTURES[entity.getBoatModel().ordinal()];
    }
}