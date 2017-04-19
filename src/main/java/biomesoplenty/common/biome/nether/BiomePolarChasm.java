/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BiomePolarChasm extends BOPHellBiome
{
    public BiomePolarChasm()
    {
        super("polar_chasm", new PropsBuilder("Polar Chasm").withGuiColour(0xA93C3E));

        this.addWeight(BOPClimates.HELL, 1);

        this.fogColor = 0x6989AD;
        this.fogDensity = 0.99F;

        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.PACKED_ICE.getDefaultState();
        this.roofTopBlock = Blocks.SNOW.getDefaultState();
        this.roofFillerBlock = Blocks.PACKED_ICE.getDefaultState();
        this.wallBlock = BOPBlocks.hard_ice.getDefaultState();
    }
}
