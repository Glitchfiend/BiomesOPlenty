package biomesoplenty.particles;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class EntityDandelionFX extends EntityFX {

	float reddustParticleScale;

	public EntityDandelionFX(World par1World, double par2, double par4, double par6, float par8)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;

		float f4 = (float)Math.random() * 0.4F + 0.6F;
		this.particleScale *= 0.75F;
		this.particleScale *= par8;
		this.reddustParticleScale = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * par8);
		this.noClip = false;
		
		this.particleTextureIndexX = 0;
		this.particleTextureIndexY = 0;
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
		final String[] textures = new String[] {"/mods/BiomesOPlenty/textures/particles/dandelion.png"};
		
        Icon[] icon = new Icon[textures.length];
        par2Icon = icon[0];
		//Icon = par1RenderEngine.bindTexture("/mods/BiomesOPlenty/textures/particles/dandelion.png");
	}

	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{	
		float f6 = ((float)this.particleAge + par2) / (float)this.particleMaxAge * 32.0F;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}

		if (f6 > 1.0F)
		{
			f6 = 1.0F;
		}

		this.particleScale = this.reddustParticleScale * f6;
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
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

		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
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
