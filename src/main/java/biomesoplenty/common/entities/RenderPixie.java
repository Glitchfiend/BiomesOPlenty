package biomesoplenty.common.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPixie extends RenderLiving<EntityPixie>
{
    private static final ResourceLocation pixieTextureLocation = new ResourceLocation("biomesoplenty:textures/entity/pixie.png");

    public RenderPixie(RenderManager renderManager)
    {
        super(renderManager, new ModelPixie(), 0.25F);
        this.shadowSize = 0.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPixie entity)
    {
        return pixieTextureLocation;
    }

    // TODO: Not sure about all this - Adubbz check please
    // Looks like the idea is to set some rendering functions, then call super.doRender, then go back to normal
    // LayerEndermanEyes have the same kind of approach I think
    @Override
    public void doRender(EntityPixie entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(1, 1);
        GlStateManager.disableLighting();

        char c0 = 61680;
        int i = c0 % 65536;
        int j = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)i / 1.0F, (float)j / 1.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);  

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

}