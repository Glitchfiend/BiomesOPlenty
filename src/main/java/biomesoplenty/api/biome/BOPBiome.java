/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class BOPBiome extends BiomeGenBase implements IExtendedBiome
{
    private GenerationManager generationManager = new GenerationManager();
    private Map<BiomeType, Integer> weightMap = new HashMap<BiomeType, Integer>();
    
    // defaults
    public int skyColor = -1; // -1 indicates the default skyColor by temperature will be used
    public boolean hasBiomeEssence = true;
    
    public BOPBiome()
    {
        super(-1, false);

        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        this.theBiomeDecorator.clayPerChunk = -999;
    }
    
    public void configure(IConfigObj conf)
    {
        
        // Allow name to be overridden
        this.biomeName = conf.getString("biomeName",this.biomeName);
        
        // Allow basic properties to be overridden
        this.topBlock = conf.getBlockState("topBlock", this.topBlock);
        this.fillerBlock = conf.getBlockState("fillerBlock", this.fillerBlock);
        this.minHeight = conf.getFloat("rootHeight", this.minHeight);
        this.maxHeight = conf.getFloat("variation", this.maxHeight);
        this.temperature = conf.getFloat("temperature", this.temperature);
        this.rainfall = conf.getFloat("rainfall", this.rainfall);
        this.color = conf.getInt("color",this.color);
        this.waterColorMultiplier = conf.getInt("waterColorMultiplier", this.waterColorMultiplier);
        this.enableRain = conf.getBool("enableRain", this.enableRain);
        this.enableSnow = conf.getBool("enableSnow", this.enableSnow);
        this.skyColor = conf.getInt("skyColor", this.skyColor);
        this.hasBiomeEssence = conf.getBool("hasBiomeEssence", this.hasBiomeEssence);
        
        // Allow weights to be overridden
        IConfigObj confWeights = conf.getObject("weights");
        if (confWeights != null)
        {
            for (BiomeType type : BiomeType.values())
            {
                Integer weight = confWeights.getInt(type.name().toLowerCase(), null);
                if (weight == null) {continue;}
                if (weight.intValue() == 0)
                {
                    this.weightMap.remove(type);
                }
                else
                {
                    this.weightMap.put(type, weight);
                }
            }
        }
        
        // Allow generators to be configured
        IConfigObj confGenerators = conf.getObject("generators");
        if (confGenerators != null)
        {
            for (String name : confGenerators.getKeys())
            {
                this.generationManager.configureWith(name, confGenerators.getObject(name));
            }
        }
        
        // Allow spawnable entites to be configured
        ArrayList<IConfigObj> confEntities = conf.getObjectArray("entities");
        if (confEntities != null)
        {
            for (IConfigObj confEntity : confEntities)
            {
                String entityName = confEntity.getString("name");
                EnumCreatureType creatureType = confEntity.getEnum("creatureType", EnumCreatureType.class);
                if (entityName == null || creatureType == null) {continue;}
                
                // Look for an entity class matching this name
                // case insensitive, dot used as mod delimiter, no spaces or underscores
                // eg  'villager', 'Zombie', 'SQUID', 'enderdragon', 'biomesoplenty.wasp' all ok
                Class <? extends Entity > entityClazz = null;
                for (Object entry : EntityList.stringToClassMapping.entrySet())
                {
                    String entryEntityName = (String)((Entry)entry).getKey();
                    if (entryEntityName.equalsIgnoreCase(entityName))
                    {
                        entityClazz = (Class <? extends Entity >)((Entry)entry).getValue();
                    }
                }
                if (entityClazz == null)
                {
                    confEntity.addMessage("No entity registered called " + entityName);
                    continue;
                }
                if (!creatureType.getCreatureClass().isAssignableFrom(entityClazz))
                {
                    confEntity.addMessage("Entity " + entityName + " is not of type " + creatureType);
                    continue;
                }
                
                List<SpawnListEntry> spawns = this.getSpawnableList(creatureType);
                Integer weight = confEntity.getInt("weight");
                if (weight != null && weight < 1)
                {
                    // weight was set to zero (or negative) so find and remove this spawn
                    Iterator<SpawnListEntry> spawnIterator = spawns.iterator();
                    while (spawnIterator.hasNext())
                    {
                        SpawnListEntry entry = spawnIterator.next();
                        if (entry.entityClass == entityClazz)
                        {
                            spawnIterator.remove();
                        }
                    }
                }
                else
                {
                    // weight was positive, or omitted, so update an existing spawn or add a new spawn
                    boolean foundIt = false;
                    for (SpawnListEntry entry : spawns)
                    {
                        if (entry.entityClass == entityClazz)
                        {
                            // the entry already exists - adjust the params
                            entry.itemWeight = confEntity.getInt("weight", entry.itemWeight);
                            entry.minGroupCount = confEntity.getInt("minGroupCount", entry.minGroupCount);
                            entry.maxGroupCount = confEntity.getInt("maxGroupCount", entry.maxGroupCount);
                            foundIt = true;
                        }
                    }
                    if (!foundIt)
                    {
                        // the entry does not exist - add it
                        SpawnListEntry entry = new SpawnListEntry(entityClazz, confEntity.getInt("weight", 10), confEntity.getInt("minGroupCount", 4), confEntity.getInt("maxGroupCount", 4));
                        spawns.add(entry);
                    }
                }
            }
        }
        
    }
    

    @Override
    public BiomeOwner getBiomeOwner()
    {
        return BiomeOwner.BIOMESOPLENTY;
    }

    @Override
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator)
    {
        this.generationManager.addGenerator(name, stage, generator);
    }
    
    @Override
    public GenerationManager getGenerationManager()
    {
        return this.generationManager;
    }

    @Override
    public Map<BiomeType, Integer> getWeightMap()
    {
        return this.weightMap;
    }

    @Override
    public void addWeight(BiomeType type, int weight)
    {
        this.weightMap.put(type, weight);
    }
    
    @Override
    public void clearWeights()
    {
        this.weightMap.clear();
    }
    
    // whether or not a biome essence item corresponding to this biome should be able to drop from biome blocks
    public boolean hasBiomeEssence()
    {
        return this.hasBiomeEssence;
    }
    
    @Override
    public int getSkyColorByTemp(float temperature)
    {
        return (this.skyColor == -1) ? super.getSkyColorByTemp(temperature) : this.skyColor;
    }
}
