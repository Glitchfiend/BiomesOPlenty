/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.core;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.fabric.init.ModClientFabric;
import biomesoplenty.init.ModClient;
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
        ModClient.setup();
        ModClientFabric.setup();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomesOPlenty.setupTerraBlender();
    }
}