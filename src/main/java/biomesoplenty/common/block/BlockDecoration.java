/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockDecoration extends Block implements IBOPBlock
{
    
    // implement IBOPBlock
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    public IProperty[] getRenderProperties() { return new IProperty[] {}; }
    public String getStateName(IBlockState state) {return "";}


    // constructor
    public BlockDecoration() {
        this(Material.plants);
    }
    public BlockDecoration(Material material)
    {
        super(material);
        
        // set some defaults
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypePiston);
        this.setBlockBoundsByRadiusAndHeight(0.3F, 0.6F);

        this.setDefaultState(this.blockState.getBaseState());     
    }
    
    // utility function for setting the block bounds - typically decoration blocks are smaller than full block size
    public void setBlockBoundsByRadiusAndHeight(float radius, float height)
    {
        this.setBlockBoundsByRadiusAndHeight(radius,height,false);
    }
    public void setBlockBoundsByRadiusAndHeight(float radius, float height, boolean fromTop)
    {
        this.setBlockBounds(0.5F - radius, (fromTop ? 1.0F - height : 0.0F), 0.5F - radius, 0.5F + radius, (fromTop ? 1.0F : height), 0.5F + radius);
    }
 
    // add a canBlockStay() check before placing this block
    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack stack)
    {        
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && this.canBlockStay(world, pos, this.getStateFromMeta(stack.getMetadata()));
    }
    
    // check this block is still able to remain after neighbor change
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    // check this block is still able to remain on update ticks
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    // drop block as item if it cannot remain here - return whether on not it could stay
    protected boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canBlockStay(worldIn, pos, state))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 3);
            return false;
        }
    }
    
    // child classes should override this with their own rules about where the block can be placed
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return true;
    }

    // no collision box - you can walk straight through them
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }

    // not opaque
    public boolean isOpaqueCube()
    {
        return false;
    }

    // not full cube
    public boolean isFullCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }


    
}
