/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.init;

import biomesoplenty.forge.common.worldgen.carver.BOPWorldCarvers;
import biomesoplenty.forge.common.worldgen.feature.BOPBaseFeatures;

public class ModFeatures
{
    public static void setup()
    {
        // Most of these setups do nothing and only serve to make sure initialization has happened at the right time

        // Features
        BOPBaseFeatures.setup();

        // Carvers
        BOPWorldCarvers.setup();
    }
}
