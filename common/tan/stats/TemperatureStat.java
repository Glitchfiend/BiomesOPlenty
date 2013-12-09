package tan.stats;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tan.api.TANStat;

public class TemperatureStat extends TANStat
{
    @Override
    public void update()
    {
        float originalTemperature = tanData.getFloat(getStatName());
        float temperature = originalTemperature;
        
        if (world.rand.nextInt(25) == 0)
        {
            temperature--;
        }
        
        if (temperature != originalTemperature)
        {
            tanData.setFloat(getStatName(), MathHelper.clamp_float(temperature, -50F, 50F));

            updatePlayerData(tanData, player);
        }
    }

    @Override
    public void setDefaults()
    {
        setDefaultFloat(getStatName(), 37F);
    }

    @Override
    public String getStatName()
    {
        return "Temp";
    }
}
