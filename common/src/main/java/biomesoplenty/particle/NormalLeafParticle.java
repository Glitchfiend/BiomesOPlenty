package biomesoplenty.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class NormalLeafParticle extends SingleQuadParticle {
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final int INITIAL_LIFETIME = 300;
    private static final int CURVE_ENDPOINT_TIME = 300;
    private float rotSpeed;
    private final float particleRandom;
    private final float spinAcceleration;
    private final float windBig;
    private boolean swirl;
    private boolean flowAway;
    private double xaFlowScale;
    private double zaFlowScale;
    private double swirlPeriod;

    public NormalLeafParticle(ClientLevel p_377646_, double p_377442_, double p_376050_, double p_377918_, SpriteSet p_376948_, TextureAtlasSprite sprite)
    {
        super(p_377646_, p_377442_, p_376050_, p_377918_, sprite);
        this.setSprite(p_376948_.get(this.random.nextInt(4), 4));
        this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.windBig = 2.5F;
        this.swirl = true;
        this.flowAway = false;
        this.lifetime = 300;
        this.gravity = 0.07F * 1.2F * 0.0025F;
        float f = 2.0F * (this.random.nextBoolean() ? 0.05F : 0.075F);
        this.quadSize = f;
        this.setSize(f, f);
        this.friction = 1.0F;
        this.yd = -0.021F;
        this.xaFlowScale = Math.cos(Math.toRadians(this.particleRandom * 60.0F)) * this.windBig;
        this.zaFlowScale = Math.sin(Math.toRadians(this.particleRandom * 60.0F)) * this.windBig;
        this.swirlPeriod = Math.toRadians(1000.0F + this.particleRandom * 3000.0F);
    }

    @Override
    public SingleQuadParticle.Layer getLayer()
    {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = 300 - this.lifetime;
            float f1 = Math.min(f / 300.0F, 1.0F);
            double d0 = 0.0;
            double d1 = 0.0;
            if (this.flowAway) {
                d0 += this.xaFlowScale * Math.pow(f1, 1.25);
                d1 += this.zaFlowScale * Math.pow(f1, 1.25);
            }

            if (this.swirl) {
                d0 += f1 * Math.cos(f1 * this.swirlPeriod) * this.windBig;
                d1 += f1 * Math.sin(f1 * this.swirlPeriod) * this.windBig;
            }

            this.xd += d0 * 0.0025F;
            this.zd += d1 * 0.0025F;
            this.yd = this.yd - this.gravity;
            this.rotSpeed = this.rotSpeed + this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll = this.roll + this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < 299 && (this.xd == 0.0 || this.zd == 0.0)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd = this.xd * this.friction;
                this.yd = this.yd * this.friction;
                this.zd = this.zd * this.friction;
            }
        }
    }
}