package biomesoplenty.common.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionParalysis extends Potion
{
	public PotionParalysis(int id, boolean isBad, int color)
	{
		super(id, isBad, color);
		this.setIconIndex(1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("biomesoplenty:textures/potions/BOPPotionFX.png"));
		return 1;
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return duration >= 1;
	}
}
