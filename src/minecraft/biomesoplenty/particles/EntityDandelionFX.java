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
		this.motionX *= 0.20000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.20000000149011612D;

		float f4 = (float)Math.random() * 0.4F + 0.6F;
		this.particleScale *= 0.25F;
		this.particleScale *= par8;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * par8);
		this.noClip = false;
		
	    this.setSize(0.01F, 0.01F);
	}
	
	@Override
	public int getFXLayer()
	{
		return 1;
	}
	
	@Override
	public void setParticleTextureIndex(int par1)
	{
	}

	@Override
	public void setParticleIcon(RenderEngine par1RenderEngine, Icon par2Icon)
	{
	}

	@Override
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{	
	    FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
	    float sizeFactor = 0.1F * this.particleScale;
	    float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - EntityFX.interpPosX);
	    float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - EntityFX.interpPosY);
	    float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - EntityFX.interpPosZ);
	    //float var16 = 1.2F - (float)Math.random() * 0.5F;
		float var16 = 1.2F * 0.5F;
	    par1Tessellator.setColorRGBA_F(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F);
	    par1Tessellator.addVertexWithUV(var13 - par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 - par5 * sizeFactor - par7 * sizeFactor, 0.0D, 1.0D);
	    par1Tessellator.addVertexWithUV(var13 - par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 - par5 * sizeFactor + par7 * sizeFactor, 1.0D, 1.0D);
	    par1Tessellator.addVertexWithUV(var13 + par3 * sizeFactor + par6 * sizeFactor, var14 + par4 * sizeFactor, var15 + par5 * sizeFactor + par7 * sizeFactor, 1.0D, 0.0D);
	    par1Tessellator.addVertexWithUV(var13 + par3 * sizeFactor - par6 * sizeFactor, var14 - par4 * sizeFactor, var15 + par5 * sizeFactor - par7 * sizeFactor, 0.0D, 0.0D);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
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
