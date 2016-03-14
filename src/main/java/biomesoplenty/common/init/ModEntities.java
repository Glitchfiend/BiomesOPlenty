/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import java.util.Map;

import com.google.common.collect.Maps;

import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.entities.EntityPixie;
import biomesoplenty.common.entities.EntitySnail;
import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.common.entities.projectiles.EntityMudball;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;

public class ModEntities
{
    
    public static final Map<Integer, EntityEggInfo> entityEggs = Maps.<Integer, EntityEggInfo>newLinkedHashMap();
    public static final Map<Integer, String> idToBOPEntityName = Maps.<Integer, String>newLinkedHashMap();

    private static int nextBOPEntityId = 1;
    
    public static void init()
    {
        // projectiles
        registerBOPEntity(EntityDart.class, "dart", 80, 3, true);
        registerBOPEntity(EntityMudball.class, "mudball", 80, 3, true);

        // mobs
        registerBOPEntityWithSpawnEgg(EntityWasp.class, "wasp", 80, 3, true, 0xE5B013, 0x333234);
        registerBOPEntityWithSpawnEgg(EntityPixie.class, "pixie", 80, 3, true, 0xFF99E9, 0xFFFFFF);     
        registerBOPEntityWithSpawnEgg(EntitySnail.class, "snail", 80, 3, true, 0xA694BC, 0xCDA26E);
        registerBOPEntityWithSpawnEgg(EntityButterfly.class, "butterfly", 80, 3, true, 0x282828, 0xEF6F1F);
    }
    
    // register an entity
    public static int registerBOPEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        int bopEntityId = nextBOPEntityId;
        nextBOPEntityId++;
        EntityRegistry.registerModEntity(entityClass, entityName, bopEntityId, BiomesOPlenty.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        idToBOPEntityName.put(bopEntityId, entityName);
        BOPCommand.entityCount++;
        return bopEntityId;
    }
    
    // register an entity and in addition create a spawn egg for it
    public static int registerBOPEntityWithSpawnEgg(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggBackgroundColor, int eggForegroundColor)
    {
        int bopEntityId = registerBOPEntity(entityClass, entityName, trackingRange, updateFrequency, sendsVelocityUpdates);
        entityEggs.put(Integer.valueOf(bopEntityId), new EntityList.EntityEggInfo(entityName, eggBackgroundColor, eggForegroundColor));
        return bopEntityId;
    }
    
    
    public static Entity createEntityByID(int bopEntityId, World worldIn)
    {
        Entity entity = null;
        ModContainer mc = FMLCommonHandler.instance().findContainerFor(BiomesOPlenty.instance);
        EntityRegistration er = EntityRegistry.instance().lookupModSpawn(mc, bopEntityId);
        if (er != null)
        {
            Class<? extends Entity> clazz = er.getEntityClass();
            try
            {
                if (clazz != null)
                {
                    entity = (Entity)clazz.getConstructor(new Class[] {World.class}).newInstance(new Object[] {worldIn});
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }            
        }
        if (entity == null)
        {
            BiomesOPlenty.logger.warn("Skipping BOP Entity with id " + bopEntityId);
        }        
        return entity;
    }
    
    
}