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

public class PusParticle extends SingleQuadParticle
{
    PusParticle(ClientLevel p_107074_, double p_107075_, double p_107076_, double p_107077_, TextureAtlasSprite sprite)
    {
        super(p_107074_, p_107075_, p_107076_, p_107077_, 0.0D, 0.0D, 0.0D, sprite);
        this.gravity = 0.75F;
        this.friction = 0.999F;
        this.xd *= (double)0.8F;
        this.yd *= (double)0.8F;
        this.zd *= (double)0.8F;
        this.yd = (double)(this.random.nextFloat() * 0.4F + 0.05F);
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.2F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public int getLightColor(float p_106065_) {
        return super.getLightColor(p_106065_);
    }

    @Override
    public float getQuadSize(float p_107089_)
    {
        float f = ((float)this.age + p_107089_) / (float)this.lifetime;
        return this.quadSize * (1.0F - f * f);
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
            return new PusParticle(level, xo, yo, zo, this.sprite.get(random));
        }
    }
}