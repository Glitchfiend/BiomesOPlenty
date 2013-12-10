package tan.temperaturemodifiers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import tan.api.temperature.ITemperatureModifier;

public class TemperatureTimeModifier implements ITemperatureModifier
{
    @Override
    public float modifyTemperature(World world, EntityPlayerMP player)
    {
        if (isNight(world)) return -2F;
        
        return 0F;
    }
    
    public boolean isDay(World world)
    {
        float celestialAngle = world.getCelestialAngle(0.0F);
        
        if (celestialAngle >= 0.75F && celestialAngle <= 1.0F || celestialAngle >= 0.0F && celestialAngle <= 0.25F) return true;
        
        return false;
    }
    
    public boolean isNight(World world)
    {
        float celestialAngle = world.getCelestialAngle(0.0F);

        if (celestialAngle >= 0.25F && celestialAngle <= 0.75F) return true;
        
        return false;
    }
}
