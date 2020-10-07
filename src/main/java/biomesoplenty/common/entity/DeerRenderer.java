package biomesoplenty.common.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel<DeerEntity>> {
    private static final ResourceLocation DEER_LOCATION = new ResourceLocation("biomesoplenty:textures/entity/deer.png");
    private static final ResourceLocation FAWN_LOCATION = new ResourceLocation("biomesoplenty:textures/entity/fawn.png");

    public DeerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DeerModel<>(), 0.7F);
    }

    public ResourceLocation getTextureLocation(DeerEntity entity) {
        if (entity.getAge() < 0)
        {
            return FAWN_LOCATION;
        }
        else
        {
            return DEER_LOCATION;
        }
    }
}