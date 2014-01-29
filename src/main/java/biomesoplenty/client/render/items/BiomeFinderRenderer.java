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
import net.minecraft.world.biome.BiomeGenBase;
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
    private static ResourceLocation radarLocation = new ResourceLocation("biomesoplenty:textures/items/biomeradarstatic.png");
    
    private double[] currentAngles = new double[BiomeGenBase.func_150565_n().length];
    private double[] angleDeltas = new double[BiomeGenBase.func_150565_n().length];

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
    	GL11.glPushMatrix();
    	
        Tessellator tessellator = Tessellator.instance;

        NBTTagCompound stackCompound = item != null ? item.getTagCompound() : null;
        int biomeID = stackCompound != null ? stackCompound.getInteger("biomeIDToFind") : 0;
        boolean foundBiome = stackCompound != null ? stackCompound.getBoolean("foundBiome") : false;
        NBTTagCompound biomePositionCompound = stackCompound != null ? stackCompound.getCompoundTag("biomePosition") : null;
        
        ItemBiomeFinder biomeFinder = ((ItemBiomeFinder)BOPItemHelper.get("biomeFinder"));
        
        IIcon radarIcon = foundBiome ? biomeFinder.biomeRadarIcons[getIconIndexFacingBiome(biomeID, biomePositionCompound)] : biomeFinder.getIconFromDamage(0);
        
        if (type.equals(IItemRenderer.ItemRenderType.ENTITY)) 
        {
            GL11.glTranslated(-0.5D, -0.1D, 0.0D);
        }

        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        texturemanager.bindTexture(texturemanager.getResourceLocation(item.getItemSpriteNumber()));

        GL11.glBlendFunc(770, 771);
        
        if (type.equals(IItemRenderer.ItemRenderType.INVENTORY))
        {
        	RenderUtils.renderIcon(radarIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
        }
        else 
        {
        	ItemRenderer.renderItemIn2D(tessellator, radarIcon.getMaxU(), radarIcon.getMinV(), radarIcon.getMinU(), radarIcon.getMaxV(), radarIcon.getIconWidth(), radarIcon.getIconHeight(), 0.0625F);
        }
        GL11.glPopMatrix();
    }

    @SideOnly(Side.CLIENT)
    private int getIconIndexFacingBiome(int biomeID, NBTTagCompound biomePositionCompound)
    {
    	Minecraft minecraft = Minecraft.getMinecraft();
    	World world = minecraft.theWorld;
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;

    	if (biomePositionCompound != null)
    	{
    		double playerPosX = player.posX;
    		double playerPosZ = player.posZ;
    		int biomePosX = biomePositionCompound.getInteger("x");
    		int biomePosZ = biomePositionCompound.getInteger("z");
    		
    		System.out.println(biomePosX);

    		double d3 = 0.0D;

    		if (world != null)
    		{
    			double d4 = (double)biomePosX - playerPosX;
    			double d5 = (double)biomePosZ - playerPosZ;
    			player.rotationYaw %= 360.0D;
    			d3 = -((player.rotationYaw - 90.0D) * Math.PI / 180.0D - Math.atan2(d5, d4));
    		}

    		double d6;

    		for (d6 = d3 - this.currentAngles[biomeID]; d6 < -Math.PI; d6 += (Math.PI * 2D))
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

    		this.angleDeltas[biomeID] += d6 * 0.1D;
    		this.angleDeltas[biomeID] *= 0.8D;
    		this.currentAngles[biomeID] += this.angleDeltas[biomeID];

    		int i;

    		for (i = (int)((this.currentAngles[biomeID] / (Math.PI * 2D) + 1.0D) * (double)32) % 32; i < 0; i = (i + 32) % 32)
    		{
    			;
    		}

    		return i;
    	}
    	
    	return 0;
    }
}
