/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.particle.BOPParticleTypes;
import net.minecraft.world.World;

public class CommonProxy
{
    public CommonProxy()
    {

    }

    public void init() {}

    public void spawnParticle(BOPParticleTypes type, World parWorld, double x, double y, double z, Object... info) {}
}
