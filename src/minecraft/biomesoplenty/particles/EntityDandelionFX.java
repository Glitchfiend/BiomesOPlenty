package biomesoplenty.particles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class EntityDandelionFX extends EntityFX {
	
    public int blendmode = 1;
    private static final String texture = "/mods/BiomesOPlenty/textures/particles/dandelion.png";

	public EntityDandelionFX(World par1World, double par2, double par4, double par6, float par8)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;

		float f4 = (float)Math.random() * 0.4F + 0.6F;
		this.particleScale *= 0.75F;
		this.particleScale *= par8;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * par8);
		this.noClip = false;
		
        this.setParticleTextureIndex(0);
	}

	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
	{	
        tessellator.draw();
        GL11.glPushMatrix();

        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, blendmode);

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        float f10 = 0.1F * particleScale;
        float f11 = (float) ((prevPosX + (posX - prevPosX) * f) - interpPosX);
        float f12 = (float) ((prevPosY + (posY - prevPosY) * f) - interpPosY);
        float f13 = (float) ((prevPosZ + (posZ - prevPosZ) * f) - interpPosZ);

        // GL11.glRotatef(this.rand.nextFloat(), 0.0F, 1.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(0x0000f0);

        tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1.0F);
        tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, 0, 1);
        tessellator.addVertexWithUV((f11 - f1 * f10) + f4 * f10, f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, 1, 1);
        tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, 1, 0);
        tessellator.addVertexWithUV((f11 + f1 * f10) - f4 * f10, f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, 0, 0);

        tessellator.draw();

        GL11.glDisable(3042);
        GL11.glDepthMask(true);

        GL11.glPopMatrix();
        GL11.glBindTexture(3553 /* GL_TEXTURE_2D *//* GL_TEXTURE_2D */, GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	 public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}
