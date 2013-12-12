package ttftcuts.atg.api.events;

import ttftcuts.atg.api.ATGBiomes.BiomeType;
import net.minecraftforge.event.Event;

public class ATGBiomeGroupAddEvent extends Event {

	public static enum ResponseType { NONE, OK, FAILED };
	
	public BiomeType type;
	public ResponseType response;
	public String name;
	public double temp;
	public double moisture;
	public double height;
	public double minHeight;
	public double maxHeight;
	public long salt;

	public ATGBiomeGroupAddEvent( BiomeType type, String name, double temp, double moisture, double height, double minHeight, double maxHeight, long salt) {
		this.type = type;
		this.name = name;
		this.temp = temp;
		this.moisture = moisture;
		this.height = height;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.salt = salt;
		this.response = ResponseType.NONE;
	}
}
