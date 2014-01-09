package biomesoplenty.common.eventhandler.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.entities.ai.EntityAITemptArmor;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TemptEventHandler 
{
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		Entity entity = event.entity;

		if (!(entity instanceof EntityLiving))
			return;

		//TODO: FEATURE Remove Reflection
		EntityAITasks tasks = ObfuscationReflectionHelper.getPrivateValue(EntityLiving.class, ((EntityLiving)entity), new String[] { "tasks" });
		
		if (entity instanceof EntityChicken) 
		{
			
			tasks.addTask(3, new EntityAITemptArmor((EntityCreature)entity, 1F, BOPItemHelper.get("flowerBand"), 0, false));
		}

		if (entity instanceof EntitySheep) 
		{
			tasks.addTask(3, new EntityAITemptArmor((EntityCreature)entity, 1F, BOPItemHelper.get("flowerBand"), 1, false));
		}

		if (entity instanceof EntityPig) 
		{
			tasks.addTask(4, new EntityAITemptArmor((EntityCreature)entity, 1F, BOPItemHelper.get("flowerBand"), 2, false));
		}

		if (entity instanceof EntityCow) 
		{
			tasks.addTask(3, new EntityAITemptArmor((EntityCreature)entity, 1F, BOPItemHelper.get("flowerBand"), 3, false));
		}
	}
}
