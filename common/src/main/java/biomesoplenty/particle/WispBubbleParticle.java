/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class WispBubbleParticle extends TextureSheetParticle
{
    WispBubbleParticle(ClientLevel p_105856_, double p_105857_, double p_105858_, double p_105859_, double p_105860_, double p_105861_, double p_105862_)
    {
        super(p_105856_, p_105857_, p_105858_, p_105859_);
        this.setSize(0.025F, 0.025F);
        this.quadSize *= this.random.nextFloat() * 1.0F + 0.2F;
        this.lifetime = this.random.nextInt(64) + 64;
        this.xd = p_105860_ * 0.20000000298023224 + (Math.random() * 2.0 - 1.0) * 0.019999999552965164;
        this.yd = p_105861_ * 0.20000000298023224 + (Math.random() * 2.0 - 1.0) * 0.019999999552965164;
        this.zd = p_105862_ * 0.20000000298023224 + (Math.random() * 2.0 - 1.0) * 0.019999999552965164;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F))
        {
            this.yd += 0.002;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.8500000238418579;
            this.yd *= 0.8500000238418579;
            this.zd *= 0.8500000238418579;
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F)
            {
                this.alpha -= 0.01F;
            }
        }
        else
        {
            this.remove();
        }
    }

    @Override
    public int getLightColor(float p_234080_)
    {
        return 216;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_105899_) {
            this.sprites = p_105899_;
        }

        public Particle createParticle(SimpleParticleType p_105910_, ClientLevel p_105911_, double p_105912_, double p_105913_, double p_105914_, double p_105915_, double p_105916_, double p_105917_)
        {
            WispBubbleParticle particle = new WispBubbleParticle(p_105911_, p_105912_, p_105913_, p_105914_, p_105915_, p_105916_, p_105917_);
            particle.setAlpha(0.5F);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}