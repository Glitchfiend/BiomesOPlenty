/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.api.block.BOPPlant;

public class BlockStoneFormations extends BOPPlant
{
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", StoneFormationType.class);
    
    public BlockStoneFormations()
    {
        super(Material.vine);
        this.setHardness(0.5F);
        this.setStepSound(soundTypePiston);
     }
    
    // bounding box is not full size
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        float radius = 0.3F;
        float height = 0.6F;
        switch ((StoneFormationType) worldIn.getBlockState(pos).getValue(VARIANT_PROP))
        {
            case STALACTITE:
                // against top of block for stalactites
                this.setBlockBounds(0.5F - radius, 1.0F - height, 0.5F - radius, 0.5F + radius, 1.0F, 0.5F + radius);
                break;
            case STALAGMITE:
                // against bottom of block for stalagmites
                this.setBlockBounds(0.5F - radius, 0.0F, 0.5F - radius, 0.5F + radius, height, 0.5F + radius);
                break;
        }
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => STALAGMITE, 1 => STALACTITE]
        return this.getDefaultState().withProperty(VARIANT_PROP, StoneFormationType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => STALAGMITE, 1 => STALACTITE]
        return ((StoneFormationType) state.getValue(VARIANT_PROP)).ordinal();
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
        return ((StoneFormationType) state.getValue(VARIANT_PROP)).getName();
    }
    
    // only allow stalactites hanging from stone, and only allow stalagmites on top of stone
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState touching;
        switch ((StoneFormationType)state.getValue(VARIANT_PROP))
        {
            case STALACTITE:
                touching = world.getBlockState(pos.up());
                break;
            case STALAGMITE: default:
                touching = world.getBlockState(pos.down());
                break;
        }
        return touching.getBlock() == Blocks.stone;
    }
    
    // enum representing the 2 variants of stone formations
    public static enum StoneFormationType implements IStringSerializable
    {
        STALAGMITE, STALACTITE;

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
    }
    
}
