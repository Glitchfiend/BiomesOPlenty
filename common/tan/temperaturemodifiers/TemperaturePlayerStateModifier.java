package tan.temperaturemodifiers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import tan.api.temperature.ITemperatureModifier;

public class TemperaturePlayerStateModifier implements ITemperatureModifier
{
    @Override
    public float modifyTemperature(World world, EntityPlayerMP player)
    {
        float modifier = 0F;
        
        if (player.isSprinting()) modifier += 2.25F;
        if (player.isWet()) modifier -= 2.5F;
        if (player.isBurning()) modifier += 4F;
        
        return modifier;
    }
}
