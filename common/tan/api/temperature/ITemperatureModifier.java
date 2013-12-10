package tan.api.temperature;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public interface ITemperatureModifier
{
    public float modifyTemperature(World world, EntityPlayerMP player);
}
