/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.init.ModBlocks;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPDecoration extends Block implements IBOPBlock
{
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    // constructor
    public BlockBOPDecoration() {
        this(Material.plants);
    }
    public BlockBOPDecoration(Material material)
    {
        super(material);
        
        // set some defaults
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);

        this.setDefaultState(this.blockState.getBaseState());     
    }

    // no collision box - you can walk straight through them
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        return NULL_AABB;
    }
    
/*    // utility function for setting the block bounds - typically decoration blocks are smaller than full block size
    public void setBlockBoundsByRadiusAndHeight(float radius, float height)
    {
        this.setBlockBoundsByRadiusAndHeight(radius, height, false);
    }
    public void setBlockBoundsByRadiusAndHeight(float radius, float height, boolean fromTop)
    {
        this.setBlockBounds(0.5F - radius, (fromTop ? 1.0F - height : 0.0F), 0.5F - radius, 0.5F + radius, (fromTop ? 1.0F : height), 0.5F + radius);
    }
    // some decoration blocks have a random XZ offset applied - if we set block bounds based on state, we may need these functions to correct for the offset
    public void setBlockBoundsByRadiusAndHeightWithXZOffset(float radius, float height, BlockPos pos)
    {
        this.setBlockBoundsByRadiusAndHeightWithXZOffset(radius, height, false, pos);
    }
    public void setBlockBoundsByRadiusAndHeightWithXZOffset(float radius, float height, boolean fromTop, BlockPos pos)
    {
        // some Minecraft weirdness to get over here: in BlockModelRenderer there are 2 alternative quad drawers
        // renderModelAmbientOcclusionQuads and renderModelStandardQuads, and they use very nearly but not quite the same functions for the XZ offset
        // both versions rely on getting an unpredictable long 'hash' of the BlockPos coordinates
        // the ambient one uses long i = MathHelper.getPositionRandom(pos)  (which equates to long i = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y; )
        // the standard one uses long i = (long)(x * 3129871) ^ (long)z * 116129781L; (no dependence on y)
        // we use the standard one here, because that's the one being used to draw the plants
        // it looks like a mistake to me.  I think they probably intended to use MathHelper.getPositionRandom for both, but maybe there is some reason behind it
        long i = (long)(pos.getX() * 3129871) ^ (long)pos.getZ() * 116129781L;
        i = i * i * 42317861L + i * 11L;
        float dx = (((float)(i >> 16 & 15L) / 15.0F) - 0.5F) * 0.5F;
        float dz = (((float)(i >> 24 & 15L) / 15.0F) - 0.5F) * 0.5F;
        this.setBlockBounds(0.5F - radius + dx, (fromTop ? 1.0F - height : 0.0F), 0.5F - radius + dz, 0.5F + radius + dx, (fromTop ? 1.0F : height), 0.5F + radius + dz);
    }
 */
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

    // our blocks usually drop their current state as opposed to a single 'default' state
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
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

    // not opaque
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    // not full cube
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    //decoration should be randomly offset by default
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
    
/*    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.3F, 0.6F, pos);
    }*/

    
    
}
