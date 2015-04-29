package biomesoplenty.common.fluids.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockHoneyFluid extends BlockFluidFinite
{

    public BlockHoneyFluid(Fluid fluid)
    {
        super(fluid, Material.water);
        this.setLightOpacity(1);
    }
    
    // TODO: check we need this - Does Forge's fluid already handle it through viscosity?
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
        }
    }

}