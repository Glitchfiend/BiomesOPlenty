package biomesoplenty.entities.render;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import biomesoplenty.entities.EntityJungleSpider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJungleSpider extends RenderSpider
{
    public RenderJungleSpider()
    {
        this.shadowSize *= 0.0F;
    }

    protected void scaleSpider(EntityJungleSpider entityjunglespider, float par2)
    {
        GL11.glScalef(0.4F, 0.4F, 0.3F);
    }

    @Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scaleSpider((EntityJungleSpider)par1EntityLivingBase, par2);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) 
	{
		return new ResourceLocation("BiomesOPlenty:textures/mobs/junglespider.png");
	}
}
