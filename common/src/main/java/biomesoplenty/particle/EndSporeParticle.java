/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class EndSporeParticle extends SingleQuadParticle
{
    EndSporeParticle(ClientLevel p_105856_, double p_105857_, double p_105858_, double p_105859_, double p_105860_, double p_105861_, double p_105862_, TextureAtlasSprite texture)
    {
        super(p_105856_, p_105857_, p_105858_, p_105859_, texture);
        this.setSize(0.05F, 0.05F);
        this.lifetime = this.random.nextInt(128) + 32;
        this.xd = (double)((this.random.nextFloat() - this.random.nextFloat()) / 100.0F);
        this.yd = (double)(this.random.nextFloat() / 100.0F);
        this.zd = (double)((this.random.nextFloat() - this.random.nextFloat()) / 100.0F);
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
    public SingleQuadParticle.Layer getLayer()
    {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd, RandomSource random)
        {
            return new EndSporeParticle(level, xo, yo, zo, xd, yd, zd, this.sprite.get(random));
        }
    }
}