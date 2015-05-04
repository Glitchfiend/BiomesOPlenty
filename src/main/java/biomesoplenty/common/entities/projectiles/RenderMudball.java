package biomesoplenty.common.entities.projectiles;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMudball extends Render
{
    protected final Item item;
    private final RenderItem renderItem;

    public RenderMudball(RenderManager renderManager, Item item, RenderItem renderItem)
    {
        super(renderManager);
        this.item = item;
        this.renderItem = renderItem;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        this.bindTexture(TextureMap.locationBlocksTexture);
        this.renderItem.renderItemModel(this.getItemStack(entity));
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public ItemStack getItemStack(Entity p_177082_1_)
    {
        return new ItemStack(this.item, 1, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TextureMap.locationBlocksTexture;
    }
}