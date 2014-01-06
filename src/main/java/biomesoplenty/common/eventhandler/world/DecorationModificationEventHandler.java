package biomesoplenty.common.eventhandler.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import biomesoplenty.common.world.decoration.ForcedDecorators;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DecorationModificationEventHandler 
{
	@SubscribeEvent
	public void modifyDecor(Decorate event)
	{
		World world = event.world;

		int chunkX = event.chunkX;
		int chunkZ = event.chunkZ;

		Random random = event.rand;
		
		int x = chunkX + 8;
		int z = chunkZ + 8;
		
		BiomeGenBase biome = world.getBiomeGenForCoordsBody(x, z);
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
			if (event.type == Decorate.EventType.PUMPKIN)
			{
				if (!bopDecoration.getWorldFeatures().generatePumpkins) 
				{
					event.setResult(Result.DENY);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void modifyPopulation(Populate event)
	{
		World world = event.world;

		int chunkX = event.chunkX;
		int chunkZ = event.chunkZ;

		Random random = event.rand;
		
		int x = chunkX * 16;
		int z = chunkZ * 16;
		
		BiomeGenBase biome = world.getBiomeGenForCoordsBody(x + 16, z + 16);
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
			if (event.type == Populate.EventType.LAKE)
			{
				event.setResult(Result.DENY);

				for (int i = 0; i < bopDecoration.getWorldFeatures().waterPondsPerChunk; i++)
				{
					if (random.nextInt(4) == 0)
					{
						int randX = x + random.nextInt(16) + 8;
						int randY = random.nextInt(256);
						int randZ = z + random.nextInt(16) + 8;

						(new WorldGenLakes(Blocks.water)).generate(world, random, randX, randY, randZ);
					}
				}
			}
			else if (event.type == Populate.EventType.LAVA)
			{
				event.setResult(Result.DENY);

				for (int i = 0; i < bopDecoration.getWorldFeatures().lavaPondsPerChunk; i++)
				{
					if (random.nextInt(8) == 0)
					{
						int randX = x + random.nextInt(16) + 8;
						int randY = random.nextInt(random.nextInt(248) + 8);
						int randZ = z + random.nextInt(16) + 8;

						if (randY < 63 || random.nextInt(10) == 0)
						{
							(new WorldGenLakes(Blocks.lava)).generate(world, random, randX, randY, randZ);
						}
					}
				}
			}
		}
	}
}
