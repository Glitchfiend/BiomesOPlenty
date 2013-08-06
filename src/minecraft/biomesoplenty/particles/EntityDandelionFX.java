package biomesoplenty.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class EntityDandelionFX extends EntityFX
{
	private static final String texture = "/mods/biomesoplenty/textures/particles/dandelion.png";

	public EntityDandelionFX(World par1World, double par2, double par4, double par6, float par8)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		motionX *= 0.20000000149011612D;
		motionY *= 0.10000000149011612D;
		motionZ *= 0.20000000149011612D;

		float f4 = (float)Math.random() * 0.4F + 0.6F;
		particleScale *= 0.25F;
		particleScale *= par8;
		particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		particleMaxAge = (int)(particleMaxAge * par8);
		noClip = false;

		this.setSize(0.01F, 0.01F);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		tessellator.draw();
		GL11.glPushMatrix();

		GL11.glDepthMask(false);
		GL11.glEnable(3042);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

		float sizeFactor = 0.1F * particleScale;
		float var13 = (float)(prevPosX + (posX - prevPosX) * par2 - EntityFX.interpPosX);
		float var14 = (float)(prevPosY + (posY - prevPosY) * par2 - EntityFX.interpPosY);
		float var15 = (float)(prevPosZ + (posZ - prevPosZ) * par2 - EntityFX.interpPosZ);

		tessellator.startDrawingQuads();
		tessellator.setBrightness(240);

		tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1.0F);
		tessellator.addVertexWithUV(var13 - par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 - par5 * sizeFactor - par7 * sizeFactor, 0.0D, 1.0D);
		tessellator.addVertexWithUV(var13 - par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 - par5 * sizeFactor + par7 * sizeFactor, 1.0D, 1.0D);
		tessellator.addVertexWithUV(var13 + par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 + par5 * sizeFactor + par7 * sizeFactor, 1.0D, 0.0D);
		tessellator.addVertexWithUV(var13 + par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 + par5 * sizeFactor - par7 * sizeFactor, 0.0D, 0.0D);

		tessellator.draw();

		GL11.glDisable(3042);
		GL11.glDepthMask(true);

		GL11.glPopMatrix();

		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/particles.png");
		tessellator.startDrawingQuads();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
		{
			this.setDead();
		}

		motionY += 0.004D;
		this.moveEntity(motionX, motionY, motionZ);

		if (posY == prevPosY)
		{
			motionX *= 1.1D;
			motionZ *= 1.1D;
		}

		motionX *= 0.9599999785423279D;
		motionY *= 0.9599999785423279D;
		motionZ *= 0.9599999785423279D;

		if (onGround)
		{
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}
