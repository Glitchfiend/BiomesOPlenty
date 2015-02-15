/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPPlant;
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

public class BlockBOPMushroom extends BOPPlant
{
    public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", MushroomType.class);

    public BlockBOPMushroom()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, MushroomType.TOADSTOOL));
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        IBlockState blockState = world.getBlockState(pos);

        if ((MushroomType) blockState.getValue(VARIANT_PROP) == MushroomType.GLOWSHROOM)
        {
            return 6;
        }

        return super.getLightValue();
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block ground = world.getBlockState(pos.down()).getBlock();
        MushroomType type = (MushroomType) state.getValue(VARIANT_PROP);

        switch (type)
        {
        // TODO: Make the toadstool, glowshroom, flat mushroom require overgrown
        // netherrack
        // TODO: Make flat mushroom, shadow shroom require bopgrass
            case TOADSTOOL:
                return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.mycelium || ground == Blocks.grass;

            case GLOWSHROOM:
                return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.mycelium || ground == Blocks.stone || ground == Blocks.netherrack;

            case FLAT_MUSHROOM:
                return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.mycelium;

            case SHADOW_SHROOM:
                return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.mycelium || ground == Blocks.end_stone;

            default:
                return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.mycelium;
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT_PROP, MushroomType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = ((MushroomType) state.getValue(VARIANT_PROP)).ordinal();

        return meta;
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
        return ((MushroomType) state.getValue(VARIANT_PROP)).getName();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
    }

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
            return getName();
        }

    }
}
