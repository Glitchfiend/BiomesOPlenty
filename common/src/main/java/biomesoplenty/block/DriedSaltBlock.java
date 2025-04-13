/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.sounds.AmbientDesertBlockSoundsPlayer;
import net.minecraft.world.level.block.state.BlockState;

public class DriedSaltBlock extends Block
{
    public DriedSaltBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public void animateTick(BlockState p_393155_, Level p_394075_, BlockPos p_394564_, RandomSource p_392109_) {
        AmbientDesertBlockSoundsPlayer.playAmbientBlockSounds(p_393155_, p_394075_, p_394564_, p_392109_);
    }
}
