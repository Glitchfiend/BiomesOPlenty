/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.fluids.blocks;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockQuicksandFluid extends BlockFluidClassic
{
    public BlockQuicksandFluid(Fluid fluid)
    {
        super(fluid, Material.WATER);
        
        this.quantaPerBlock = 3;
        this.setLightOpacity(0); // should be zero to prevent any light from passing through it
        this.renderLayer = BlockRenderLayer.SOLID;
        this.setHardness(0.5F);// match the hardness of vanilla sand
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        entity.setInWeb();
    }
    
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	super.onBlockAdded(worldIn, pos, state);
        this.checkForMixing(worldIn, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighborPos)
    {
    	super.neighborChanged(state, worldIn, pos, blockIn, neighborPos);
        this.checkForMixing(worldIn, pos, state);
    }
    
    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state)
    {
        boolean flag = false;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (enumfacing != EnumFacing.DOWN && (worldIn.getBlockState(pos.offset(enumfacing)).getMaterial().isLiquid() == true))
            {
            	if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this.getBlockState().getBlock())
            	{
	                flag = true;
	                break;
            	}
            }
        }

        if (flag)
        {
            Integer integer = state.getValue(LEVEL);

            if (integer.intValue() == 0)
            {
                worldIn.setBlockState(pos, BOPBlocks.mud.getDefaultState());
                return true;
            }

            if (integer.intValue() <= 4)
            {
                worldIn.setBlockState(pos, BOPBlocks.mud.getDefaultState());
                return true;
            }
        }

        return false;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
