/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.core;

import biomesoplenty.core.BiomesOPlenty;
import glitchcore.fabric.GlitchCoreInitializer;
import terrablender.api.TerraBlenderApi;

public class BiomesOPlentyFabric implements GlitchCoreInitializer, TerraBlenderApi
{
    @Override
    public void onInitialize()
    {
        BiomesOPlenty.init();
    }

    @Override
    public void onInitializeClient()
    {
        BiomesOPlenty.setupClient();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomesOPlenty.setupTerraBlender();
    }
}