/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static biomesoplenty.common.block.BlockGem.VARIANT_PROP;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.common.block.BlockGem.GemType;
import biomesoplenty.common.util.block.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

public class BlockGemOre extends BOPBlock
{
    public BlockGemOre()
    {
        super(Material.rock);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, GemType.AMETHYST));

        this.presetStates = BlockStateUtils.getValidStatesForProperties(this.getDefaultState(), this.getPresetProperties());

        for (IBlockState state : presetStates)
        {
            this.setHarvestLevel("pickaxe", 2, state);
        }

        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypePiston);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT_PROP, GemType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = ((GemType) state.getValue(VARIANT_PROP)).ordinal();

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
        return ((GemType) state.getValue(VARIANT_PROP)).getName() + (fullName ? "_ore" : "");
    }
}
