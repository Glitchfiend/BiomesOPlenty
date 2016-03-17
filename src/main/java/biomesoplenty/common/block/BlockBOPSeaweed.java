/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static net.minecraft.block.BlockLiquid.LEVEL;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
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
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { LEVEL, POSITION, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
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
        this.setSoundType(SoundType.PLANT);
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
    
/*    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.4F, 0.8F, pos);
    }*/
    
    // require water or seaweed above and earth or seaweed below
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return (BlockQueries.fertileSeaBed.matches(world, pos.down()) || world.getBlockState(pos.down()) == state) && (BlockQueries.underwater.matches(world, pos.up()) || world.getBlockState(pos.up()) == state);
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