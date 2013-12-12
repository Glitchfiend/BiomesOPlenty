package biomesoplenty.entities.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import biomesoplenty.entities.models.ModelWasp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWasp extends RenderLiving
{
    public RenderWasp()
    {
        super(new ModelWasp(), 0.25F);
        this.shadowSize = 0.0F;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime)
    {
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.75F, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation("biomesoplenty:textures/mobs/wasp.png");
    }
}
