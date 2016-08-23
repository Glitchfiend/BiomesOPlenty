package biomesoplenty.common.handler;

import biomesoplenty.common.entities.ai.EntityAIEatBOPGrass;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SheepEventHandler
{
    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntitySheep)
        {
            EntitySheep sheep = (EntitySheep) event.getEntity();

            sheep.tasks.addTask(5, new EntityAIEatBOPGrass(sheep));
        }
    }
}