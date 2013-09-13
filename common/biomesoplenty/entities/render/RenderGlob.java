package biomesoplenty.entities.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import biomesoplenty.entities.EntityGlob;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGlob extends RenderLiving
{
	private ModelBase scaleAmount;

	public RenderGlob(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
	{
		super(par1ModelBase, par3);
		scaleAmount = par2ModelBase;
	}

	protected int shouldGlobRenderPass(EntityGlob par1EntityGlob, int par2, float par3)
	{
		if (par1EntityGlob.isInvisible())
			return 0;
		else if (par2 == 0)
		{
			this.setRenderPassModel(scaleAmount);
			GL11.glEnable(GL11.GL_NORMALIZE);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			return 1;
		}
		else
		{
			if (par2 == 1)
			{
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}

			return -1;
		}
	}

	/**
	 * sets the scale for the Glob based on getGlobSize in EntityGlob
	 */
	protected void scaleGlob(EntityGlob par1EntityGlob, float par2)
	{
		float f1 = par1EntityGlob.getGlobSize();
		float f2 = (par1EntityGlob.field_70812_c + (par1EntityGlob.field_70811_b - par1EntityGlob.field_70812_c) * par2) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}
	
    @Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scaleGlob((EntityGlob)par1EntityLivingBase, par2);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) 
	{
		return new ResourceLocation("biomesoplenty:textures/mobs/glob.png");
	}
	
    @Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.shouldGlobRenderPass((EntityGlob)par1EntityLivingBase, par2, par3);
    }
}