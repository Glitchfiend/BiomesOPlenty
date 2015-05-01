/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.particle;

import org.lwjgl.opengl.GL11;

import biomesoplenty.core.ClientProxy;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityDandelionFX extends EntityFX
{
    
    public EntityDandelionFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, float par14)
    {
        this(world, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D, par14);
    }
    
    public EntityDandelionFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float par14)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        
        // dandelion texture is at position 0,0
        this.particleTextureIndexX = 0;
        this.particleTextureIndexY = 0;
                
        this.motionX *= 0.20000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.20000000149011612D;
        this.particleScale *= 0.25F;
        this.particleScale *= par14;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * par14);
        this.setAlphaF(1.0F);
        this.noClip = false;
        
        this.setSize(0.01F, 0.01F);
    }
    
    @Override
    public int getFXLayer()
    {
        return 2;
    }
    
    @Override
    public void renderParticle(WorldRenderer renderer, Entity entity, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY)
    {
        
        // EffectRenderer will by default bind the vanilla particles texture, override with our own
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxy.particleTexturesLocation);
        
        GlStateManager.depthMask(false);
        // TODO what's this?
        GL11.glEnable(3042);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);
        
        super.renderParticle(renderer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);
        
        GL11.glDisable(3042);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);

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