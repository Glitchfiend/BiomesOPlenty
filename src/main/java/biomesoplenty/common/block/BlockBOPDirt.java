/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.IStringSerializable;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.block.BOPBlocks;

public class BlockBOPDirt extends BOPBlock
{

    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BOPDirtType.class);
    
    public BlockBOPDirt() {
        super(Material.ground);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, BOPDirtType.LOAMY));
        this.setHardness(0.5F);
        this.setHarvestLevel("shovel", 0);
        this.setStepSound(Block.soundTypeGravel);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map according to integer index in BOPDirtType
        return this.getDefaultState().withProperty(VARIANT_PROP, BOPDirtType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map according to integer index in BOPDirtType
        return ((BOPDirtType) state.getValue(VARIANT_PROP)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { VARIANT_PROP };
    }

    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        return ((BOPDirtType) state.getValue(VARIANT_PROP)).getName();
    }
    
    
    
    // enum representing the variants of grass
    public static enum BOPDirtType implements IStringSerializable
    {
        LOAMY, SANDY, SILTY;

        @Override
        public String getName()
        {
            return this.name().toLowerCase() + "_dirt";
        }

        @Override
        public String toString()
        {
            return getName();
        }
        
        // get the blockstate which corresponds to the type of grass which grows on this dirt
        public IBlockState getGrassBlockState()
        {
            switch(this)
            {   
                case LOAMY:
                    return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT_PROP, BlockBOPGrass.BOPGrassType.LOAMY);
                case SANDY:
                    return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT_PROP, BlockBOPGrass.BOPGrassType.SANDY);
                case SILTY:
                    return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT_PROP, BlockBOPGrass.BOPGrassType.SILTY);
                default:
                    return Blocks.grass.getStateFromMeta(BlockDirt.DirtType.DIRT.getMetadata());
            }
        }
        
        public Block getGrassBlock()
        {
            return this.getGrassBlockState().getBlock();
        }
        
        public int getGrassBlockMeta()
        {
            return this.getGrassBlock().getMetaFromState(this.getGrassBlockState());
        }
    }
    
}
