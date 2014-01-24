package biomesoplenty.client.textures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TextureBiomeFinder extends TextureAtlasSprite
{
    public double currentAngle;
    public double angleDelta;

    public TextureBiomeFinder(String path)
    {
        super(path);
    }

    @Override
    public void updateAnimation()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        
        NBTTagCompound playerData = minecraft.thePlayer != null ? minecraft.thePlayer.getEntityData() : null;
        NBTTagCompound biomePositionsCompound = null;
        NBTTagCompound biomeCompound = null;
        
        if (playerData != null) biomePositionsCompound = playerData.getCompoundTag("biomePositions");
        if (biomePositionsCompound != null) biomeCompound = biomePositionsCompound.getCompoundTag("Lavender Fields");

        if (minecraft.theWorld != null && minecraft.thePlayer != null && biomeCompound != null)
        {
            this.updateFinder(minecraft.theWorld, biomeCompound.getInteger("x"), biomeCompound.getInteger("z"), minecraft.thePlayer.posX, minecraft.thePlayer.posZ, (double)minecraft.thePlayer.rotationYaw, false, false);
        }
        else
        {
            this.updateFinder((World)null, 0, 0, 0.0D, 0.0D, 0.0D, true, false);
        }
    }

    public void updateFinder(World par1World, int biomePosX, int biomePosZ, double playerPosX, double playerPosZ, double par6, boolean par8, boolean par9)
    {
        if (!this.framesTextureData.isEmpty())
        {
            double d3 = 0.0D;

            if (par1World != null && !par8)
            {
                //System.out.println(biomePosX + " " + biomePosZ);
                
                double d4 = (double)biomePosX - playerPosX;
                double d5 = (double)biomePosZ - playerPosZ;
                par6 %= 360.0D;
                d3 = -((par6 - 90.0D) * Math.PI / 180.0D - Math.atan2(d5, d4));
            }

            if (par9)
            {
                this.currentAngle = d3;
            }
            else
            {
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
            }

            int i;

            for (i = (int)((this.currentAngle / (Math.PI * 2D) + 1.0D) * (double)this.framesTextureData.size()) % this.framesTextureData.size(); i < 0; i = (i + this.framesTextureData.size()) % this.framesTextureData.size())
            {
                ;
            }

            if (i != this.frameCounter)
            {
                this.frameCounter = i;
                TextureUtil.func_147955_a((int[][])this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
            }
        }
    }
}
