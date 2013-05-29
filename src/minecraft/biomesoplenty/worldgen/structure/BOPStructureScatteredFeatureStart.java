package biomesoplenty.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureDesertPyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureJunglePyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureSwampHut;
import net.minecraft.world.gen.structure.StructureStart;
import biomesoplenty.api.Biomes;

public class BOPStructureScatteredFeatureStart extends StructureStart
{
    public BOPStructureScatteredFeatureStart(World world, Random random, int x, int z)
    {
        BiomeGenBase biome = world.getBiomeGenForCoords(x * 16 + 8, z * 16 + 8);

        if (biome != BiomeGenBase.jungle && biome != BiomeGenBase.jungleHills && biome != Biomes.rainforest.get() && 
                biome != Biomes.temperateRainforest.get() && biome != Biomes.tropicalRainforest.get() && biome != Biomes.jungleNew.get())
        {
            if (biome == BiomeGenBase.swampland || biome == Biomes.bayou.get() || biome == Biomes.bog.get()
                    || biome == Biomes.deadSwamp.get() || biome == Biomes.fen.get() || biome == Biomes.sludgepit.get()
                    || biome == Biomes.swamplandNew.get())
            {
                ComponentScatteredFeatureSwampHut componentscatteredfeatureswamphut = new ComponentScatteredFeatureSwampHut(random, x * 16, z * 16);
                this.components.add(componentscatteredfeatureswamphut);
            }
            else
            {
                ComponentScatteredFeatureDesertPyramid componentscatteredfeaturedesertpyramid = new ComponentScatteredFeatureDesertPyramid(random, x * 16, z * 16);
                this.components.add(componentscatteredfeaturedesertpyramid);
            }
        }
        else
        {
            ComponentScatteredFeatureJunglePyramid componentscatteredfeaturejunglepyramid = new ComponentScatteredFeatureJunglePyramid(random, x * 16, z * 16);
            this.components.add(componentscatteredfeaturejunglepyramid);
        }

        this.updateBoundingBox();
    }
}
