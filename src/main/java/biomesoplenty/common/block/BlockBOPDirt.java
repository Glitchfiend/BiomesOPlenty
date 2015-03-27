/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.block.BOPBlocks;

public class BlockBOPDirt extends BOPBlock
{

    public static final PropertyBool COARSE = PropertyBool.create("coarse");
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BOPDirtType.class);
    
    public BlockBOPDirt() {
        super(Material.ground);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(false)).withProperty(VARIANT_PROP, BOPDirtType.LOAMY));
        this.setHardness(0.5F);
        this.setHarvestLevel("shovel", 0);
        this.setStepSound(Block.soundTypeGravel);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // both variant and coarseness saved in meta, first bit coarseness, other bits variant
        return this.getDefaultState().withProperty(COARSE, Boolean.valueOf((meta & 8) > 0)).withProperty(VARIANT_PROP, BOPDirtType.values()[Math.min(2, meta & 7)]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // both variant and coarseness saved in meta, first bit coarseness, other bits variant
        return (Boolean.TRUE.equals(state.getValue(COARSE)) ? 8 : 0) | ((BOPDirtType) state.getValue(VARIANT_PROP)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { COARSE, VARIANT_PROP });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { COARSE, VARIANT_PROP };
    }

    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        return (Boolean.TRUE.equals(state.getValue(COARSE)) ? "coarse_" : "") + ((BOPDirtType) state.getValue(VARIANT_PROP)).getName() + "_dirt";
    }
    
    // enum representing the variants of dirt
    public static enum BOPDirtType implements IStringSerializable
    {
        LOAMY, SANDY, SILTY;

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
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
