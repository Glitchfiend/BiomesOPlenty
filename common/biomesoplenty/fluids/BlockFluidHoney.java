package biomesoplenty.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidHoney extends BlockFluidFinite
{
	public static Icon honeyStillIcon;
	public static Icon honeyFlowingIcon;

	public BlockFluidHoney(int id, Fluid fluid, Material material)
	{
		super(id, fluid, material);

		this.setLightOpacity(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		honeyStillIcon = iconRegister.registerIcon("biomesoplenty:honey_still");
		honeyFlowingIcon = iconRegister.registerIcon("biomesoplenty:honey_flowing");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? honeyFlowingIcon : honeyStillIcon;
	}
}
