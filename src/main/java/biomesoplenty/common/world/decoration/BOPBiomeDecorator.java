package biomesoplenty.common.world.decoration;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.lang.reflect.Field;
import java.util.Random;

import biomesoplenty.common.world.generation.ForcedBOPWorldGenerators;
import biomesoplenty.common.world.generation.IWorldGeneratorBOP;
import biomesoplenty.common.world.generation.WorldGenFieldAssociation;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BOPBiomeDecorator extends BiomeDecorator
{
    @Override
    public void func_150512_a(World world, Random random, BiomeGenBase biome, int chunk_X, int chunk_Z)
    {
        if (this.currentWorld == null)
        {
            this.currentWorld = world;
            this.randomGenerator = random;
            this.chunk_X = chunk_X;
            this.chunk_Z = chunk_Z;
            this.func_150513_a(biome);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    @Override
    protected void func_150513_a(BiomeGenBase biome)
    {
        doBOPDecoration(biome);
        super.func_150513_a(biome);
    }
    
    public void doBOPDecoration(BiomeGenBase biome)
    {
        IBOPDecoration bopDecoration = null;

        if (biome instanceof IBOPDecoration)
        {
            bopDecoration = (IBOPDecoration)biome;
        }
        else if (ForcedDecorators.biomeHasForcedDecorator(biome.biomeID))
        {
            bopDecoration = ForcedDecorators.getForcedDecorator(biome.biomeID);
        }

        if (bopDecoration != null)
        {
            for (Field worldGeneratorField : BOPWorldFeatures.class.getFields())
            {
                WorldGenerator worldGenerator = null;

                if (worldGeneratorField.getName().equals("bopFlowersPerChunk") && TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, FLOWERS))
                {
                    worldGenerator = bopDecoration.getRandomWorldGenForBOPFlowers(this.randomGenerator);
                }
                else
                {
                    worldGenerator = WorldGenFieldAssociation.getAssociatedWorldGenerator(worldGeneratorField.getName());
                }

                if (worldGenerator != null)
                {
                    IWorldGeneratorBOP worldGeneratorBOP = null;

                    if (worldGenerator instanceof IWorldGeneratorBOP)
                    {
                        worldGeneratorBOP = (IWorldGeneratorBOP)worldGenerator;
                    }
                    else if (ForcedBOPWorldGenerators.hasForcedGenerator(worldGenerator.getClass()))
                    {
                        worldGeneratorBOP = ForcedBOPWorldGenerators.getForcedGenerator(worldGenerator.getClass());
                    }

                    if (worldGeneratorBOP != null)
                    {
                        try
                        {
                            worldGeneratorBOP.doGeneration(this.currentWorld, this.randomGenerator, worldGeneratorField, worldGenerator, biome, bopDecoration, this.chunk_X, this.chunk_Z);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
