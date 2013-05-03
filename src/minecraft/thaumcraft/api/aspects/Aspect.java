package thaumcraft.api.aspects;

import java.util.HashMap;

public class Aspect {
	
	String tag;
	Aspect[] components;
	
	public Aspect(String tag, Aspect[] components) {
		this.tag = tag;
		this.components = components;
		
		aspects.put(tag, this);
	}

	public Aspect(String tag) {
		this.tag = tag;
		aspects.put(tag, this);
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Aspect[] getComponents() {
		return components;
	}

	public void setComponents(Aspect[] components) {
		this.components = components;
	}

	///////////////////////////////
	public static HashMap<String,Aspect> aspects = new HashMap<String,Aspect>();
	
	//PRIMAL
		public static final Aspect AIR = new Aspect("Air");
		public static final Aspect EARTH = new Aspect("Earth");
		public static final Aspect FIRE = new Aspect("Fire");
		public static final Aspect WATER = new Aspect("Water");
		public static final Aspect POSITIVE = new Aspect("Positive");
		public static final Aspect NEGATIVE = new Aspect("Negative");
		public static final Aspect ORDER = new Aspect("Order");
		public static final Aspect CHAOS = new Aspect("Chaos");
	
	//SECONDARY  	TODO
		public static final Aspect VOID = new Aspect("Void", new Aspect[] {NEGATIVE, POSITIVE});
		public static final Aspect LIGHT = new Aspect("Light", new Aspect[] {AIR, FIRE});
		public static final Aspect DARKNESS = new Aspect("Darkness", new Aspect[] {VOID, LIGHT});
		public static final Aspect ASTRAL = new Aspect("Astral", new Aspect[] {VOID, DARKNESS});
		public static final Aspect ENERGY = new Aspect("Energy", new Aspect[] {POSITIVE, FIRE});
		public static final Aspect LIFE = new Aspect("Life", new Aspect[] {POSITIVE, ENERGY});
		public static final Aspect DEATH = new Aspect("Death", new Aspect[] {NEGATIVE, ENERGY});
		public static final Aspect MOTION = new Aspect("Motion", new Aspect[] {AIR, ORDER});
		public static final Aspect WEATHER = new Aspect("Weather", new Aspect[] {AIR, CHAOS});
		public static final Aspect STONE = new Aspect("Stone", new Aspect[] {EARTH, ORDER});
		public static final Aspect METAL = new Aspect("Metal", new Aspect[] {STONE, FIRE});
		public static final Aspect SAND = new Aspect("Sand", new Aspect[] {AIR, STONE});
		public static final Aspect SOUL = new Aspect("Soul", new Aspect[] {DEATH, LIFE});
		public static final Aspect HEAL = new Aspect("Heal", new Aspect[] {POSITIVE, LIFE});
		public static final Aspect HARM = new Aspect("Harm", new Aspect[] {NEGATIVE, LIFE});
		public static final Aspect ANIMATE = new Aspect("Animate",new Aspect[] {MOTION, LIFE});
		public static final Aspect MAN = new Aspect("Man", new Aspect[] {LIFE, SOUL});
		public static final Aspect BEAST = new Aspect("Beast", new Aspect[] {LIFE, CHAOS});
		public static final Aspect BIRD = new Aspect("Bird", new Aspect[] {BEAST, AIR});
		public static final Aspect FISH = new Aspect("Fish", new Aspect[] {BEAST, WATER});
		public static final Aspect SEED = new Aspect("Seed", new Aspect[] {LIFE, EARTH});
		public static final Aspect TREE = new Aspect("Tree", new Aspect[] {SEED, EARTH});
		public static final Aspect TOOL = new Aspect("Tool", new Aspect[] {MAN, METAL});
		public static final Aspect MINE = new Aspect("Mine", new Aspect[] {MAN, STONE});
		public static final Aspect WOOD = new Aspect("Wood", new Aspect[] {TREE, TOOL});
		public static final Aspect MACHINE = new Aspect("Machine", new Aspect[] {TOOL, ORDER});
		
		
//		public static final Aspect TRAVEL = new Aspect("Travel", new Aspect[] {MOTION, EARTH});
//		public static final Aspect TELEPORT = new Aspect("Teleport", new Aspect[] {TRAVEL, VOID});
//		public static final Aspect GLASS = new Aspect("Glass", new Aspect[] {SAND, FIRE});
//		public static final Aspect CLAY = new Aspect("Clay", new Aspect[] {SAND, LIFE});
		
}
