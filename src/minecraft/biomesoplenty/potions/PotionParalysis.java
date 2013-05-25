package biomesoplenty.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionParalysis extends Potion
{
    public PotionParalysis(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
        this.setIconIndex(1, 0);
    }
    
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/BiomesOPlenty/textures/potions/BOPPotionFX.png");
        return 1;
    }
    
    @Override
    public boolean isReady(int par1, int par2)
    {
        return par1 >= 1;
    }
}
