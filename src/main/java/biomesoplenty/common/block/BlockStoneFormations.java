/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStoneFormations extends BlockDecoration
{
    
    // add properties
    public static enum StoneFormationType implements IStringSerializable {STALACTITE, STALAGMITE; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", StoneFormationType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}

    // constructor
    public BlockStoneFormations() {
                
        // set some defaults
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypePiston);
        
        // define named states
        this.namedStates.put("stalactite", this.blockState.getBaseState().withProperty(VARIANT, StoneFormationType.STALACTITE) );
        this.namedStates.put("stalagmite", this.blockState.getBaseState().withProperty(VARIANT, StoneFormationType.STALAGMITE) );
        
        this.setDefaultState(this.namedStates.get("stalagmite"));
        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, StoneFormationType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((StoneFormationType) state.getValue(VARIANT)).ordinal();
    }
    
    // bounding box is not full size and depends on whether it's a stalagmite or stalactite
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        switch ((StoneFormationType) worldIn.getBlockState(pos).getValue(VARIANT))
        {
            case STALACTITE:
                // against top of block for stalactites
                this.setBlockBoundsByRadiusAndHeight(0.3F, 0.6F, true);
                break;
            case STALAGMITE: default:
                // against bottom of block for stalagmites
                this.setBlockBoundsByRadiusAndHeight(0.3F, 0.6F);
                break;
        }
    }
 
    // only allow stalactites hanging from stone, and only allow stalagmites on top of stone
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState touching;
        switch ((StoneFormationType)state.getValue(VARIANT))
        {
            case STALACTITE:
                touching = world.getBlockState(pos.up());
                break;
            case STALAGMITE: default:
                touching = world.getBlockState(pos.down());
                break;
        }
        return touching.getBlock() == Blocks.stone;
    }
    
}
