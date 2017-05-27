package biomesoplenty.common.world;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;

import biomesoplenty.api.biome.BOPBiomes;
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
    private static final List<Biome> BIOMELIST = Arrays.<Biome>asList(new Biome[] {
            
            Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.SWAMPLAND,
            Biomes.ICE_PLAINS, Biomes.COLD_TAIGA, BOPBiomes.bamboo_forest.get(), BOPBiomes.eucalyptus_forest.get(),
            BOPBiomes.overgrown_cliffs.get(), BOPBiomes.rainforest.get(), BOPBiomes.sacred_springs.get(),
            BOPBiomes.tropical_rainforest.get(), BOPBiomes.bayou.get(), BOPBiomes.bog.get(), BOPBiomes.dead_swamp.get(),
            BOPBiomes.fen.get(), BOPBiomes.lush_swamp.get(), BOPBiomes.moor.get(), BOPBiomes.ominous_woods.get(),
            BOPBiomes.quagmire.get(), BOPBiomes.wetland.get(), BOPBiomes.lush_desert.get(), BOPBiomes.outback.get(),
            BOPBiomes.oasis.get(), BOPBiomes.xeric_shrubland.get(), BOPBiomes.alps.get(), BOPBiomes.cold_desert.get(),
            BOPBiomes.snowy_coniferous_forest.get(), BOPBiomes.snowy_forest.get()
    });
    private final List<Biome.SpawnListEntry> scatteredFeatureSpawnList;
    private int maxDistanceBetweenScatteredFeatures;
    private final int minDistanceBetweenScatteredFeatures;

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

            if (biome == null)
            {
                return false;
            }

            for (Biome biome1 : BIOMELIST)
            {
                if (biome == biome1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos, boolean findUnexplored)
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
            public Start()
            {
            }

            public Start(World worldIn, Random random, int chunkX, int chunkZ)
            {
                this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
            }

            public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
            {
                super(chunkX, chunkZ);

                if (biomeIn != Biomes.JUNGLE && biomeIn != Biomes.JUNGLE_HILLS && biomeIn != BOPBiomes.bamboo_forest.get() && biomeIn != BOPBiomes.eucalyptus_forest.get() && biomeIn != BOPBiomes.overgrown_cliffs.get() && biomeIn != BOPBiomes.rainforest.get() && biomeIn != BOPBiomes.sacred_springs.get() && biomeIn != BOPBiomes.tropical_rainforest.get())
                {
                    if (biomeIn == Biomes.SWAMPLAND || biomeIn == BOPBiomes.bayou.get() || biomeIn == BOPBiomes.bog.get() || biomeIn == BOPBiomes.dead_swamp.get() || biomeIn == BOPBiomes.fen.get() || biomeIn == BOPBiomes.lush_swamp.get() || biomeIn == BOPBiomes.moor.get() || biomeIn == BOPBiomes.ominous_woods.get() || biomeIn == BOPBiomes.quagmire.get() || biomeIn == BOPBiomes.wetland.get())
                    {
                        ComponentScatteredFeaturePieces.SwampHut componentscatteredfeaturepieces$swamphut = new ComponentScatteredFeaturePieces.SwampHut(random, chunkX * 16, chunkZ * 16);
                        this.components.add(componentscatteredfeaturepieces$swamphut);
                    }
                    else if (biomeIn != Biomes.DESERT && biomeIn != Biomes.DESERT_HILLS && biomeIn != BOPBiomes.lush_desert.get() && biomeIn != BOPBiomes.outback.get() && biomeIn != BOPBiomes.oasis.get() && biomeIn != BOPBiomes.xeric_shrubland.get())
                    {
                        if (biomeIn == Biomes.ICE_PLAINS || biomeIn == Biomes.COLD_TAIGA || biomeIn == BOPBiomes.alps.get() || biomeIn == BOPBiomes.cold_desert.get() || biomeIn == BOPBiomes.snowy_coniferous_forest.get() || biomeIn == BOPBiomes.snowy_forest.get())
                        {
                            ComponentScatteredFeaturePieces.Igloo componentscatteredfeaturepieces$igloo = new ComponentScatteredFeaturePieces.Igloo(random, chunkX * 16, chunkZ * 16);
                            this.components.add(componentscatteredfeaturepieces$igloo);
                        }
                    }
                    else
                    {
                        ComponentScatteredFeaturePieces.DesertPyramid componentscatteredfeaturepieces$desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                        this.components.add(componentscatteredfeaturepieces$desertpyramid);
                    }
                }
                else
                {
                    ComponentScatteredFeaturePieces.JunglePyramid componentscatteredfeaturepieces$junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                    this.components.add(componentscatteredfeaturepieces$junglepyramid);
                }

                this.updateBoundingBox();
            }
        }
}