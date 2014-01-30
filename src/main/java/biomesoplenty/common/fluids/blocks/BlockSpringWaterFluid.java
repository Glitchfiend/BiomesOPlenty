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

public class BlockSpringWaterFluid extends BlockFluidClassic
{
	public static IIcon springWaterStillIcon;
	public static IIcon springWaterFlowingIcon;

	public BlockSpringWaterFluid() 
	{
		//TODO:											  water
		super(FluidRegistry.getFluid("spring_water"), Material.field_151586_h);
	}

    @Override
    //TODO: 	randomDisplayTick()
    public void func_149734_b(World world, int x, int y, int z, Random random)
    {
    	super.func_149734_b(world, x, y, z, random);

    	if (random.nextInt(1) == 0)
    	{
    		BiomesOPlenty.proxy.spawnParticle("steam", x + random.nextFloat(), y + 1.0F, z + random.nextFloat());
    	}
    }
	
	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (!world.isRemote && BOPConfigurationMisc.hotSpringsRegeneration)
		{
			if (entity instanceof EntityLivingBase) 
			{
				if (!((EntityLivingBase)entity).isPotionActive(Potion.regeneration.id))
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 50));
			}
		}
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		springWaterStillIcon = iconRegister.registerIcon("biomesoplenty:spring_water_still");
		springWaterFlowingIcon = iconRegister.registerIcon("biomesoplenty:spring_water_flowing");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		return side != 0 && side != 1 ? springWaterFlowingIcon : springWaterStillIcon;
	}
}
