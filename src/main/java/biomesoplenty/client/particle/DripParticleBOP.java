/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.particle;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.init.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DripParticleBOP extends TextureSheetParticle
{
    private final Fluid type;
    protected boolean isGlowing;

    DripParticleBOP(ClientLevel p_106051_, double p_106052_, double p_106053_, double p_106054_, Fluid p_106055_)
    {
        super(p_106051_, p_106052_, p_106053_, p_106054_);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = p_106055_;
    }

    protected Fluid getType() {
        return this.type;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getLightColor(float p_106065_) {
        return this.isGlowing ? 240 : super.getLightColor(p_106065_);
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed)
        {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed)
            {
                this.xd *= (double)0.98F;
                this.yd *= (double)0.98F;
                this.zd *= (double)0.98F;
                BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
                FluidState fluidstate = this.level.getFluidState(blockpos);
                if (fluidstate.getType() == this.type && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos)))
                {
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
    static class DripHangParticle extends DripParticleBOP {
        private final ParticleOptions fallingParticle;

        DripHangParticle(ClientLevel p_106085_, double p_106086_, double p_106087_, double p_106088_, Fluid p_106089_, ParticleOptions p_106090_) {
            super(p_106085_, p_106086_, p_106087_, p_106088_, p_106089_);
            this.fallingParticle = p_106090_;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }

        }

        protected void postMoveUpdate() {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class DripLandParticle extends DripParticleBOP
    {
        DripLandParticle(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_, Fluid p_106106_)
        {
            super(p_106102_, p_106103_, p_106104_, p_106105_, p_106106_);
            this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class FallAndLandParticle extends DripParticleBOP.FallingParticle
    {
        protected final ParticleOptions landParticle;

        FallAndLandParticle(ClientLevel p_106116_, double p_106117_, double p_106118_, double p_106119_, Fluid p_106120_, ParticleOptions p_106121_)
        {
            super(p_106116_, p_106117_, p_106118_, p_106119_, p_106120_);
            this.landParticle = p_106121_;
        }

        protected void postMoveUpdate()
        {
            if (this.onGround)
            {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
            }

        }
    }

    @OnlyIn(Dist.CLIENT)
    static class FallingParticle extends DripParticleBOP
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
    static class BloodFallAndLandParticle extends DripParticleBOP.FallAndLandParticle
    {
        BloodFallAndLandParticle(ClientLevel p_106146_, double p_106147_, double p_106148_, double p_106149_, Fluid p_106150_, ParticleOptions p_106151_) {
            super(p_106146_, p_106147_, p_106148_, p_106149_, p_106150_, p_106151_);
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
                float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
                this.level.playLocalSound(this.x, this.y, this.z, BOPSounds.FLESH_TENDON_DRIP.get(), SoundSource.BLOCKS, f, 1.0F, false);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class BloodFallProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public BloodFallProvider(SpriteSet p_106163_) {
            this.sprite = p_106163_;
        }

        public Particle createParticle(SimpleParticleType p_106174_, ClientLevel p_106175_, double p_106176_, double p_106177_, double p_106178_, double p_106179_, double p_106180_, double p_106181_)
        {
            DripParticleBOP dripparticle = new DripParticleBOP.BloodFallAndLandParticle(p_106175_, p_106176_, p_106177_, p_106178_, Fluids.EMPTY, ModParticles.LANDING_BLOOD.get());
            dripparticle.gravity = 0.01F;
            dripparticle.setColor(0.443F, 0.141F, 0.149F);
            dripparticle.pickSprite(this.sprite);
            return dripparticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class BloodHangProvider implements ParticleProvider<SimpleParticleType>
    {
        protected final SpriteSet sprite;

        public BloodHangProvider(SpriteSet p_106184_)
        {
            this.sprite = p_106184_;
        }

        public Particle createParticle(SimpleParticleType p_106195_, ClientLevel p_106196_, double p_106197_, double p_106198_, double p_106199_, double p_106200_, double p_106201_, double p_106202_)
        {
            DripParticleBOP.DripHangParticle dripparticle$driphangparticle = new DripParticleBOP.DripHangParticle(p_106196_, p_106197_, p_106198_, p_106199_, Fluids.EMPTY, ModParticles.FALLING_BLOOD.get());
            dripparticle$driphangparticle.gravity *= 0.01F;
            dripparticle$driphangparticle.lifetime = 100;
            dripparticle$driphangparticle.setColor(0.443F, 0.141F, 0.149F);
            dripparticle$driphangparticle.pickSprite(this.sprite);
            return dripparticle$driphangparticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class BloodLandProvider implements ParticleProvider<SimpleParticleType>
    {
        protected final SpriteSet sprite;

        public BloodLandProvider(SpriteSet p_106205_) {
            this.sprite = p_106205_;
        }

        public Particle createParticle(SimpleParticleType p_106216_, ClientLevel p_106217_, double p_106218_, double p_106219_, double p_106220_, double p_106221_, double p_106222_, double p_106223_)
        {
            DripParticleBOP dripparticle = new DripParticleBOP.DripLandParticle(p_106217_, p_106218_, p_106219_, p_106220_, Fluids.EMPTY);
            dripparticle.lifetime = (int)(128.0D / (Math.random() * 0.8D + 0.2D));
            dripparticle.setColor(0.443F, 0.141F, 0.149F);
            dripparticle.pickSprite(this.sprite);
            return dripparticle;
        }
    }
}