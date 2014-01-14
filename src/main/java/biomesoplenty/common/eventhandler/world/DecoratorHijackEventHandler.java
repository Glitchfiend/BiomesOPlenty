package biomesoplenty.common.eventhandler.world;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.event.terraingen.BiomeEvent.CreateDecorator;
import biomesoplenty.common.world.decoration.BOPBiomeDecorator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DecoratorHijackEventHandler
{
    @SubscribeEvent
    public void onDecoratorReplace(CreateDecorator event)
    {
        //if (BOPBiomeHelper.biomeList.containsValue(event.biome))
        //{
            BiomeDecorator originalBiomeDecorator = event.originalBiomeDecorator;
            BOPBiomeDecorator newBiomeDecorator = new BOPBiomeDecorator();

            newBiomeDecorator.bigMushroomsPerChunk = originalBiomeDecorator.bigMushroomsPerChunk;
            newBiomeDecorator.cactiPerChunk = originalBiomeDecorator.cactiPerChunk;
            newBiomeDecorator.clayPerChunk = originalBiomeDecorator.clayPerChunk;
            newBiomeDecorator.deadBushPerChunk = originalBiomeDecorator.deadBushPerChunk;
            newBiomeDecorator.flowersPerChunk = originalBiomeDecorator.flowersPerChunk;
            newBiomeDecorator.generateLakes = originalBiomeDecorator.generateLakes;
            newBiomeDecorator.grassPerChunk = originalBiomeDecorator.grassPerChunk;
            newBiomeDecorator.mushroomsPerChunk = originalBiomeDecorator.mushroomsPerChunk;
            newBiomeDecorator.reedsPerChunk = originalBiomeDecorator.reedsPerChunk;
            newBiomeDecorator.sandPerChunk = originalBiomeDecorator.sandPerChunk;
            newBiomeDecorator.sandPerChunk2 = originalBiomeDecorator.sandPerChunk2;
            newBiomeDecorator.treesPerChunk = originalBiomeDecorator.treesPerChunk;
            newBiomeDecorator.waterlilyPerChunk = originalBiomeDecorator.waterlilyPerChunk;

            event.newBiomeDecorator = newBiomeDecorator;
        //}
    }
}
