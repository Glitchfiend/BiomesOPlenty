/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityPixieTrailFX extends EntityFX
{
    
    public static ResourceLocation textureLocation = new ResourceLocation("biomesoplenty:textures/particles/pixietrail.png");
    public static ResourceLocation vanillaParticleTextureLocation = new ResourceLocation("textures/particle/particles.png");

    public EntityPixieTrailFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        this(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, 1.0F);
    }
    
    public EntityPixieTrailFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float par14)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
                
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += xSpeedIn;
        this.motionY += ySpeedIn;
        this.motionZ += zSpeedIn;
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.particleMaxAge = (int)((8.0D / (Math.random() * 0.8D + 0.2D)) * 8);
        this.particleMaxAge = (int)((float)this.particleMaxAge * par14);
        this.particleAge = (particleMaxAge / 2) + (int)((particleMaxAge / 2) * world.rand.nextInt(7));
        this.particleAlpha = 1.0F;
        this.noClip = false;
    }
    
    @Override
    public void renderParticle(WorldRenderer renderer, Entity entity, float partialTicks, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
    {
        
        Tessellator.getInstance().draw();
        // EffectRenderer will by default bind the vanilla particles texture, override with our own
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(textureLocation);
        renderer.startDrawingQuads();     
        
        float scaleMultiplier = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
        scaleMultiplier = MathHelper.clamp_float(scaleMultiplier, 0.0F, 1.0F);
        this.particleScale = this.particleScale * scaleMultiplier;
        
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);

        super.renderParticle(renderer, entity, partialTicks, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);

        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        
        Tessellator.getInstance().draw();
        // put texture back to vanilla version
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(vanillaParticleTextureLocation);
        renderer.startDrawingQuads();
    }
    
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