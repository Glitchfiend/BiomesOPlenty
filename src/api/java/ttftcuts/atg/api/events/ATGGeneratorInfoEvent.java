package ttftcuts.atg.api.events;

import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;

public class ATGGeneratorInfoEvent extends Event {

	public World world;
	public double x;
	public double z;
	public double[] info;
	
	public ATGGeneratorInfoEvent(World world, double x, double z) {
		this.world = world;
		this.x = x;
		this.z = z;
	}
}
