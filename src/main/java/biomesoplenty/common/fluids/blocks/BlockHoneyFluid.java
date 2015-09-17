package biomesoplenty.common.fluids.blocks;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockHoneyFluid extends BlockFluidFinite
{
	public static IIcon honeyStillIcon;
	public static IIcon honeyFlowingIcon;

	public BlockHoneyFluid()
	{
		super(FluidRegistry.getFluid("honey"), Material.water);

		this.setLightOpacity(1);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		honeyStillIcon = iconRegister.registerIcon("biomesoplenty:honey_still");
		honeyFlowingIcon = iconRegister.registerIcon("biomesoplenty:honey_flowing");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side != 0 && side != 1 ? honeyFlowingIcon : honeyStillIcon;
	}
}
