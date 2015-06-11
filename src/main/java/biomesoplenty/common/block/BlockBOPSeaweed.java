/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static net.minecraft.block.BlockLiquid.LEVEL;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBOPSeaweed extends BlockBOPDecoration implements IBOPBlock
{
    
    // TODO: is it supposed to grow?
    
    public static enum SeaweedType implements IStringSerializable
    {
        KELP;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    };
    
    public static enum SeaweedPosition implements IStringSerializable
    {
        SINGLE, BOTTOM, MIDDLE, TOP;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }        
    }
    
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", SeaweedType.class);
    public static final PropertyEnum POSITION = PropertyEnum.create("position", SeaweedPosition.class);

    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { LEVEL, POSITION, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {LEVEL}; }
    @Override
    public String getStateName(IBlockState state) {
        SeaweedType type = (SeaweedType)state.getValue(VARIANT);
        return type.getName();
    }
    
    
    public BlockBOPSeaweed()
    {
        super(Material.water);
        
        // set some defaults
        this.setStepSound(Block.soundTypeSand);
        this.setDefaultState( this.blockState.getBaseState().withProperty(LEVEL, 15).withProperty(POSITION, SeaweedPosition.SINGLE).withProperty(VARIANT, SeaweedType.KELP) );
    }
    
    // examine neighbors to figure out the SeaweedPosition value
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        boolean seaweedAbove = (worldIn.getBlockState(pos.up()).getBlock() == this);
        boolean seaweedBelow = (worldIn.getBlockState(pos.down()).getBlock() == this);
        SeaweedPosition position;
        if (seaweedAbove && seaweedBelow) {position = SeaweedPosition.MIDDLE;}
        else if (seaweedAbove) {position = SeaweedPosition.BOTTOM;}
        else if (seaweedBelow) {position = SeaweedPosition.TOP;}
        else {position = SeaweedPosition.SINGLE;}
        return state.withProperty(POSITION, position);
    }
    
    // map from state to meta and vice verca - note the LEVEL and POSITION properties are ignored
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, SeaweedType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((SeaweedType) state.getValue(VARIANT)).ordinal();
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.4F, 0.8F, pos);
    }
    
    
    // require water or seaweed above and earth or seaweed below
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {

        IBlockState stateAbove = world.getBlockState(pos.up());
        Block blockAbove = stateAbove.getBlock();
        IBlockState stateBelow = world.getBlockState(pos.down());
        Block blockBelow = stateBelow.getBlock();
        
        boolean hasWaterAbove = (blockAbove == Blocks.water || blockAbove == Blocks.flowing_water);
        boolean sameSeaweedAbove = ( (blockAbove == this) && ((SeaweedType)state.getValue(VARIANT) == (SeaweedType)stateAbove.getValue(VARIANT)) );
        boolean hasEarthBelow = (blockBelow == Blocks.dirt || blockBelow == BOPBlocks.dirt || blockBelow == BOPBlocks.mud || blockBelow == Blocks.sand || blockBelow == Blocks.sponge || blockBelow == Blocks.stone || blockBelow == Blocks.clay || blockBelow == Blocks.gravel);
        boolean sameSeaweedBelow = ( (blockBelow == this) && ((SeaweedType)state.getValue(VARIANT) == (SeaweedType)stateBelow.getValue(VARIANT)) );
        
        return (hasWaterAbove || sameSeaweedAbove) && (hasEarthBelow || sameSeaweedBelow);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
    {
        world.setBlockState(pos, Blocks.water.getDefaultState() );
    }

  
    
}