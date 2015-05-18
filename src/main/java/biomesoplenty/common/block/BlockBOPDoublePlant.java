/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;
import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPDoublePlant extends BlockDoubleDecoration implements IShearable
{
    
    // add properties (note we inherit HALF from BlockDoubleDecoration)
    // TODO: rename this
    public static enum FoliageType implements IStringSerializable
    {
        FLAX, TALL_CATTAIL;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FoliageType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { HALF, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((FoliageType) state.getValue(VARIANT)).getName();
    }
    
    
    public BlockBOPDoublePlant()
    {
        super();
        this.setDefaultState( this.blockState.getBaseState().withProperty(HALF, Half.LOWER) .withProperty(VARIANT, FoliageType.FLAX) );
    }
    
    // map from state to meta and vice verca - use highest bit for Half, and lower bits for variant
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HALF, Half.values()[meta >> 3]).withProperty(VARIANT, FoliageType.values()[meta & 7]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Half) state.getValue(HALF)).ordinal() * 8 + ((FoliageType) state.getValue(VARIANT)).ordinal();
    }
    
    
    
    public enum ColoringType {PLAIN, LIKE_LEAVES, LIKE_GRASS};
    
    public static ColoringType getColoringType(FoliageType plant)
    {
        switch (plant)
        {
            case FLAX:
                return ColoringType.LIKE_GRASS;
            default:
                return ColoringType.PLAIN;
        }       
    }
 
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        switch (getColoringType((FoliageType) state.getValue(VARIANT)))
        {
            case LIKE_LEAVES:
                return ColorizerFoliage.getFoliageColorBasic();
            case LIKE_GRASS:
                return ColorizerGrass.getGrassColor(0.5D, 1.0D);
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        switch (getColoringType((FoliageType) worldIn.getBlockState(pos).getValue(VARIANT)))
        {
            case LIKE_LEAVES:
                return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
            case LIKE_GRASS:
                return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }
    
    // flax item should not be tinted, even though the model is
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex)
    {
        switch ((FoliageType) state.getValue(VARIANT))
        {
            case FLAX:
                return 0xFFFFFF;
            default:
                return this.getRenderColor(state);
        }
    }
    
    
    
    // different variants have different sizes
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {    
        switch ((FoliageType) worldIn.getBlockState(pos).getValue(VARIANT))
        {
            default:
                this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
                break;
        }        
    }
    
    
    
    @Override
    public boolean canBlockStay(World world, BlockPos lowerPos, IBlockState state)
    {        
        IBlockState groundState = world.getBlockState(lowerPos.down());
        Block groundBlock = groundState.getBlock();
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.mycelium || groundBlock == Blocks.grass);
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
                case SPECTRAL_MOSS: case SMOLDERING:
                    break;
                case OVERGROWN_NETHERRACK: case LOAMY: case SANDY: case SILTY: case ORIGIN: default:
                    onFertile = true;
                    break;
            }
        }
        return onFertile;
        
    }
    
    
    // get the items dropped when you bash the bush
    @Override
    public List<ItemStack> getLowerDrops(IBlockAccess world, BlockPos lowerPos, IBlockState lowerState, int fortune)
    {        
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT - default is to return nothing (require shears to collect the block)
        switch ((FoliageType) lowerState.getValue(VARIANT))
        {
            case FLAX:
                if (rand.nextInt(8) == 0)
                {
                    // 1 in 8 chance of getting a seed from this grass
                    ret.add(ForgeHooks.getGrassSeed(rand));
                }            
        
            default:
                break;
        }
        return ret;
    }
    
    
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }
    
    @Override
    public List<ItemStack> getLowerShearDrops(ItemStack item, IBlockAccess world, BlockPos lowerPos, IBlockState lowerState, int fortune) {
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        FoliageType type = (FoliageType) lowerState.getValue(VARIANT);
        switch (type)
        {
            default:
                // default is to get the (lower) block unaltered
                ret.add(this.getVariantItem(type));
        }
        return ret;
    }

    public ItemStack getVariantItem(FoliageType type) {
        IBlockState state = this.getDefaultState().withProperty(HALF, Half.LOWER).withProperty(VARIANT, type);
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }
    
}