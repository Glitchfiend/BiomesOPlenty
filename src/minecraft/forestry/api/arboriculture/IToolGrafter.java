package forestry.api.arboriculture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IToolGrafter {
	/**
	 * Called by leaves to determine the increase in sapling droprate.
	 * 
	 * @param stack
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	float getSaplingModifier(ItemStack stack, World world, EntityPlayer player, int x, int y, int z);
}
