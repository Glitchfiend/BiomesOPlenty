package biomesoplenty.fabric.mixin;

import biomesoplenty.util.Environment;
import java.nio.file.Path;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Environment.class, remap = false)
public abstract class FabricEnvironmentMixin
{
  @Overwrite
  public static boolean isClient()
  {
    return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
  }

  @Overwrite
  public static Path getConfigPath()
  {
    return FabricLoader.getInstance().getConfigDir();
  }

  @Overwrite
  public static boolean isModLoaded(String id)
  {
    return FabricLoader.getInstance().isModLoaded(id);
  }
}
