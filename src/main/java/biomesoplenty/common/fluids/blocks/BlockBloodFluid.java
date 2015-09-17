package biomesoplenty.common.fluids.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.configuration.BOPConfigurationMisc;

public class BlockBloodFluid extends BlockFluidClassic
{
	public static IIcon bloodStillIcon;
	public static IIcon bloodFlowingIcon;

	public BlockBloodFluid() 
	{
		super(FluidRegistry.getFluid("hell_blood"), Material.water);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		bloodStillIcon = iconRegister.registerIcon("biomesoplenty:blood_still");
		bloodFlowingIcon = iconRegister.registerIcon("biomesoplenty:blood_flowing");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side != 0 && side != 1 ? bloodFlowingIcon : bloodStillIcon;
	}
}
