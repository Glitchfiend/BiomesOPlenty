package biomesoplenty.core;

import biomesoplenty.core.Event;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public final class RegistryEvent extends Event
{
  @NotNull
  protected final ResourceKey<? extends Registry<?>> registryKey;
  @NotNull
  private final BiConsumer<ResourceLocation, ?> doRegister;

  public RegistryEvent(ResourceKey<? extends Registry<?>> registryKey, BiConsumer<ResourceLocation, ?> doRegister)
  {
    this.registryKey = registryKey;
    this.doRegister = doRegister;
  }

  public <T> T register(ResourceLocation location, T value)
  {
    ((BiConsumer<ResourceLocation, T>)this.doRegister).accept(location, value);
    return value;
  }

  public ResourceKey<? extends Registry<?>> getRegistryKey()
  {
    return this.registryKey;
  }
}
