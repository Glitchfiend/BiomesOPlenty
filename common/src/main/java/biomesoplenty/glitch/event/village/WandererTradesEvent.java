/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.glitch.event.village;

import biomesoplenty.glitch.event.Event;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WandererTradesEvent extends Event
{
  protected List<VillagerTrades.ItemListing> generic;
  protected List<VillagerTrades.ItemListing> rare;

  public WandererTradesEvent()
  {
    this.generic = new ArrayList<>();
    this.rare = new ArrayList<>();
  }

  public void addGenericTrade(VillagerTrades.ItemListing... trades)
  {
    Collections.addAll(this.generic, trades);
  }

  public void addGenericTrades(List<VillagerTrades.ItemListing> trades)
  {
    this.generic.addAll(trades);
  }

  public void addRareTrade(VillagerTrades.ItemListing... trades)
  {
    Collections.addAll(this.rare, trades);
  }

  public void addRareTrades(List<VillagerTrades.ItemListing> trades)
  {
    this.rare.addAll(trades);
  }

  public ImmutableList<VillagerTrades.ItemListing> getGenericTrades()
  {
    return ImmutableList.copyOf(this.generic);
  }

  public ImmutableList<VillagerTrades.ItemListing> getRareTrades()
  {
    return ImmutableList.copyOf(this.rare);
  }
}
