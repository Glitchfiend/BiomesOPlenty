package biomesoplenty.common.entity.item;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBoatBOP extends EntityRenderer<EntityBoatBOP>
{
   private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{new ResourceLocation("biomesoplenty:textures/entity/boat/fir.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/redwood.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/cherry.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/mahogany.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/jacaranda.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/palm.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/willow.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/dead.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/magic.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/umbran.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/hellbark.png"), new ResourceLocation("biomesoplenty:textures/entity/boat/ethereal.png")};
   /** instance of ModelBoat for rendering */
   protected BoatModel modelBoat = new BoatModel();

   public RenderBoatBOP(EntityRendererManager renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   /**
    * Renders the desired {@code T} type Entity.
    */
   public void doRender(EntityBoatBOP entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.pushMatrix();
      this.setupTranslation(x, y, z);
      this.setupRotation(entity, entityYaw, partialTicks);
      this.bindEntityTexture(entity);
      if (this.renderOutlines) {
         GlStateManager.enableColorMaterial();
         GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
      }

      this.modelBoat.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      if (this.renderOutlines) {
         GlStateManager.tearDownSolidRenderingTextureCombine();
         GlStateManager.disableColorMaterial();
      }

      GlStateManager.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   public void setupRotation(EntityBoatBOP entityIn, float entityYaw, float partialTicks) {
      GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
      float f = (float)entityIn.getTimeSinceHit() - partialTicks;
      float f1 = entityIn.getDamageTaken() - partialTicks;
      if (f1 < 0.0F) {
         f1 = 0.0F;
      }

      if (f > 0.0F) {
         GlStateManager.rotatef(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
      }

      float f2 = entityIn.func_203056_b(partialTicks);
      if (!MathHelper.epsilonEquals(f2, 0.0F)) {
         GlStateManager.rotatef(entityIn.func_203056_b(partialTicks), 1.0F, 0.0F, 1.0F);
      }

      GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
   }

   public void setupTranslation(double x, double y, double z) {
      GlStateManager.translatef((float)x, (float)y + 0.375F, (float)z);
   }

   /**
    * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
    */
   protected ResourceLocation getEntityTexture(EntityBoatBOP entity) {
      return BOAT_TEXTURES[entity.getBoatType().ordinal()];
   }

   public boolean isMultipass() {
      return true;
   }

   public void renderMultipass(EntityBoatBOP entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.pushMatrix();
      this.setupTranslation(x, y, z);
      this.setupRotation(entityIn, entityYaw, partialTicks);
      this.bindEntityTexture(entityIn);
      this.modelBoat.renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.popMatrix();
   }
}