/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static net.minecraft.block.BlockLiquid.LEVEL;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.api.block.BOPPlant;

public class BlockCoral extends BOPPlant
{
    public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", CoralType.class);

    public BlockCoral()
    {
        super(Material.water);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, CoralType.PINK));

        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        if ((CoralType) state.getValue(VARIANT_PROP) == CoralType.GLOWING)
        {
            return 10;
        }

        return super.getLightValue();
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block ground = world.getBlockState(pos.down()).getBlock();
        Block cover = world.getBlockState(pos.up()).getBlock();
        boolean hasWater = cover == Blocks.water || cover == Blocks.flowing_water;

        // TODO: Make all types depend on mud
        return hasWater && (ground == Blocks.dirt || ground == Blocks.sand || ground == Blocks.sponge || ground == Blocks.stone || ground == Blocks.clay || ground == Blocks.gravel || ground == Blocks.grass);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT_PROP, CoralType.values()[meta]).withProperty(LEVEL, 15);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = ((CoralType) state.getValue(VARIANT_PROP)).ordinal();

        return meta;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP, LEVEL });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { VARIANT_PROP };
    }

    @Override
    public IProperty[] getHiddenProperties()
    {
        return new IProperty[] { LEVEL };
    }

    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        CoralType type = (CoralType) state.getValue(VARIANT_PROP);

        return type.getName() + (fullName && type != CoralType.ALGAE ? "_coral" : "");
    }

    // TODO: Readd kelp
    public static enum CoralType implements IStringSerializable
    {
        PINK, ORANGE, BLUE, GLOWING, ALGAE;

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
