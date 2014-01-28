package biomesoplenty.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.items.ItemBiomeFinder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeFinderRenderer implements IItemRenderer
{
    private int tickCount = 0;
    private int loopIndex = 0;
    
    private static ResourceLocation radarLocation = new ResourceLocation("biomesoplenty:textures/items/biomeradarstatic.png");
    
    public double currentAngle;
    public double angleDelta;

    @Override
    @SideOnly(Side.CLIENT)
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return (type.equals(IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)) || (type.equals(IItemRenderer.ItemRenderType.INVENTORY)) || (type.equals(IItemRenderer.ItemRenderType.EQUIPPED)) || (type.equals(IItemRenderer.ItemRenderType.ENTITY));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return type == IItemRenderer.ItemRenderType.ENTITY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        Tessellator tessellator = Tessellator.instance;

        IIcon radarIcon = ((ItemBiomeFinder)BOPItemHelper.get("biomeFinder")).radarIcon;

        int index = getIconIndexFacingBiome(item);

        GL11.glEnable(3042);
        
        if (type.equals(IItemRenderer.ItemRenderType.ENTITY)) 
        {
            GL11.glTranslated(-0.5D, -0.1D, 0.0D);
        }

        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        texturemanager.bindTexture(texturemanager.getResourceLocation(item.getItemSpriteNumber()));

        float f = (float)(0.01D / radarIcon.getIconWidth());
        //            (originX     )
        float minU = ((index * 15F) / radarIcon.getIconWidth() + f) / 2;
        float maxU = (((index * 15F) + 16F) / radarIcon.getIconWidth() - f) / 2;
        float minV = ((0 * 15F) / radarIcon.getIconHeight() + f);
        float maxV = (((0 * 15F) + 16F) / radarIcon.getIconHeight() - f);

        GL11.glBlendFunc(770, 771);
        
        if (type.equals(IItemRenderer.ItemRenderType.INVENTORY))
        {
            RenderUtils.renderIcon(index, 0, minU, maxU, minV, maxV, 0.001D);
        }
        else
        {
            ItemRenderer.renderItemIn2D(tessellator, maxU, minV, minU, maxV, radarIcon.getIconWidth(), radarIcon.getIconHeight(), 0.0625F);
        }
        
        GL11.glDisable(3042);
    }

    private int getIconIndexFacingBiome(ItemStack itemStack)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        World world = minecraft.theWorld;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        NBTTagCompound stackCompound = itemStack != null ? itemStack.getTagCompound() : null;
        boolean foundBiome = stackCompound != null ? stackCompound.getBoolean("foundBiome") : false;
        NBTTagCompound biomePositionCompound = stackCompound != null ? stackCompound.getCompoundTag("biomePosition") : null;

        if (foundBiome)
        {
            double playerPosX = player.posX;
            double playerPosZ = player.posZ;
            int biomePosX = biomePositionCompound.getInteger("x");
            int biomePosZ = biomePositionCompound.getInteger("z");

            double d3 = 0.0D;

            if (world != null)
            {
                double d4 = (double)biomePosX - playerPosX;
                double d5 = (double)biomePosZ - playerPosZ;
                player.rotationYaw %= 360.0D;
                d3 = -((player.rotationYaw - 90.0D) * Math.PI / 180.0D - Math.atan2(d5, d4));
            }

            double d6;

            for (d6 = d3 - this.currentAngle; d6 < -Math.PI; d6 += (Math.PI * 2D))
            {
                ;
            }

            while (d6 >= Math.PI)
            {
                d6 -= (Math.PI * 2D);
            }

            if (d6 < -1.0D)
            {
                d6 = -1.0D;
            }

            if (d6 > 1.0D)
            {
                d6 = 1.0D;
            }

            this.angleDelta += d6 * 0.1D;
            this.angleDelta *= 0.8D;
            this.currentAngle += this.angleDelta;

            int i;

            for (i = (int)((this.currentAngle / (Math.PI * 2D) + 1.0D) * (double)32) % 32; i < 0; i = (i + 32) % 32)
            {
                ;
            }
            
            return i;
        }
        else
        {
            if (tickCount++ > 3) 
            { 
                if (loopIndex++ < 31)
                {
                    tickCount = 0; 
                }
                else
                {
                    loopIndex = 0;
                }
            }
            
            return loopIndex;
        }
    }
}
