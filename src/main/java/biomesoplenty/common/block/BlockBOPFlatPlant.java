/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;
import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.common.block.BlockBOPPlant.ColoringType;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPFlatPlant extends BlockBOPDecoration
{
    // add properties
    public enum PlantType implements IStringSerializable
    {
        LEAFPILE, DEADLEAFPILE;
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

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", PlantType.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, VARIANT);}
 
    
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
        return ((PlantType) state.getValue(VARIANT)).getName();
    }

    
    public BlockBOPFlatPlant()
    {
        // set some defaults
        //this.setBlockBoundsByRadiusAndHeight(0.2F, 0.4F);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, PlantType.LEAFPILE) );        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, PlantType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((PlantType) state.getValue(VARIANT)).ordinal();
    }
    
    public enum ColoringType {PLAIN, LIKE_LEAVES, LIKE_GRASS}

    public static ColoringType getColoringType(PlantType plant)
    {
        switch (plant)
        {
            case LEAFPILE:
            return ColoringType.LIKE_LEAVES;
            default:
                return ColoringType.PLAIN;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor()
    {
        return new IBlockColor()
        {
            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex)
            {
                boolean inWorld = world != null && pos != null;

                switch (getColoringType((PlantType) state.getValue(VARIANT)))
                {
                    case LIKE_LEAVES:
                        return inWorld ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();

                    case LIKE_GRASS:
                        return inWorld ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
                }

                return 0xFFFFFF;
            }
        };
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor()
    {
        return (stack, tintIndex) -> {
            IBlockState state = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

            return BlockBOPFlatPlant.this.getBlockColor().colorMultiplier(state, null, null, tintIndex);
        };
    }
    
    // get the items dropped when you bash the bush
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        PlantType plant = (PlantType) state.getValue(VARIANT);
        switch (plant)
        {
            default:
                break;
        }
        return ret;
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        PlantType plant = (PlantType) state.getValue(VARIANT);
        
        switch (plant)
        {
            default:
                return true;
        }
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	PlantType plant = (PlantType) state.getValue(VARIANT);
        switch (plant)
        {
            case LEAFPILE: case DEADLEAFPILE:
            	return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);
            default:
            	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
        }        
    }

    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        PlantType plant = ((PlantType) state.getValue(VARIANT));
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
      
        switch (plant)
        {
            case LEAFPILE: case DEADLEAFPILE:
                return BlockQueries.solid.matches(world, pos.down());
            default:
                return BlockQueries.litFertile.matches(world, pos.down());            
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.NONE;
    }
}
