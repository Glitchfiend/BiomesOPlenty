/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerator;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.world.GenerationManager;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

public abstract class BOPBiome extends Biome implements IExtendedBiome
{
    protected GenerationManager generationManager = new GenerationManager();
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();

    // defaults

    // -1 indicates the defaults as set by Vanilla will be used for the below fields
    public int skyColor = -1;
    public int fogColor = -1;

    /** 1.0 is the lowest possible amount of fog. 0.0 is the greatest.*/
    public float fogDensity = 1.0F;

    public final ResourceLocation location;
    public IConfigObj conf;

    private BOPBiome(ResourceLocation idLoc, PropsBuilder defaultBuilder, IConfigObj conf)
    {
        super(configureBiomeProps(idLoc, defaultBuilder, conf));

        this.location = idLoc;
        this.conf = conf;

        this.decorator.treesPerChunk = -999;
        this.decorator.flowersPerChunk = -999;
        this.decorator.grassPerChunk = -999;
        this.decorator.gravelPatchesPerChunk = -999;
        this.decorator.sandPatchesPerChunk = -999;
        //this.theBiomeDecorator.generateLakes = false;
    }

    protected BOPBiome(String idName, PropsBuilder defaultBuilder)
    {
        this(new ResourceLocation(BiomesOPlenty.MOD_ID, idName), defaultBuilder, ModBiomes.readConfigFile(idName));
    }

    public static BOPBiome.BiomeProps configureBiomeProps(ResourceLocation idLoc, BOPBiome.PropsBuilder defaultBuilder, IConfigObj conf)
    {
        // If there isn't a valid config file, don't use it to configure the biome
        if (conf.isEmpty())
            return defaultBuilder.build();

        defaultBuilder.withTemperature(conf.getFloat("temperature", defaultBuilder.temperature));
        defaultBuilder.withRainfall(conf.getFloat("rainfall", defaultBuilder.rainfall));
        defaultBuilder.withWaterColor(conf.getInt("waterColor", defaultBuilder.waterColor));

        Boolean enableRain = conf.getBool("enableRain", defaultBuilder.enableRain);
        if (enableRain != null && !enableRain) defaultBuilder.withRainDisabled();

        Boolean enableSnow = conf.getBool("enableSnow", defaultBuilder.enableSnow);
        if (enableSnow != null && enableSnow) defaultBuilder.withSnowEnabled();

        defaultBuilder.withBaseBiome(conf.getString("baseBiome", defaultBuilder.baseBiomeRegName));
        defaultBuilder.withGuiColour(conf.getInt("guiColour", defaultBuilder.guiColour));

        return defaultBuilder.build();
    }

    @Override
    public void applySettings(IBOPWorldSettings settings) {}

    @Override
    public void configure(IConfigObj conf)
    {
        this.topBlock = conf.getBlockState("topBlock", this.topBlock);
        this.fillerBlock = conf.getBlockState("fillerBlock", this.fillerBlock);

        this.skyColor = conf.getInt("skyColor", this.skyColor);
        this.fogColor = conf.getInt("fogColor", this.fogColor);
        this.fogDensity = conf.getFloat("fogDensity", this.fogDensity);

        // Allow weights to be overridden
        IConfigObj confWeights = conf.getObject("weights");
        for (BOPClimates climate : BOPClimates.values())
        {
            Integer weight = confWeights.getInt(climate.name().toLowerCase(), this.weightMap.get(climate));
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

        // Allow generators to be configured
        IConfigObj confGenerators = conf.getObject("generators");
        this.generationManager.configure(confGenerators);

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
                // eg  'villager', 'Zombie', 'SQUID', 'enderdragon' all ok
                Class <? extends Entity> entityClazz = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entityName)).getEntityClass();
                Class <? extends EntityLiving> livingClazz = null;
                if (!(entityClazz.isAssignableFrom(EntityLiving.class))) {
                    confEntity.addMessage("Entity " + entityName + " is not of type EntityLiving");
                    continue;
                }
                else {
                    livingClazz = (Class <? extends EntityLiving>)entityClazz;
                }

                if (livingClazz == null)
                {
                    confEntity.addMessage("No entity registered called " + entityName);
                    continue;
                }
                if (!creatureType.getCreatureClass().isAssignableFrom(livingClazz))
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
                        if (entry.entityClass == livingClazz)
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
                        SpawnListEntry entry = new SpawnListEntry(livingClazz, confEntity.getInt("weight", 10), confEntity.getInt("minGroupCount", 4), confEntity.getInt("maxGroupCount", 4));
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

    public IGenerator getGenerator(String name)
    {
        return this.generationManager.getGenerator(name);
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

    public int getFogColor(BlockPos pos) { return this.fogColor; }

    public float getFogDensity(BlockPos pos) { return this.fogDensity; }

    @Override
    public int getSkyColorByTemp(float temperature)
    {
        return (this.skyColor == -1) ? super.getSkyColorByTemp(temperature) : this.skyColor;
    }

    @Override
    public Biome getBaseBiome()
    {
        return this;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return this.location;
    }

    public static class PropsBuilder
    {
        private final String biomeName;

        /**The colour of this biome as seen in guis**/
        private int guiColour = 0xffffff;
        private float baseHeight = 0.1F;
        private float heightVariation = 0.2F;
        private float temperature = 0.5F;
        private float rainfall = 0.5F;
        private int waterColor = 16777215;
        private boolean enableSnow = false;
        private boolean enableRain = true;
        private String baseBiomeRegName;

        public PropsBuilder(String name) { this.biomeName = name; }

        public PropsBuilder withGuiColour(Integer colour) { if (colour != null) this.guiColour = colour; return this; }
        public PropsBuilder withTemperature(Float temperature) { if (temperature != null) this.temperature = temperature; return this; }
        public PropsBuilder withRainfall(Float rainfall) { if (rainfall != null) this.rainfall = rainfall; return this; }
        public PropsBuilder withBaseHeight(Float baseHeight) { if (baseHeight != null) this.baseHeight = baseHeight; return this; }
        public PropsBuilder withHeightVariation(Float heightVariation) { if (heightVariation != null) this.heightVariation = heightVariation; return this; }
        public PropsBuilder withRainDisabled() { this.enableRain = false; return this; }
        public PropsBuilder withSnowEnabled() { this.enableSnow = true; return this; }
        public PropsBuilder withWaterColor(Integer waterColor) { if (waterColor != null) this.waterColor = waterColor; return this; }
        public PropsBuilder withBaseBiome(String name) { if (name != null) this.baseBiomeRegName = name; return this; }

        public BiomeProps build()
        {
            return new BiomeProps(this.biomeName, this.temperature, this.rainfall, this.baseHeight, this.heightVariation, this.enableRain, this.enableSnow, this.waterColor, this.baseBiomeRegName, this.guiColour);
        }
    }

    public static class BiomeProps extends BiomeProperties
    {
        /**The colour of this biome as seen in guis**/
        private int guiColour = 0xffffff;

        private BiomeProps(String name, float temperature, float rainfall, float baseHeight, float heightVariation, boolean enableRain, boolean enableSnow, int waterColor, String baseBiomeRegName, int guiColour)
        {
            super(name);

            this.setTemperature(temperature);
            this.setRainfall(rainfall);
            this.setBaseHeight(baseHeight);
            this.setHeightVariation(heightVariation);
            if (!enableRain) this.setRainDisabled();
            if (enableSnow) this.setSnowEnabled();
            this.setWaterColor(waterColor);
            this.setBaseBiome(baseBiomeRegName);

            this.guiColour = guiColour;
        }
    }
}
