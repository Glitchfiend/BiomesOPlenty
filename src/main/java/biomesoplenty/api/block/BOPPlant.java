/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BOPPlant extends BOPBlock
{
    protected BOPPlant(PropertyEnum variantProperty)
    {
	    super(Material.plants, variantProperty);
	    
	    this.setTickRandomly(true);
	    
	    this.setHardness(0.0F);
	    this.setStepSound(Block.soundTypeGrass);
    }
    
    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack stack)
    {
        return super.canPlaceBlockOnSide(world, pos, side) && this.canBlockStay(world, pos, this.getStateFromMeta(stack.getMetadata()));
    }
    
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
    	Block ground = world.getBlockState(pos.offsetDown()).getBlock();
    	
        return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.farmland;
    }
    
    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(world, pos, state, neighborBlock);
        this.checkAndDropBlock(world, pos, state);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(world, pos, state);
    }

    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
    	return Block.EnumOffsetType.XZ;
    }
}
