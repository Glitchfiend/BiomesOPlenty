/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.entities.EntityPixie;
import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.entities.projectiles.EntityMudball;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;

public class ModEntities
{
    private static int nextBOPEntityId = 1;
    
    public static void init()
    {
        // projectiles
        registerBOPEntity(EntityMudball.class, "mudball", 64, 10, true);

        // mobs
        registerBOPEntityWithSpawnEgg(EntityWasp.class, "wasp", 80, 3, true, 0xE5B013, 0x333234);
        registerBOPEntityWithSpawnEgg(EntityPixie.class, "pixie", 80, 3, true, 0xFF99E9, 0xFFFFFF);
    }
    
    // register an entity
    public static int registerBOPEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        int bopEntityId = nextBOPEntityId;
        nextBOPEntityId++;
        EntityRegistry.registerModEntity(new ResourceLocation(BiomesOPlenty.MOD_ID, entityName), entityClass, entityName, bopEntityId, BiomesOPlenty.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        BOPCommand.entityCount++;
        return bopEntityId;
    }
    
    // register an entity and in addition create a spawn egg for it
    public static int registerBOPEntityWithSpawnEgg(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggBackgroundColor, int eggForegroundColor)
    {
        int bopEntityId = registerBOPEntity(entityClass, entityName, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerEgg(new ResourceLocation(BiomesOPlenty.MOD_ID, entityName), eggBackgroundColor, eggForegroundColor);
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
                    entity = clazz.getConstructor(new Class[] {World.class}).newInstance(new Object[] {worldIn});
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