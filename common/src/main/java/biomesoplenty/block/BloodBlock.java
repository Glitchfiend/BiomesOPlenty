/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;

public class BloodBlock extends LiquidBlock implements BucketPickup
{
    public BloodBlock(FlowingFluid p_54694_, BlockBehaviour.Properties p_54695_) {
        super(p_54694_, p_54695_);
    }
}
