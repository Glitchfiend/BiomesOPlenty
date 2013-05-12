package biomesoplenty.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.HasResult;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingPackSizeEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import biomesoplenty.api.Blocks;
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
    
    @ForgeSubscribe
    public void canEntitySpawn(LivingSpawnEvent event)
    {
    	
        int i = MathHelper.floor_double(event.entity.posX);
        int j = MathHelper.floor_double(event.entity.boundingBox.minY);
        int k = MathHelper.floor_double(event.entity.posZ);
        
        if (event.entity.worldObj.getBlockId(i, j - 1, k) == Blocks.holyGrass.get().blockID && event.entity.worldObj.getFullBlockLightValue(i, j, k) > 8)
        	event.setResult(Result.ALLOW);
    }
}
