package tan.temperaturemodifiers;

import java.util.ArrayList;
import java.util.Collections;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tan.api.temperature.ITemperatureModifier;
import tan.api.temperature.TemperatureRegistry;

public class TemperatureSourceModifier implements ITemperatureModifier
{
    @Override
    public float modifyTemperature(World world, EntityPlayerMP player)
    {
        ArrayList<Float> temperatureModifiers = new ArrayList<Float>();
        
        int x = MathHelper.floor_double(player.posX);
        int y = MathHelper.floor_double(player.posY);
        int z = MathHelper.floor_double(player.posZ);
        
        for (int ix = -2; ix <= 2; ix++)
        {
            for (int iy = -1; iy <= 1; iy++)
            {
                for (int iz = -2; iz <= 2; iz++)
                {
                    int blockID = world.getBlockId(x + ix, y + iy, z + iz);
                    int metadata = world.getBlockMetadata(x + ix, y + iy, z + iz);

                    float temperatureModifier = TemperatureRegistry.getTemperatureSourceModifier(blockID, metadata);

                    temperatureModifiers.add(temperatureModifier);
                }
            }
        }
        
        float total = 0F;
        int divider = 0;
        
        for (float temperatureModifier : temperatureModifiers)
        {
            total += temperatureModifier;
            divider++;
        }
        
        if ((total / divider) > 0)
        {
            return Collections.max(temperatureModifiers);
        }
        else
        {
            return Collections.min(temperatureModifiers);
        }
    }
}
