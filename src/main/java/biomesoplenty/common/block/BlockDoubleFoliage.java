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
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoubleFoliage extends BlockDoubleDecoration implements IShearable
{
    
    // add properties (note we inherit HALF from BlockDoubleDecoration)
    public static enum FoliageType implements IStringSerializable {FLAX; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FoliageType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { HALF, VARIANT });}
    
    public BlockDoubleFoliage()
    {
        super();
        
        // define named states
        for(FoliageType foliageType : FoliageType.values())
        {
            this.namedStates.put(foliageType.getName(), this.blockState.getBaseState().withProperty(HALF, Half.LOWER) .withProperty(VARIANT, foliageType));
        }
        this.setDefaultState(this.getNamedState("flax"));
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
    
    
    
    // TODO: comment these
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return this.getBlockColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        switch ((FoliageType) worldIn.getBlockState(pos).getValue(VARIANT))
        {
            default:
                return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
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
        switch ((FoliageType) lowerState.getValue(VARIANT))
        {       
            default:
                // default is to get the (lower) block unaltered
                ret.add(new ItemStack(this, 1, this.getMetaFromState(lowerState.withProperty(HALF, Half.LOWER) )));
        }
        return ret;
    }

    
}