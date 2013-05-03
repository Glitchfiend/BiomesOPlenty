package thaumcraft.api.aura;

import java.io.Serializable;

import thaumcraft.api.ObjectTags;

public class AuraNode implements Serializable {
	//key
	public int key;
	
	//aura
	public short level;
	public short baseLevel;
	public ObjectTags flux = new ObjectTags();
	public EnumNodeType type;
	
	//location
	public int dimension;
	public double xPos;
	public double yPos;
	public double zPos;
	public boolean locked;
	
	public AuraNode(int key, short lvl, EnumNodeType type, int dim, int x, int y, int z) {
		this.key = key;
		this.level = lvl;
		this.baseLevel = lvl;
		this.type = type;
		this.dimension = dim;
		this.xPos = x+.5d;
		this.yPos = y+.5d;
		this.zPos = z+.5d;
	}

	public AuraNode() {
	}

}
