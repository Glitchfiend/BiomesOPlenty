package biomesoplenty.entities.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import biomesoplenty.entities.models.ModelWasp;

public class RenderWasp extends RenderLiving
{
    public RenderWasp()
    {
        super(new ModelWasp(), 0.25F);
        this.shadowSize = 0.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation("biomesoplenty:textures/mobs/wasp.png");
    }
}
