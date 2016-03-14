/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.fluids.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockPoisonFluid extends BlockFluidClassic
{

    public BlockPoisonFluid(Fluid fluid)
    {
        super(fluid, Material.water);
        this.setLightOpacity(3);
        this.quantaPerBlock = 4;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.poison, 100));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.hunger, 100));
        }
    }

}