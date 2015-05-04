package biomesoplenty.common.fluids.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockBloodFluid extends BlockFluidFinite
{

    public BlockBloodFluid(Fluid fluid)
    {
        super(fluid, Material.water);
    }

}