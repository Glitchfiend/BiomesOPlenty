package biomesoplenty.common.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderButterfly extends RenderLiving<EntityButterfly>
{
    private static final ResourceLocation butterflyTextureLocation = new ResourceLocation("biomesoplenty:textures/entity/butterfly.png");

    public RenderButterfly(RenderManager renderManager)
    {
        super(renderManager, new ModelButterfly(), 0.25F);
        this.shadowSize = 0.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityButterfly entity)
    {
        return butterflyTextureLocation;
    }

}