package biomesoplenty.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class EntitySteamFX extends EntityFX
{
	private static final String texture = "/mods/biomesoplenty/textures/particles/steam.png";

	public EntitySteamFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
	{
		this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
	}

	public EntitySteamFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.setParticleTextureIndex(9);
		noClip = false;
		motionX *= 0.10000000149011612D;
		motionY *= 0.10000000149011612D;
		motionZ *= 0.10000000149011612D;
		motionX += par8;
		motionY += par10;
		motionZ += par12;
		particleScale *= 0.75F;
		particleScale *= par14;
		particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		particleMaxAge = (int)(particleMaxAge * par14);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		float f6 = (particleAge + par2) / particleMaxAge * 32.0F;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}

		if (f6 > 1.0F)
		{
			f6 = 1.0F;
		}

		particleScale = particleScale * f6;

		tessellator.draw();
		GL11.glPushMatrix();

		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

		float sizeFactor = 0.1F * particleScale;
		float var13 = (float)(prevPosX + (posX - prevPosX) * par2 - EntityFX.interpPosX);
		float var14 = (float)(prevPosY + (posY - prevPosY) * par2 - EntityFX.interpPosY);
		float var15 = (float)(prevPosZ + (posZ - prevPosZ) * par2 - EntityFX.interpPosZ);

		tessellator.startDrawingQuads();
		tessellator.setBrightness(10);

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

		 this.setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
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
