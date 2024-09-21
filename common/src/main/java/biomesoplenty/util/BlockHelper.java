package biomesoplenty.util;

import com.google.common.collect.Maps;
import java.lang.reflect.Field;
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
  private static Map<Block, Block> STRIPPABLES;
  private static Map<Block, BlockState> FLATTENABLES;
  static {
    try {
      Field strippablesField = AxeItem.class.getDeclaredField("STRIPPABLES");
      strippablesField.setAccessible(true);
      STRIPPABLES = (Map<Block, Block>) strippablesField.get(null);

      Field flattenablesField = ShovelItem.class.getDeclaredField("FLATTENABLES");
      flattenablesField.setAccessible(true);
      FLATTENABLES = (Map<Block, BlockState>) flattenablesField.get(null);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  public static void registerStrippable(Block log, Block stripped)
  {
    STRIPPABLES = Maps.newHashMap(STRIPPABLES);
    STRIPPABLES.put(log, stripped);
  }

  public static void registerFlattenable(Block block, BlockState flattened)
  {
    FLATTENABLES = Maps.newHashMap(FLATTENABLES);
    FLATTENABLES.put(block, flattened);
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
