/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;
import net.minecraft.world.gen.layer.GenLayer;

public abstract class BOPGenLayer extends GenLayer
{
    
    public BOPGenLayer(long seed) {
        super(seed);
    }

    // make nextInt public
    @Override
    public int nextInt(int a)
    {
        return super.nextInt(a);
    }

}