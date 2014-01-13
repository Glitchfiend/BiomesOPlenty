package biomesoplenty.common.eventhandler.world;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.decoration.ForcedDecorators;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.ForcedBOPWorldGenerators;
import biomesoplenty.common.world.generation.IWorldGeneratorBOP;
import biomesoplenty.common.world.generation.WorldGenFieldAssociation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DecorateBiomeEventHandler 
{
	@SubscribeEvent
	public void onBiomeDecorate(DecorateBiomeEvent.Pre event)
	{
		World world = event.world;

		int chunkX = event.chunkX;
		int chunkZ = event.chunkZ;

		Random random = event.rand;

		BiomeGenBase biome = world.getBiomeGenForCoordsBody(chunkX + 16, chunkZ + 16);
		
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
				try
				{
					WorldGenerator worldGenerator = null;

					if (worldGeneratorField.getName().equals("bopFlowersPerChunk") && TerrainGen.decorate(world, random, chunkX, chunkZ, FLOWERS))
					{
						worldGenerator = bopDecoration.getRandomWorldGenForBOPFlowers(random);
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
							worldGeneratorBOP.doGeneration(world, random, worldGeneratorField, worldGenerator, biome, bopDecoration, chunkX, chunkZ);
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
	}
}
