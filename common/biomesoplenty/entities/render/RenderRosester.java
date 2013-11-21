package biomesoplenty.entities.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import biomesoplenty.entities.EntityRosester;

public class RenderRosester extends RenderLiving
{
    public RenderRosester(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderRosester(EntityRosester par1EntityRosester, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityRosester, par2, par4, par6, par8, par9);
    }

    protected float getWingRotation(EntityRosester par1EntityRosester, float par2)
    {
        float f1 = par1EntityRosester.field_70888_h + (par1EntityRosester.field_70886_e - par1EntityRosester.field_70888_h) * par2;
        float f2 = par1EntityRosester.field_70884_g + (par1EntityRosester.destPos - par1EntityRosester.field_70884_g) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    @Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderRosester((EntityRosester)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
	protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getWingRotation((EntityRosester)par1EntityLivingBase, par2);
    }

    @Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderRosester((EntityRosester)par1Entity, par2, par4, par6, par8, par9);
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return new ResourceLocation("biomesoplenty:textures/mobs/rosester.png");
	}
}
