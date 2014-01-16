package biomesoplenty.common.eventhandler.world;

import net.minecraft.world.gen.structure.MapGenVillage;
import biomesoplenty.common.configuration.structures.BOPConfigurationVillages;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class BOPMapGenVillageEventHandler extends MapGenVillage
{
	public BOPMapGenVillageEventHandler()
	{
		super();
		
        ReflectionHelper.setPrivateValue(MapGenVillage.class, this, BOPConfigurationVillages.villageDistance, "field_82665_g");
        ReflectionHelper.setPrivateValue(MapGenVillage.class, this, BOPConfigurationVillages.villageDistance / 4, "field_82666_h");
	}
}
