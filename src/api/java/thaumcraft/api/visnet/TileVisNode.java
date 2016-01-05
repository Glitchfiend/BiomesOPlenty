package thaumcraft.api.visnet;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;

/**
 * @author Azanor
 * 
 * The tile entity used by nodes in the vis energy network. A node is either a source (like an aura node),
 * a transport relay or vis receiver (like the infernal furnace).
 *
 */
public abstract class TileVisNode extends TileThaumcraft {
	
	WeakReference<TileVisNode> parent = null;
	ArrayList<WeakReference<TileVisNode>> children = new ArrayList<WeakReference<TileVisNode>>();
	
	/**
	 * @return the WorldCoordinates location of where this node is located
	 */
	public WorldCoordinates getLocation() {
		return new WorldCoordinates(this);
	}
	
	/**
	 * @return the number of blocks away this node will check for parent nodes to connect to. 
	 */
	public abstract int getRange();
	
	/**
	 * @return true if this is the source or root node of the vis network. 
	 */
	public abstract boolean isSource();
		
	/**
	 * This method should never be called directly. Use VisNetHandler.drainVis() instead
	 * @param aspect what aspect to drain
	 * @param vis how much to drain
	 * @return how much was actually drained
	 */
	public int consumeVis(Aspect aspect, int vis) {
		if (VisNetHandler.isNodeValid(getParent())) {
			int out = getParent().get().consumeVis(aspect, vis);
			if (out>0) {
				triggerConsumeEffect(aspect);
			}
			return out;
		}
		return 0;
	}
	
	public void removeThisNode() {
		for (WeakReference<TileVisNode> n:getChildren()) {
			if (n!=null && n.get()!=null) {
				n.get().removeThisNode();
			}
		}	
		
		children = new ArrayList<WeakReference<TileVisNode>>();
		if (VisNetHandler.isNodeValid(this.getParent())) {
			this.getParent().get().nodeRefresh=true;
		}
		this.setParent(null);
		this.parentChanged();
		
		if (this.isSource()) {
			HashMap<WorldCoordinates, WeakReference<TileVisNode>> sourcelist = VisNetHandler.sources.get(worldObj.provider.dimensionId);
			if (sourcelist==null) {
				sourcelist = new HashMap<WorldCoordinates, WeakReference<TileVisNode>>();
			}
			sourcelist.remove(getLocation());
			VisNetHandler.sources.put( worldObj.provider.dimensionId, sourcelist );
		}
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	

	@Override
	public void invalidate() {
		removeThisNode();
		super.invalidate();
	}

	public void triggerConsumeEffect(Aspect aspect) {	}

	/**
	 * @return
	 */
	public WeakReference<TileVisNode> getParent() {
		return parent;
	}
	
	/**
	 * @return
	 */
	public WeakReference<TileVisNode> getRootSource() {
		return VisNetHandler.isNodeValid(getParent()) ? 
				getParent().get().getRootSource() : this.isSource() ?
						new WeakReference(this) : null;
	}
	
	/**
	 * @param parent
	 */
	public void setParent(WeakReference<TileVisNode> parent) {
		this.parent = parent;
	}
	
	/**
	 * @return
	 */
	public ArrayList<WeakReference<TileVisNode>> getChildren() {
		return children;
	}
	
	@Override
	public boolean canUpdate() {
		return true;
	}
	
	protected int nodeCounter = 0;
	private boolean nodeRegged = false;
	public boolean nodeRefresh = false;

	@Override
	public void updateEntity() {
				
		if (!worldObj.isRemote && ((nodeCounter++) % 40==0 || nodeRefresh)) {
			//check for changes
			if (!nodeRefresh && children.size()>0) {
				for (WeakReference<TileVisNode> n:children) {
					if (n==null || n.get()==null || !VisNetHandler.canNodeBeSeen(this, n.get())) {
						nodeRefresh=true;
						break;
					}
				}			
			}
			
			//refresh linked nodes
			if (nodeRefresh) {
				for (WeakReference<TileVisNode> n:children) {
					if (n.get()!=null) {
						n.get().nodeRefresh=true;
					}
				}
				children.clear();
				parent=null;
			}
			
			//redo stuff
			if (isSource() && !nodeRegged) {
				VisNetHandler.addSource(getWorldObj(), this);
				nodeRegged = true;
			} else 
			if (!isSource() && !VisNetHandler.isNodeValid(getParent())) {
				setParent(VisNetHandler.addNode(getWorldObj(), this));				
				nodeRefresh=true;
			}
			
			if (nodeRefresh) {
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				parentChanged();
			}
			nodeRefresh=false;
		}
		
	}
	
	public void parentChanged() { }
	
	/**
	 * @return the type of shard this is attuned to:
	 * none -1, air 0, fire 1, water 2, earth 3, order 4, entropy 5
	 * Should return -1 for most implementations
	 */
	public byte getAttunement() {
		return -1;
	}
		
	
}
