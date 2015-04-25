/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.model;

import biomesoplenty.client.util.ModelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartItemModel;

public class ModelBiomeFinder extends IFlexibleBakedModel.Wrapper implements ISmartItemModel
{
    private double[] currentAngles = new double[BiomeGenBase.getBiomeGenArray().length];
    private double[] angleDeltas = new double[BiomeGenBase.getBiomeGenArray().length];
    
    private IBakedModel defaultModel;
    private IBakedModel[] frames; 
    
    public ModelBiomeFinder(IBakedModel defaultModel, TextureAtlasSprite[] frameTextures)
    {
        super(defaultModel, DefaultVertexFormats.ITEM);
        
        this.defaultModel = defaultModel;
        this.frames = ModelUtils.generateModelsForTextures(defaultModel, frameTextures);
    }
    
    @Override
    public TextureAtlasSprite getTexture()
    {
        return null;
    }

    @Override
    public IBakedModel handleItemState(ItemStack stack)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP player = minecraft.thePlayer;
        NBTTagCompound compound = stack.getTagCompound();
        
        if (player != null && compound != null)
        {
            int biomeID = compound.getInteger("biomeIDToFind");
            int posX = compound.getInteger("posX");
            int posZ = compound.getInteger("posZ");
            boolean foundBiome = compound.getBoolean("foundBiome");
            
            return frames[getIconIndexFacingBiome(player, posX, posZ, biomeID)];
        }
        
        return defaultModel;
    }
    
    public int getIconIndexFacingBiome(EntityPlayer player, int biomePosX, int biomePosZ, int biomeID)
    {
        double biomeAngle = 0.0D;

        double xDiff = (double)biomePosX - player.posX;
        double zDiff = (double)biomePosZ - player.posZ;
        player.rotationYaw %= 360.0D;

        //Converts the player's yaw to radians, subtracts the angle of the coord of the biome
        biomeAngle = -((player.rotationYaw - 90.0D) / 180.0D * Math.PI - Math.atan2(zDiff, xDiff));

        double d6;

        for (d6 = biomeAngle - this.currentAngles[biomeID]; d6 < -Math.PI; d6 += (Math.PI * 2D))
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
}
