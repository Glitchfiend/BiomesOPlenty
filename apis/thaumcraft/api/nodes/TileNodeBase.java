package thaumcraft.api.nodes;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * 
 * @author azanor
 * 
 * Used as a base for all node tile entities. All the work actually gets done in a non-api TE
 * that extends this class.
 *
 */
public class TileNodeBase extends TileThaumcraft {
	
	public static enum NodeType
    {
        NORMAL, UNSTABLE, DARK, TAINTED, HUNGRY, PURE
    }
	
	public static enum NodeModifier
    {
        BRIGHT, PALE, FADING
    }
	
	short nodeVisBase = 0;
	
	AspectList aspects = new AspectList();

	public static HashMap<String,ArrayList<Integer>> locations = new HashMap<String,ArrayList<Integer>>();  
		
	private NodeType nodeType=NodeType.NORMAL;
	private NodeModifier nodeModifier=null;
	
	

	protected String id;
	
	public String getId() {
		if (id==null) {
			id = generateIdentifier();
		}
		return id;
	}
	
	public String generateIdentifier() {
		id = worldObj.provider.dimensionId + ":" + xCoord + ":" + yCoord+ ":" + zCoord;
		
		if (worldObj!=null && locations!=null) {
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(worldObj.provider.dimensionId);
			t.add(xCoord);
			t.add(yCoord);
			t.add(zCoord);
			locations.put(id, t);
		}
		
		return id;
	}
	
	@Override
	public void onChunkUnload() {
		if (locations!=null) locations.remove(id);
		super.onChunkUnload();
	}
			
	@Override
	public void validate() {
		super.validate();
		if (id==null) generateIdentifier();
	}

	/**
	 * Returns all the aspects and their amounts that this node contains
	 * @return
	 */
	public AspectList getAspects() {
		return aspects;
	}
	
	/**
	 * Sets the aspects contained within this node
	 * @return
	 */
	public void setAspects(AspectList aspects) {
		this.aspects = aspects;
		short highest=0;
		for (Aspect aspect:aspects.getAspects()) {
			if (aspects.getAmount(aspect)>highest)
				highest=(short) aspects.getAmount(aspect);
		}
		nodeVisBase = highest;
	}

	/**
	 * This method is used to add a certain amount of an aspect to the node.
	 * @param tag 
	 * @param amount
	 * @return the amount of aspect left over that could not be added.
	 */
	public int addToSource(Aspect aspect, int amount) {
		int left = (amount+aspects.getAmount(aspect))-nodeVisBase;
		left = left>0?left:0;
		aspects.add(aspect, amount-left);
		return left;
	}

	/**
	 * Removes a certain amount of a specific aspect from the node
	 * @param tag
	 * @param amount
	 * @return true if that amount of aspect was available and was removed
	 */
	public boolean takeFromSource(Aspect aspect, int amount) {
		return aspects.reduce(aspect, amount);
	}
	
	/**
	 * Removes a single random aspect from the source
	 * @return the aspect that was retrieved (it is assumed that only 1 of that aspect has been removed). 
	 * Should return null if there are not enough aspects.
	 */
	public Aspect takeRandomPrimalFromSource() {
		Aspect[] primals = aspects.getPrimalAspects();
		Aspect asp = primals[worldObj.rand.nextInt(primals.length)];
		if (asp !=null && aspects.reduce(asp,1)) {
			return asp;
		} else {
			return null;
		}
	}
	
	public Aspect chooseRandomFilteredFromSource(AspectList filter) {
		ArrayList<Aspect> validaspects = new ArrayList<Aspect>();
		for (Aspect prim:aspects.getAspects()) {
			if (filter.getAmount(prim)>0 && aspects.getAmount(prim)>0) validaspects.add(prim);
		}
		if (validaspects.size()==0) return null;		
		Aspect asp = validaspects.get(worldObj.rand.nextInt(validaspects.size()));
		if (asp !=null && aspects.getAmount(asp)>0) {
			return asp;
		} else {
			return null;
		}
	}
	
	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
    {
		this.id = nbttagcompound.getString("nodeId");
		this.nodeVisBase = nbttagcompound.getShort("nodeVisBase");
		
		if (worldObj!=null && locations!=null) {
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(worldObj.provider.dimensionId);
			t.add(xCoord);
			t.add(yCoord);
			t.add(zCoord);
			locations.put(id, t);
		}
		
		this.setNodeType(NodeType.values()[nbttagcompound.getByte("type")]);
		byte mod = nbttagcompound.getByte("modifier");
		if (mod>=0)
			this.setNodeModifier(NodeModifier.values()[mod]);
		else
			this.setNodeModifier(null);
		
		aspects.readFromNBT(nbttagcompound);
		
    }
	
	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
    {
		if (id==null) id = generateIdentifier();
		nbttagcompound.setString("nodeId", id);
		nbttagcompound.setShort("nodeVisBase", nodeVisBase);
		nbttagcompound.setByte("type", (byte) this.getNodeType().ordinal());
		nbttagcompound.setByte("modifier", getNodeModifier()==null?-1:(byte) this.getNodeModifier().ordinal());
		aspects.writeToNBT(nbttagcompound);

    }

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void setNodeModifier(NodeModifier nodeModifier) {
		this.nodeModifier = nodeModifier;
	}
	
	public NodeModifier getNodeModifier() {
		return nodeModifier;
	}
	
	public int getNodeVisBase() {
		return nodeVisBase;
	}

	public void setNodeVisBase(short nodeVisBase) {
		this.nodeVisBase = nodeVisBase;
	}

	public void nodeChange() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

}
