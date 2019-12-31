package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

public class WhiteSandBlock extends SandBlock
{
    public WhiteSandBlock(int p_i48338_1_, Block.Properties properties)
    {
        super(p_i48338_1_, properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));

        switch (type) {
            case Desert: return true;
            case Nether: return false;
            case Crop: return false;
            case Cave: return true;
            case Plains: return false;
            case Water: return false;
            case Beach:
            boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                    world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                    world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                    world.getBlockState(pos.south()).getMaterial() == Material.WATER);
            return hasWater;
        }
        return false;
    }
}
