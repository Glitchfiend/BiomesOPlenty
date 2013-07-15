package biomesoplenty.fluids;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Fluids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidSpringWater extends BlockFluidClassic
{
	public static Icon springWaterStillIcon;
	public static Icon springWaterFlowingIcon;

	public BlockFluidSpringWater(int id, Fluid fluid, Material material)
	{
		super(id, fluid, material);
		stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);

		for (int i = 8; i < 11; i++)
		{
			displacementIds.put(i, false);
		}

		displacementIds.put(Fluids.liquidPoison.get().blockID, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

		if (par5Random.nextInt(1) == 0)
		{
			BiomesOPlenty.proxy.spawnParticle("steam", par2 + par5Random.nextFloat(), par3 + 1.0F, par4 + par5Random.nextFloat());
		}
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {

		int bId = world.getBlockId(x, y, z);

		if (bId == 0)
			return true;
		if (bId == blockID)
			return false;
		if (displacementIds.containsKey(bId))
			return displacementIds.get(bId);
		Material material = Block.blocksList[bId].blockMaterial;

		if (material.blocksMovement() || material == Material.water || material == Material.lava || material == Material.portal)
			return false;
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		
		if (!world.isRemote)
		{
			if (entity instanceof EntityLivingBase) 
			{
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 20));
			}

			if (entity instanceof EntityPlayer) 
			{
				if (world.rand.nextInt(150) == 0)
				{
					EntityPlayer player = (EntityPlayer)entity;

					if (player.getFoodStats().needFood())
					{
						//((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 1));
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		springWaterStillIcon = iconRegister.registerIcon("biomesoplenty:spring_water_still");
		springWaterFlowingIcon = iconRegister.registerIcon("biomesoplenty:spring_water_flowing");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? springWaterFlowingIcon : springWaterStillIcon;
	}
}
