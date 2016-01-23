/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
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
import java.util.Random;

import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.TerrainSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeManager;

public class BOPBiome extends BiomeGenBase implements IExtendedBiome
{
    private GenerationManager generationManager = new GenerationManager();
    private Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    
    // defaults
    public int skyColor = -1; // -1 indicates the default skyColor by temperature will be used
    public boolean hasBiomeEssence = true;
    public IBlockState seaFloorBlock = Blocks.dirt.getDefaultState();
    
    public boolean canSpawnInBiome = true;
    public boolean canGenerateVillages = true;
    public boolean canGenerateRivers = true;
    
    public TerrainSettings terrainSettings = new TerrainSettings();
    public boolean noNeighborTerrainInfuence = false;
    public int avgDirtDepth = 3;
    
    public BOPBiome()
    {
        super(-1, false);
        
        this.terrainSettings.setDefaults();
        
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        this.theBiomeDecorator.generateLakes = false;       
        
        // roots
        this.addGenerator("roots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(4.0F).with(BOPPlants.ROOT).create());
    }
    
    public void applySettings(BOPWorldSettings settings)
    {
        
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        
        // Allow name to be overridden
        this.biomeName = conf.getString("biomeName",this.biomeName);
        
        // Allow basic properties to be overridden
        this.topBlock = conf.getBlockState("topBlock", this.topBlock);
        this.fillerBlock = conf.getBlockState("fillerBlock", this.fillerBlock);
        this.seaFloorBlock = conf.getBlockState("seaFloorBlock", this.seaFloorBlock);
        
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
        
        this.canSpawnInBiome = conf.getBool("canSpawnInBiome", this.canSpawnInBiome);
        this.canGenerateVillages = conf.getBool("canGenerateVillages", this.canGenerateVillages);
        this.canGenerateRivers = conf.getBool("canGenerateRivers", this.canGenerateRivers);
        
        // Allow weights to be overridden
        IConfigObj confWeights = conf.getObject("weights");
        if (confWeights != null)
        {
            for (BOPClimates climate : BOPClimates.values())
            {
                Integer weight = confWeights.getInt(climate.name().toLowerCase(), null);
                if (weight == null) {continue;}
                if (weight.intValue() < 1)
                {
                    this.weightMap.remove(climate);
                }
                else
                {
                    this.weightMap.put(climate, weight);
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
                Class <? extends EntityLiving> entityClazz = null;
                for (Object entry : EntityList.stringToClassMapping.entrySet())
                {
                    String entryEntityName = (String)((Entry)entry).getKey();
                    if (entryEntityName.equalsIgnoreCase(entityName))
                    {
                        entityClazz = (Class <? extends EntityLiving>)((Entry)entry).getValue();
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
    
    public void removeGenerator(String name)
    {
        this.generationManager.removeGenerator(name);
    }
    
    @Override
    public GenerationManager getGenerationManager()
    {
        return this.generationManager;
    }

    @Override
    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }

    @Override
    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
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
    
    @Override
    public BiomeGenBase setTemperatureRainfall(float temp, float rain)
    {
        this.temperature = temp;
        this.rainfall = rain;
        return this;
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double stoneNoiseVal)
    {

        IBlockState topBlock = this.topBlock;
        IBlockState fillerBlock = this.fillerBlock;
        IBlockState seaFloorBlock = this.seaFloorBlock;

        boolean hitFloorYet = false;
        int topBlocksToFill = 0;
        int dirtBlocksToFill = 0;
        int seaFloorBlocksToFill = 0;
        int dirtDepth = Math.max(0, (int)(stoneNoiseVal / 3.0D + this.avgDirtDepth + rand.nextDouble() * 0.25D));
        int seaFloorDepth = 1 + rand.nextInt(2);
        
        int localX = x & 15;
        int localZ = z & 15;

        // start at the top and move downwards
        for (int y = 255; y >= 0; --y)
        {
            
            IBlockState state = primer.getBlockState(localZ, y, localX);
            
            // bedrock at the bottom
            if (y <= rand.nextInt(5))
            {
                primer.setBlockState(localZ, y, localX, Blocks.bedrock.getDefaultState());
                continue;
            }

            if (state.getBlock().getMaterial() == Material.air)
            {
                // topBlocks and dirtBlocks can occur after any pocket of air
                topBlocksToFill = (topBlock == null ? 0 : 1);
                dirtBlocksToFill = dirtDepth;
                continue;
            }
            else if (!hitFloorYet && state.getBlock().getMaterial() == Material.water)
            {
                // seaFloorBlocks can occur after surface water
                seaFloorBlocksToFill = seaFloorDepth;
            }
            
            if (state.getBlock() == Blocks.stone)
            {
                hitFloorYet = true;
                if (topBlocksToFill > 0)
                {
                    if (y >= 62)
                    {
                        primer.setBlockState(localZ, y, localX, topBlock);
                    }
                    else if (y >= 56 - dirtDepth)
                    {
                        primer.setBlockState(localZ, y, localX, fillerBlock);
                    }
                    else
                    {
                        primer.setBlockState(localZ, y, localX, Blocks.gravel.getDefaultState());
                        dirtBlocksToFill = 0;
                    }
                    topBlocksToFill--;
                }
                else if (seaFloorBlocksToFill > 0)
                {
                    primer.setBlockState(localZ, y, localX, seaFloorBlock);
                    --seaFloorBlocksToFill;
                }
                else if (dirtBlocksToFill > 0)
                {
                    primer.setBlockState(localZ, y, localX, fillerBlock);
                    --dirtBlocksToFill;

                    // add sandstone after a patch of sand
                    if (dirtBlocksToFill == 0 && fillerBlock.getBlock() == Blocks.sand)
                    {
                        dirtBlocksToFill = rand.nextInt(4) + Math.max(0, y - 63);
                        fillerBlock = fillerBlock.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? Blocks.red_sandstone.getDefaultState() : Blocks.sandstone.getDefaultState();
                    }
                }
            }
        }
    }

    @Override
    public BiomeGenBase getBaseBiome()
    {
        return this;
    }
}
