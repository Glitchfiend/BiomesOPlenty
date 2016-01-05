package thaumcraft.api;

import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaTransport;

public class ThaumcraftApiHelper {
	
	public static AspectList cullTags(AspectList temp) {
		AspectList temp2 = new AspectList();
		for (Aspect tag:temp.getAspects()) {
			if (tag!=null)
				temp2.add(tag, temp.getAmount(tag));
		}
		while (temp2!=null && temp2.size()>6) {
			Aspect lowest = null;
			float low = Short.MAX_VALUE;
			for (Aspect tag:temp2.getAspects()) {
				if (tag==null) continue;
				float ta=temp2.getAmount(tag);
				if (tag.isPrimal()) {
					ta *= .9f;
				} else {
					if (!tag.getComponents()[0].isPrimal()) {
						ta *= 1.1f;
						if (!tag.getComponents()[0].getComponents()[0].isPrimal()) {
							ta *= 1.05f;
						}
						if (!tag.getComponents()[0].getComponents()[1].isPrimal()) {
							ta *= 1.05f;
						}
					}
					if (!tag.getComponents()[1].isPrimal()) {
						ta *= 1.1f;
						if (!tag.getComponents()[1].getComponents()[0].isPrimal()) {
							ta *= 1.05f;
						}
						if (!tag.getComponents()[1].getComponents()[1].isPrimal()) {
							ta *= 1.05f;
						}
					}
				}
				
				if (ta<low) {
					low = ta;					 
					lowest = tag;
				}
			}
			temp2.aspects.remove(lowest);
		}
		return temp2; 
	}
	
	public static boolean areItemsEqual(ItemStack s1,ItemStack s2)
    {
		if (s1.isItemStackDamageable() && s2.isItemStackDamageable())
		{
			return s1.getItem() == s2.getItem();
		} else
			return s1.getItem() == s2.getItem() && s1.getItemDamage() == s2.getItemDamage();
    }

	public static boolean isResearchComplete(String username, String researchkey) {
		return ThaumcraftApi.internalMethods.isResearchComplete(username, researchkey);
	}
	
	public static boolean hasDiscoveredAspect(String username, Aspect aspect) {
		return ThaumcraftApi.internalMethods.hasDiscoveredAspect(username, aspect);
	}
	
	public static AspectList getDiscoveredAspects(String username) {
		return ThaumcraftApi.internalMethods.getDiscoveredAspects(username);
	}

	public static ItemStack getStackInRowAndColumn(Object instance, int row, int column) {
		return ThaumcraftApi.internalMethods.getStackInRowAndColumn(instance, row, column);
	}

	public static AspectList getObjectAspects(ItemStack is) {
		return ThaumcraftApi.internalMethods.getObjectAspects(is);
	}

	public static AspectList getBonusObjectTags(ItemStack is,AspectList ot) {
		return ThaumcraftApi.internalMethods.getBonusObjectTags(is, ot);
	}

	public static AspectList generateTags(Item item, int meta) {
		return ThaumcraftApi.internalMethods.generateTags(item, meta);
	}
	
