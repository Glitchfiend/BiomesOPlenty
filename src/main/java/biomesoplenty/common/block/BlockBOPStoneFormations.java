/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockBOPStoneFormations extends BlockBOPDecoration implements IBOPBlock
{
    // add properties
    public static enum FormationType implements IStringSerializable
    {
        STONE_FORMATION;
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
    
    public static enum FormationPosition implements IStringSerializable
    {
    	STALAGMITE_SMALL, STALACTITE_SMALL, STAL_SINGLE, STAL_CONNECTOR, STALAGMITE_MEDIUM, STALACTITE_MEDIUM, STALAGMITE_TOP, STALACTITE_BOTTOM;
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
    
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FormationType.class);
    public static final PropertyEnum POSITION = PropertyEnum.create("position", FormationPosition.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { POSITION, VARIANT });}  
 
    
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
        return ((FormationType) state.getValue(VARIANT)).getName();
    }

    
    public BlockBOPStoneFormations()
    {
    	super(Material.rock);
        // set some defaults
    	this.setHardness(1.0F);
    	this.setSoundType(SoundType.STONE);
        this.setDefaultState( this.blockState.getBaseState().withProperty(POSITION, FormationPosition.STALAGMITE_SMALL).withProperty(VARIANT, FormationType.STONE_FORMATION) );        
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        boolean formationAbove = (worldIn.getBlockState(pos.up()).getBlock() == this);
        boolean formationBelow = (worldIn.getBlockState(pos.down()).getBlock() == this);
        
        boolean groundAbove = (worldIn.getBlockState(pos.up()).getBlock() == Blocks.stone);
        boolean groundBelow = (worldIn.getBlockState(pos.down()).getBlock() == Blocks.stone);
        
        FormationPosition position;
        position = FormationPosition.STALAGMITE_SMALL;
        
        if (groundAbove && groundBelow && !formationAbove && !formationBelow) {position = FormationPosition.STAL_SINGLE;}
        if (!groundAbove && !groundBelow && formationAbove && formationBelow) {position = FormationPosition.STAL_CONNECTOR;}
        if (!groundAbove && groundBelow && !formationAbove && !formationBelow) {position = FormationPosition.STALAGMITE_SMALL;}
        if (!groundAbove && groundBelow && formationAbove && !formationBelow) {position = FormationPosition.STALAGMITE_MEDIUM;}
        if (groundAbove && !groundBelow && !formationAbove && !formationBelow) {position = FormationPosition.STALACTITE_SMALL;}
        if (groundAbove && !groundBelow && !formationAbove && formationBelow) {position = FormationPosition.STALACTITE_MEDIUM;}
        if (!groundAbove && !groundBelow && !formationAbove && formationBelow) {position = FormationPosition.STALAGMITE_TOP;}
        if (!groundAbove && !groundBelow && formationAbove && !formationBelow) {position = FormationPosition.STALACTITE_BOTTOM;}
        
        return state.withProperty(POSITION, position);
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, FormationType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((FormationType) state.getValue(VARIANT)).ordinal();
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 1.0D, 0.8999999761581421D); 
    	/*FormationPosition position = (FormationPosition) state.getValue(this.POSITION);
        switch (position)
        {
	        case STALAGMITE_SMALL: case STALAGMITE_TOP:
	        	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);   
	        case STALACTITE_SMALL: case STALACTITE_BOTTOM:
	        	return new AxisAlignedBB(0.09999999403953552D, 0.199999988079071D, 0.09999999403953552D, 0.8999999761581421D, 1.0D, 0.8999999761581421D);
	        default:
	        	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 1.0D, 0.8999999761581421D);   
        }*/
    }
    
    // which types of mushroom can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {        
    	return world.getBlockState(pos.down()).getBlock() == Blocks.stone || world.getBlockState(pos.down()) == state || world.getBlockState(pos.up()).getBlock() == Blocks.stone || world.getBlockState(pos.up()) == state;
    }
    
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.NONE;
    }

}
