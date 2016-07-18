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

public class QuicksandFluid extends Fluid {
    
    public static final String name = "sand";
    public static final QuicksandFluid instance = new QuicksandFluid();

    public QuicksandFluid()
    {
        super(name, new ResourceLocation("biomesoplenty:blocks/sand_still"), new ResourceLocation("biomesoplenty:blocks/sand_flowing"));
    }

}
