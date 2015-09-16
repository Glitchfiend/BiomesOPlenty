/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.api.core;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

/**
 * Taken from BuildCraft 5.0.x
 */
public interface IToolPipette {

    /**
     * @param pipette
     *            ItemStack of the pipette.
     * @return Capacity of the pipette.
     */
    int getCapacity(ItemStack pipette);

    /**
     * @param pipette
     * @return true if the pipette can pipette.
     */
    boolean canPipette(ItemStack pipette);

    /**
     * Fills the pipette with the given liquid stack.
     *
     * @param pipette
     * @param liquid
     * @param doFill
     * @return Amount of liquid used in filling the pipette.
     */
    int fill(ItemStack pipette, FluidStack liquid, boolean doFill);

    /**
     * Drains liquid from the pipette
     *
     * @param pipette
     * @param maxDrain
     * @param doDrain
     * @return Fluid stack representing the liquid and amount drained from the pipette.
     */
    FluidStack drain(ItemStack pipette, int maxDrain, boolean doDrain);
}
