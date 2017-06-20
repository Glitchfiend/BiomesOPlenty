package biomesoplenty.common.world;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import biomesoplenty.common.util.biome.BiomeUtils;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import biomesoplenty.api.biome.BOPBiomes;
import com.google.common.collect.Sets;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class BOPMapGenScatteredFeature extends MapGenScatteredFeature
{
    private static final List<Biome> JUNGLE_BIOMES = Lists.newArrayList(Biomes.JUNGLE, Biomes.JUNGLE_HILLS);
    private static final List<Biome> SWAMP_BIOMES = Lists.newArrayList(Biomes.SWAMPLAND);
    private static final List<Biome> DESERT_BIOMES = Lists.newArrayList( Biomes.DESERT, Biomes.DESERT_HILLS);
    private static final List<Biome> ICE_BIOMES = Lists.newArrayList(Biomes.ICE_PLAINS, Biomes.COLD_TAIGA);

    private final List<Biome.SpawnListEntry> scatteredFeatureSpawnList;
    private int maxDistanceBetweenScatteredFeatures;
    private final int minDistanceBetweenScatteredFeatures;

    static
    {
        JUNGLE_BIOMES.addAll(BiomeUtils.filterPresentBiomes(BOPBiomes.bamboo_forest, BOPBiomes.eucalyptus_forest, BOPBiomes.overgrown_cliffs, BOPBiomes.rainforest, BOPBiomes.sacred_springs, BOPBiomes.tropical_rainforest));
        SWAMP_BIOMES.addAll(BiomeUtils.filterPresentBiomes(BOPBiomes.bayou, BOPBiomes.bog, BOPBiomes.dead_swamp, BOPBiomes.fen, BOPBiomes.lush_swamp, BOPBiomes.moor, BOPBiomes.ominous_woods, BOPBiomes.quagmire, BOPBiomes.wetland));
        DESERT_BIOMES.addAll(BiomeUtils.filterPresentBiomes(BOPBiomes.lush_desert, BOPBiomes.outback, BOPBiomes.oasis, BOPBiomes.xeric_shrubland));
        ICE_BIOMES.addAll(BiomeUtils.filterPresentBiomes(BOPBiomes.alps, BOPBiomes.cold_desert, BOPBiomes.snowy_coniferous_forest, BOPBiomes.snowy_forest));
    }

    public BOPMapGenScatteredFeature()
    {
        this.scatteredFeatureSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
        this.maxDistanceBetweenScatteredFeatures = 32;
        this.minDistanceBetweenScatteredFeatures = 8;
        this.scatteredFeatureSpawnList.add(new Biome.SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }

    public BOPMapGenScatteredFeature(Map<String, String> p_i2061_1_)
    {
        this();

        for (Entry<String, String> entry : p_i2061_1_.entrySet())
        {
            if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.getInt((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, 9);
            }
        }
    }

    @Override
    public String getStructureName()
    {
        return "Temple";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int k = chunkX / this.maxDistanceBetweenScatteredFeatures;
        int l = chunkZ / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.world.setRandomSeed(k, l, 14357617);
        k = k * this.maxDistanceBetweenScatteredFeatures;
        l = l * this.maxDistanceBetweenScatteredFeatures;
        k = k + random.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);
        l = l + random.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);

        if (i == k && j == l)
        {
            Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 8, 0, j * 16 + 8));

            if (biome != null && (JUNGLE_BIOMES.contains(biome) || SWAMP_BIOMES.contains(biome) || DESERT_BIOMES.contains(biome) || ICE_BIOMES.contains(biome)))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 14357617, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new BOPMapGenScatteredFeature.Start(this.world, this.rand, chunkX, chunkZ);
    }

    @Override
    public boolean isSwampHut(BlockPos p_175798_1_)
    {
        StructureStart structurestart = this.getStructureAt(p_175798_1_);

        if (structurestart != null && structurestart instanceof BOPMapGenScatteredFeature.Start && !structurestart.getComponents().isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent)structurestart.getComponents().get(0);
            return structurecomponent instanceof ComponentScatteredFeaturePieces.SwampHut;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Biome.SpawnListEntry> getScatteredFeatureSpawnList()
    {
        return this.scatteredFeatureSpawnList;
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biome)
        {
            super(chunkX, chunkZ);

            if (JUNGLE_BIOMES.contains(biome))
            {
                ComponentScatteredFeaturePieces.JunglePyramid componentscatteredfeaturepieces$junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                this.components.add(componentscatteredfeaturepieces$junglepyramid);
            }
            else if (SWAMP_BIOMES.contains(biome))
            {
                ComponentScatteredFeaturePieces.SwampHut componentscatteredfeaturepieces$swamphut = new ComponentScatteredFeaturePieces.SwampHut(random, chunkX * 16, chunkZ * 16);
                this.components.add(componentscatteredfeaturepieces$swamphut);
            }
            else if (DESERT_BIOMES.contains(biome))
            {
                ComponentScatteredFeaturePieces.DesertPyramid componentscatteredfeaturepieces$desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                this.components.add(componentscatteredfeaturepieces$desertpyramid);
            }
            else if (ICE_BIOMES.contains(biome))
            {
                ComponentScatteredFeaturePieces.Igloo componentscatteredfeaturepieces$igloo = new ComponentScatteredFeaturePieces.Igloo(random, chunkX * 16, chunkZ * 16);
                this.components.add(componentscatteredfeaturepieces$igloo);
            }

            this.updateBoundingBox();
        }
    }


}