/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: where have lily flowers and lilyofthevalley gone?
// TODO implement as paged block
public class BlockBOPFlower2 extends BlockDecoration {
        
    // add properties
    public static enum FlowerType implements IStringSerializable
    {
        LAVENDER, GOLDENROD, BLUEBELLS, MINERS_DELIGHT, ICY_IRIS, ROSE;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FlowerType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((FlowerType) state.getValue(VARIANT)).getName();
    }
    
    
    public BlockBOPFlower2()
    {
        super();
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, FlowerType.LAVENDER) );        
    }
    
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, FlowerType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((FlowerType) state.getValue(VARIANT)).ordinal();
    }
    
    // set the size of the different flowers' bounding boxes
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        switch ((FlowerType) world.getBlockState(pos).getValue(VARIANT))
        {
            default:
                this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
                break;
        }
    }
    
 
    
    // which types of flower can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();
        
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == Blocks.farmland || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.grass);
        boolean onStone = (groundBlock == Blocks.stone);
        boolean onOrigin = false;
        
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
                case SPECTRAL_MOSS: case SMOLDERING:
                    break;
                case LOAMY: case SANDY: case SILTY: case OVERGROWN_NETHERRACK: case ORIGIN: default:
                    onFertile = true;
                    break;
            }
        }
        
        switch ((FlowerType) state.getValue(VARIANT))
        {        
            case MINERS_DELIGHT:
                return onStone;
            case ROSE:
                return onFertile || onOrigin;
            default:
                return onFertile;
        }
    }
    
    
    

    
}