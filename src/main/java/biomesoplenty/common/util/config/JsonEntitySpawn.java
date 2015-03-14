/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class JsonEntitySpawn
{
    public String entityType;
    public String entityClass;
    public int weight;
    public int minGroupCount;
    public int maxGroupCount;

    public static ArrayList<JsonEntitySpawn> getBiomeEntitySpawns(BiomeGenBase biome)
    {
        ArrayList<JsonEntitySpawn> entitySpawns = new ArrayList();

        for (EnumCreatureType creatureType : EnumCreatureType.values())
        {
            List<SpawnListEntry> spawnableList = biome.getSpawnableList(creatureType);

            for (SpawnListEntry spawnListEntry : spawnableList)
            {
                JsonEntitySpawn entitySpawn = new JsonEntitySpawn();

                entitySpawn.entityType = creatureType.toString().toLowerCase();
                entitySpawn.entityClass = spawnListEntry.entityClass.getCanonicalName();
                entitySpawn.weight = spawnListEntry.itemWeight;
                entitySpawn.minGroupCount = spawnListEntry.minGroupCount;
                entitySpawn.maxGroupCount = spawnListEntry.maxGroupCount;

                entitySpawns.add(entitySpawn);
            }
        }

        return entitySpawns;
    }

    public static void addBiomeEntitySpawns(BiomeGenBase biome, JsonBiome jsonBiome)
    {
        for (EnumCreatureType creatureType : EnumCreatureType.values())
        {
            biome.getSpawnableList(creatureType).clear();
        }

        for (JsonEntitySpawn entitySpawn : jsonBiome.entities)
        {
            if (entitySpawn != null)
            {
                try
                {
                    EnumCreatureType creatureType = EnumCreatureType.valueOf(entitySpawn.entityType.toUpperCase());
                    Class entityClass = Class.forName(entitySpawn.entityClass);

                    biome.getSpawnableList(creatureType).add(new BiomeGenBase.SpawnListEntry(entityClass, entitySpawn.weight, entitySpawn.minGroupCount, entitySpawn.maxGroupCount));
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
