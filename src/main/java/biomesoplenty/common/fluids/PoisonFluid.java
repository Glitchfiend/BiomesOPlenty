/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class PoisonFluid extends Fluid {
    
    public static final String name = "poison";
    public static final PoisonFluid instance = new PoisonFluid();

    public PoisonFluid()
    {
        super(name, new ResourceLocation("biomesoplenty:blocks/poison_still"), new ResourceLocation("biomesoplenty:blocks/poison_flowing"));
    }

}