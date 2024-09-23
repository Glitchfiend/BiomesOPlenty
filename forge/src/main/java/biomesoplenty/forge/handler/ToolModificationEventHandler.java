/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.handler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ToolModificationEventHandler
{
  public static final Multimap<Block, Pair<Predicate<UseOnContext>, BlockState>> tillables = HashMultimap.create();

  @SubscribeEvent
  public static void onToolModification(BlockEvent.BlockToolModificationEvent event)
  {
    BlockState originalState = event.getState();

    if (event.getToolAction() == ToolActions.HOE_TILL && tillables.containsKey(originalState.getBlock()))
    {
      for (var tillable : tillables.get(originalState.getBlock()))
      {
        if (tillable.getFirst().test(event.getContext()))
        {
          event.setFinalState(tillable.getSecond());
          return;
        }
      }
    }
  }
}