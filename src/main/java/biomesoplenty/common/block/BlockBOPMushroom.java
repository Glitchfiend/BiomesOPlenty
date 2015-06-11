/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
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
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}  
 
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
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
        this.setBlockBoundsByRadiusAndHeight(0.2F, 0.4F);       
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
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        switch ((MushroomType) world.getBlockState(pos).getValue(VARIANT))
        {
            case GLOWSHROOM:
                return 6;
            default:
                return super.getLightValue();
        }
    }

    // which types of mushroom can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();
        
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.mycelium || groundBlock == Blocks.grass);
        boolean onNetherrack = (groundBlock == Blocks.netherrack);
        boolean onStone = (groundBlock == Blocks.stone || groundBlock == BOPBlocks.stone); // TODO: hard dirt too? the other edge cases?
        boolean onEndstone = (groundBlock == Blocks.end_stone);
        
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
                case SPECTRAL_MOSS:
                    onEndstone = true;
                    break;
                case SMOLDERING:
                    break;
                case OVERGROWN_NETHERRACK:
                    onFertile = true;
                    onNetherrack = true;
                case LOAMY: case SANDY: case SILTY: case ORIGIN: default:
                    onFertile = true;
                    break;
            }
        }
        
        switch ((MushroomType) state.getValue(VARIANT))
        {
            case TOADSTOOL:
                return onFertile || onNetherrack;
            case GLOWSHROOM:
                return onFertile || onStone || onNetherrack;
            case SHADOW_SHROOM:
                return onFertile || onEndstone; // TODO: should this be allowed on smoldering grass?
            default:
                return onFertile;
        }
    }

}
