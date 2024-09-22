/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.config;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.glitch.config.Config;
import biomesoplenty.glitch.util.Environment;

public class GameplayConfig extends Config
{
    public boolean wanderingTraderTrades;

    public GameplayConfig()
    {
        super(Environment.getConfigPath().resolve(BiomesOPlenty.MOD_ID + "/gameplay.toml"));
    }

    @Override
    public void load()
    {
        wanderingTraderTrades = add("general.wandering_trader_trades", true, "Add various BOP resources to the Wandering Trader trade pool.");
    }
}
