package ttftcuts.atg.api.events.listenable;

import cpw.mods.fml.common.eventhandler.Event;

// Listen for this to change the biold group at a point!
public class ATGBiomeGroupAssignmentEvent extends Event {

	public int x;
	public int z;
	public double height;
	public double temperature;
	public double moisture;
	public String group;
	public boolean modified;
	
	public ATGBiomeGroupAssignmentEvent(int x, int z, double height, double temp, double moisture, String group) {
		this.x = x;
		this.z = z;
		this.height = height;
		this.temperature = temp;
		this.moisture = moisture;
		this.group = group;
		this.modified = false;
	}
	
	public void setGroup(String newgroup) {
		this.group = newgroup;
		this.modified = true;
	}
	
	public static class ATGGroupActivationEvent extends Event {}
}
