package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.generation.IBOPWorldGenerator;
import biomesoplenty.common.world.generation.WorldGenFieldAssociation;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BOPDecorationManager implements IWorldGenerator
{
    private static BOPWorldFeatures[] biomeFeaturesMap = new BOPWorldFeatures[BiomeGenBase.getBiomeGenArray().length];

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        chunkX <<= 4;
        chunkZ <<= 4;

        BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
        BOPWorldFeatures biomeFeatures = getBiomeFeatures(biome.biomeID);

        for (String featureName : biomeFeatures.getFeatureNames())
        {
            try
            {
            if (featureName.equals("bopFlowersPerChunk"))
            {
                if (!TerrainGen.decorate(world, random, chunkX, chunkZ, DecorateBiomeEvent.Decorate.EventType.FLOWERS)) continue;
            }

                WorldGenFieldAssociation.WorldFeature worldFeature = WorldGenFieldAssociation.getAssociatedFeature(featureName);

                if (worldFeature != null)
                {
                    IBOPWorldGenerator worldGenerator = worldFeature.getBOPWorldGenerator();

                    if (worldGenerator != null)
                    {
                        worldGenerator.setupGeneration(world, random, biome, featureName, chunkX, chunkZ);
                    }
                }
            }
            catch (Exception e)
            {
                Throwable cause = e.getCause();

                if (e.getMessage() != null && e.getMessage().equals("Already decorating!!") || (cause != null && cause.getMessage() != null && cause.getMessage().equals("Already decorating!!")))
                {
                }
                else
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static BOPWorldFeatures getBiomeFeatures(int biomeID)
    {
        if (biomeFeaturesMap[biomeID] == null) return biomeFeaturesMap[biomeID] = new BOPWorldFeatures();
        else return biomeFeaturesMap[biomeID];
    }
}
