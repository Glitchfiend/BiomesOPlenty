package biomesoplenty.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import biomesoplenty.api.Items;
import biomesoplenty.mobs.ai.EntityAITemptArmour;

public class EntitiesHelper
{
    @ForgeSubscribe
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        Entity entity = event.entity;
        
        if (!(entity instanceof EntityLiving))
            return;
        
        if (entity instanceof EntityChicken)
            ((EntityLiving)entity).tasks.addTask(3, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 0, false));
        
        if (entity instanceof EntitySheep)
            ((EntityLiving)entity).tasks.addTask(3, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 1, false));
        
        if (entity instanceof EntityPig)
            ((EntityLiving)entity).tasks.addTask(4, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 2, false));
        
        if (entity instanceof EntityCow)
            ((EntityLiving)entity).tasks.addTask(3, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 3, false));
    }
}
