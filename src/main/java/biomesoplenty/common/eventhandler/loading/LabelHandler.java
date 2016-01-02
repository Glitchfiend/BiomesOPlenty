package biomesoplenty.common.eventhandler.loading;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.event.world.WorldEvent;

public class LabelHandler 
{
    /**This should be updated every time there is a world-breaking change**/
    public static final int CURRENT_LOAD_VERSION = 0;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        World world = event.world;
        MapStorage mapStorage = world.perWorldStorage;
        BOPSavedData savedData = (BOPSavedData)mapStorage.loadData(BOPSavedData.class, BOPSavedData.DATA_IDENTIFIER);

        //If the saved data file hasn't been created before, create it
        if (savedData == null)
        {
            savedData = new BOPSavedData(BOPSavedData.DATA_IDENTIFIER);
            mapStorage.setData(BOPSavedData.DATA_IDENTIFIER, savedData);
            savedData.markDirty(); //Mark for saving
        }

        //Update load version to match current version if it doesn't already
        if (savedData.lastLoadVersion != CURRENT_LOAD_VERSION)
        {
            savedData.lastLoadVersion = CURRENT_LOAD_VERSION; 
            savedData.markDirty(); //Mark for saving
        }
    }
}
