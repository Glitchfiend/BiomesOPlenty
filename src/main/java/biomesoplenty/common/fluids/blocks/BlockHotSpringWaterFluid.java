/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.fluids.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHotSpringWaterFluid extends BlockFluidClassic
{

    public BlockHotSpringWaterFluid(Fluid fluid)
    {
        super(fluid, Material.water);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 100));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) 
    {
        super.randomDisplayTick(state, world, pos, rand);
        if (rand.nextInt(20)==0)
        {           
        	world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + rand.nextFloat(), pos.getY() + 1.0F, pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

}