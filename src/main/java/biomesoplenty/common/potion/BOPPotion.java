package biomesoplenty.common.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BOPPotion extends Potion
{
    private static final ResourceLocation POTIONS_LOCATION = new ResourceLocation("biomesoplenty:textures/potions/bop_potion_fx.png");
    
    protected BOPPotion(boolean isBadEffectIn, int liquidColorIn, int x, int y)
    {
        super(isBadEffectIn, liquidColorIn);
        this.setIconIndex(x, y);
    }

    // We handle status icon rendering ourselves
    @Override
    public boolean hasStatusIcon()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(POTIONS_LOCATION);
        int iconIndex = this.getStatusIconIndex();
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 0 + iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
        mc.getTextureManager().bindTexture(POTIONS_LOCATION);
        int iconIndex = this.getStatusIconIndex();
        mc.ingameGUI.drawTexturedModalRect(x + 3, y + 3, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
    }
}