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
        if (event.type == Decorate.EventType.LAKE)
        {
            event.setResult(Result.DENY);
            return;
        }
	    
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
		}
	}
}