	public static boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack... targets)
    {
        for (ItemStack input : inputs)
        {
            for (ItemStack target : targets)
            {
                if (itemMatches(target, input, strict))
                {
                    return true;
                }
            }
        }
        return false;
    }
	
	public static boolean areItemStackTagsEqualForCrafting(ItemStack slotItem,ItemStack recipeItem)
    {
    	if (recipeItem == null || slotItem == null) return false;
    	if (recipeItem.stackTagCompound!=null && slotItem.stackTagCompound==null ) return false;
    	if (recipeItem.stackTagCompound==null ) return true;
    	
    	Iterator iterator = recipeItem.stackTagCompound.func_150296_c().iterator();
        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            if (slotItem.stackTagCompound.hasKey(s)) {
            	if (!slotItem.stackTagCompound.getTag(s).toString().equals(
            			recipeItem.stackTagCompound.getTag(s).toString())) {
            		return false;
            	}
            } else {
        		return false;
            }
            
        }
        return true;
    }

    public static boolean itemMatches(ItemStack target, ItemStack input, boolean strict)
    {
        if (input == null && target != null || input != null && target == null)
        {
            return false;
        }
        return (target.getItem() == input.getItem() && 
        		((target.getItemDamage() == OreDictionary.WILDCARD_VALUE && !strict) || target.getItemDamage() == input.getItemDamage()));
    }
    
    
    public static TileEntity getConnectableTile(World world, int x, int y, int z, ForgeDirection face) {
		TileEntity te = world.getTileEntity(x+face.offsetX, y+face.offsetY, z+face.offsetZ);
		if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.getOpposite())) 
			return te;
		else
			return null;
	}
    
    public static TileEntity getConnectableTile(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		TileEntity te = world.getTileEntity(x+face.offsetX, y+face.offsetY, z+face.offsetZ);
		if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.getOpposite())) 
			return te;
		else
			return null;
	}
    
    private static HashMap<Integer, AspectList> allAspects= new HashMap<Integer, AspectList>();
    private static HashMap<Integer, AspectList> allCompoundAspects= new HashMap<Integer, AspectList>();
    
    public static AspectList getAllAspects(int amount) {
    	if (allAspects.get(amount)==null) {
    		AspectList al = new AspectList();
    		for (Aspect aspect:Aspect.aspects.values()) {
    			al.add(aspect, amount);
    		}
    		allAspects.put(amount, al);
    	} 
    	return allAspects.get(amount);
    }
    
    public static AspectList getAllCompoundAspects(int amount) {
    	if (allCompoundAspects.get(amount)==null) {
    		AspectList al = new AspectList();
    		for (Aspect aspect:Aspect.getCompoundAspects()) {
    			al.add(aspect, amount);
    		}
    		allCompoundAspects.put(amount, al);
    	} 
    	return allCompoundAspects.get(amount);
    }
    
    
	/**
	 * Use to subtract vis from a wand for most operations
	 * Wands store vis differently so "real" vis costs need to be multiplied by 100 before calling this method
	 * @param wand the wand itemstack
	 * @param player the player using the wand
	 * @param cost the cost of the operation. 
	 * @param doit actually subtract the vis from the wand if true - if false just simulate the result
	 * @param crafting is this a crafting operation or not - if 
	 * false then things like frugal and potency will apply to the costs
	 * @return was the vis successfully subtracted
	 */
	public static boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, 
			AspectList cost, boolean doit, boolean crafting) {
		return ThaumcraftApi.internalMethods.consumeVisFromWand(wand, player, cost, doit, crafting);
	}
	
	/**
	 * Subtract vis for use by a crafting mechanic. Costs are calculated slightly 
	 * differently and things like the frugal enchant is ignored
	 * Must NOT be multiplied by 100 - send the actual vis cost
	 * @param wand the wand itemstack
	 * @param player the player using the wand
	 * @param cost the cost of the operation. 
	 * @param doit actually subtract the vis from the wand if true - if false just simulate the result
	 * @return was the vis successfully subtracted
	 */
	public static boolean consumeVisFromWandCrafting(ItemStack wand, EntityPlayer player, 
			AspectList cost, boolean doit) {
		return ThaumcraftApi.internalMethods.consumeVisFromWandCrafting(wand, player, cost, doit);
	}
	
	/**
	 * Subtract vis from a wand the player is carrying. Works like consumeVisFromWand in that actual vis
	 * costs should be multiplied by 100. The costs are handled like crafting however and things like 
	 * frugal don't effect them
	 * @param player the player using the wand
	 * @param cost the cost of the operation. 
	 * @return was the vis successfully subtracted
	 */
	public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost) {
		return ThaumcraftApi.internalMethods.consumeVisFromInventory(player, cost);
	}
	
	
	/**
	 * This adds permanents or temporary warp to a player. It will automatically be synced clientside
	 * @param player the player using the wand
	 * @param amount how much warp to add. Negative amounts are only valid for temporary warp
	 * @param temporary add temporary warp instead of permanent
	 */
	public static void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary) {
		ThaumcraftApi.internalMethods.addWarpToPlayer(player, amount, temporary);
	}
	
	/**
	 * This "sticky" warp to a player. Sticky warp is permanent warp that can be removed.
	 * It will automatically be synced clientside
	 * @param player the player using the wand
	 * @param amount how much warp to add. Can have negative amounts.
	 */
	public static void addStickyWarpToPlayer(EntityPlayer player, int amount) {
		ThaumcraftApi.internalMethods.addStickyWarpToPlayer(player, amount);
	}

	public static MovingObjectPosition rayTraceIgnoringSource(World world, Vec3 v1, Vec3 v2, 
			boolean bool1, boolean bool2, boolean bool3)
	{
	    if (!Double.isNaN(v1.xCoord) && !Double.isNaN(v1.yCoord) && !Double.isNaN(v1.zCoord))
	    {
	        if (!Double.isNaN(v2.xCoord) && !Double.isNaN(v2.yCoord) && !Double.isNaN(v2.zCoord))
	        {
	            int i = MathHelper.floor_double(v2.xCoord);
	            int j = MathHelper.floor_double(v2.yCoord);
	            int k = MathHelper.floor_double(v2.zCoord);
	            int l = MathHelper.floor_double(v1.xCoord);
	            int i1 = MathHelper.floor_double(v1.yCoord);
	            int j1 = MathHelper.floor_double(v1.zCoord);
	            Block block = world.getBlock(l, i1, j1);
	            int k1 = world.getBlockMetadata(l, i1, j1);
	
	            MovingObjectPosition movingobjectposition2 = null;
	            k1 = 200;
	
	            while (k1-- >= 0)
	            {
	                if (Double.isNaN(v1.xCoord) || Double.isNaN(v1.yCoord) || Double.isNaN(v1.zCoord))
	                {
	                    return null;
	                }
	
	                if (l == i && i1 == j && j1 == k)
	                {
	                    continue;
	                }
	
	                boolean flag6 = true;
	                boolean flag3 = true;
	                boolean flag4 = true;
	                double d0 = 999.0D;
	                double d1 = 999.0D;
	                double d2 = 999.0D;
	
	                if (i > l)
	                {
	                    d0 = (double)l + 1.0D;
	                }
	                else if (i < l)
	                {
	                    d0 = (double)l + 0.0D;
	                }
	                else
	                {
	                    flag6 = false;
	                }
	
	                if (j > i1)
	                {
	                    d1 = (double)i1 + 1.0D;
	                }
	                else if (j < i1)
	                {
	                    d1 = (double)i1 + 0.0D;
	                }
	                else
	                {
	                    flag3 = false;
	                }
	
	                if (k > j1)
	                {
	                    d2 = (double)j1 + 1.0D;
	                }
	                else if (k < j1)
	                {
	                    d2 = (double)j1 + 0.0D;
	                }
	                else
	                {
	                    flag4 = false;
	                }
	
	                double d3 = 999.0D;
	                double d4 = 999.0D;
	                double d5 = 999.0D;
	                double d6 = v2.xCoord - v1.xCoord;
	                double d7 = v2.yCoord - v1.yCoord;
	                double d8 = v2.zCoord - v1.zCoord;
	
	                if (flag6)
	                {
	                    d3 = (d0 - v1.xCoord) / d6;
	                }
	
	                if (flag3)
	                {
	                    d4 = (d1 - v1.yCoord) / d7;
	                }
	
	                if (flag4)
	                {
	                    d5 = (d2 - v1.zCoord) / d8;
	                }
	
	                boolean flag5 = false;
	                byte b0;
	
	                if (d3 < d4 && d3 < d5)
	                {
	                    if (i > l)
	                    {
	                        b0 = 4;
	                    }
	                    else
	                    {
	                        b0 = 5;
	                    }
	
	                    v1.xCoord = d0;
	                    v1.yCoord += d7 * d3;
	                    v1.zCoord += d8 * d3;
	                }
	                else if (d4 < d5)
	                {
	                    if (j > i1)
	                    {
	                        b0 = 0;
	                    }
	                    else
	                    {
	                        b0 = 1;
	                    }
	
	                    v1.xCoord += d6 * d4;
	                    v1.yCoord = d1;
	                    v1.zCoord += d8 * d4;
	                }
	                else
	                {
	                    if (k > j1)
	                    {
	                        b0 = 2;
	                    }
	                    else
	                    {
	                        b0 = 3;
	                    }
	
	                    v1.xCoord += d6 * d5;
	                    v1.yCoord += d7 * d5;
	                    v1.zCoord = d2;
	                }
	
	                Vec3 vec32 = Vec3.createVectorHelper(v1.xCoord, v1.yCoord, v1.zCoord);
	                l = (int)(vec32.xCoord = (double)MathHelper.floor_double(v1.xCoord));
	
	                if (b0 == 5)
	                {
	                    --l;
	                    ++vec32.xCoord;
	                }
	
	                i1 = (int)(vec32.yCoord = (double)MathHelper.floor_double(v1.yCoord));
	
	                if (b0 == 1)
	                {
	                    --i1;
	                    ++vec32.yCoord;
	                }
	
	                j1 = (int)(vec32.zCoord = (double)MathHelper.floor_double(v1.zCoord));
	
	                if (b0 == 3)
	                {
	                    --j1;
	                    ++vec32.zCoord;
	                }
	
	                Block block1 = world.getBlock(l, i1, j1);
	                int l1 = world.getBlockMetadata(l, i1, j1);
	
	                if (!bool2 || block1.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null)
	                {
	                    if (block1.canCollideCheck(l1, bool1))
	                    {
	                        MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(world, l, i1, j1, v1, v2);
	
	                        if (movingobjectposition1 != null)
	                        {
	                            return movingobjectposition1;
	                        }
	                    }
	                    else
	                    {
	                        movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, v1, false);
	                    }
	                }
	            }
	
	            return bool3 ? movingobjectposition2 : null;
	        }
	        else
	        {
	            return null;
	        }
	    }
	    else
	    {
	        return null;
	    }
	}
}
