package biomesoplenty.fabric.mixin;

import biomesoplenty.glitch.util.BlockHelper;
import java.lang.reflect.Field;
import java.util.Map;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Predicate;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockHelper.class, remap = false)
public class MixinBlockHelper {

  @Overwrite
  public static void registerTillable(Block input, Predicate<UseOnContext> usePredicate,
      BlockState tilled) {
    TillableBlockRegistry.register(input, usePredicate, tilled);
  }
}