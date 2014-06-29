package biomesoplenty.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.client.utils.ParticleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFlowerScatterFX extends EntityFX
{
	private int index;
	
	public EntityFlowerScatterFX(World world, double x, double y, double z, IIcon flowerIcon)
	{
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		
		this.setParticleIcon(flowerIcon);
        this.motionX = this.motionY = this.motionZ = 0.0D;
		this.particleMaxAge = 550;
		this.index = this.rand.nextInt(4);
	}

    @Override
    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
    
	@Override
	public void renderParticle(Tessellator tessellator, float partialTickTime, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
	{
        float minU = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
        float maxU = minU + 0.015609375F;
        float minV = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
        float maxV = minV + 0.015609375F;

        if (this.particleIcon != null)
        {
        	minU = particleIcon.getMinU();
            maxU = particleIcon.getMaxU();
            minV = particleIcon.getMinV();
            maxV = particleIcon.getMaxV();
        }
        
        float uMod = (particleIcon.getMaxU() - particleIcon.getMinU()) / 2F;
        float vMod = (particleIcon.getMaxV() - particleIcon.getMinV()) / 2F;
        
        if (index % 2 == 0) maxU -= uMod;
        else minU += uMod;

        if (index < 2) maxV -= vMod;
        else minV += vMod;

        float alpha = 1.0F - Math.min(1.0F, 2.0F * this.particleAge / this.particleMaxAge);
        float width = 0.15F;
		float x = (float)(prevPosX + (posX - prevPosX) - interpPosX);
		float y = (float)(prevPosY + (posY - prevPosY) - interpPosY);
		float z = (float)(prevPosZ + (posZ - prevPosZ) - interpPosZ);

        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, alpha);
        tessellator.addVertexWithUV(x - width, y, z + width, minU, maxV);
        tessellator.addVertexWithUV(x + width, y, z + width, minU, minV);
        tessellator.addVertexWithUV(x + width, y, z - width, maxU, minV);
        tessellator.addVertexWithUV(x - width, y, z - width, maxU, maxV);
	}
	
    @Override
	public int getFXLayer()
    {
        return 2;
    }
}