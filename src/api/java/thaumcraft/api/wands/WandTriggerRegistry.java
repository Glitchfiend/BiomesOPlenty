package thaumcraft.api.wands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * This class serves a similar function to IWandable in that it allows wands to interact
 * with object in the world. In this case it is most useful for adding interaction with non-mod
 * blocks where you can't control what happens in their code.
 * Example where it is used is in crafting the thaumonomicon from a bookshelf and the
 * crucible from a cauldron
 * 
 * @author azanor
 *
 */
public class WandTriggerRegistry {
	
	private static HashMap<String,LinkedHashMap<IBlockState,List<Trigger>>> triggers = new HashMap<String,LinkedHashMap<IBlockState,List<Trigger>>>();
	private static final String DEFAULT = "default";
	
	private static class Trigger {
		IWandTriggerManager manager;
		int event;
		public Trigger(IWandTriggerManager manager, int event) {
			super();
			this.manager = manager;
			this.event = event;
		}	
	}

	/**
	 * Registers an action to perform when a casting wand right clicks on a specific block. 
	 * A manager class needs to be created that implements IWandTriggerManager.
	 * @param manager
	 * @param event a logical number that you can use to differentiate different events or actions
	 * @param blockState
	 * @param meta send -1 as a wildcard value for all possible meta values
	 * @param modid a unique identifier. It is best to register your own triggers using your mod id to avoid conflicts with mods that register triggers for the same block
	 */
	public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, IBlockState state, String modid) {
		if (!triggers.containsKey(modid)) {
			triggers.put(modid, new LinkedHashMap<IBlockState,List<Trigger>>());
		}
		LinkedHashMap<IBlockState,List<Trigger>> temp = triggers.get(modid);
		List<Trigger> ts = temp.get(state);
		if (ts==null) ts = new ArrayList<Trigger>();
		ts.add(new Trigger(manager,event));
		temp.put(state,ts);
		triggers.put(modid, temp);
	}
	
	/**
	 * for legacy support
	 */
	public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, IBlockState state) {
		registerWandBlockTrigger(manager, event, state, DEFAULT);
	}
	
	/**
	 * Checks all trigger registries if one exists for the given block and meta
	 * @param blockState
	 * @param meta
	 * @return
	 */
	public static boolean hasTrigger(IBlockState state) {
		for (String modid:triggers.keySet()) {
			LinkedHashMap<IBlockState,List<Trigger>> temp = triggers.get(modid);
			if (temp.containsKey(state)) return true;
		}
		return false;
	}
	
	/**
	 * modid sensitive version
	 */
	public static boolean hasTrigger(IBlockState state, String modid) {
		if (!triggers.containsKey(modid)) return false;
		LinkedHashMap<IBlockState,List<Trigger>> temp = triggers.get(modid);
		if (temp.containsKey(state)) return true;
		return false;
	}
	
	
	/**
	 * This is called by the onItemUseFirst function in wands. 
	 * Parameters and return value functions like you would expect for that function.
	 * @param world
	 * @param wand
	 * @param player
	 * @param x
	 * @param y
	 * @param z
	 * @param side
	 * @param blockState
	 * @param meta
	 * @return
	 */
	public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, 
			BlockPos pos, EnumFacing side, IBlockState state) {
		for (String modid:triggers.keySet()) {
			LinkedHashMap<IBlockState,List<Trigger>> temp = triggers.get(modid);
			List<Trigger> l = temp.get(state);
			if (l==null || l.size()==0) continue;
			for (Trigger trig:l) {				
				boolean result = trig.manager.performTrigger(world, wand, player, pos, side, trig.event);
				if (result) return true;
			}
		}
		return false;
	}
	
	/**
	 * modid sensitive version
	 */
	public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, 
			BlockPos pos, EnumFacing side, IBlockState state, String modid) {
		if (!triggers.containsKey(modid)) return false;
		LinkedHashMap<IBlockState,List<Trigger>> temp = triggers.get(modid);
		List<Trigger> l = temp.get(state);
		if (l==null || l.size()==0) return false;
		for (Trigger trig:l) {				
			boolean result = trig.manager.performTrigger(world, wand, player, pos, side, trig.event);
			if (result) return true;
		}
		return false;
	}
		
}
