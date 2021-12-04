/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.material;

import biomesoplenty.common.worldgen.BOPNoiseChunk;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.material.WorldGenMaterialRule;

import javax.annotation.Nullable;
import java.util.List;

public class BOPMaterialRuleList implements BOPWorldGenMaterialRule
{
    private final List<BOPWorldGenMaterialRule> materialRuleList;

    public BOPMaterialRuleList(List<BOPWorldGenMaterialRule> p_191547_)
    {
        this.materialRuleList = p_191547_;
    }

    @Override
    @Nullable
    public BlockState apply(BOPNoiseChunk chunk, int p_191550_, int p_191551_, int p_191552_)
    {
        for(BOPWorldGenMaterialRule worldgenmaterialrule : this.materialRuleList) {
            BlockState blockstate = worldgenmaterialrule.apply(chunk, p_191550_, p_191551_, p_191552_);
            if (blockstate != null) {
                return blockstate;
            }
        }

        return null;
    }
}
