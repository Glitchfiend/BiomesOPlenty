/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities.item;

import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBOPBoat extends Render<EntityBOPBoat>
{
    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {new ResourceLocation("biomesoplenty:textures/entity/boats/boat_sacred_oak.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_cherry.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_umbran.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_fir.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_ethereal.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_magic.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_mangrove.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_palm.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_redwood.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_willow.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_pine.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_hellbark.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_jacaranda.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_mahogany.png"),
    		new ResourceLocation("biomesoplenty:textures/entity/boats/boat_ebony.png"), new ResourceLocation("biomesoplenty:textures/entity/boats/boat_eucalyptus.png")};
    protected ModelBase modelBOPBoat = new ModelBOPBoat();

    public RenderBOPBoat(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }

    public void doRender(EntityBOPBoat entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.modelBOPBoat.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(EntityBOPBoat p_188311_1_, float p_188311_2_, float p_188311_3_)
    {
        GlStateManager.rotate(180.0F - p_188311_2_, 0.0F, 1.0F, 0.0F);
        float f = (float)p_188311_1_.getTimeSinceHit() - p_188311_3_;
        float f1 = p_188311_1_.getDamageTaken() - p_188311_3_;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f > 0.0F)
        {
            GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float)p_188311_1_.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
    {
        GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_ + 0.375F, (float)p_188309_5_);
    }

    protected ResourceLocation getEntityTexture(EntityBOPBoat entity)
    {
        return BOAT_TEXTURES[entity.getBOPBoatType().ordinal()];
    }

    public boolean isMultipass()
    {
        return true;
    }

    public void renderMultipass(EntityBOPBoat p_188300_1_, double p_188300_2_, double p_188300_4_, double p_188300_6_, float p_188300_8_, float p_188300_9_)
    {
        GlStateManager.pushMatrix();
        this.setupTranslation(p_188300_2_, p_188300_4_, p_188300_6_);
        this.setupRotation(p_188300_1_, p_188300_8_, p_188300_9_);
        this.bindEntityTexture(p_188300_1_);
        ((IMultipassModel)this.modelBOPBoat).renderMultipass(p_188300_1_, p_188300_9_, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}