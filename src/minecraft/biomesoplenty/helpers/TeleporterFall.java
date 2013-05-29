package biomesoplenty.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterFall extends Teleporter
{
    public TeleporterFall(WorldServer par1WorldServer)
    {
        super(par1WorldServer);
    }

    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        par1Entity.setLocationAndAngles(par2, 256.0, par6, par1Entity.rotationYaw, 0.0F);
        par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
    }
}
