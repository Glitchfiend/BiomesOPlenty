/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBOPStone extends BOPBlock
{
    public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", StoneType.class);
    public static PropertyBool POLISHED_PROP = PropertyBool.create("polished");

    public BlockBOPStone()
    {
        super(Material.rock);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, StoneType.LIMESTONE).withProperty(POLISHED_PROP, Boolean.valueOf(false)));

        this.setHarvestLevel("pickaxe", 1, this.getDefaultState().withProperty(VARIANT_PROP, StoneType.LIMESTONE));
        this.setHarvestLevel("pickaxe", 2, this.getDefaultState().withProperty(VARIANT_PROP, StoneType.SILTSTONE));
        this.setHarvestLevel("pickaxe", 3, this.getDefaultState().withProperty(VARIANT_PROP, StoneType.SHALE));
    }

    @Override
    public float getBlockHardness(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() == this)
        {
            boolean polished = (Boolean) state.getValue(POLISHED_PROP);

            if (polished)
                return 1.5F;
            return 3.0F;
        }

        return super.getBlockHardness(world, pos);
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() == this)
        {
            boolean polished = (Boolean) state.getValue(POLISHED_PROP);

            if (polished)
                return 7.0F;
            return 5.0F;
        }

        return super.getExplosionResistance(world, pos, exploder, explosion);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        boolean polished = (meta & 8) > 0;
        int type = meta & 3;

        return this.getDefaultState().withProperty(VARIANT_PROP, StoneType.values()[type]).withProperty(POLISHED_PROP, Boolean.valueOf(polished));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int baseMeta = ((StoneType) state.getValue(VARIANT_PROP)).ordinal();
        boolean polished = (Boolean) state.getValue(POLISHED_PROP);

        return polished ? baseMeta | 8 : baseMeta;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP, POLISHED_PROP });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { VARIANT_PROP, POLISHED_PROP };
    }

    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        boolean polished = (Boolean) state.getValue(POLISHED_PROP);

        return (fullName && polished ? "polished_" : "") + ((StoneType) state.getValue(VARIANT_PROP)).getName();
    }

    public static enum StoneType implements IStringSerializable
    {
        LIMESTONE, SILTSTONE, SHALE;

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
