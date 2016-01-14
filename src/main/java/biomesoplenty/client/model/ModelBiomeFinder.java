/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
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
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ISmartItemModel;

public class ModelBiomeFinder extends IFlexibleBakedModel.Wrapper implements ISmartItemModel
{
    private IBakedModel[] frames;
    
    public ModelBiomeFinder(IModel defaultModel, TextureAtlasSprite[] frameTextures)
    {
        super(null, DefaultVertexFormats.ITEM);

        this.frames = ModelUtils.generateModelsForTextures(defaultModel, frameTextures);
     }

    @Override
    public IBakedModel handleItemState(ItemStack stack)
    {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) {return this.frames[0];}
        
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && nbt.hasKey("biomeIDToFind"))
        {                
            if (nbt.hasKey("searchStarted"))
            {
                // searching for biome, but not yet found indicate searching by flashing
                return this.getFlashingFrame(player);
            }
            else if (nbt.getBoolean("found"))
            {
                // if the biome has been found, point at it
                int posX = nbt.getInteger("posX");
                int posZ = nbt.getInteger("posZ");
                return getFrameForPositionRelativeToPlayer(player, posX, posZ);
            }
            else
            {
                // the search has not yet been started, show all sectors lit
                return this.frames[9];
            }
        }
        else
        {
            // if we've got here, the biome finder has not been bound to a biome yet - show no sectors lit
            return this.frames[8];
        }
    }
    
    public IBakedModel getFlashingFrame(EntityPlayerSP player)
    {
        return (player.getRNG().nextInt(2) == 0 ? this.frames[10] : this.frames[11]);
    }
    
    public IBakedModel getFrameForPositionRelativeToPlayer(EntityPlayer player, int biomePosX, int biomePosZ)
    {
        double xDiff = (double)biomePosX - player.posX;
        double zDiff = (double)biomePosZ - player.posZ;
        // angle (in degrees) of direction from player to biome (relative to player rotation)
        double angleDiff = (Math.atan2(zDiff, xDiff) * 180.0D / Math.PI) + 270.0D - player.rotationYaw;
        // there are 8 sectors on the biome finder, so 45 degrees each (offset by 22.5 to center the angle in the middle of the sector)
        int sector = (int)Math.floor((angleDiff + 22.5D) / 45.0D);
        return this.frames[((sector % 8) + 8) % 8];        
    }
}
