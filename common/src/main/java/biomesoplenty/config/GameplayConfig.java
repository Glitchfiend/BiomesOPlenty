/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.config;

import biomesoplenty.core.BiomesOPlenty;
import glitchcore.config.Config;
import glitchcore.util.Environment;

public class GameplayConfig extends Config
{
    public GameplayConfig()
    {
        super(Environment.getConfigPath().resolve(BiomesOPlenty.MOD_ID + "/gameplay.toml"));
    }

    @Override
    public void load()
    {

    }
}
