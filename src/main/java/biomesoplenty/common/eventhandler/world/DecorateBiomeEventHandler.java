package biomesoplenty.common.eventhandler.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import biomesoplenty.common.world.ForcedDecorators;
import biomesoplenty.common.world.IBOPDecoration;
import biomesoplenty.common.world.WorldGenFieldAssociation;
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
			for (String worldGeneratorField : WorldGenFieldAssociation.getDeclaredFields())
			{
				int worldGenPerChunk = bopDecoration.getWorldGenPerChunk(worldGeneratorField);

				for (int i = 0; i < worldGenPerChunk; i++)
				{
					int randX = x + random.nextInt(16);
					int randZ = z + random.nextInt(16);
					
					WorldGenerator worldGenerator = WorldGenFieldAssociation.getAssociatedWorldGenerator(worldGeneratorField);

					worldGenerator.generate(world, random, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
				}
			}
		}
	}
}
