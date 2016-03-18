/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.io.File;
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
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.TerrainSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

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
    
    public ResourceLocation beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.beach);
    
    public TerrainSettings terrainSettings = new TerrainSettings();
    public boolean noNeighborTerrainInfuence = false;
    public int avgDirtDepth = 3;
    
    public final ResourceLocation location;
    
    private BOPBiome(ResourceLocation idLoc, PropsBuilder defaultBuilder, BOPConfig.IConfigObj conf)
    {
        super(configureBiomeProps(idLoc, defaultBuilder, conf));

        this.configure(conf);

        this.location = idLoc;
        this.terrainSettings.setDefaults();
        
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        //this.theBiomeDecorator.generateLakes = false;
        
        // roots
        this.addGenerator("roots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(4.0F).with(BOPPlants.ROOT).create());
    }
    
    public BOPBiome(String idName, PropsBuilder defaultBuilder)
    {
        this(new ResourceLocation(BiomesOPlenty.MOD_ID, idName), defaultBuilder, ModBiomes.readConfigFile(idName));
    }
    
    public static BiomeProps configureBiomeProps(ResourceLocation idLoc, PropsBuilder defaultBuilder, BOPConfig.IConfigObj conf)
    {
        // If there isn't a valid config file, don't use it to configure the biome
        if (conf.isEmpty())
            return defaultBuilder.build();
        
        defaultBuilder.withBaseHeight(conf.getFloat("rootHeight"));
        defaultBuilder.withHeightVariation(conf.getFloat("variation"));
        defaultBuilder.withTemperature(conf.getFloat("temperature"));
        defaultBuilder.withRainfall(conf.getFloat("rainfall"));
        defaultBuilder.withWaterColor(conf.getInt("waterColor"));
        
        Boolean enableRain = conf.getBool("enableRain");
        if (enableRain != null && !enableRain) defaultBuilder.withRainDisabled();

        Boolean enableSnow = conf.getBool("enableSnow");
        if (enableSnow != null && enableSnow) defaultBuilder.withSnowEnabled();
        
        defaultBuilder.withBaseBiome(conf.getString("baseBiome"));
        defaultBuilder.withGuiColour(conf.getInt("guiColour"));
        
        return defaultBuilder.build();
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings){}
    
    @Override
    public void configure(IConfigObj conf)
    {
        // Allow basic properties to be overridden
        this.topBlock = conf.getBlockState("topBlock", this.topBlock);
        this.fillerBlock = conf.getBlockState("fillerBlock", this.fillerBlock);
        this.seaFloorBlock = conf.getBlockState("seaFloorBlock", this.seaFloorBlock);
        
        this.skyColor = conf.getInt("skyColor", this.skyColor);
        this.hasBiomeEssence = conf.getBool("hasBiomeEssence", this.hasBiomeEssence);
        
        this.canSpawnInBiome = conf.getBool("canSpawnInBiome", this.canSpawnInBiome);
        this.canGenerateVillages = conf.getBool("canGenerateVillages", this.canGenerateVillages);
        this.canGenerateRivers = conf.getBool("canGenerateRivers", this.canGenerateRivers);
        
        this.beachBiomeLocation = conf.getResourceLocation("beachBiomeLocation", this.beachBiomeLocation);
        
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

            if (state.getMaterial() == Material.air)
            {
                // topBlocks and dirtBlocks can occur after any pocket of air
                topBlocksToFill = (topBlock == null ? 0 : 1);
                dirtBlocksToFill = dirtDepth;
                continue;
            }
            else if (!hitFloorYet && state.getMaterial() == Material.water)
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
    public ResourceLocation getBeachLocation()
    {
        return this.beachBiomeLocation;
    }

    @Override
    public BiomeGenBase getBaseBiome()
    {
        return this;
    }
    
    @Override
    public ResourceLocation getResourceLocation() 
    {
        return this.location;
    }
    
    protected static class PropsBuilder
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
    
    private static class BiomeProps extends BiomeGenBase.BiomeProperties
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
