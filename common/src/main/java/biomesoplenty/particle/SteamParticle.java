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
import net.minecraft.world.level.material.Fluids;

public class SteamParticle extends SingleQuadParticle
{
    SteamParticle(ClientLevel p_105856_, double p_105857_, double p_105858_, double p_105859_, double p_105860_, double p_105861_, double p_105862_, TextureAtlasSprite sprite)
    {
        super(p_105856_, p_105857_, p_105858_, p_105859_, sprite);
        this.scale(2.0F);
        this.setSize(0.25F, 0.25F);
        this.lifetime = this.random.nextInt(50) + 280;
        this.gravity = 3.0E-6F;
        this.xd = p_105860_;
        this.yd = p_105861_ + (double)(this.random.nextFloat() / 500.0F);
        this.zd = p_105862_;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F))
        {
            this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
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
    public SingleQuadParticle.Layer getLayer()
    {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_105899_) {
            this.sprites = p_105899_;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd, RandomSource random)
        {
            SteamParticle steamparticle = new SteamParticle(level, xo, yo, zo, xd, yd, zd, this.sprites.get(random));
            steamparticle.setAlpha(0.5F);
            return steamparticle;
        }
    }
}