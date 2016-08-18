package biomesoplenty.common.handler;

import biomesoplenty.common.entities.ai.EntityAIEatBOPGrass;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

public class SheepEventHandler
{
    @SubscribeEvent
    public void onEntityConstructEvent(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntitySheep)
        {
            EntitySheep sheep = (EntitySheep)event.getEntity();
            Field eatGrass = ReflectionHelper.findField(EntitySheep.class, new String[]{"entityAIEatGrass", "field_146087_bs"});
            try
            {
                eatGrass.set(sheep, new EntityAIEatBOPGrass(sheep));
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
