/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// TODO: mushroom spreading? giant mushrooms with bonemeal? mushrooms popping if too bright?
public class BlockBOPMushroom extends BlockBOPDecoration
{
    // add properties
    public static enum MushroomType implements IStringSerializable
    {
        TOADSTOOL, PORTOBELLO, BLUE_MILK_CAP, GLOWSHROOM, FLAT_MUSHROOM, SHADOW_SHROOM;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", MushroomType.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { VARIANT });}  
 
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((MushroomType) state.getValue(VARIANT)).getName();
    }

    
    public BlockBOPMushroom()
    {
        // set some defaults
        //this.setBlockBoundsByRadiusAndHeight(0.2F, 0.4F);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, MushroomType.TOADSTOOL) );        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, MushroomType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((MushroomType) state.getValue(VARIANT)).ordinal();
    }
    
    // glowshrooms emit light
    @Override
    public int getLightValue(IBlockState state)
    {
        switch ((MushroomType) state.getValue(VARIANT))
        {
            case GLOWSHROOM:
                return 6;
            default:
                return super.getLightValue(state);
        }
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	MushroomType plant = (MushroomType) state.getValue(this.VARIANT);
        switch (plant)
        {
	        case GLOWSHROOM: case SHADOW_SHROOM:
	        	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	        case TOADSTOOL: case FLAT_MUSHROOM:
            	return new AxisAlignedBB(0.20000001788D, 0.0D, 0.20000001788D, 0.79999998211D, 0.6000000238418579D, 0.79999998211D);
	        default:
            	return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
        }        
    }
    
    // which types of mushroom can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {        
        switch ((MushroomType) state.getValue(VARIANT))
        {
            case TOADSTOOL:
                return BlockQueries.fertileOrNetherrack.matches(world, pos.down());
            case GLOWSHROOM:
                return BlockQueries.sustainsCave.matches(world, pos.down());
            case SHADOW_SHROOM:
                return BlockQueries.endish.matches(world, pos.down());
            default:
                return BlockQueries.fertile.matches(world, pos.down());
        }
    }

}
