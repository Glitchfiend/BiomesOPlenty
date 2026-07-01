/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.carver;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;

import java.util.Set;

public class OriginCaveWorldCarver extends CaveWorldCarver
{
    protected Set<Block> replaceableBlocks = ImmutableSet.of(BOPBlocks.ORIGIN_GRASS_BLOCK, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.DYED_TERRACOTTA.white(), Blocks.DYED_TERRACOTTA.orange(), Blocks.DYED_TERRACOTTA.magenta(), Blocks.DYED_TERRACOTTA.lightBlue(), Blocks.DYED_TERRACOTTA.yellow(), Blocks.DYED_TERRACOTTA.lime(), Blocks.DYED_TERRACOTTA.pink(), Blocks.DYED_TERRACOTTA.gray(), Blocks.DYED_TERRACOTTA.lightGray(), Blocks.DYED_TERRACOTTA.cyan(), Blocks.DYED_TERRACOTTA.purple(), Blocks.DYED_TERRACOTTA.blue(), Blocks.DYED_TERRACOTTA.brown(), Blocks.DYED_TERRACOTTA.green(), Blocks.DYED_TERRACOTTA.red(), Blocks.DYED_TERRACOTTA.black(), Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE);

    public OriginCaveWorldCarver(Codec<CaveCarverConfiguration> p_i231917_1_)
    {
        super(p_i231917_1_);
    }

    @Override
    protected boolean canReplaceBlock(CaveCarverConfiguration configuration, BlockState state)
    {
        return this.replaceableBlocks.contains(state.getBlock());
    }
}
