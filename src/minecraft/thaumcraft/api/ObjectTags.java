package thaumcraft.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class ObjectTags implements Serializable {
	
	public Map<EnumTag,Integer> tags = new HashMap<EnumTag,Integer>();//aspects associated with this object

	
	/**
	 * this creates a new aspect list with preloaded values based off the aspects of the given item.
	 * @param id the item/block id of an existing item
	 * @param meta the damage value of an existing item
	 */
	public ObjectTags(int id, int meta) {
		ObjectTags temp = ThaumcraftApiHelper.getObjectTags(new ItemStack(id,1,meta));
		if (temp!=null)
		for (EnumTag tag:temp.getAspects()) {
			add(tag,temp.getAmount(tag));
		}
	}
	
	public ObjectTags() {
	}
	
	/**
	 * @return the amount of different aspects in this collection
	 */
	public int size() {
		return tags.size();
	}
	
	/**
	 * @return an array of all the aspects in this collection
	 */
	public EnumTag[] getAspects() {
		EnumTag[] q = new EnumTag[1];
		return tags.keySet().toArray(q);
	}
	
	/**
	 * @return an array of all the aspects in this collection sorted by name
	 */
	public EnumTag[] getAspectsSorted() {
		EnumTag[] out = tags.keySet().toArray(new EnumTag[1]);
		boolean change=false;
		do {
			change=false;
			for(int a=0;a<out.length-1;a++) {
				EnumTag e1 = out[a];
				EnumTag e2 = out[a+1];
				if (e1!=null && e2!=null && e1.name.compareTo(e2.name)>0) {
					out[a] = e2;
					out[a+1] = e1;
					change = true;
					break;
				}
			}
		} while (change==true);
		return out;
	}
	
	/**
	 * @return an array of all the aspects in this collection sorted by name
	 */
	public EnumTag[] getAspectsSortedAmount() {
		EnumTag[] out = tags.keySet().toArray(new EnumTag[1]);
		boolean change=false;
		do {
			change=false;
			for(int a=0;a<out.length-1;a++) {
				int e1 = getAmount(out[a]);
				int e2 = getAmount(out[a+1]);
				if (e1>0 && e2>0 && e2>e1) {
					EnumTag ea = out[a];
					EnumTag eb = out[a+1];
					out[a] = eb;
					out[a+1] = ea;
					change = true;
					break;
				}
			}
		} while (change==true);
		return out;
	}
	
	/**
	 * @param key
	 * @return the amount associated with the given aspect in this collection
	 */
	public int getAmount(EnumTag key) {
		return  tags.get(key)==null?0:tags.get(key);
	}
	
	/**
	 * Reduces the amount of an aspect in this collection by the given amount. 
	 * If reduced below 0 the aspect will be removed completely.
	 * @param key
	 * @param amount
	 * @return 
	 */
	public boolean reduceAmount(EnumTag key, int amount) {
		if (getAmount(key)>=amount) {
			int am = getAmount(key)-amount;
			if (am<=0) tags.remove(key); else
			tags.put(key, am);
			return true;
		}
		return false;
	}
	
	/**
	 * Reduces the amount of an aspect in this collection by the given amount. 
	 * If reduced below 0 the aspect will be removed completely. 
	 * If the aspect does not exist then a negative value will be added. 
	 * Only used by aura internals for flux management.
	 * @param key
	 * @param amount
	 * @return
	 */
	public ObjectTags remove(EnumTag key, int amount) {
		if (getAmount(key)>=amount) {
			int am = getAmount(key)-amount;
			if (am<=0) tags.remove(key); else
			this.tags.put(key, am);
		} else if (getAmount(key)==0) {
			this.tags.put(key, -amount);
		}
		return this;
	}
	
	/**
	 * Adds this aspect and amount to the collection. 
	 * If the aspect exists then its value will be increased by the given amount.
	 * @param aspect
	 * @param amount
	 * @return
	 */
	public ObjectTags add(EnumTag aspect, int amount) {
		if (this.tags.containsKey(aspect)) {
			int oldamount = this.tags.get(aspect);
			amount+=oldamount;
		}
		this.tags.put( aspect, amount );
		return this;
	}

	
	/**
	 * Adds this aspect and amount to the collection. 
	 * If the aspect exists then only the highest of the old or new amount will be used.
	 * @param aspect
	 * @param amount
	 * @return
	 */
	public ObjectTags merge(EnumTag aspect, int amount) {
		if (this.tags.containsKey(aspect)) {
			int oldamount = this.tags.get(aspect);
			if (amount<oldamount) amount=oldamount;
		}
		this.tags.put( aspect, amount );
		return this;
	}
	
	
	
}
