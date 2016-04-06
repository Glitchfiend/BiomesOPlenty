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
        boolean groundBelow = (worldIn.getBlockState(pos.down()).getMaterial() != Material.air && worldIn.getBlockState(pos.down()).getBlock() != this);
        boolean groundAbove = (worldIn.getBlockState(pos.up()).getMaterial() != Material.air && worldIn.getBlockState(pos.up()).getBlock() != this);
        FormationPosition position;
        if (groundAbove && groundBelow) {position = FormationPosition.STAL_SINGLE;}
        else if (!formationAbove && groundBelow) {position = FormationPosition.STALAGMITE_SMALL;}
        else if (formationAbove && groundBelow) {position = FormationPosition.STALAGMITE_MEDIUM;}
        else if (!formationBelow && groundAbove) {position = FormationPosition.STALACTITE_SMALL;}
        else if (formationBelow && groundAbove) {position = FormationPosition.STALACTITE_MEDIUM;}
        else if (formationAbove && formationBelow) {position = FormationPosition.STAL_CONNECTOR;}
        else if (formationAbove && !formationBelow) {position = FormationPosition.STALACTITE_BOTTOM;}
        else if (formationBelow && !formationAbove) {position = FormationPosition.STALAGMITE_TOP;}
        else {position = FormationPosition.STALAGMITE_SMALL;}
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
    	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);     
    }
    
    // which types of mushroom can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {        
    	return (BlockQueries.sustainsCave.matches(world, pos.down()) || world.getBlockState(pos.down()) == state) || (BlockQueries.sustainsCave.matches(world, pos.up()) || world.getBlockState(pos.up()) == state);
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
