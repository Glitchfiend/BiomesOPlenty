package biomesoplenty.common.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TurkeyRenderer extends MobRenderer<TurkeyEntity, TurkeyModel<TurkeyEntity>> {
    private static final ResourceLocation TURKEY_LOCATION = new ResourceLocation("biomesoplenty:textures/entity/turkey.png");

    public TurkeyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TurkeyModel<>(), 0.6F);
    }

    public ResourceLocation getTextureLocation(TurkeyEntity entity) {
        return TURKEY_LOCATION;
    }

    protected float getBob(TurkeyEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}