package biomesoplenty.client.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import biomesoplenty.client.models.entities.ModelBird;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBird extends RenderLiving
{
    public RenderBird()
    {
        super(new ModelBird(), 0.25F);
        this.shadowSize = 0.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation("biomesoplenty:textures/mobs/bird.png");
    }
}
