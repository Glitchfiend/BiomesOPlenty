/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowwormParticle extends TextureSheetParticle
{
    private final Fluid type;

    GlowwormParticle(ClientLevel p_106051_, double p_106052_, double p_106053_, double p_106054_, Fluid p_106055_)
    {
        super(p_106051_, p_106052_, p_106053_, p_106054_);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = p_106055_;
    }

    protected Fluid getType()
    {
        return this.type;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getLightColor(float p_107086_)
    {
        int i = super.getLightColor(p_107086_);
        int j = 240;
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= (double)0.98F;
                this.yd *= (double)0.98F;
                this.zd *= (double)0.98F;
                BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
                FluidState fluidstate = this.level.getFluidState(blockpos);
                if (fluidstate.getType() == this.type && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                    this.remove();
                }

            }
        }
    }

    protected void preMoveUpdate()
    {
        if (this.lifetime-- <= 0)
        {
            this.remove();
        }
    }

    protected void postMoveUpdate() {}

    @OnlyIn(Dist.CLIENT)
    static class FallingParticle extends GlowwormParticle
    {
        FallingParticle(ClientLevel p_106132_, double p_106133_, double p_106134_, double p_106135_, Fluid p_106136_)
        {
            this(p_106132_, p_106133_, p_106134_, p_106135_, p_106136_, (int)(64.0D / (Math.random() * 0.8D + 0.2D)));
        }

        FallingParticle(ClientLevel p_172022_, double p_172023_, double p_172024_, double p_172025_, Fluid p_172026_, int p_172027_)
        {
            super(p_172022_, p_172023_, p_172024_, p_172025_, p_172026_);
            this.lifetime = p_172027_;
        }

        protected void postMoveUpdate()
        {
            if (this.onGround)
            {
                this.remove();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        protected final SpriteSet sprite;

        public Provider(SpriteSet p_106163_) {
            this.sprite = p_106163_;
        }

        public Particle createParticle(SimpleParticleType p_106174_, ClientLevel p_106175_, double p_106176_, double p_106177_, double p_106178_, double p_106179_, double p_106180_, double p_106181_)
        {
            GlowwormParticle glowwormparticle = new GlowwormParticle.FallingParticle(p_106175_, p_106176_, p_106177_, p_106178_, Fluids.EMPTY);
            glowwormparticle.gravity = 0.025F;
            glowwormparticle.pickSprite(this.sprite);
            return glowwormparticle;
        }
    }
}