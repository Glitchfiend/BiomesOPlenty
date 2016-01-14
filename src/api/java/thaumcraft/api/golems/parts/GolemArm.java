package thaumcraft.api.golems.parts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.IGolemAPI;

public class GolemArm {		
	
	protected static GolemArm[] arms = new GolemArm[1];
	
	public byte id;
	public String key;
	public String[] research;
	/**
	 * The icon used in the golem builder 
	 */
	public ResourceLocation icon;
	public Object[] components;
	public EnumGolemTrait[] traits;
	public IArmFunction function; 	
	public PartModel model;
	
	public GolemArm(String key,String[] research,ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags) {
		this.key = key;
		this.research=research;
		this.icon=icon;
		this.components = comp;
		this.traits = tags;
		this.model = model;
		this.function = null;
	}
	
	public GolemArm(String key,String[] research,ResourceLocation icon, PartModel model, Object[] comp, IArmFunction function, EnumGolemTrait[] tags) {
		this(key,research,icon,model,comp,tags);
		this.function = function;
	}
	
	private static byte lastID = 0; 
	public static void register(GolemArm thing)  {
		thing.id = lastID;	
		lastID++;
		// allocate space
		if (thing.id>=arms.length) {
			GolemArm[] temp = new GolemArm[thing.id+1];
			System.arraycopy(arms, 0, temp, 0, arms.length);
			arms = temp;
		}			
		arms[thing.id] = thing;
	}
	
	public String getLocalizedName() {
		return StatCollector.translateToLocal("golem.arm."+this.key.toLowerCase());
	}
	
	public String getLocalizedDescription() {
		return StatCollector.translateToLocal("golem.arm.text."+this.key.toLowerCase());
	}

	public static GolemArm[] getArms() {
		return arms;
	}
	
	/**
	 * This optional interface allows you to create a class that will be called whenever 
	 * the golem makes a ranged (using IRangedAttackMob) or melee attack.
	 * This will allow you to create your own projectiles 
	 */
	public interface IArmFunction extends IGenericFunction {
		public void onMeleeAttack(IGolemAPI golem, Entity ent);
		public void onRangedAttack(IGolemAPI golem, EntityLivingBase target, float range);
		public EntityAIArrowAttack getRangedAttackAI(IRangedAttackMob golem);
	}
	
}