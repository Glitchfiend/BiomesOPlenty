/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer0;

public enum LandLayer implements AreaTransformer0
{
    INSTANCE;

    public int applyPixel(Context random, int x, int z) {
        return 1;
    }
}
