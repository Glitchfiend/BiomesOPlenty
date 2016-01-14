package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemAddon {		
	
	protected static GolemAddon[] addons = new GolemAddon[1];
	
	public byte id;
	public String key;
	public String[] research;
	/**
	 * The icon used in the golem builder 
	 */
	public ResourceLocation icon;
	public Object[] components;
	public EnumGolemTrait[] traits;
	public IAddonFunction function; 
	public PartModel model;
	
	public GolemAddon(String key,String[] research,ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags) {
		this.key = key;
		this.research=research;
		this.icon=icon;
		this.components = comp;
		this.traits = tags;
		this.model = model;
		this.function = null;
	}
	
	public GolemAddon(String key,String[] research,ResourceLocation icon, PartModel model, Object[] comp, IAddonFunction function, EnumGolemTrait[] tags) {
		this(key,research,icon,model,comp,tags);
		this.function=function;
	}
	
	private static byte lastID = 0; 
	public static void register(GolemAddon thing)  {
		thing.id = lastID;	
		lastID++;
		// allocate space
		if (thing.id>=addons.length) {
			GolemAddon[] temp = new GolemAddon[thing.id+1];
			System.arraycopy(addons, 0, temp, 0, addons.length);
			addons = temp;
		}			
		addons[thing.id] = thing;
	}
	
	public String getLocalizedName() {
		return StatCollector.translateToLocal("golem.addon."+this.key.toLowerCase());
	}
	
	public String getLocalizedDescription() {
		return StatCollector.translateToLocal("golem.addon.text."+this.key.toLowerCase());
	}

	public static GolemAddon[] getAddons() {
		return addons;
	}
	
	/**
	 * This optional interface allows you to create a class that will add functionality for a specific part.
	 */
	public interface IAddonFunction extends IGenericFunction {
	}
}