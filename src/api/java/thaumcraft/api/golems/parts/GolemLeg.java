package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemLeg {		
	
	protected static GolemLeg[] legs = new GolemLeg[1];
	
	public byte id;
	public String key;
	public String[] research;
	/**
	 * The icon used in the golem builder 
	 */
	public ResourceLocation icon;
	public Object[] components;
	public EnumGolemTrait[] traits;
	public ILegFunction function; 
	public PartModel model;
	
	public GolemLeg(String key,String[] research,ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags) {
		this.key = key;
		this.research=research;
		this.icon=icon;
		this.components = comp;
		this.traits = tags;
		this.model = model;
		this.function = null;
	}
	
	public GolemLeg(String key,String[] research,ResourceLocation icon, PartModel model, Object[] comp, ILegFunction function, EnumGolemTrait[] tags) {
		this(key,research,icon,model,comp,tags);
		this.function=function;
	}
	
	private static byte lastID = 0; 
	public static void register(GolemLeg thing)  {
		thing.id = lastID;	
		lastID++;
		// allocate space
		if (thing.id>=legs.length) {
			GolemLeg[] temp = new GolemLeg[thing.id+1];
			System.arraycopy(legs, 0, temp, 0, legs.length);
			legs = temp;
		}			
		legs[thing.id] = thing;
	}
	
	public String getLocalizedName() {
		return StatCollector.translateToLocal("golem.leg."+this.key.toLowerCase());
	}
	
	public String getLocalizedDescription() {
		return StatCollector.translateToLocal("golem.leg.text."+this.key.toLowerCase());
	}

	public static GolemLeg[] getLegs() {
		return legs;
	}
	
	/**
	 * This optional interface allows you to create a class that will add functionality for a specific part.
	 */
	public interface ILegFunction extends IGenericFunction {
	}
}