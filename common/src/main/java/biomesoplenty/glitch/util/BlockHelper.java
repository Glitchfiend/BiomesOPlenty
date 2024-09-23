package biomesoplenty.glitch.util;

import com.google.common.collect.Maps;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import java.util.function.Predicate;

public class BlockHelper
{
  public static void registerStrippable(Block log, Block stripped)
  {
    AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
    AxeItem.STRIPPABLES.put(log, stripped);
  }
  public static void registerFlattenable(Block block, BlockState flattened)
  {
    ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
    ShovelItem.FLATTENABLES.put(block, flattened);
  }
  public static void registerCompostable(float chance, ItemLike item)
  {
    ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
  }
  public static void registerFlammable(Block block, int encouragement, int flammability)
  {
    FireBlock fireblock = (FireBlock) Blocks.FIRE;
    fireblock.setFlammable(block, encouragement, flammability);
  }
  public static void registerTillable(Block input, Predicate<UseOnContext> usePredicate, BlockState tilled)
  {
    throw new UnsupportedOperationException();
  }
}