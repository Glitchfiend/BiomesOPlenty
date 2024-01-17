/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class BinaryParticle extends TextureSheetParticle
{
    BinaryParticle(ClientLevel p_105856_, double p_105857_, double p_105858_, double p_105859_, double p_105860_, double p_105861_, double p_105862_)
    {
        super(p_105856_, p_105857_, p_105858_, p_105859_);
        this.setSize(0.05F, 0.05F);
        this.lifetime = this.random.nextInt(32) + 32;
        this.xd = p_105860_;
        this.yd = -0.02D;
        this.zd = p_105862_;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime)
        {
            this.move(this.xd, this.yd, this.zd);
        }
        else
        {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_105899_) {
            this.sprites = p_105899_;
        }

        public Particle createParticle(SimpleParticleType p_105910_, ClientLevel p_105911_, double p_105912_, double p_105913_, double p_105914_, double p_105915_, double p_105916_, double p_105917_)
        {
            BinaryParticle particle = new BinaryParticle(p_105911_, p_105912_, p_105913_, p_105914_, p_105915_, p_105916_, p_105917_);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}